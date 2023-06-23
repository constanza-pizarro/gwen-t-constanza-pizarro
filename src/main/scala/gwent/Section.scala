package cl.uchile.dcc
package gwent

import gwent.cards.*
import java.util.Objects

/** Class representing a section in the Gwen't game.
 *
 * Each section has Close, Ranged and Siege combat zones.
 *
 * @constructor Create a new section with Close, Ranged and Siege combat zones
 * @param _closeCombatZone The list of cards in the close combat zone.
 * @param _rangedCombatZone The list of cards in the ranged combat zone.
 * @param _siegeCombatZone The list of cards in the siege combat zone.
 *
 * @author Constanza Pizarro
 */
class Section(private var _closeCombatZone: List[CloseCombatCard] = List(),
              private var _rangedCombatZone: List[RangedCombatCard] = List(),
              private var _siegeCombatZone: List[SiegeCombatCard] = List()) extends Equals {
  /** Accessor method for the section's close combat zone */
  def closeCombatZone: List[CloseCombatCard] = {
    val ccCopy = _closeCombatZone
    ccCopy
  }
  /** Accessor method for the section's ranged combat zone */
  def rangedCombatZone: List[RangedCombatCard] = {
    val rcCopy = _rangedCombatZone
    rcCopy
  }
  /** Accessor method for the section's siege combat zone */
  def siegeCombatZone: List[SiegeCombatCard] = {
    val scCopy = _siegeCombatZone
    scCopy
  }
  /** Adds a close combat card to the close combat zone. */
  def playCloseCombatCard(ccCard: CloseCombatCard): Unit = {
    _closeCombatZone = ccCard :: closeCombatZone
    ccCard.effect(ccCard, closeCombatZone)
  }
  /** Adds a ranged combat card to the ranged combat zone. */
  def playRangedCombatCard(rcCard: RangedCombatCard): Unit = {
    _rangedCombatZone = rcCard :: rangedCombatZone
    rcCard.effect(rcCard, rangedCombatZone)
  }
  /** Adds a siege combat card to the siege combat zone. */
  def playSiegeCombatCard(scCard: SiegeCombatCard): Unit = {
    _siegeCombatZone = scCard :: siegeCombatZone
    scCard.effect(scCard, siegeCombatZone)
  }
  override def canEqual(that: Any): Boolean =
    that.isInstanceOf[Section]
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
