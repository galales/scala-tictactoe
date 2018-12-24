package com.galales.tictactoe.services.interaction

import com.galales.tictactoe.models.Move
import com.galales.tictactoe.services.ui.UserInterfaceService

object ConsoleInteraction extends InteractionService {

  override def makeMove(implicit userInterface: UserInterfaceService): Move = {
    userInterface.outputMessage("Make your move: \n")

    userInterface.outputMessage("Row: ")
    val row = scala.io.StdIn.readInt
    userInterface.outputMessage("Column: ")
    val column = scala.io.StdIn.readInt

    Move(row, column)
  }
}
