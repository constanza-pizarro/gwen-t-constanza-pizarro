package cl.uchile.dcc
package gwent.cards

/** Represents a generic card in the game. Not to be instanced
 *
 * @param name The name of the card
 * @param ability The ability of the card, if it has */
abstract class AbstractCard(val name: String, val ability: Option[String]) extends ICard with Equals {
  //def playCard: Unit
  /** Compares a card object with an object of any type.
   *
   * @param that object to compare.
   */
  override def equals(that: Any): Boolean = {
    if (canEqual(that)) {
      val other = that.asInstanceOf[AbstractCard]
      (this eq other) || (name == other.name && ability == other.ability)
    } else {
      false
    }
  }
}
