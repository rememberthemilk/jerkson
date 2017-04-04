name := "jerkson"

organization := "com.codahale"

scalaVersion := "2.12.1"

version := "0.5.6"

val jacksonVersion = "2.8.8"

libraryDependencies ++= Seq(
  "com.fasterxml.jackson.core" % "jackson-annotations" % jacksonVersion,
  "com.fasterxml.jackson.core" % "jackson-core" % jacksonVersion,
  "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion,
  "com.fasterxml.jackson.module" % "jackson-module-afterburner" % jacksonVersion,
  "org.specs2" %% "specs2-core" % "3.8.9" % "test"
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

scalacOptions ++= Seq("-opt:l:method", "-opt:l:project", "-opt:l:classpath")

scalacOptions in Test ++= Seq("-Yrangepos")
