package cl.uchile.dcc
package gwent

import gwent.cards.Card

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
class Section(var board: Board, var closeCombatZone: List[Card] = List(),
              var rangedCombatZone: List[Card] = List(),
              var siegeCombatZone: List[Card] = List())
