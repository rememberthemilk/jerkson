name := "jerkson"

organization := "com.codahale"

scalaVersion := "2.9.1"

version := "0.5.1-SNAPSHOT"

libraryDependencies ++= Seq(
  "org.codehaus.jackson" % "jackson-core-asl" % "[1.9.0,2.0.0)",
  "org.codehaus.jackson" % "jackson-mapper-asl" % "[1.9.0,2.0.0)",
  "com.simple" %% "simplespec" % "0.6.0"
)
