package com.galales.tictactoe.models

import scala.collection.mutable.ArrayBuffer

case class Board() {

  private val board = ArrayBuffer.fill[BoardCellValue.Value](3,3)(BoardCellValue.empty)

  def execMove(move: Move, player: BoardCellValue.Value): Either[BadMove, GoodMove] = {
    if(player == BoardCellValue.empty || board(move.row)(move.column) != BoardCellValue.empty) {
      Left(BadMove())
    } else {
      board(move.row)(move.column) = player
      Right(GoodMove())
    }
  }

  def getRow(row: Int) : List[BoardCellValue.Value] = board(row).toList
  def getColumn(column: Int) : List[BoardCellValue.Value] = board.map{_(column - 1)}.toList
  def getDiagonal(main: Boolean) : List[BoardCellValue.Value] =
    for(
      i <- if(main) 0 to 2 else 2 to 0;
      row <- board(i).asInstanceOf[BoardCellValue.Value]
    ) yield row(i)
}
