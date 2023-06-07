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
  /** Changes the current player. */
  def playCard(): Unit = {}
  /** Ends the turn for the current player. */
  def endTurn(): Unit = {}
  private def transitionError(targetState: String): Unit = {
    throw new InvalidTransitionException(
      s"Cannot transition from ${getClass.getSimpleName} to $targetState"
    )
  }
}
