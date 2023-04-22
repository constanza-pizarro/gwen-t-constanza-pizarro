package cl.uchile.dcc
package gwent

/** A class that represents a player from the game. */
class Player(private val name: String, private val gemCount: Int,
             private val cardDeck: Deck, private val cardHand: Deck) {
  /** Set your card hand.
   *
   * @param quantity the number of cards you want on your hand.
   */
  def setCardHand(quantity: Int): Unit = {
    cardHand.setCardHand(quantity)
  }
  /** Place a selected card from your hand on the board.
   *
   * @param card the card you choose to put on the board.
   */
  def playCard(card: Card): Unit = {
    // posicionarla en el tablero y "eliminarla" de la mano
  }

  /** Draw a card from your deck. */
  def drawCard(): Unit = {
    cardDeck.drawCard()
  }

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
