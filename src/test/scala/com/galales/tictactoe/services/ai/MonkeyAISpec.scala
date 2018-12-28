package com.galales.tictactoe.services.ai

import com.galales.tictactoe.UnitSpec
import com.galales.tictactoe.models.{Board, Move, Player}

class MonkeyAISpec extends UnitSpec {

  "A Monkey" should "put a banana on a random tile on the floor" in {
    val board = Board()
    val move = MonkeyAI.makeMove(board)

    move.row should be >= 0
    move.row should be < 3

    move.column should be >= 0
    move.column should be < 3
  }

  it should "not step on a banana already on the floor" in {
    val board = Board()

    for(
      row <- 0 until 3;
      column <- 0 until 3 if row != 2 || column != 2
    ) yield board.execMove(Move(row, column), Player.user)

    val move = MonkeyAI.makeMove(board)

    move.row should be (2)
    move.column should be (2)
  }

}
