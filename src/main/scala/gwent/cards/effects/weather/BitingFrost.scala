package cl.uchile.dcc
package gwent.cards.effects.weather

import gwent.cards.effects.AbstractWeatherEffect
import gwent.cards.*
import gwent.*

/** Case class representing the biting frost effect of a weather card in the Gwen't game.
 *
 * A `BitingFrost` is a type of [[AbstractWeatherEffect]].
 * As per the game's rules, when a card with this effect is placed on the board the current
 * power of all cards on the close combat zone of both players is set to 1.
 *
 * @constructor Creates a new `BitingFrost` effect.
 *
 * @author Constanza Pizarro
 */
case class BitingFrost() extends AbstractWeatherEffect {
  override def apply(self: Card, target: Board): Unit = {
    super.apply(self, target)
    applyEffect(target.player1.section.closeCombatZone, target.player2.section.closeCombatZone)
  }
  override def undo(target: Board): Unit = {
    unapplyEffect(target.player1.section.closeCombatZone, target.player2.section.closeCombatZone)
  }
}
