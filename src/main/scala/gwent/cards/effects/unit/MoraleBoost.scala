package cl.uchile.dcc
package gwent.cards.effects.unit

import gwent.cards.effects.UnitEffect
import gwent.cards.UnitCard

class MoraleBoost extends UnitEffect {
  def apply(self: UnitCard, target: List[UnitCard]): Unit = {
    var nTarget = target.filterNot(_ eq self)
    nTarget.foreach(i => i.currentPower += 1)
  }

}
