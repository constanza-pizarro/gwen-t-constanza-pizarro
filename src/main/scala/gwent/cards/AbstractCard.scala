package cl.uchile.dcc
package gwent.cards

/** Represents a generic card in the game. Not to be instanced
 *
 * @param name The name of the card */
abstract class AbstractCard(name: String) extends ICard with Equals {
  def playCard(): Unit
}
