package com.galales.tictactoe.core

import com.galales.tictactoe.enums.Player

import scala.collection.mutable.ArrayBuffer

case class Board(size: Int = 3) {

  private val board = ArrayBuffer.fill[Option[Player.Value]](size,size)(None)

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
    List.range(0, size).map(i => board(i)(if(main) i else size - 1 - i))

  def isComplete: Boolean = !board.flatMap(_.toList).toList.exists(_.isEmpty)
}
