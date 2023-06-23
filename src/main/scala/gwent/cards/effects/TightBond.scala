package cl.uchile.dcc
package gwent.cards.effects

import gwent.cards.UnitCard

class TightBond extends UnitEffect {
  def apply(self: UnitCard, target: List[UnitCard]): Unit = {
    val nTarget = target.filter(_.equals(self))
    if (nTarget.nonEmpty) {
      nTarget.foreach(_.currentPower *= 2)
      self.currentPower *= 2
    }
  }
}