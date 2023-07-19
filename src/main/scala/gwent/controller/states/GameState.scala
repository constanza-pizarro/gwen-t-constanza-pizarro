package cl.uchile.dcc
package gwent.controller.states

import gwent.controller.GameController
import gwent.exceptions.InvalidTransitionException
/** Class representing a player in the Gwen't game.
 *
 * Each player has a name, a section, a gem counter, a deck of cards and a hand of cards.
 * The gem counter, section, deck and hand are private variables, but can be accessed via
 * their corresponding getter methods.
 *
 */
/** Class representing a state of the Gwen't game.
 *
 * Each state has a controller and might transition to another state, this is a sort
 * of default state where every transition is invalid.
 *
 * @constructor Creates a new `GameState` with a specified controller.
 * @param controller The controller the state belongs to.
 *
 * @author Constanza Pizarro
 */
class GameState(var controller: GameController) {
  /** Transitions to TurnState. */
  def startGame(): Unit = {
    transitionError("TurnState")
  }
  /** Transitions to either AloneState or CountState. */
  def endTurn(): Unit = {
    transitionError("AloneState or CountState")
  }
  /** Transitions to RoundState. */
  def newRound(): Unit = {
    transitionError("RoundState")
  }
  /** Transitions to TurnState. */
  def startRound(): Unit = {
    transitionError("TurnState")
  }
  /** Transitions to FinalState. */
  def declareWinner(): Unit = {
    transitionError("FinalState")
  }
  /** Transitions to StartState. */
  def playAgain(): Unit = {
    transitionError("StartState")
  }
  /** Calls a method of the [[controller]]. */
  def doAction(): Unit = {
    // do nothing
  }
  /** Asserts if the current state is StartState. */
  def isInStart: Boolean = false
  /** Asserts if the current state is TurnState. */
  def isInTurn: Boolean = false
  /** Asserts if the current state is AloneState. */
  def isInAlone: Boolean = false
  /** Asserts if the current state is CountState. */
  def isInCount: Boolean = false
  /** Asserts if the current state is RoundState. */
  def isInRound: Boolean = false
  /** Asserts if the current state is FinalState. */
  def isInFinal: Boolean = false
  /** Throws an exception to indicate the transition can be done. */
  private def transitionError(targetState: String): Unit = {
    throw new InvalidTransitionException(
      s"Cannot transition from ${getClass.getSimpleName} to $targetState"
    )
  }
}
