package cl.uchile.dcc
package gwent.cards.effects.unit

import gwent.cards.UnitCard
import gwent.cards.effects.Effect

trait UnitEffect extends Effect {
  def apply(self: UnitCard, target: List[UnitCard]): Unit
}
