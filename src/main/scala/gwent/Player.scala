package cl.uchile.dcc
package gwent

import cards.Card

/** Class representing a player in the Gwen't game.
 *
 * Each player has a name, a section, a gem counter, a deck of cards and a hand of cards.
 * The gem counter, deck and hand are private variables, but can be accessed via their corresponding
 * getter methods.
 *
 * @constructor Create a new player with a name, section, gem counter, deck and hand.
 * @param name The name of the player.
 * @param _section The player's unit section.
 * @param _deck The initial list of cards in the player's deck.
 * @param _hand The initial list of cards in the player's hand.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author Constanza Pizarro
 */
class Player(val name: String, private var _section: Section = new Section(),
             private var _deck: List[Card]=List(), private var _hand: List[Card]=List()) {
  /** The current gems of the player.
   * Initially set to 2.
   */
  private var _gemCounter = 2
  /** Accessor method for the player's section */
  def section: Section = {
    val sCopy = _section
    sCopy
  }
  /** Accessor method for the player's gem counter */
  def gemCounter: Int = {
    val gcCopy = _gemCounter
    gcCopy
  }
  /** Subtract a gem from the player */
  def loseGem(): Unit = {
    require(gemCounter > 0, "the gemCounter must be non-negative.")
    _gemCounter -= 1
  }
  /** Accessor method for the player's deck */
  def deck: List[Card] = {
    val dCopy =_deck
    dCopy
  }
  /** Accessor method for the player's hand */
  def hand: List[Card] = {
    val hCopy = _hand
    hCopy
  }
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
  /** Plays the given card.
   *
   * Draws the card from the hand and puts it on its respective zone of the board.
   */
  def playCard(card: Card, board: Board): Unit = {
    require(_hand.contains(card), "the card must be on the player's hand.")
    _hand = _hand.filterNot(_ eq card)
    card.playCard(board, section)
  }
  override def equals(obj: Any): Boolean = obj match {
    case other: Player =>
      (this eq other) || (name == other.name
                      && _section == other._section
                      && _gemCounter == other._gemCounter
                      && _deck == other._deck
                      && _hand == other._hand)
    case _ => false
  }
}
