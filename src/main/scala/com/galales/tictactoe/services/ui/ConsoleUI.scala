package com.galales.tictactoe.services.ui

import com.galales.tictactoe.enums.GameResult
import com.galales.tictactoe.models.{Board, Player}

object ConsoleUI extends UserInterfaceService {
  val ANSI_RED = "\u001B[31m"
  val ANSI_RESET = "\u001B[0m"

  override def updateBoard(board: Board): Unit = {
    clearScreen()
    (0 until board.size).foreach(i =>
      {
        println(
          board.getRow(i).
            foldLeft(""){
              (total, current) =>
                total + createVerticalLine(total) + playerToPlaceCard(current)
            }
        )
        printHorizontalLine(i, board.size)
      }
    )
  }

  override def showResult(result: GameResult.Value): Unit = {
    clearScreen()
    val resultString =
      result match {
        case GameResult.draw => "Draw"
        case GameResult.aiWin => "AI Wins"
        case GameResult.userWin => "User Wins"
      }
    println(s"Result: $resultString")
  }

  override def outputMessage(message: String): Unit = print(message)

  override def outputErrorMessage(message: String): Unit = {
    outputMessage(ANSI_RED + message + ANSI_RESET)
    scala.io.StdIn.readLine()
  }

  private def playerToPlaceCard(player: Option[Player.Value]) : String = {
    player match {
      case Some(v) => v match {
        case Player.user => " X "
        case Player.ai => " O "
      }
      case None => "   "
    }
  }

  private def printHorizontalLine(currentRow: Int, boardSize: Int) : Unit = {
    if(currentRow < boardSize - 1) {
      println("-" * (4 * boardSize - 0.5).toInt)
    }
  }

  private def createVerticalLine(currentOutput: String) : String = {
    if(currentOutput.nonEmpty) {
      "|"
    } else {
      ""
    }
  }

  private def clearScreen() {
    System.out.print("\033[H\033[2J")
    System.out.flush()
  }

}
