name := "jerkson"

organization := "com.codahale"

scalaVersion := "2.10.0"

version := "0.5.1-SNAPSHOT"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % "2.10.0",
  "org.codehaus.jackson" % "jackson-core-asl" % "[1.9.0,2.0.0)",
  "org.codehaus.jackson" % "jackson-mapper-asl" % "[1.9.0,2.0.0)",
  "org.specs2" %% "specs2" % "1.13" % "test"
)
