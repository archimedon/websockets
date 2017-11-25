resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

name := "websocket-akka-http"

version := "1.0"

// lazy val root = (project in file(".")).enablePlugins(PlayScala)
lazy val root = (project in file(".")).enablePlugins(JavaAppPackaging)

// enablePlugins(JavaAppPackaging)
// enablePlugins(JavaServerAppPackaging)

//https://secret-island-53944.herokuapp.com/

scalaVersion := "2.11.11"


libraryDependencies ++= Seq(
//  jdbc,
//  cache,
//  ws,
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc41"
)


libraryDependencies ++= {
  val akkaVersion = "2.5.6"
  val akkaExperimental = "2.4.11.2"
  val akkaHttpVersion = "1.0-RC4"

  Seq(
    "com.typesafe.akka" %% "akka-stream-experimental" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-core-experimental" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-experimental" % akkaHttpVersion
  )
}

libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-compiler" % _ )
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
