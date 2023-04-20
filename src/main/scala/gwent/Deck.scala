package cl.uchile.dcc
package gwent
/** A class that represents a card deck that each player has. */
class Deck {
  private val deck: Set[Card] = Set()
  /** Draw a card from the deck. */
  def drawCard(): Unit = {
    // elegir una carta random?
    // o inicializar el mazo, revolver y luego empezar a sacar
  }
}
