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
  /** Ends the turn for the current player. */
  def endTurn(): Unit = {
    transitionError("AloneState or CountState")
  }
  def newRound(): Unit = {
    transitionError("RoundState")
  }
  def isInStart(): Boolean = false
  def isInTurn(): Boolean = false
  def isInAlone(): Boolean = false
  private def transitionError(targetState: String): Unit = {
    throw new InvalidTransitionException(
      s"Cannot transition from ${getClass.getSimpleName} to $targetState"
    )
  }
}
