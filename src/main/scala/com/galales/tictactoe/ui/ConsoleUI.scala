package com.galales.tictactoe.ui
import com.galales.tictactoe.models.Board

object ConsoleUI extends UserInterfaceDrawer {
  override def printBoard(board: Board): Unit = {
    (0 to 2).foreach(i => println(board.getRow(i).fold(""){(total, current) => total + s"$current "}))
  }
}
