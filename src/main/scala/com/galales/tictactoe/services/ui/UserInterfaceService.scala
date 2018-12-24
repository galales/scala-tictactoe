package com.galales.tictactoe.services.ui

import com.galales.tictactoe.enums.GameResult
import com.galales.tictactoe.models.Board

trait UserInterfaceService {

  def updateBoard(board: Board)

  def showResult(result: GameResult.Value)
}
