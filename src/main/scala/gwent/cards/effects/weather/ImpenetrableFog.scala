package cl.uchile.dcc
package gwent.cards.effects.weather

import gwent.cards.effects.AbstractWeatherEffect
import cl.uchile.dcc.gwent.*
import cl.uchile.dcc.gwent.cards.Card

/** Case class representing the impenetrable fog effect of a weather card in the Gwen't game.
 *
 * A `ImpenetrableFog` is a type of [[AbstractWeatherEffect]].
 * As per the game's rules, when a card with this effect is placed on the board the current
 * power of all cards on the close combat zone of both players is set to 1.
 *
 * @constructor Creates a new `ImpenetrableFog` effect.
 *
 * @author Constanza Pizarro
 */
case class ImpenetrableFog() extends AbstractWeatherEffect {
  override def apply(self: Card, target: Board): Unit = {
    super.apply(self, target)
    applyEffect(target.player1.section.rangedCombatZone)
    applyEffect(target.player2.section.rangedCombatZone)
  }
  override def undo(target: Board): Unit = {
    unapplyEffect(target.player1.section.rangedCombatZone)
    unapplyEffect(target.player2.section.rangedCombatZone)
  }
}
