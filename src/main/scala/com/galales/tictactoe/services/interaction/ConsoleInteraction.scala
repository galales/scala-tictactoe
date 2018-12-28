package com.galales.tictactoe.services.interaction

import com.galales.tictactoe.models.Move
import com.galales.tictactoe.services.ui.UserInterfaceService

import scala.util.{Failure, Success, Try}

object ConsoleInteraction extends InteractionService {

  override def makeMove(implicit userInterface: UserInterfaceService): Move = {
    userInterface.outputMessage("Make your move: \n")

    val row = requestInput("Row: ") - 1
    val column = requestInput("Column: ") - 1

    Move(row, column)
  }

  private def requestInput(message: String)(implicit userInterface: UserInterfaceService): Int = {
    userInterface.outputMessage(message)
    Try(scala.io.StdIn.readInt) match {
      case Success(i) =>
        if(i >= 1 && i <= 3 )
          i
        else {
          userInterface.outputErrorMessage("The value must be 1, 2 or 3\n")
          requestInput(message)
        }
      case Failure(_) =>
        userInterface.outputErrorMessage("The input is not a number, please try again\n")
        requestInput(message)
    }
  }
}
