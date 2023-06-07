package cl.uchile.dcc
package gwent.controller.states

import gwent.controller.GameController

class TurnState(controller: GameController) extends GameState(controller) {
  /** Gives the turn to the other player. */
  override def playCard(): Unit = {
    controller.changePlayer()
  }
}
