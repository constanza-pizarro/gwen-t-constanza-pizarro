package cl.uchile.dcc
package gwent.cards.effects.unit

import gwent.cards.effects.AbstractUnitEffect
import gwent.cards.*

/** Class representing the moral boost effect of an unit card in the Gwen't game.
 *
 * A `MoraleBoost` is a type of [[AbstractUnitEffect]].
 * As per the game's rules, when a card with this effect is placed on the board all
 * cards on the zone (except itself) receive +1 of power.
 *
 * @constructor Creates a new `MoraleBoost` effect.
 *
 * @author Constanza Pizarro
 */
case class MoraleBoost() extends AbstractUnitEffect {
  override def apply(self: Card, target: List[UnitCard]): Unit = {
    var nTarget = target.filterNot(_ eq self)
    nTarget.foreach(i => i.currentPower += 1)
  }
}
