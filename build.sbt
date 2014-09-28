name := """qask"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.2"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws
)

resolvers += "newzly External snapshots" at "http://newzly-artifactory.elasticbeanstalk.com/ext-release-local"

resolvers += "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases/"

libraryDependencies += "com.datastax.cassandra" % "cassandra-driver-core" % "2.1.1"


libraryDependencies += "com.websudos"  %% "phantom-dsl"  % "1.2.7"

libraryDependencies += "net.liftweb" % "lift-json_2.10" % "2.6-M4"

libraryDependencies += "joda-time" % "joda-time" % "2.3"

libraryDependencies += "org.scala-lang.modules" %% "scala-async" % "0.9.1"

libraryDependencies += "com.google.code.gson" % "gson" % "2.2.4"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.1.7"

libraryDependencies += "com.livestream" %% "scredis" % "2.0.2"

libraryDependencies ++= Seq(
  "net.debasishg" %% "redisclient" % "2.13"
)

libraryDependencies += "com.syncthemall" % "boilerpipe" % "1.2.2"

libraryDependencies += "net.sourceforge.nekohtml" % "nekohtml" % "1.9.21"

libraryDependencies += "xerces" % "xercesImpl" % "2.10.0"

libraryDependencies += "com.syncthemall" % "goose" % "2.1.25"

libraryDependencies += "com.googlecode.juniversalchardet" % "juniversalchardet" % "1.0.3"

libraryDependencies += "commons-io" % "commons-io" % "2.4"

libraryDependencies += "commons-lang" % "commons-lang" % "2.6"

libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.3.3"

libraryDependencies += "org.jsoup" % "jsoup" % "1.7.3"

libraryDependencies += "com.github.slugify" % "slugify" % "2.1.2"




