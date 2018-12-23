package com.galales.tictactoe.services.interaction

import com.galales.tictactoe.models.Move

trait InteractionService {
  def makeMove: Move
}
