package cl.uchile.dcc
package gwent.cards.effects.unit

import gwent.cards.effects.AbstractUnitEffect
import gwent.cards.*

case class MoraleBoost() extends AbstractUnitEffect {
  override def apply(self: Card, target: List[UnitCard]): Unit = {
    var nTarget = target.filterNot(_ eq self)
    nTarget.foreach(i => i.currentPower += 1)
  }
}
