name := "jerkson"

organization := "com.codahale"

scalaVersion := "2.11.0"

version := "0.5.2-SNAPSHOT"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % "2.10.0",
  "com.fasterxml.jackson.core" % "jackson-annotations" % "2.3.2",
  "com.fasterxml.jackson.core" % "jackson-core" % "2.3.2",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.3.2",
  "org.specs2" %% "specs2" % "2.3.11" % "test"
)

scalacOptions ++= Seq("-unchecked", "-Ywarn-dead-code", "-deprecation", "-language:implicitConversions")
