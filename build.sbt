import Versions._

name := "TicTacToe"

version := "0.1"

scalaVersion := "2.12.8"


libraryDependencies ++= Seq(
  "com.github.scopt"      %% "scopt"      % scoptVersion      % "compile",
  "org.scalatest"         %% "scalatest"  % scalatestVersion  % "test"
)
