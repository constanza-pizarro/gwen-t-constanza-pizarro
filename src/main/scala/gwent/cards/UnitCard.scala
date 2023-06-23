package cl.uchile.dcc
package gwent.cards

import gwent.Section

trait UnitCard extends Card {
  val power: Int
  /** The current power of the card, which may be affected by various conditions during
   * gameplay.
   * Initially set to the base [[power]] of the card.
   */
  var currentPower: Int = power
  /** Puts the unit card in its respective section of the board.
   *
   * @param section the section the unit card will be added to
   */
  def playUnitCard(section: Section): Unit
}
