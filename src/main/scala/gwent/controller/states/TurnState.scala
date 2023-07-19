package cl.uchile.dcc
package gwent.controller.states

import gwent.controller.GameController

class TurnState(controller: GameController) extends GameState(controller) {
  override def endTurn(): Unit = {
    controller.changeTurn()
    controller.state = new AloneState(controller)
  }
  override def doAction(): Unit = {
    controller.changeTurn()
  }
  override def isInTurn: Boolean = true
}
