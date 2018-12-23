package com.galales.tictactoe

import com.galales.tictactoe.ai.AI
import com.galales.tictactoe.models.Board
import com.galales.tictactoe.enums.GameResult
import com.galales.tictactoe.interaction.UserInteractionHandler
import com.galales.tictactoe.ui.UserInterfaceDrawer

object Game {

  def isThisTheEnd(board: Board) : Boolean = ???

  def getGameResult(board: Board) : GameResult.Value = ???

  def play(board: Board, ai: AI, userInterface: UserInterfaceDrawer, userInteract: UserInteractionHandler, playerTurn: Boolean) : GameResult.Value = {

    if(playerTurn) {
      // Player Turn
      board.execMove(userInteract.makeMove)
    } else {
      // AI Turn
      board.execMove(ai.makeMove(board))
    }

    if(isThisTheEnd(board)) {
      getGameResult(board)
    } else {
      play(board, ai, userInterface, userInteract, !playerTurn)
    }

  }
}
