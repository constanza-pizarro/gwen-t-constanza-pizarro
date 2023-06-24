package cl.uchile.dcc
package gwent.cards.effects.weather

import gwent.cards.effects.AbstractWeatherEffect

import cl.uchile.dcc.gwent.*
import cl.uchile.dcc.gwent.cards.Card

case class TorrentialRain() extends AbstractWeatherEffect {
  override def apply(self: Card, target: Board): Unit = {
    super.apply(self, target)
    target.player1.section.siegeCombatZone
      .foreach(card => {
        card.lastPower = card.currentPower
        card.currentPower = 1
      })
    target.player2.section.siegeCombatZone
      .foreach(card => {
        card.lastPower = card.currentPower
        card.currentPower = 1
      })
  }
}
