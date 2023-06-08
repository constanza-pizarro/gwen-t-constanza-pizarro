package cl.uchile.dcc
package gwent.controller.states

import gwent.controller.GameController

import cl.uchile.dcc.gwent.Player

class CountState(controller: GameController) extends GameState(controller) {
  override def isInCount(): Boolean = true
}
