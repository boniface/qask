name := """qask"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws
)

libraryDependencies += "com.datastax.cassandra" % "cassandra-driver-core" % "2.0.3"

libraryDependencies += "com.newzly" % "phantom-dsl_2.10" % "0.8.0"

libraryDependencies += "com.newzly" % "phantom-cassandra-unit_2.10" % "0.8.0"

libraryDependencies += "com.newzly" % "phantom-test_2.10" % "0.8.0"

libraryDependencies += "joda-time" % "joda-time" % "2.3"

libraryDependencies += "org.scala-lang.modules" %% "scala-async" % "0.9.1"

libraryDependencies += "com.google.code.gson" % "gson" % "2.2.4"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.1.7"


