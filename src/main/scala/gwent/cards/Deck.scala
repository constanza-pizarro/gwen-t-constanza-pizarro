package cl.uchile.dcc
package gwent.cards

import gwent.cards.ICard

import java.util.Objects
import scala.collection.mutable
import scala.util.Random

/** A class that represents a card deck that each player has. */
class Deck(set: mutable.Set[ICard] = mutable.Set()) extends AbstractCardSet(set) {
  /** Shuffles the card deck.*/
  def setCardDeck(): Unit = Random.shuffle(set)
  /** Draw a card from the deck.
   *
   * @return A card if the deck is not empty.
   */
  def drawCard: ICard = {
    val card: ICard = set.head
    set-=card
    return card
  }

  //DOCUMENTAR!!
  override def canEqual(obj: Any): Boolean = obj.isInstanceOf[Deck]

  /** Compare a Deck object with an object of any type.
   *
   * @param obj object to compare.
   */
  override def equals(obj: Any): Boolean = obj match {
    case other: Deck =>
      super.equals(other)
    case _ => false
  }

  //DOCUMENTAR!!!
  override def hashCode(): Int = Objects.hash(classOf[Deck], set)
}
