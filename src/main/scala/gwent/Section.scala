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
class Section(var closeCombatZone: List[Card] = List(),
              var rangedCombatZone: List[Card] = List(),
              var siegeCombatZone: List[Card] = List()) {
  def playCloseCombatCard(ccCard: CloseCombatCard): Unit = {
    closeCombatZone = ccCard :: closeCombatZone
  }
  def playRangedCombatCard(rcCard: RangedCombatCard): Unit = {
    rangedCombatZone = rcCard :: rangedCombatZone
  }
  def playSiegeCombatCard(scCard: SiegeCombatCard): Unit = {
    siegeCombatZone = scCard :: siegeCombatZone
  }
  override def equals(obj: Any): Boolean = obj match {
    case other: Section =>
      (this eq other) || (closeCombatZone == other.closeCombatZone
        && rangedCombatZone == other.rangedCombatZone
        && siegeCombatZone == other.siegeCombatZone)
    case _ => false
  }
  override def hashCode(): Int =
    Objects.hash(classOf[Section], closeCombatZone, rangedCombatZone, siegeCombatZone)
}
