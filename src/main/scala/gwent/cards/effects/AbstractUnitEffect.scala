package cl.uchile.dcc
package gwent.cards.effects

import gwent.cards.Card
import gwent.Board

abstract class AbstractUnitEffect extends Effect {
  override def apply(self: Card, board: Board): Unit = {
    // do nothing c:
  }
}
