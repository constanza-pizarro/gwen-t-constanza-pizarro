package cl.uchile.dcc
package gwent.cards

import cl.uchile.dcc.gwent.Player
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
class SiegeCombatCard(name: String, description: String, power: Int)
  extends AbstractUnitCard(name, description, power) {
  override def playUnitCard(player: Player): Unit = {
    val zone = player.section.siegeCombatZone
    player.section.siegeCombatZone = this :: zone
  }
  override def equals(obj: Any): Boolean = obj match {
    case other: SiegeCombatCard =>
      super.equals(other)
    case _ =>
      false
  }
  override def hashCode(): Int =
    Objects.hash(classOf[SiegeCombatCard], name, description, power)
  override def toString =
    s"SiegeCombatCard(name: $name, description: $description, power: $power)"
}
