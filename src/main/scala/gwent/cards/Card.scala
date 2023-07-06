package cl.uchile.dcc
package gwent.cards

import gwent.{Board, Section}
import gwent.cards.effects.Effect

/**
 * Represents the common structure of a card in the game.
 *
 * A card is characterized by its [[name]] and [[description]].
 */
trait Card {
  /** The name of the card.
   * This is an immutable property.
   */
  val name: String
  /** A description of the card's properties or effects.
   * This is an immutable property.
   */
  val description: String
  /** The effect of the card. 
   * This is an immutable property.
   */
  val effect: Effect
  /** Puts the card on its respective section of the board.
   *
   * @param board the board a card can be added to
   * @param section the section of a player a card can be added to
   */
  def playCard(board: Board, section: Section): Unit
}
