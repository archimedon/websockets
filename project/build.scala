import sbt._
import Keys._
import com.typesafe.sbt.packager.archetypes.JavaAppPackaging

object HerokuExampleBuild extends Build {
  val Organization = "org.ronnied"
  val Name = "websocket-akka-http"
  val Version = "0.1.0-SNAPSHOT"
  val ScalaVersion = "2.11.11"




  version := "1.0"



  //
  //javaOptions in Universal ++= Seq(
  //    // -J params will be added as jvm parameters
  //    "-J-Xmx64m",
  //    "-J-Xms64m",
  //
  //    // others will be added as app parameters
  //    "-Dproperty=true",
  //    "-port=8080",
  //
  //    // you can access any build setting/task here
  //   s"-version=${version.value}"
  //)

  val akkaVersion = "2.5.6"
  val akkaExperimental = "2.4.11.2"
  val akkaHttpVersion = "1.0-RC4"

  lazy val project = Project (
    "websocket-akka-http",
    file("."),
    settings = Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      resolvers += "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
      resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/",
        libraryDependencies ++= Seq(
          "ch.qos.logback"              %  "logback-classic"     % "1.1.3"          % "runtime",
          "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
          "com.typesafe.akka" %% "akka-stream-experimental" % akkaHttpVersion,
          "com.typesafe.akka" %% "akka-http-core-experimental" % akkaHttpVersion,
          "com.typesafe.akka" %% "akka-http-experimental" % akkaHttpVersion
        )
    )
  ).enablePlugins(JavaAppPackaging)
}
