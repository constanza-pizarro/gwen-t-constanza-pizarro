package cl.uchile.dcc
package gwent.cards.effects

import gwent.cards.{Card, UnitCard}
import gwent.Board

/** Abstract class representing an effect of a weather card in the Gwen't game.
 *
 * An `AbstractWeatherEffect` is a type of [[Effect]] that can be applied to a
 * specific unit zone of both players a board.
 *
 * @constructor Creates a new `AbstractWeatherEffect`.
 *
 * @author Constanza Pizarro
 */
abstract class AbstractWeatherEffect extends Effect {
  override def apply(self: Card, target: List[UnitCard]): Unit = {
    // do nothing :p
  }
  override def apply(self: Card, target: Board): Unit = {
    target.applied += this
  }
  /** Applies the weather effect to a list of unit cards.
   *
   * Saves the previous power and sets the current power to 1 of all
   * the cards of the list.
   *
   * @param list a list of unit cards
   */
  def applyEffect(list: List[UnitCard]): Unit = {
    list
      .foreach(card => {
        card.previousPower = card.currentPower
        card.currentPower = 1
      })
  }
  /** Reverses all weather effects from a list of unit cards.
   *
   * Sets the current power to the previous power plus the power gained after the
   * effect was applied of all cards of the list whose previous power was modified.
   *
   * @param list a list of unit cards
   */
  def unapplyEffect(list: List[UnitCard]): Unit = {
    list
      .foreach(card => {
        val lp = card.previousPower
        if (lp > 0) card.currentPower = lp + card.currentPower - 1
      })
  }
}
