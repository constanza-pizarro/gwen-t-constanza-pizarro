package cl.uchile.dcc
package gwent.cards.classes

import gwent.cards.AbstractCard
import java.util.Objects

/** A class representing a weather card from the game.
 *
 * This card is defined by its name and ability.
 *
 * @param name The name of the weather card.
 * @param ability The ability of the weather card.
 *
 * @constructor Creates a new weather card with the specified name and ability.
 *
 * @example
 * {{{
 * val weatherCard = new WeatherCard("Biting Frost", "Sets the strength of all Close Combat cards to 1 for both players.")
 * }}}
 *
 * @author Constanza Pizarro
 */
class WeatherCard(name: String, val ability: String) extends AbstractCard(name){
  //override def playCard(): Unit = {}
  /** A method that verifies that this instance can equal an object by telling if they're an instance of the same class.
   *
   * @param obj The value being probed for possible equality.
   * @return true if this instance can possibly equal obj, otherwise false.
   */
  override def canEqual(obj: Any): Boolean = {
    obj.isInstanceOf[WeatherCard]
  }
  /** Compares a weather card with an object of any type.
   *
   * @param obj object to compare with this instance.
   * @return true if the object and this instance are equal (structurally or referentially).
   */
  override def equals(obj: Any): Boolean = obj match {
    case other: WeatherCard =>
      super.equals(other)
    case _ => false
  }
  /** Returns a hash code value for the weather card.
   * If two objects are equal according to the equals method, then calling the hashCode method on each of the two
   * objects must produce the same integer result.
   *
   * @return a hash code value for this weather card.
   */
  override def hashCode(): Int = {
    Objects.hash(classOf[WeatherCard], name, ability)
  }
  /** Generates a string expressing the statistics of the weather card. */
  override def toString = s"WeatherCard(name: $name, ability: $ability)"
}