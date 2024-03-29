name := "monoid"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies += "org.typelevel" %% "simulacrum" % "1.0.0"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.8"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"

scalacOptions += "-Ymacro-annotations"
