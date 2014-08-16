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

resolvers += "newzly External snapshots" at "http://newzly-artifactory.elasticbeanstalk.com/ext-release-local"

libraryDependencies += "com.datastax.cassandra" % "cassandra-driver-core" % "2.0.3"

libraryDependencies += "com.websudos"  %% "phantom-dsl"  % "1.0.7"

libraryDependencies += "net.liftweb" % "lift-json_2.10" % "2.6-M4"

libraryDependencies += "joda-time" % "joda-time" % "2.3"

libraryDependencies += "org.scala-lang.modules" %% "scala-async" % "0.9.1"

libraryDependencies += "com.google.code.gson" % "gson" % "2.2.4"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.1.7"


