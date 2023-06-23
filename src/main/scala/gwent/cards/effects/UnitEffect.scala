package cl.uchile.dcc
package gwent.cards.effects

import gwent.cards.UnitCard

trait UnitEffect extends Effect {
  def apply(self: UnitCard, target: List[UnitCard]): Unit
}
