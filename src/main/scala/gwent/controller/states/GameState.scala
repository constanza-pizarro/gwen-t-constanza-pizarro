package cl.uchile.dcc
package gwent.controller.states

import gwent.controller.GameController
import gwent.cards.Card
import gwent.Player

class GameState(var context: GameController) {
  /** Transitions to TurnState. */
  def startGame(): Unit = {
    transitionError("TurnState")
  }
  /** Does nothing. */
  def playCard(): Unit = {}
  private def transitionError(targetState: String): Unit = {
    throw new InvalidTransitionException(
      s"Cannot transition from ${getClass.getSimpleName} to $targetState"
    )
  }
}
