package com.galales.tictactoe.models

import scala.collection.mutable.ArrayBuffer

case class Board() {

  private val board = ArrayBuffer.fill[Option[Player.Value]](3,3)(None)

  def execMove(move: Move, player: Player.Value): Either[BadMove, GoodMove] = {
    if(board(move.row)(move.column).isDefined) {
      Left(BadMove())
    } else {
      board(move.row)(move.column) = Some(player)
      Right(GoodMove())
    }
  }

  def getRow(row: Int) : List[Option[Player.Value]] = board(row).toList
  def getColumn(column: Int) : List[Option[Player.Value]] = board.map{_(column - 1)}.toList
  def getDiagonal(main: Boolean) : List[Option[Player.Value]] =
    for(
      row <- List.range(0, 2);
      col <- if(main) List.range(0, 2) else List.range(2, 0);
    ) yield board(row)(col)
}
