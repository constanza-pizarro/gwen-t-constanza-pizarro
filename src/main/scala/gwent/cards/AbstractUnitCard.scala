package cl.uchile.dcc
package gwent.cards

import gwent.cards.effects.UnitEffect
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
abstract class AbstractUnitCard protected(val name: String, val effect: UnitEffect, val description: String,
                                          val power: Int) extends UnitCard with Equals {
  override def playCard(board: Board, section: Section): Unit =
    this.playUnitCard(section)
  override def equals(that: Any): Boolean = {
    if (canEqual(that)) {
      val other = that.asInstanceOf[AbstractUnitCard]
      (this eq other) || (name == other.name
                      && effect == other.effect
                      && description == other.description
                      && power == other.power)
    } else {
      false
    }
  }
}
