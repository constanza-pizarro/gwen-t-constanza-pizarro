package cl.uchile.dcc
package gwent.controller.states

import gwent.controller.GameController

class FinalState(controller: GameController) extends GameState(controller) {
  override def declareWinner(): Unit = {
    // do nothing
  }
  override def isInFinal: Boolean = true
}
