package com.galales.tictactoe.services.ui

import com.galales.tictactoe.enums.GameResult
import com.galales.tictactoe.models.{Board, Player}

object ConsoleUI extends UserInterfaceService {
  override def printBoard(board: Board): Unit = {
    (0 to 2).foreach(i =>
      {
        println(
          board.getRow(i).
            fold(""){
              (total, current) => total + createVerticalLine(total.asInstanceOf[String]) + playerToPlaceCard(current.asInstanceOf[Option[Player.Value]])
            }
        )
        printHorizontalLine(i)
      }

    )
  }

  override def printResult(result: GameResult.Value): Unit = println(s"Result: $result")

  
  private def playerToPlaceCard(player: Option[Player.Value]) : String = {
    player match {
      case Some(v) => v match {
        case Player.user => " X "
        case Player.ai => " O "
      }
      case None => "   "
    }
  }

  private def printHorizontalLine(currentRow: Int) : Unit = {
    if(currentRow < 2) {
      println("-----------")
    }
  }

  private def createVerticalLine(currentOutput: String) : String = {
    if(currentOutput.nonEmpty) {
      "|"
    } else {
      ""
    }
  }
}
