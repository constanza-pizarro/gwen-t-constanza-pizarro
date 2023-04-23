package cl.uchile.dcc
package gwent

import scala.collection.mutable.ListBuffer
/** A class that represents a card hand that each player has. */
class Hand extends ICardList {
  val deck: ListBuffer[ICard] = ListBuffer()
  /** Set your card hand.
   *
   * @param deck the deck you're getting your cards from.
   * @param quantity the number of cards you want on your hand.
   */
  def setCardHand(deck: Deck, quantity: Int): Unit = {
    for(i <- 0.until(quantity)) {
      val card=deck.drawCard
      this.deck+=card
    }
  }
  /** Compare a Hand object with an object of any type.
   *
   * @param obj object to compare.
   */
  override def equals(obj: Any): Boolean = {
    if (obj.isInstanceOf[Hand]) {
      val other = obj.asInstanceOf[Hand]
      (this eq other) || (deck == other.deck)
    } else
      false
  }
}
