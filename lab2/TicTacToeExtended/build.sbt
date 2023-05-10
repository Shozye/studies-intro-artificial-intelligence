ThisBuild / version := "0.1.0-Simulation"
ThisBuild / scalaVersion := "3.2.2"

assembly / mainClass := Some("studies.wsi.tictactoe.SimulationMain")

lazy val root = (project in file("."))
  .settings(
    name := "TTTExtended",
    idePackagePrefix := Some("studies.wsi.tictactoe")
  )
libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "2.2.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15" % Test
