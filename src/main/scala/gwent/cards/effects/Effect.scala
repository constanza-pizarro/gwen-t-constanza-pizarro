package cl.uchile.dcc
package gwent.cards.effects

import gwent.cards.*
import gwent.*

trait Effect {
  def apply(self: Card, target: List[UnitCard]): Unit
  def apply(self: Card, target: Board): Unit
  def undo(target: Board): Unit = {
    // do nothing uwu
  }
}
