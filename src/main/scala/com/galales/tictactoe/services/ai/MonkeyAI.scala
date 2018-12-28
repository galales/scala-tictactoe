package com.galales.tictactoe.services.ai

import com.galales.tictactoe.models.{Board, Move}

object MonkeyAI extends AIService {

  private val random = scala.util.Random

  // Set mark in a random spot
  override def makeMove(board: Board) : Move = {
    val emptyBoxes = for(
      row <- 0 until board.size;
      column <- 0 until board.size
      if board(row)(column).isEmpty
    ) yield (row, column)

    val nextMove = emptyBoxes(random.nextInt(emptyBoxes.length))

    Move(nextMove._1, nextMove._2)
  }

}
