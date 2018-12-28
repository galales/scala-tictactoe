package com.galales.tictactoe.services.ai

import com.galales.tictactoe.models.{Board, Move, Player}

object ChallengeAI extends AIService {

  // First tries to block user, then tries to win
  // If user is about to win in the next move, it tries to block it

  override def makeMove(board: Board) : Move = {
    val playerWinningMoves = SimpleAI.getPotentiallyWinningCoordinates(board, 2, Player.user).map(c => Move(c._1, c._2))
    val aiWinningMoves = SimpleAI.getPotentiallyWinningCoordinates(board, 2, Player.ai).map(c => Move(c._1, c._2))

    // Give precedence to AI move if it is about to win
    if(playerWinningMoves.isEmpty || aiWinningMoves.nonEmpty) {
      SimpleAI.makeMove(board)
    } else {
      playerWinningMoves.head
    }

  }

}
