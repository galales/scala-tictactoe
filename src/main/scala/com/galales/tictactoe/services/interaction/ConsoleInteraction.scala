package com.galales.tictactoe.services.interaction

import com.galales.tictactoe.models.Move
import com.galales.tictactoe.services.ui.UserInterfaceService

import scala.util.{Failure, Success, Try}

object ConsoleInteraction extends InteractionService {

  override def makeMove(boardSize: Int)(implicit userInterface: UserInterfaceService): Move = {
    userInterface.outputMessage("Make your move: \n")

    val row = requestInput("Row: ", boardSize) - 1
    val column = requestInput("Column: ", boardSize) - 1

    Move(row, column)
  }

  private def requestInput(message: String, boardSize: Int)(implicit userInterface: UserInterfaceService): Int = {
    userInterface.outputMessage(message)
    Try(scala.io.StdIn.readInt) match {
      case Success(i) =>
        if(i >= 1 && i <= boardSize )
          i
        else {
          userInterface.outputErrorMessage(s"The value must be between 1 and $boardSize\n")
          requestInput(message, boardSize)
        }
      case Failure(_) =>
        userInterface.outputErrorMessage("The input is not a number, please try again\n")
        requestInput(message, boardSize)
    }
  }
}
