package cl.uchile.dcc
package gwent.cards.effects

import gwent.cards.*

abstract class AbstractWeatherEffect extends Effect {
  override def apply(self: Card, target: List[UnitCard]): Unit = {
    // do nothing :p
  }

}
