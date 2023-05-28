package cl.uchile.dcc
package gwent.cards

import cl.uchile.dcc.gwent.Player
import java.util.Objects

/** Class representing a ranged combat unit card in the Gwen't game.
 *
 * A `RangedCombatCard` is a type of [[AbstractUnitCard]].
 * These cards represent troops that engage in ranged combat on the battlefield.
 * As per the game's rules, these cards can only be placed in the ranged combat zone.
 *
 * @constructor create a new ranged combat card with name, description, and power.
 * @param name the name of the card.
 * @param description the description of the card.
 * @param power the initial power value of the card, which also corresponds to the
 *              strength of the troop it represents.
 *              
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author Constanza Pizarro
 */
class RangedCombatCard(name: String, description: String, power: Int)
  extends AbstractUnitCard(name, description, power) {
  override def playUnitCard(player: Player): Unit = {
    super.playUnitCard(player)
    val zone = player.section.rangedCombatZone
    player.section.rangedCombatZone = this :: zone
  }
  override def equals(obj: Any): Boolean = obj match {
    case other: RangedCombatCard =>
      super.equals(other)
    case _ =>
      false
  }
  override def hashCode(): Int =
    Objects.hash(classOf[RangedCombatCard], name, description, power)
  override def toString =
    s"RangedCombatCard(name: $name, description: $description, power: $power)"
}
