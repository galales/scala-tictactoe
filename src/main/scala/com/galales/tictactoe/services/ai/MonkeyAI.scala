package com.galales.tictactoe.services.ai

import com.galales.tictactoe.models.{Board, Move}

object MonkeyAI extends AI {

  private val random = scala.util.Random

  // Set mark in a random spot
  override def makeMove(board: Board) : Move = {
    val emptyBoxes = for(
      row <- 0 to 2;
      column <- 0 to 2
      if board(row)(column).isEmpty
    ) yield (row, column)

    val nextMove = emptyBoxes(random.nextInt(emptyBoxes.length))

    Move(nextMove._1, nextMove._2)
  }

}
