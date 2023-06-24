package cl.uchile.dcc
package gwent.cards.effects.unit

import gwent.cards.effects.AbstractUnitEffect
import gwent.cards.*

case class NoEffect() extends AbstractUnitEffect {
  override def apply(self: Card, target: List[UnitCard]): Unit = {
    // do nothing :P
  }
}
