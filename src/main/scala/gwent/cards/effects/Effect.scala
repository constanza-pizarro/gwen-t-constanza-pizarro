package cl.uchile.dcc
package gwent.cards.effects

import gwent.cards.*
import gwent.*

/**
 * Represents the common structure of an effect of a card in the game.
 */
trait Effect {
  /** Applies the effect of a card to an unit zone of the board.
   *
   * @param self the card whose effect is applied
   * @param target the unit zone where the effect is applied
   */
  def apply(self: Card, target: List[UnitCard]): Unit
  /** Applies the effect of a card to an unit zone of both players of a board.
   *
   * @param self the card whose effect is applied
   * @param target the board where the effect is applied
   */
  def apply(self: Card, target: Board): Unit
  /** Reverses the current weather cards' effects.
   *
   * @param target the board where the current weather effects are reverted
   */
  def undo(target: Board): Unit = {
    // do nothing uwu
  }
}
