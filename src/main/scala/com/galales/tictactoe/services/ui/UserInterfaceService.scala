package com.galales.tictactoe.services.ui

import com.galales.tictactoe.enums.GameResult
import com.galales.tictactoe.models.Board

trait UserInterfaceService {

  def printBoard(board: Board)

  def printResult(result: GameResult.Value): Unit = println(s"Result: $result")
}
