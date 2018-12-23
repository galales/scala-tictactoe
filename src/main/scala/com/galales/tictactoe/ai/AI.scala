package com.galales.tictactoe.ai

import com.galales.tictactoe.models.{Board, Move}

trait AI {

  def makeMove(board: Board) : Move
}
