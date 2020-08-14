name := "email"

version := "0.1"

scalaVersion := "2.13.3"

lazy val AkkaVersion = "2.6.8"
lazy val AkkaHttpVersion = "10.2.0"
lazy val akkaLoggingVersion = "2.6.8"
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,

  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
  "com.typesafe.akka" %% "akka-actor" % AkkaVersion,


  "com.sendgrid" % "sendgrid-java" % "1.2.1", // send grid package


  "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion,
  "com.typesafe.akka" %% "akka-slf4j"     % akkaLoggingVersion,
  "ch.qos.logback" % "logback-classic" % "1.1.3" % Runtime
)

