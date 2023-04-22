package cl.uchile.dcc
package gwent
import scala.util.Random
import scala.collection.mutable.ListBuffer
/** A class that represents a card deck that each player has. */
class Deck() extends ICardList {
  private val deck: ListBuffer[ICard] = ListBuffer()
  /** Shuffles card deck.*/
  def setCardDeck(): Unit = {
    Random.shuffle(deck)
  }
  /** Draw a card from the deck.
   *
   * @return A card if the deck is not empty.
   */
  def drawCard(): ICard = {
    val card = deck.head
    deck-=card
    return card
    //falta el caso en que está vacío el deck
  }
}
