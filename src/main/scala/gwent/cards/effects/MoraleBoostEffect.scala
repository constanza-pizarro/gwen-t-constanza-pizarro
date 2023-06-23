package cl.uchile.dcc
package gwent.cards.effects
import gwent.cards.{AbstractUnitCard, Card, UnitCard}

class MoraleBoostEffect extends UnitEffect {
  //var targetSelf = false
  def apply(self: Card, target: List[UnitCard]): Unit = {
    var nTarget = target.filterNot(_ eq self)
    nTarget.foreach(i => i.currentPower += 1)
  }

}
