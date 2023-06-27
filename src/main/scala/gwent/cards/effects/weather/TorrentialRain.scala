package cl.uchile.dcc
package gwent.cards.effects.weather

import gwent.cards.effects.AbstractWeatherEffect

import cl.uchile.dcc.gwent.*
import cl.uchile.dcc.gwent.cards.Card

case class TorrentialRain() extends AbstractWeatherEffect {
  override def apply(self: Card, target: Board): Unit = {
    super.apply(self, target)
    applyEffect(target.player1.section.siegeCombatZone, target.player2.section.siegeCombatZone)
  }
  override def undo(target: Board): Unit = {
    unapplyEffect(target.player1.section.siegeCombatZone, target.player2.section.siegeCombatZone)
  }
}
