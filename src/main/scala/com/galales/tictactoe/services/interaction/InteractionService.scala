package com.galales.tictactoe.services.interaction

import com.galales.tictactoe.models.Move
import com.galales.tictactoe.services.ui.UserInterfaceService

trait InteractionService {

  def requestInput: Either[String, Int]


  def makeMove(boardSize: Int)(implicit userInterface: UserInterfaceService): Move = {
    userInterface.outputMessage("Make your move: \n")

    val row = getValue("Row: ", boardSize) - 1
    val column = getValue("Column: ", boardSize) - 1

    Move(row, column)
  }

  private def getValue(message: String, boardSize: Int)(implicit userInterface: UserInterfaceService): Int = {
    userInterface.outputMessage(message)

    requestInput match {
      case Left(s) =>
        userInterface.outputErrorMessage(s)
        getValue(message, boardSize)
      case Right(i) =>
        if(i >= 1 && i <= boardSize )
          i
        else {
          userInterface.outputErrorMessage(s"The value must be between 1 and $boardSize\n")
          getValue(message, boardSize)
        }
    }

  }
}
