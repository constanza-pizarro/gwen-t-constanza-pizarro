package cl.uchile.dcc
package gwent.cards

import cl.uchile.dcc.gwent.cardsets.AbstractCardSet
import java.util.Objects
import scala.collection.mutable
/** A class that represents a card hand that each player has. */
class Hand(set: mutable.Set[ICard] = mutable.Set()) extends AbstractCardSet(set) {
  /*
  def drawCard(): Unit = {
    //this.deck += cardDeck.drawCard
  }
  /** Set your 10-cards hand.
   *
   * @param deck The deck you're getting the cards from.
   */
  def setCardHand(): Unit = {
    for(i <- 0.until(10)) {
      //val card=deck.drawCard
      this.deck+=card
    }
  }*/
  //DOCUMENTAR!!
  override def canEqual(obj: Any): Boolean = obj.isInstanceOf[Hand]
  /** Compare a Hand object with an object of any type.
   *
   * @param obj object to compare.
   */
  override def equals(obj: Any): Boolean = obj match {
    case other: Hand =>
      super.equals(other)
    case _ => false
  }
  //DOCUMENTAR!!!
  override def hashCode(): Int = Objects.hash(classOf[Hand], set)
}
