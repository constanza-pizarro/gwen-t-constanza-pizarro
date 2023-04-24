package cl.uchile.dcc
package gwent

import cl.uchile.dcc.gwent.cards.ICard
import cl.uchile.dcc.gwent.cardsets.classes.{Deck, Hand}

/** A class representing a player from the game.
 *
 * This player is defined by its name, gemCount, cardDeck and cardHand.
 *
 * @param name The identifier of a player.
 * @param gemCount The number of gems of a player.
 * @param cardDeck The card deck each player has.
 * @param cardHand The card hand each player has.
 *
 * @constructor Creates a new player with the specified name, gemCount, cardDeck and cardHand.
 *
 * @author Constanza Pizarro
 */
class Player(val name: String, val gemCount: Int, val cardDeck: Deck, val cardHand: Hand) {
  /** Sets your card hand by adding the quantity of cards you choose from the deck.
   *
   * @param quantity The quantity of cards you draw from the deck.
   */
  def setHand(quantity: Int): Unit = cardHand.setHand(cardDeck, quantity)
  /** Draws a card from your deck and puts it in your hand.
   */
  def drawCard(): Unit = cardHand.setHand(cardDeck, 1)
  /*
  def playCard(card: Card): Unit = {
    //posicionarla en el tablero y "eliminarla" de la mano
  }
  */
  /** Compares a player with an object of any type.
   *
   * @param obj object to compare with this instance.
   * @return true if the object and this instance are equal (structurally or referentially).
   */
  override def equals(obj: Any): Boolean = obj match {
    case other: Player =>
      (this eq other) || (name == other.name
                      && gemCount == other.gemCount
                      && cardDeck == other.cardDeck
                      && cardHand == other.cardHand)
    case _ => false
  }
}
