package com.galales.tictactoe.services.ai

import com.galales.tictactoe.models.{Board, Move}

object SimpleAI extends AIService {

  // Just tries to put marks in the best place to win
  override def makeMove(board: Board) : Move = ???

}
