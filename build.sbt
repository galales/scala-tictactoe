import Versions._

name := "TicTacToe"

version := "1.0"

scalaVersion := "2.12.8"


libraryDependencies ++= Seq(
  "com.github.scopt"      %% "scopt"      % scoptVersion      % Compile,
  "org.scalatest"         %% "scalatest"  % scalaTestVersion  % Test,
  "org.scalamock"         %% "scalamock"  % scalaMockVersion  % Test
)
