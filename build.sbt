organization := "com.github.hexx"

name := "gaeds"

version := "0.0.1"

scalaVersion := "2.9.1-1"

scalacOptions ++= Seq("-deprecation", "-unchecked")

libraryDependencies ++= {
  val appengineVersion = "1.6.3.1"
  Seq(
    "com.google.appengine" % "appengine-api-1.0-sdk" % appengineVersion,
    "com.google.appengine" % "appengine-api-labs"    % appengineVersion,
    "com.google.appengine" % "appengine-api-stubs"   % appengineVersion,
    "com.google.appengine" % "appengine-testing"     % appengineVersion,
    "org.scalatest" %% "scalatest" % "1.7.1" % "test"
  )
}
