package cl.uchile.dcc
package gwent.cards.effects.weather

import gwent.cards.effects.AbstractWeatherEffect
import gwent.cards.*
import gwent.*

case class BitingFrost() extends AbstractWeatherEffect {
  override def apply(self: Card, target: Board): Unit = {
    super.apply(self, target)
    target.player1.section.closeCombatZone
      .foreach(card => {
        card.lastPower = card.currentPower
        card.currentPower = 1
      })
    target.player2.section.closeCombatZone
      .foreach(card => {
        card.lastPower = card.currentPower
        card.currentPower = 1
      })
  }
}
