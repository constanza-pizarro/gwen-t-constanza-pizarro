package cl.uchile.dcc
package gwent.cards.effects

import gwent.cards.Card
import gwent.Board

/** Abstract class representing an effect of a unit card in the Gwen't game.
 *
 * An `AbstractUnitEffect` is a type of [[Effect]] that can be applied to a
 * specific unit zone of a board.
 *
 * @constructor Creates a new `AbstractUnitEffect`.
 *
 * @author Constanza Pizarro
 */
abstract class AbstractUnitEffect extends Effect {
  override def apply(self: Card, board: Board): Unit = {
    // do nothing c:
  }
  override def undo(target: Board): Unit = {
    // do nothing uwu
  }
}
