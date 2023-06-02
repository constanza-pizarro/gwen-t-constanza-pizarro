package cl.uchile.dcc
package gwent.cards

import gwent.{Board, Section}
import java.util.Objects

/** Abstract class representing a unit card in the Gwen't game.
 *
 * An `AbstractUnitCard` is a type of [[Card]] that has a power value which contributes to
 * the player's total power in the game.
 * Each unit card starts with its current power equal to its base power.
 *
 * @constructor Creates a new `AbstractUnitCard` with a specified name, description, and
 *              power.
 * @param name The name of the card.
 * @param description The description of the card, explaining its specific abilities or
 *                    role in the game.
 * @param power The base power of the card, indicating the contribution of this card to
 *              the player's total power when unaffected by any special conditions.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author Constanza Pizarro
 */
abstract class AbstractUnitCard protected(val name: String, val description: String,
                                          val power: Int) extends Card with Equals {
  /** The current power of the card, which may be affected by various conditions during
   * gameplay.
   * Initially set to the base [[power]] of the card.
   */
  var currentPower: Int = power
  override def playCard(board: Board, section: Section): Unit =
    this.playUnitCard(section)
  /** Puts the unit card in its respective section of the board.
   *
   * @param section the section the unit card will be added to
   */
  def playUnitCard(section: Section): Unit
  override def equals(that: Any): Boolean = {
    if (canEqual(that)) {
      val other = that.asInstanceOf[AbstractUnitCard]
      (this eq other) || (name == other.name
                      && description == other.description
                      && power == other.power)
    } else {
      false
    }
  }
}
