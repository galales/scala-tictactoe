package com.galales.tictactoe

import com.galales.tictactoe.ai.{AI, MonkeyAI}
import com.galales.tictactoe.interaction.{ConsoleInteraction, UserInteractionHandler}
import com.galales.tictactoe.models.Board
import com.galales.tictactoe.ui.{ConsoleUI, UserInterfaceDrawer}

object Main extends App {

  val ai: AI = MonkeyAI
  val userInteraction: UserInteractionHandler = ConsoleInteraction
  val userInterface: UserInterfaceDrawer = ConsoleUI
  val board: Board = Board()

  Game.play(board, ai, userInterface, userInteraction, isUserTurn = true)
}
