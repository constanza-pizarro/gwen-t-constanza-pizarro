package cl.uchile.dcc
package gwent.cards.effects.weather

import gwent.cards.effects.AbstractWeatherEffect
import cl.uchile.dcc.gwent.*
import cl.uchile.dcc.gwent.cards.Card

case class ImpenetrableFog() extends AbstractWeatherEffect {
  override def apply(self: Card, target: Board): Unit = {
    super.apply(self, target)
    target.player1.section.rangedCombatZone
      .foreach(card => {
        card.lastPower = card.currentPower
        card.currentPower = 1
      })
    target.player2.section.rangedCombatZone
      .foreach(card => {
        card.lastPower = card.currentPower
        card.currentPower = 1
      })
  }
}
