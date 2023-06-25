package cl.uchile.dcc
package gwent.cards.effects

import gwent.cards.{Card, UnitCard}
import gwent.Board

abstract class AbstractWeatherEffect extends Effect {
  override def apply(self: Card, target: List[UnitCard]): Unit = {
    // do nothing :p
  }
  override def apply(self: Card, target: Board): Unit = {
    target.applied += this
  }
  def applyEffect(list1: List[Card], list2: List[Card]): Unit = {
    list1
      .foreach(card => {
        card.lastPower = card.currentPower
        card.currentPower = 1
      })
    list2
      .foreach(card => {
        card.lastPower = card.currentPower
        card.currentPower = 1
      })
  }
  def unapplyEffect(list1: List[Card], list2: List[Card]): Unit = {
    list1
      .foreach(card => {
        val lp = card.lastPower
        if (lp > 0) card.currentPower = lp
      })
    list2
      .foreach(card => {
        val lp = card.lastPower
        if (lp > 0) card.currentPower = lp
      })
  }
}
