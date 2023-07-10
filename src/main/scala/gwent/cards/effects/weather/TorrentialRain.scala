package cl.uchile.dcc
package gwent.cards.effects.weather

import gwent.cards.effects.AbstractWeatherEffect

import cl.uchile.dcc.gwent.*
import cl.uchile.dcc.gwent.cards.Card

/** Case class representing the torrential rain effect of a weather card in the Gwen't game.
 *
 * A `TorrentialRain` is a type of [[AbstractWeatherEffect]].
 * As per the game's rules, when a card with this effect is placed on the board the current
 * power of all cards on the siege combat zone of both players is set to 1.
 *
 * @constructor Creates a new `TorrentialRain` effect.
 *
 * @author Constanza Pizarro
 */
case class TorrentialRain() extends AbstractWeatherEffect {
  override def apply(self: Card, target: Board): Unit = {
    super.apply(self, target)
    applyEffect(target.player1.section.siegeCombatZone)
    applyEffect(target.player2.section.siegeCombatZone)
  }
  override def undo(target: Board): Unit = {
    unapplyEffect(target.player1.section.siegeCombatZone)
    unapplyEffect(target.player2.section.siegeCombatZone)
  }
}
