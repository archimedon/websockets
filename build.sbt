enablePlugins(JavaAppPackaging)

name := "websocket-akka-http"

version := "1.0"

scalaVersion := "2.11.11"

libraryDependencies ++= {
//  val akkaHttpVersion = "10.0.10"
  val akkaVersion = "2.5.6"
  val akkaExperimental = "2.4.11.2"
  val akkaHttpVersion = "1.0-RC4"

  Seq(
    "com.typesafe.akka" %% "akka-stream-experimental" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-core-experimental" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-experimental" % akkaHttpVersion
  )
}
