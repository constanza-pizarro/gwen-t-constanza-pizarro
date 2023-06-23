package cl.uchile.dcc
package gwent.cards.effects.unit

import gwent.cards.effects.UnitEffect
import gwent.cards.UnitCard

case class TightBond() extends UnitEffect {
  def apply(self: UnitCard, target: List[UnitCard]): Unit = {
    val nTarget = target.filter(_.equals(self))
    if (nTarget.length>1)
      nTarget.foreach(_.currentPower *=2)
  }
}