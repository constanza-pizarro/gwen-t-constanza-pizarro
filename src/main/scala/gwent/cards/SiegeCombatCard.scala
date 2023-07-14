package cl.uchile.dcc
package gwent.cards

import gwent.Section
import gwent.cards.effects.Effect

import java.util.Objects

/** Class representing a siege combat unit card in the Gwen't game.
 *
 * A `SiegeCombatCard` is a type of [[AbstractUnitCard]].
 * These cards represent siege machinery used on the battlefield.
 * As per the game's rules, these cards can only be placed in the siege combat zone.
 *
 * @constructor create a new siege combat card with name, description, and power.
 * @param name the name of the card.
 * @param description the description of the card.
 * @param power the initial power value of the card, which also corresponds to the siege
 *              machinery's strength.
 *              
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author Constanza Pizarro
 */
class SiegeCombatCard(name: String, effect: Effect, description: String, power: Int)
  extends AbstractUnitCard(name, effect, description, power) {
  override def copy(): Card = {
    new SiegeCombatCard(name, effect, description, power)
  }
  override def playUnitCard(section: Section): Unit =
    section.playSiegeCombatCard(this)
  override def canEqual(that: Any): Boolean = 
    that.isInstanceOf[SiegeCombatCard]
  override def hashCode(): Int =
    Objects.hash(classOf[SiegeCombatCard], name, description, power)
  override def toString =
    s"SiegeCombatCard(name: $name, description: $description, power: $power)"
}
