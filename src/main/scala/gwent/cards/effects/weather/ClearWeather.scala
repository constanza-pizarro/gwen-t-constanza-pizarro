package cl.uchile.dcc
package gwent.cards.effects.weather

import gwent.*
import gwent.cards.Card
import gwent.cards.effects.*

/** Case class representing the clear weather effect of a weather card in the Gwen't game.
 *
 * A `ClearWeather` is a type of [[AbstractWeatherEffect]].
 * As per the game's rules, when a card with this effect is placed on the board the effect
 * of all weather cards placed on the board is reverted.
 *
 * @constructor Creates a new `ClearWeather` effect.
 *
 * @author Constanza Pizarro
 */
case class ClearWeather() extends AbstractWeatherEffect {
  override def apply(self: Card, target: Board): Unit = {
    target.applied.foreach(effect => effect.undo(target))
  }
  override def undo(target: Board): Unit = {
    // do nothing
  }
}
