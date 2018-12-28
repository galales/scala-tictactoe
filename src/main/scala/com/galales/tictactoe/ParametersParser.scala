package com.galales.tictactoe

import com.galales.tictactoe.enums.{AIType, InteractionType, UserInterfaceType}

final case class ParametersParser(userInterface: UserInterfaceType.Value = UserInterfaceType.console, userInteraction: InteractionType.Value = InteractionType.console, ai: AIType.Value = AIType.monkey, boardSize: Int = 3)

object ParametersParser {

  implicit val userInterfaceTypeRead: scopt.Read[UserInterfaceType.Value] =
    scopt.Read.reads(UserInterfaceType.withName)

  implicit val userInteractionRead: scopt.Read[InteractionType.Value] =
    scopt.Read.reads(InteractionType.withName)

  implicit val aiTypeRead: scopt.Read[AIType.Value] =
    scopt.Read.reads(AIType.withName)


  def apply(args: Array[String]): Option[ParametersParser] = {
    val parser = new scopt.OptionParser[ParametersParser]("Tic Tac Toe") {

      opt[Int]('s', "boardSize").
        optional().
        action((s, p) => p.copy(boardSize = s))
        .validate(s =>
          if (s > 2) success
          else failure("Value <boardSize> must be > 2"))
        .text(s"Integer greater than 2")

      opt[UserInterfaceType.Value]('i', "userInterface").
        optional().
        action((i, p) => p.copy(userInterface = i))
        .text(s"Allowed values: ${UserInterfaceType.values.mkString(",")}")

      opt[InteractionType.Value]('t', "userInteraction").
        optional().
        action((t, p) => p.copy(userInteraction = t))
        .text(s"Allowed values: ${InteractionType.values.mkString(",")}")

      opt[AIType.Value]('a', "ai").
        optional().
        action((a, p) => p.copy(ai = a))
        .text(s"Allowed values: ${AIType.values.mkString(",")}")

    }

    parser.parse(args, ParametersParser())
  }

}
