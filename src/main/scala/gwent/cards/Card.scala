package cl.uchile.dcc
package gwent.cards

import gwent.{Board, Player}

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
  /** Puts the card on its respective section of the board.
   */
  def playCard(board: Board, player: Player): Unit
}
