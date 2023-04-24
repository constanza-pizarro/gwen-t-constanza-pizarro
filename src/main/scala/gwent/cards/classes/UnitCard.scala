package cl.uchile.dcc
package gwent.cards.classes

import gwent.cards.AbstractCard
import java.util.Objects

/** A class representing a unit card from the game.
 *
 * This card is defined by its name, classification and strength.
 *
 * @param name The name of the unit card.
 * @param classification The classification of the unit card, it can be Close Combat, Ranged Combat or Siege Combat.
 * @param strength The strength of the unit card.
 *
 * @constructor Creates a new unit card with the specified name, classification and strength.
 *
 * @example
 * {{{
 * val unitCard = new UnitCard("Ballista", "Siege", 6)
 * }}}
 *
 * @author Constanza Pizarro
 */
class UnitCard(name: String, val classification: String, val strength: Int) extends AbstractCard(name) {
  //override def playCard: Unit = {}
  /** A method that verifies that this instance can equal an object by telling if they're an instance of the same class.
   *
   *  @param obj The value being probed for possible equality.
   *  @return true if this instance can possibly equal obj, otherwise false.
   */
  override def canEqual(obj: Any): Boolean = {
    obj.isInstanceOf[UnitCard]
  }
  /** Compares a unit card with an object of any type.
   *
   * @param obj object to compare with this instance.
   * @return true if the object and this instance are equal (structurally or referentially).
   */
  override def equals(obj: Any): Boolean = obj match {
    case other: UnitCard =>
      super.equals(other) && classification == other.classification
                          && strength == other.strength
    case _ => false
  }
  /** Returns a hash code value for the unit card.
   *  If two objects are equal according to the equals method, then calling the hashCode method on each of the two
   *  objects must produce the same integer result.
   *
   *  @return a hash code value for this unit card.
   */
  override def hashCode(): Int = {
    Objects.hash(classOf[UnitCard], name, classification, strength)
  }
  /** Generates a string expressing the statistics of the unit card.
   *
   * @return A string showing the attributes of the unit card.
   */
  override def toString: String = {
    s"UnitCard(name: $name, classification: $classification, strength: $strength)"
  }
}
