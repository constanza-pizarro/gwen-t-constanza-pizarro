package cl.uchile.dcc
package gwent

/** A class that represents a player from the game. */
class Player(private val name: String, private val boardSection: String, private val gemCount: Int,
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
}
