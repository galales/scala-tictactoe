package com.galales.tictactoe.interaction

import com.galales.tictactoe.models.Move

trait UserInteractionHandler {
  def makeMove: Move
}
