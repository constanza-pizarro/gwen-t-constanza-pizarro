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
}
