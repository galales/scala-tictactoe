package com.galales.tictactoe.services.ai

import com.galales.tictactoe.UnitSpec
import com.galales.tictactoe.models.{Board, Move, Player}

class ChallengeAISpec extends UnitSpec {

  "The Challenger" should "stay inside the playing field" in {
    val board = Board()
    val move = ChallengeAI.makeMove(board)

    move.row should be >= 0
    move.row should be < 3

    move.column should be >= 0
    move.column should be < 3
  }

  it should "try to win" in {
    val board = Board()

    // Row almost complete
    board.execMove(Move(1, 0), Player.ai)
    board.execMove(Move(1, 1), Player.ai)

    ChallengeAI.makeMove(board) should be (Move(1,2))
  }

  it should "with all" in {
    val board = Board()

    // Column almost complete
    board.execMove(Move(0, 1), Player.ai)
    board.execMove(Move(1, 1), Player.ai)

    ChallengeAI.makeMove(board) should be (Move(2,1))
  }

  it should "it's might" in {
    val boardMain = Board()
    val boardSecondary = Board()

    // Main Diagonal almost complete
    boardMain.execMove(Move(0, 0), Player.ai)
    boardMain.execMove(Move(1, 1), Player.ai)

    // Secondary Diagonal almost complete
    boardSecondary.execMove(Move(0, 2), Player.ai)
    boardSecondary.execMove(Move(1, 1), Player.ai)

    ChallengeAI.makeMove(boardMain) should be (Move(2,2))
    ChallengeAI.makeMove(boardSecondary) should be (Move(2,0))
  }

  it should "block the opponent" in {
    val board = Board()

    // User is winning
    board.execMove(Move(0, 1), Player.user)
    board.execMove(Move(1, 1), Player.user)

    // AI has nothing in his hands
    board.execMove(Move(2, 2), Player.ai)

    ChallengeAI.makeMove(board) should be (Move(2,1))
  }

  it should "unless the victory is in its pocket" in {
    val board = Board()

    // User is winning
    board.execMove(Move(0, 1), Player.user)
    board.execMove(Move(1, 1), Player.user)

    // And AI too
    board.execMove(Move(0, 2), Player.ai)
    board.execMove(Move(1, 2), Player.ai)

    ChallengeAI.makeMove(board) should be (Move(2,2))
  }

}
