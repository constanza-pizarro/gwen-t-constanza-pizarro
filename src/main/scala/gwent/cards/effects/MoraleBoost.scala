package cl.uchile.dcc
package gwent.cards.effects
import gwent.cards.{AbstractUnitCard, Card, UnitCard}

class MoraleBoost extends UnitEffect {
  def apply(self: UnitCard, target: List[UnitCard]): Unit = {
    var nTarget = target.filterNot(_ eq self)
    nTarget.foreach(i => i.currentPower += 1)
  }

}
