package cl.uchile.dcc
package gwent.cardsets.classes

import gwent.cards.ICard
import gwent.cardsets.AbstractCardSet

import java.util.Objects
import scala.collection.mutable

/** A class representing a card hand.
 *
 * This hand is defined by a set of cards, each player of the game has a hand and it can contain both weather
 * and unit cards.
 *
 * @param set The mutable set of cards.
 *
 * @constructor Creates a new card hand with the specified set of cards.
 *
 * @example
 * {{{
 * val hand = new Hand(new UnitCard("Ballista", "Siege", 6),
 *                     new WeatherCard("Biting Frost", "Sets the strength of all Close Combat cards to 1 for both players."))
 * }}}
 *
 * @author Constanza Pizarro
 */
class Hand(set: mutable.Set[ICard] = mutable.Set()) extends AbstractCardSet(set) {
  /** Sets your card hand by adding the quantity of cards you choose from the deck.
   *
   * @param deck The deck you're getting the cards from.
   * @param quantity The quantity of card you draw from the deck.
   */
  def setHand(deck: Deck, quantity: Int): Unit = {
    for(i <- 0.until(quantity)) {
      val card=deck.drawCard()
      set+=card
    }
  }
  /** A method that verifies that this instance can equal an object by telling if they're an instance of the same class.
   *
   * @param obj The value being probed for possible equality.
   * @return true if this instance can possibly equal obj, otherwise false.
   */
  override def canEqual(obj: Any): Boolean = obj.isInstanceOf[Hand]
  /** Compares a hand with an object of any type.
   *
   * @param obj object to compare with this instance.
   * @return true if the object and this instance are equal (structurally or referentially).
   */
  override def equals(obj: Any): Boolean = obj match {
    case other: Hand =>
      super.equals(other)
    case _ => false
  }
  /** Returns a hash code value for the hand.
   * If two objects are equal according to the equals method, then calling the hashCode method on each of the two
   * objects must produce the same integer result.
   *
   * @return a hash code value for this hand.
   */
  override def hashCode(): Int = Objects.hash(classOf[Hand], set)
}
