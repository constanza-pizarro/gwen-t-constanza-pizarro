package cl.uchile.dcc
package gwent.controller.states

import gwent.controller.GameController
import gwent.cards.Card
import gwent.Player

class GameState(var context: GameController) {
  def startGame(): Unit = {
    transitionError("TurnState")
  }
  private def transitionError(targetState: String): Unit = {
    throw new InvalidTransitionException(
      s"Cannot transition from ${getClass.getSimpleName} to $targetState"
    )
  }
}
