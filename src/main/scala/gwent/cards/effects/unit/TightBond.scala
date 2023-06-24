package cl.uchile.dcc
package gwent.cards.effects.unit

import gwent.cards.effects.AbstractUnitEffect
import gwent.cards.*

case class TightBond() extends AbstractUnitEffect {
  override def apply(self: Card, target: List[UnitCard]): Unit = {
    val nTarget = target.filter(_.equals(self))
    if (nTarget.length>1)
      nTarget.foreach(_.currentPower *=2)
  }
}