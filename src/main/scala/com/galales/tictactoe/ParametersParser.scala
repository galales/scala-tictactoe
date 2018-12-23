package com.galales.tictactoe

import com.galales.tictactoe.enums.{AIType, InteractionType, UserInterfaceType}

final case class ParametersParser(userInterface: Option[UserInterfaceType.Value] = Some(UserInterfaceType.console), userInteraction: Option[InteractionType.Value] = Some(InteractionType.console), ai: Option[AIType.Value] = Some(AIType.monkey))

object ParametersParser {

  implicit val userInterfaceTypeRead: scopt.Read[UserInterfaceType.Value] =
    scopt.Read.reads(UserInterfaceType.withName)

  implicit val userInteractionRead: scopt.Read[InteractionType.Value] =
    scopt.Read.reads(InteractionType.withName)

  implicit val aiTypeRead: scopt.Read[AIType.Value] =
    scopt.Read.reads(AIType.withName)


  def apply(args: Array[String]): Option[ParametersParser] = {
    val parser = new scopt.OptionParser[ParametersParser]("Tic Tac Toe") {

      opt[UserInterfaceType.Value]('i', "userInterface").
        optional().
        action((i, p) => p.copy(userInterface = Some(i)))

      opt[InteractionType.Value]('t', "userInteraction").
        optional().
        action((t, p) => p.copy(userInteraction = Some(t)))

      opt[AIType.Value]('a', "ai").
        optional().
        action((a, p) => p.copy(ai = Some(a)))

    }

    parser.parse(args, ParametersParser())
  }

}
