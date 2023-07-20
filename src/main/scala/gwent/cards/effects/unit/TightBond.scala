package cl.uchile.dcc
package gwent.cards.effects.unit

import gwent.cards.effects.AbstractUnitEffect
import gwent.cards.*

/** Class representing the tight bond effect of a unit card in the Gwen't game.
 *
 * A `TightBond` is a type of [[AbstractUnitEffect]].
 * As per the game's rules, when a card with this effect is placed on the board 
 * if the same card was already on the board (at least once) the current power of 
 * all these cards is duplicated.
 *
 * @constructor Creates a new `TightBond` effect.
 *
 * @author Constanza Pizarro
 */
case class TightBond() extends AbstractUnitEffect {
  override def apply(self: Card, target: List[UnitCard]): Unit = {
    val nTarget = target.filter(_.equals(self))
    if (nTarget.length>1)
      nTarget.foreach(_.currentPower *=2)
  }
}