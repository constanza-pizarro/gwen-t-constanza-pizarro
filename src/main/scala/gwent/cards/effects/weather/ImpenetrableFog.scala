package cl.uchile.dcc
package gwent.cards.effects.weather

import gwent.cards.effects.AbstractWeatherEffect
import cl.uchile.dcc.gwent.*
import cl.uchile.dcc.gwent.cards.Card

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
