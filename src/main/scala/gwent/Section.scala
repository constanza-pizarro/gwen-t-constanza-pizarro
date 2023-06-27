package cl.uchile.dcc
package gwent

import gwent.cards.*
import java.util.Objects

/** Class representing a section in the Gwen't game.
 *
 * Each section has Close, Ranged and Siege combat zones.
 *
 * @constructor Create a new section with empty Close, Ranged and Siege combat zones
 *
 * @author Constanza Pizarro
 */
class Section extends Equals {
  /** The list of cards in the close combat zone */
  private var _closeCombatZone = List[UnitCard]()
  /** The list of cards in the ranged combat zone */
  private var _rangedCombatZone = List[UnitCard]()
  /** The list of cards in the siege combat zone */
  private var _siegeCombatZone = List[UnitCard]()
  /** Accessor method for the section's close combat zone */
  def closeCombatZone: List[UnitCard] = {
    val ccCopy = _closeCombatZone
    ccCopy
  }
  /** Accessor method for the section's ranged combat zone */
  def rangedCombatZone: List[UnitCard] = {
    val rcCopy = _rangedCombatZone
    rcCopy
  }
  /** Accessor method for the section's siege combat zone */
  def siegeCombatZone: List[UnitCard] = {
    val scCopy = _siegeCombatZone
    scCopy
  }
  /** Adds a close combat card to the close combat zone and applies the card's effect (if it has). */
  def playCloseCombatCard(ccCard: CloseCombatCard): Unit = {
    _closeCombatZone = ccCard :: closeCombatZone
    ccCard.effect(ccCard, closeCombatZone)
  }
  /** Adds a ranged combat card to the ranged combat zone and applies the card's effect (if it has). */
  def playRangedCombatCard(rcCard: RangedCombatCard): Unit = {
    _rangedCombatZone = rcCard :: rangedCombatZone
    rcCard.effect(rcCard, rangedCombatZone)
  }
  /** Adds a siege combat card to the siege combat zone and applies the card's effect (if it has). */
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
}
