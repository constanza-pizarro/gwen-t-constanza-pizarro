package cl.uchile.dcc
package gwent

import cards.Card

/** Class representing a player in the Gwen't game.
 *
 * Each player has a name, a gem counter, a deck of cards, and a hand of cards.
 * The deck and hand are private variables, but can be accessed via their corresponding
 * getter methods.
 *
 * @constructor Create a new player with a name, gem counter, deck, and hand.
 * @param name The name of the player.
 * @param gemCounter The initial gem count for the player.
 * @param _deck The initial list of cards in the player's deck.
 * @param _hand The initial list of cards in the player's hand.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author Constanza Pizarro
 */
class Player(val name: String, var gemCounter: Int, private var _deck: List[Card],
             private var _hand: List[Card], var closeCombatZone: List[Card]=List(),
             var rangedCombatZone: List[Card]=List(), var siegeCombatZone: List[Card]=List(),
             var board: Board) {
  require(gemCounter >= 0, "the gemCounter must be non-negative.")
  /** Accessor method for the player's deck */
  def deck: List[Card] = _deck
  /** Accessor method for the player's hand */
  def hand: List[Card] = _hand
  /** Draws a card from the deck and adds it to the hand.
   *
   * The top card from the deck is removed and added to the player's hand.
   *
   * @return The card that was drawn from the deck.
   */
  def drawCard(): Card = {
    val card = deck.head
    _deck = deck.tail
    _hand = card :: hand
    card
  }
  /** Shuffles the player's deck.
   *
   * The order of cards in the player's deck is randomized.
   */
  def shuffleDeck(): Unit = {
    _deck = scala.util.Random.shuffle(_deck)
  }
  override def equals(obj: Any): Boolean = obj match {
    case other: Player =>
      (this eq other) || (name == other.name
                      && gemCounter == other.gemCounter
                      && _deck == other._deck
                      && _hand == other._hand)
    case _ => false
  }
}
