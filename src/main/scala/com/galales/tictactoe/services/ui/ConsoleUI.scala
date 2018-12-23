package com.galales.tictactoe.services.ui

import com.galales.tictactoe.models.Board

object ConsoleUI extends UserInterfaceService {
  override def printBoard(board: Board): Unit = {
    (0 to 2).foreach(i => println(board.getRow(i).fold(""){(total, current) => total + s"$current "}))
  override def printResult(result: GameResult.Value): Unit = println(s"Result: $result")
  }
}
