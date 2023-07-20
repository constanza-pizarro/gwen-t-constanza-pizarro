package cl.uchile.dcc
package gwent.controller.states

import gwent.controller.GameController

import cl.uchile.dcc.gwent.Player

class RoundState(controller: GameController) extends GameState(controller) {
  override def startRound(): Unit = {
    controller.state = new TurnState(controller)
  }
  override def declareWinner(): Unit = {
    controller.state = new FinalState(controller)
  }
  override def isInRound: Boolean = true
}
