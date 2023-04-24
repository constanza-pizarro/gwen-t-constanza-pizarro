package cl.uchile.dcc
package gwent.cardsets.classes

import gwent.cards.ICard
import gwent.cardsets.AbstractCardSet

import java.util.Objects
import scala.collection.mutable
import scala.util.Random

/** A class representing a card deck.
 *
 * This deck is defined by a set of cards, each player of the game has a deck and it can contain both weather
 * and unit cards.
 *
 * @param set The mutable set of cards.
 *
 * @constructor Creates a new card deck with the specified set of cards.
 *
 * @example
 * {{{
 * val deck = new Deck(new UnitCard("Ballista", "Siege", 6),
 *                     new WeatherCard("Biting Frost", "Sets the strength of all Close Combat cards to 1 for both players."))
 * }}}
 *
 * @author Constanza Pizarro
 */
class Deck(set: mutable.Set[ICard] = mutable.Set()) extends AbstractCardSet(set) {
  /** Shuffles the card deck, it should be used before starting a round.
   */
  def setDeck(): Unit = Random.shuffle(set)
  /** Draws a card from the deck.
   *
   * @return A card if the deck is not empty.
   */
  def drawCard(): ICard = {
    val card: ICard = set.head
    set-=card
    return card
  }
  /** A method that verifies that this instance can equal an object by telling if they're an instance of the same class.
   *
   * @param obj The value being probed for possible equality.
   * @return true if this instance can possibly equal obj, otherwise false.
   */
  override def canEqual(obj: Any): Boolean = obj.isInstanceOf[Deck]
  /** Compares a deck with an object of any type.
   *
   * @param obj object to compare with this instance.
   * @return true if the object and this instance are equal (structurally or referentially).
   */
  override def equals(obj: Any): Boolean = obj match {
    case other: Deck =>
      super.equals(other)
    case _ => false
  }
  /** Returns a hash code value for the deck.
   * If two objects are equal according to the equals method, then calling the hashCode method on each of the two
   * objects must produce the same integer result.
   *
   * @return a hash code value for this deck.
   */
  override def hashCode(): Int = Objects.hash(classOf[Deck], set)
}
