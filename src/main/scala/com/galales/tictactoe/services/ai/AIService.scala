package com.galales.tictactoe.services.ai

import com.galales.tictactoe.core.{Board, Move}

trait AIService {

  def makeMove(board: Board) : Move
}
