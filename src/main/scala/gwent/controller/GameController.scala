package cl.uchile.dcc
package gwent.controller

import gwent.controller.states.*

class GameController {
  var state: GameState = new StartState(this)
  def startGame(): Unit = {
    println("Player 1 name?")
    val name1: String = scala.io.StdIn.readLine()
    println("Player 2 name?")
    val name2: String = scala.io.StdIn.readLine()
  }
}
