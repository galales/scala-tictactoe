package com.galales.tictactoe

import com.galales.tictactoe.models.Board
import com.galales.tictactoe.services.ai.{AI, MonkeyAI}
import com.galales.tictactoe.services.interaction.{ConsoleInteraction, InteractionService}
import com.galales.tictactoe.services.ui.{ConsoleUI, UserInterfaceService}

object Main extends App {

  val ai: AI = MonkeyAI
  val interaction: InteractionService = ConsoleInteraction
  val userInterface: UserInterfaceService = ConsoleUI
  val board: Board = Board()

  userInterface.showResult(Game.play(board, ai, userInterface, interaction, isUserTurn = true))
}
