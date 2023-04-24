package cl.uchile.dcc
package gwent.cards

/** A class representing a generic card in the game, not to be instanced.
 *
 * This card is defined by its name.
 *
 * @param name The name of the card.
 *
 * @constructor Creates a new card with the specified name.
 *
 * @author Constanza Pizarro
 */
abstract class AbstractCard(val name: String) extends ICard with Equals {
  //def playCard: Unit
  /** Compares a card object with an object of any type.
   *
   * @param that object to compare with this instance.
   * @return true if the object and this instance are equal (structurally or referentially).
   */
  override def equals(that: Any): Boolean = {
    if (canEqual(that)) {
      val other = that.asInstanceOf[AbstractCard]
      (this eq other) || (name == other.name)
    } else {
      false
    }
  }
}
