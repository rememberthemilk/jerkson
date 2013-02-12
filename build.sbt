name := "jerkson"

organization := "com.codahale"

scalaVersion := "2.10.0"

version := "0.5.1-SNAPSHOT"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % "2.10.0",
  "com.fasterxml.jackson.core" % "jackson-annotations" % "2.0.0",
  "com.fasterxml.jackson.core" % "jackson-core" % "2.0.0",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.0.0",
  "org.specs2" %% "specs2" % "1.13" % "test"
)
