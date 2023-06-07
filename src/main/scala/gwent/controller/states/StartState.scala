package cl.uchile.dcc
package gwent.controller.states

import gwent.controller.GameController

class StartState(controller: GameController) extends GameState(controller) {
  override def startGame(): Unit = {
    controller.state = new TurnState(controller)
  }

}
