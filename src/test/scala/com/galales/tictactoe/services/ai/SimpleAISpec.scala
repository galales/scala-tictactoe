package com.galales.tictactoe.services.ai

import com.galales.tictactoe.UnitSpec
import com.galales.tictactoe.models.{Board, Move, Player}

class SimpleAISpec extends UnitSpec {

  "A Simple Man" should "come sit beside me" in {
    val board = Board()
    val move = SimpleAI.makeMove(board)

    move.row should be >= 0
    move.row should be < 3

    move.column should be >= 0
    move.column should be < 3
  }

  it should "follow his heart and nothing else" in {
    val board = Board()

    // Row almost complete
    board.execMove(Move(1, 0), Player.ai)
    board.execMove(Move(1, 1), Player.ai)

    val move = SimpleAI.makeMove(board)

    move.row should be (1)
    move.column should be (2)
  }

  it should "be satisfied" in {
    val board = Board()

    // Column almost complete
    board.execMove(Move(0, 1), Player.ai)
    board.execMove(Move(1, 1), Player.ai)

    val move = SimpleAI.makeMove(board)

    move.row should be (2)
    move.column should be (1)
  }

  it should "be something you love and understand" in {
    val boardMain = Board()
    val boardSecondary = Board()

    // Main Diagonal almost complete
    boardMain.execMove(Move(0, 0), Player.ai)
    boardMain.execMove(Move(1, 1), Player.ai)

    // Secondary Diagonal almost complete
    boardSecondary.execMove(Move(0, 2), Player.ai)
    boardSecondary.execMove(Move(1, 1), Player.ai)

    val moveMain = SimpleAI.makeMove(boardMain)
    val moveSecondary = SimpleAI.makeMove(boardSecondary)

    moveMain.row should be (2)
    moveMain.column should be (2)

    moveSecondary.row should be (2)
    moveSecondary.column should be (0)
  }

  it should "not worry, he'll find himself" in {
    val board = Board()

    // No possible winning moves left
    board.execMove(Move(0, 0), Player.user)
    board.execMove(Move(1, 1), Player.user)
    board.execMove(Move(2, 2), Player.user)

    val move = SimpleAI.makeMove(board)

    move.row should be >= 0
    move.row should be < 3

    move.column should be >= 0
    move.column should be < 3

    move should not be Move(0, 0)
    move should not be Move(1, 1)
    move should not be Move(2, 2)
  }

}
