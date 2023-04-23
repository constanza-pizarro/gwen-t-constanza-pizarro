package cl.uchile.dcc
package gwent

import cl.uchile.dcc.gwent.cards.ICard
import cl.uchile.dcc.gwent.cardsets.classes.{Deck, Hand}

/** Represents a player from the game. */
class Player(private val name: String, private val gemCount: Int,
             val cardDeck: Deck, private val cardHand: Hand) {
  /** Set your card hand.
   *
   * @param quantity The number of cards in your hand
   */
  def setHand(quantity: Int): Unit = cardHand.setHand(cardDeck, quantity)
  /** Draw a card from your deck. */
  def drawCard(): Unit = cardHand.setHand(cardDeck, 1)
  /** Place a selected card from your hand on the board.
   *
   * param card the card you choose to put on the board.
   */
  //def playCard(card: Card): Unit = {
    // posicionarla en el tablero y "eliminarla" de la mano

  /** Compare a Player object with an object of any type.
   *
   * @param obj object to compare.
   */
  override def equals(obj: Any): Boolean = {
    if (obj.isInstanceOf[Player]) {
      val other = obj.asInstanceOf[Player]
      (this eq other) || (name == other.name &&
        gemCount == other.gemCount &&
        cardDeck == other.cardDeck &&
        cardHand == other.cardHand)
    } else
      false
  }
}
