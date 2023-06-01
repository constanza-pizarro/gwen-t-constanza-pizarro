package cl.uchile.dcc
package gwent

import gwent.cards.*
import java.util.Objects

/** Class representing a section in the Gwen't game.
 *
 * Each section has Close, Ranged and Siege combat zones.
 *
 * @constructor Create a new section with Close, Ranged and Siege combat zones
 * @param closeCombatZone The list of cards in the close combat zone.
 * @param rangedCombatZone The list of cards in the ranged combat zone.
 * @param siegeCombatZone The list of cards in the siege combat zone.
 *
 * @author Constanza Pizarro
 */
class Section(var closeCombatZone: List[CloseCombatCard] = List(),
              var rangedCombatZone: List[RangedCombatCard] = List(),
              var siegeCombatZone: List[SiegeCombatCard] = List()) extends Equals {
  /** Adds a close combat card to the close combat zone. */
  def playCloseCombatCard(ccCard: CloseCombatCard): Unit = {
    closeCombatZone = ccCard :: closeCombatZone
  }
  /** Adds a ranged combat card to the ranged combat zone. */
  def playRangedCombatCard(rcCard: RangedCombatCard): Unit = {
    rangedCombatZone = rcCard :: rangedCombatZone
  }
  /** Adds a siege combat card to the siege combat zone. */
  def playSiegeCombatCard(scCard: SiegeCombatCard): Unit = {
    siegeCombatZone = scCard :: siegeCombatZone
  }
  override def canEqual(that: Any): Boolean = {
    that.isInstanceOf[Section]
  }
  override def equals(that: Any): Boolean = {
    if (canEqual(that)) {
      val other = that.asInstanceOf[Section]
      (this eq other) || (closeCombatZone == other.closeCombatZone
        && rangedCombatZone == other.rangedCombatZone
        && siegeCombatZone == other.siegeCombatZone)
    } else {
      false
    }
  }
  override def hashCode(): Int =
    Objects.hash(classOf[Section], closeCombatZone, rangedCombatZone, siegeCombatZone)
}
