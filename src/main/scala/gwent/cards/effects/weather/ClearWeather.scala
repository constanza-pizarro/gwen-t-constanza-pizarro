package cl.uchile.dcc
package gwent.cards.effects.weather

import gwent.*
import gwent.cards.Card
import gwent.cards.effects.*

case class ClearWeather() extends AbstractWeatherEffect {
  override def apply(self: Card, target: Board): Unit = {
    target.applied.foreach(effect => effect.undo(target))
  }
  override def undo(target: Board): Unit = {
    // do nothing
  }
}
