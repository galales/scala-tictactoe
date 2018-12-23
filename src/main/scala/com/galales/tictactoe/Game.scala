package com.galales.tictactoe

import com.galales.tictactoe.ai.AI
import com.galales.tictactoe.models.{Board, Player}
import com.galales.tictactoe.enums.GameResult
import com.galales.tictactoe.interaction.UserInteractionHandler
import com.galales.tictactoe.ui.UserInterfaceDrawer

object Game {

  def isThisTheEnd(board: Board) : Boolean = ???

  def getGameResult(board: Board) : GameResult.Value = ???

  def play(board: Board, ai: AI, userInterface: UserInterfaceDrawer, userInteract: UserInteractionHandler, isUserTurn: Boolean) : GameResult.Value = {

    if(isUserTurn) {
      // User Turn
      board.execMove(userInteract.makeMove, Player.user)
    } else {
      // AI Turn
      board.execMove(ai.makeMove(board), Player.ai)
    }

    if(isThisTheEnd(board)) {
      getGameResult(board)
    } else {
      play(board, ai, userInterface, userInteract, !isUserTurn)
    }

  }
}
