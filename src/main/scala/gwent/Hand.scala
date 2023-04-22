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
      card=Deck.drawCard
      this.deck+=card
    }
  }
}
