package cl.uchile.dcc
package gwent.cards

import gwent.Section
import gwent.cards.effects.Effect

/**
 * Represents the common structure of a unit card in the game.
 *
 * A `UnitCard` is a type of [[Card]].
 * A unit card is characterized by its [[name]], [[description]], [[effect]] and [[power]].
 * It also has a [[currentPower]] and a [[previousPower]] to keep track of the effects
 * applied to the card.
 */
trait UnitCard extends Card {
  val power: Int
  /** The current power of the card, which may be affected by various conditions during
   * gameplay.
   * Initially set to the base [[power]] of the card.
   */
  var currentPower: Int = power
  /** The previous power of the card, which may be changed when a weather card is placed
   * on the board.
   * Initially set to 0.
   */
  var previousPower: Int = 0
  /** Puts the unit card in its respective section of the board.
   *
   * @param section the section the unit card will be added to
   */
  def playUnitCard(section: Section): Unit
}
