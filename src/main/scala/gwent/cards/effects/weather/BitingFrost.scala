package cl.uchile.dcc
package gwent.cards.effects.weather

import gwent.cards.effects.AbstractWeatherEffect
import gwent.cards.*
import gwent.*

case class BitingFrost() extends AbstractWeatherEffect {
  override def apply(self: Card, target: Board): Unit = {
    super.apply(self, target)
    applyEffect(target.player1.section.closeCombatZone, target.player2.section.closeCombatZone)
  }
  override def undo(target: Board): Unit = {
    unapplyEffect(target.player1.section.closeCombatZone, target.player2.section.closeCombatZone)
  }
}
