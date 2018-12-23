package com.galales.tictactoe.services.ai

import com.galales.tictactoe.models.{Board, Move}

trait AI {

  def makeMove(board: Board) : Move
}
