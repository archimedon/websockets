package io.scalac.akka.http.websockets

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives
import akka.stream.ActorMaterializer

//import scala.io.StdIn
import akka.http.scaladsl.model.ws.{Message, TextMessage}
import akka.stream.scaladsl.Flow

//import akka.http.scaladsl.server.Directives._
//import akka.http.scaladsl.server.Route


import akka.actor.{Props, ActorSystem}
//import akka.io.IO
//import org.apache.commons.daemon._

object Server extends App {

  private[this] var started: Boolean = true

  val applicationName = "test-sockets"
  implicit val actorSystem = ActorSystem(applicationName)
  implicit val flowMaterializer = ActorMaterializer()

  val config = actorSystem.settings.config
  val interface = config.getString("app.interface")

  val port = sys.env("PORT").toInt
  
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

  val mainThread = Thread.currentThread();

  Runtime.getRuntime.addShutdownHook(new Thread() {
    override def run = {

      if (started) {
        println(s"Shutting down: $applicationName Service")
        started = false
        mainThread.join()
        cleanUp();
        Thread.currentThread.interrupt()
      }
    }})


  def cleanUp() {

    import actorSystem.dispatcher

    println(s"Releasing bindings")
    binding.flatMap(_.unbind()).onComplete(_ => actorSystem.shutdown())
    Http().shutdownAllConnectionPools() ; //andThen { case _ => actorSystem.shutdown() }
//    actorSystem.shutdown()
    complete("Shutting down app")

  }


}
