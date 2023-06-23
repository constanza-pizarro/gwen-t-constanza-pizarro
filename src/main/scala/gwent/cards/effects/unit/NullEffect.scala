package cl.uchile.dcc
package gwent.cards.effects.unit

import gwent.cards.effects.UnitEffect
import gwent.cards.UnitCard

class NullEffect extends UnitEffect {
  def apply(self: UnitCard, target: List[UnitCard]): Unit = {
    // do nothing :P
  }
}
