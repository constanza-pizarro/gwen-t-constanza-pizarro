package cl.uchile.dcc
package gwent.controller.states

import gwent.controller.GameController

class AloneState(controller: GameController) extends GameState(controller) {
  override def isInAlone(): Boolean = true
}
