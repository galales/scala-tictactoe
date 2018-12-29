package com.galales.tictactoe.services.ui

import com.galales.tictactoe.enums.GameResult
import com.galales.tictactoe.models.Board

trait UserInterfaceService {

  def updateBoard(board: Board) : Unit

  def showResult(result: GameResult.Value) : Unit

  def outputMessage(message: String) : Unit
  def outputErrorMessage(message: String) : Unit
}
