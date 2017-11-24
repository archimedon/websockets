logLevel := Level.Warn

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"


addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "5.1.0")

// Required for Heroku deploy
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.3.1")
addSbtPlugin("com.heroku" % "sbt-heroku" % "1.0.1")
