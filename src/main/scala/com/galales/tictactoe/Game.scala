package com.galales.tictactoe

import com.galales.tictactoe.ai.AI
import com.galales.tictactoe.models.{Board, Player}
import com.galales.tictactoe.enums.GameResult
import com.galales.tictactoe.interaction.UserInteractionHandler
import com.galales.tictactoe.ui.UserInterfaceDrawer

object Game {

  def getGameResult(board: Board) : GameResult.Value = {
//    (0 to 2).map(board.getRow(_).flatten).filter(r => r.length == 3 && r.distinct.length == 1).head.head,
//    (0 to 2).map(board.getColumn(_).flatten).filter(r => r.length == 3 && r.distinct.length == 1).head.head,
//    List(true, false).map(board.getDiagonal(_).flatten).filter(r => r.length == 3 && r.distinct.length == 1).head.head,
    // TODO Check .head.head for errors
    val winner = List(
      (0 to 2).map(board.getRow).filter(r => r.length == 3 && r.distinct.length == 1).head.head,
      (0 to 2).map(board.getColumn).filter(r => r.length == 3 && r.distinct.length == 1).head.head,
      List(true, false).map(board.getDiagonal).filter(r => r.length == 3 && r.distinct.length == 1).head.head,
    ).flatten.head

    winner match {
      case Player.user => GameResult.userWin
      case Player.ai => GameResult.aiWin
      case _ => if(board.isComplete) GameResult.draw else GameResult.stillGoing
    }
  }

  def play(board: Board, ai: AI, userInterface: UserInterfaceDrawer, userInteract: UserInteractionHandler, isUserTurn: Boolean) : GameResult.Value = {

    if(isUserTurn) {
      // User Turn
      board.execMove(userInteract.makeMove, Player.user)
    } else {
      // AI Turn
      board.execMove(ai.makeMove(board), Player.ai)
    }

    val result = getGameResult(board)

    if(result != GameResult.stillGoing) {
      result
    } else {
      play(board, ai, userInterface, userInteract, !isUserTurn)
    }

  }
}
