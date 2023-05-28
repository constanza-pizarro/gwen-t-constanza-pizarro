package cl.uchile.dcc
package gwent.cards

import cl.uchile.dcc.gwent.Player
import java.util.Objects

/** Class representing a close combat unit card in the Gwen't game.
 *
 * A `CloseCombatCard` is a type of [[AbstractUnitCard]].
 * These cards represent troops that engage in close combat on the battlefield.
 * As per the game's rules, these cards can only be placed in the close combat zone.
 *
 * @constructor Creates a new `CloseCombatCard` with a specified name, description, and
 *              power.
 * @param name the name of the card.
 * @param description the description of the card.
 * @param power the initial power value of the card, which also corresponds to the
 *              strength of the troop it represents.
 *              
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author Constanza Pizarro
 */
class CloseCombatCard(name: String, description: String, power: Int)
  extends AbstractUnitCard(name, description, power) {
  override def playUnitCard(player: Player): Unit = {
    val zone = player.section.closeCombatZone
    player.section.closeCombatZone = this :: zone
  }
  override def equals(obj: Any): Boolean = obj match {
    case other: CloseCombatCard =>
      super.equals(other)
    case _ =>
      false
  }
  override def hashCode(): Int =
    Objects.hash(classOf[CloseCombatCard], name, description, power)
  override def toString =
    s"CloseCombatCard(name: $name, description: $description, power: $power)"
}
