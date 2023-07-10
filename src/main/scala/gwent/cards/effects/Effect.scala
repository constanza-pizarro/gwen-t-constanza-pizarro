package cl.uchile.dcc
package gwent.cards.effects

import gwent.cards.*
import gwent.*

/**
 * Represents the common structure of an effect of a card in the game.
 */
trait Effect {
  /** Applies the effect of the card to an unit section of the board. */
  def apply(self: Card, target: List[UnitCard]): Unit
  /** Applies the effect of the card to the weather section of the board. */
  def apply(self: Card, target: Board): Unit
  /** Reverses the current weather cards' effects. */
  def undo(target: Board): Unit = {
    // do nothing uwu
  }
}
