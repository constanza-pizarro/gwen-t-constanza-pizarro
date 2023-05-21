package cl.uchile.dcc
package gwent.cards

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
  //def playCard(): Unit
}
