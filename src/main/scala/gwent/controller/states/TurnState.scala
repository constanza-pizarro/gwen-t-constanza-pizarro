package cl.uchile.dcc
package gwent.controller.states

import gwent.controller.GameController

class TurnState(controller: GameController) extends GameState(controller) {
  override def endTurn(): Unit = {
    controller.state = new AloneState(controller)
  }
  override def isInTurn(): Boolean = true
}
