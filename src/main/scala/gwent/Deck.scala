package cl.uchile.dcc
package gwent
/** A class that represents a card deck that each player has. */
class Deck {
  private var deck: Set[Card] = Set()
  /** Draw a card from the deck.
   *
   * @return A card if the deck is not empty.
   */
  def drawCard(): Any = {
    if(deck.nonEmpty) {
      val card = deck.head
      deck=deck-card
      return card
    }
    // elegir una carta random?
    // o inicializar el mazo, revolver y luego empezar a sacar
  }
}
