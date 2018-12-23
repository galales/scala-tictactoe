package com.galales.tictactoe.ui

import com.galales.tictactoe.enums.GameResult
import com.galales.tictactoe.models.Board

trait UserInterfaceDrawer {

  def printBoard(board: Board)

  def printResult(result: GameResult.Value): Unit = println(s"Result: $result")
}
