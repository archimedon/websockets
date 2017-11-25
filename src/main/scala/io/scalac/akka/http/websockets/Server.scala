package poksockets

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives
import akka.stream.ActorMaterializer

import scala.io.StdIn
import akka.http.scaladsl.model.ws.{Message, TextMessage}
import akka.stream.scaladsl.Flow

//import akka.http.scaladsl.server.Directives._
//import akka.http.scaladsl.server.Route


import akka.actor.{Props, ActorSystem}
import akka.io.IO
//import org.apache.commons.daemon._

object Server extends App {

  private var shutdown: Boolean = false

  val applicationName = "test-sockets"
  implicit val actorSystem = ActorSystem(applicationName)
  implicit val flowMaterializer = ActorMaterializer()

  val config = actorSystem.settings.config
  val interface = config.getString("app.interface")

//  val port = sys.env("PORT").toInt
  val port = System.getenv("PORT").toInt
//  val port = 8080;
//    if (sys.env.contains("PORT")) sys.env("PORT").toInt
//    else config.getInt("app.port")

  import Directives._

  val route = get {
    pathEndOrSingleSlash {
      complete("Welcome to websocket server")
    }
  } ~
  path("ws-echo") {
    get {
      handleWebsocketMessages(echoService)
    }
  }

  val echoService: Flow[Message, Message, _] = Flow[Message].map {
    case TextMessage.Strict(txt) => TextMessage("ECHO: " + txt)
    case _ => TextMessage("Message type unsupported")
  }

  val binding = Http().bindAndHandle(route, interface, port)

  println(s"Server is now online at http://$interface:$port")

  // Exit on Ctrl-D
  def isExit(s: String): Boolean = (s == null) || s.headOption.map(_.toInt) == Some(4)

  exitListen()


  def exitListen() {

    StdIn.readLine match {
      case x if isExit(x) => println("No way out 1"); exitListen
      case _              => println("No way out"); exitListen
    }

  }
//  val mainThread = Thread.currentThread();

//  Runtime.getRuntime.addShutdownHook(new Thread() {
//    override def run = {
//  }})

  def cleanUp() {
    if (shutdown) {

      println("Shutting down") ;

      import actorSystem.dispatcher

      binding.flatMap(_.unbind()).onComplete(_ => actorSystem.shutdown())
      Http().shutdownAllConnectionPools() ; //andThen { case _ => actorSystem.shutdown() }

//      mainThread.join()
//      Thread.currentThread.interrupt()
      complete("Shutting down app")
    }


  }


}
