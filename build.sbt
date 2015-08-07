name := "jerkson"

organization := "com.codahale"

scalaVersion := "2.11.7"

version := "0.5.2-SNAPSHOT"

val jacksonVersion = "2.5.4"

libraryDependencies ++= Seq(
  "com.fasterxml.jackson.core" % "jackson-annotations" % jacksonVersion,
  "com.fasterxml.jackson.core" % "jackson-core" % jacksonVersion,
  "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion,
  "org.specs2" %% "specs2-core" % "3.6.4" % "test"
)

libraryDependencies <+= scalaVersion { v => "org.scala-lang" % "scala-reflect" % v }

scalacOptions ++= Seq(
  "-unchecked",
  "-Ywarn-dead-code",
  "-deprecation",
  "-feature",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-language:higherKinds"
)

scalacOptions in Test ++= Seq("-Yrangepos")
