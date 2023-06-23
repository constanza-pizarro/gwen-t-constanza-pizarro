package cl.uchile.dcc
package gwent.cards.effects

import gwent.cards.UnitCard

trait UnitEffect {
  def apply(self: UnitCard, target: List[UnitCard]): Unit
}
