package cl.uchile.dcc
package gwent

import gwent.cards.Card
import java.util.Objects

/** Class representing a section in the Gwen't game.
 *
 * Each section belongs to a board and has Close, Ranged and Siege combat zones.
 *
 * @constructor Create a new section with a board and Close, Ranged and Siege combat zones
 * @param board The board the section belongs to.
 * @param closeCombatZone The list of cards in the close combat zone.
 * @param rangedCombatZone The list of cards in the ranged combat zone.
 * @param siegeCombatZone The list of cards in the siege combat zone.
 *
 * @author Constanza Pizarro
 */
class Section(val board: Board, var closeCombatZone: List[Card] = List(),
              var rangedCombatZone: List[Card] = List(),
              var siegeCombatZone: List[Card] = List()) {
  override def equals(obj: Any): Boolean = obj match {
    case other: Section =>
      (this eq other) || (board == other.board
        && closeCombatZone == other.closeCombatZone
        && rangedCombatZone == other.rangedCombatZone
        && siegeCombatZone == other.siegeCombatZone)
    case _ => false
  }
  override def hashCode(): Int =
    Objects.hash(classOf[Section], board, closeCombatZone, rangedCombatZone, siegeCombatZone)
}
