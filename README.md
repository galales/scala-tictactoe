# TicTacToe

This is just a proof of concept project to dive into Scala capabilities and build some skills.

The project has been built aiming to modularity and flexibility, that's why every service can potentially be replaced with a different implementation.
<br>Following Scala principles, with the exception of the Board, the code is completely immutable.

#### Services
The application leverages on 3 services:
- AI
- User Interaction
- User Interface


To play with extensibility, 3 different AIs have been developed:
- Monkey, just puts random marks
- Simple, tries to win placing marks in the current best spot
- Challenge, same as Simple AI, but it will also get in the user way

Currently, user interaction and user interface services are handled only with console implementations.

#### Misc

The last configuration is the board size, that has been implemented just to test how far the code would have been generic and adaptable.

Every configuration can be selected using CLI parameters when launching the application.
 
---

##### External dependencies
- [ScalaTest](https://github.com/scalatest/scalatest)
- [ScalaMock](https://github.com/paulbutcher/ScalaMock)
- [scopt](https://github.com/scopt/scopt)


 

