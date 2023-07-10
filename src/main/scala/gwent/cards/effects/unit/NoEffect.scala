package cl.uchile.dcc
package gwent.cards.effects.unit

import gwent.cards.effects.AbstractUnitEffect
import gwent.cards.*

/** Class representing a "null" effect of an unit card in the Gwen't game.
 *
 * A `NoEffect` is a type of [[AbstractUnitEffect]].
 * When a card has this effect it means the card has no effect.
 *
 * @constructor Creates a new `NoEffect` effect.
 *
 * @author Constanza Pizarro
 */
case class NoEffect() extends AbstractUnitEffect {
  override def apply(self: Card, target: List[UnitCard]): Unit = {
    // do nothing :P
  }
}
