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
  /** Applies a weather effect to two lists of unit cards.
   *
   * Saves the previous power and sets the current power to 1 of all
   * the cards of both lists.
   *
   * @param list1 a list of unit cards
   * @param list2 a list of unit cards
   */
  def applyEffect(list1: List[UnitCard], list2: List[UnitCard]): Unit = {
    list1
      .foreach(card => {
        card.previousPower = card.currentPower
        card.currentPower = 1
      })
    list2
      .foreach(card => {
        card.previousPower = card.currentPower
        card.currentPower = 1
      })
  }
  /** Reverses all weather effects to two lists of unit cards.
   *
   * Sets the current power to the previous power of all cards
   * (of both lists) whose previous power was modified.
   *
   * @param list1 a list of unit cards
   * @param list2 a list of unit cards
   */
  def unapplyEffect(list1: List[UnitCard], list2: List[UnitCard]): Unit = {
    list1
      .foreach(card => {
        val lp = card.previousPower
        if (lp > 0) card.currentPower = lp
      })
    list2
      .foreach(card => {
        val lp = card.previousPower
        if (lp > 0) card.currentPower = lp
      })
  }
}
