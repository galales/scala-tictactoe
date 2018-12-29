package com.galales.tictactoe.services.interaction

import scala.util.{Failure, Success, Try}

object ConsoleInteraction extends InteractionService {

  override def requestInput: Either[String, Int] = {
    Try(scala.io.StdIn.readInt) match {
      case Success(i) =>
        Right(i)
      case Failure(_) =>
        Left("The input is not a number, please try again\n")
    }
  }
}
