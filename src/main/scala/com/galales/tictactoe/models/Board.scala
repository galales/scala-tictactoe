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

  def apply(row: Int)(column: Int): Option[Player.Value] = board(row)(column)
  def getRow(row: Int) : List[Option[Player.Value]] = board(row).toList
  def getColumn(column: Int) : List[Option[Player.Value]] = board.map{_(column)}.toList
  def getDiagonal(main: Boolean) : List[Option[Player.Value]] =
    List.range(0, 3).map(i => board(i)(if(main) i else 2 - i))

  def isComplete: Boolean = !board.flatMap(_.toList).toList.exists(_.isEmpty)
}
