name := "jerkson"

organization := "com.codahale"

scalaVersion := "2.12.4"

version := "0.6.0"

val jacksonVersion = "2.9.2"

libraryDependencies ++= Seq(
  "com.fasterxml.jackson.core" % "jackson-annotations" % jacksonVersion,
  "com.fasterxml.jackson.core" % "jackson-core" % jacksonVersion,
  "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion,
  "com.fasterxml.jackson.module" % "jackson-module-afterburner" % jacksonVersion,
  "org.specs2" %% "specs2-core" % "4.0.1" % "test"
)

libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value

resolvers += Resolver.sonatypeRepo("snapshots")

scalacOptions ++= Seq(
  "-unchecked",
  "-Ywarn-dead-code",
  "-deprecation",
  "-feature",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-language:higherKinds"
)

scalacOptions ++= Seq("-opt:l:inline", "-opt-inline-from:com.codahale.**")

scalacOptions in Test ++= Seq("-Yrangepos")
