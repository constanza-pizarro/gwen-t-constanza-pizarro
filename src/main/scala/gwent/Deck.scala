package cl.uchile.dcc
package gwent
/** A class that represents a card deck that each player has. */
class Deck {
  private var deck: Set[Card] = Set()
  /** Draw a card from the deck.
   *
   * @return A card if the deck is not empty.
   */
  def drawCard(): Card = {
    val card = deck.head
    deck=deck-card
    return card
    //falta el caso en que está vacío el deck
  }

  /** Set your card hand.
   *
   * @param quantity the number of cards you want on your hand.
   */
  def setCardHand(quantity: Int): Unit = {
    for (w <- 0 to quantity)
      val card: Card = drawCard()
      deck=deck+card
  }
}
