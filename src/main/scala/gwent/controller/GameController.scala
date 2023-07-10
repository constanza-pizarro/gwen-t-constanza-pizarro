package cl.uchile.dcc
package gwent.controller

import gwent.cards.*
import gwent.cards.effects.weather.*
import gwent.cards.effects.unit.*
import gwent.controller.states.*
import gwent.{Board, Player}

import scala.util.Random

class GameController {
  // Estado actual del juego
  var state: GameState = new StartState(this)
  //var round: Int = 1
  private var _board: Option[Board] = None
  private var _player1: Option[Player] = None
  private var _player2: Option[Player] = None
  private var _currentPlayer: Option[Player] = _player1
  private var _otherPlayer: Option[Player] = _player2
  private var _players: List[Player] = List()

  /** Accessor method for the board */
  def board: Board = {
    val b = _board.get
    b
  }
  /** Accessor method for player 1 */
  def player1: Player = {
    val p1 = _player1.get
    p1
  }
  def player2: Player = {
    val p2 = _player2.get
    p2
  }
  /** Accessor method for the list of players */
  def players: List[Player] = {
    val p = _players
    p
  }
  /** Accessor method for the current player */
  def currentPlayer: Player = {
    val cPlayer = _currentPlayer.get
    cPlayer
  }
  /** Accessor method for the other player */
  def otherPlayer: Player = {
    val oPlayer = _otherPlayer.get
    oPlayer
  }
  /** Sets the current player to the other player and the other player to the current player. */
  def changeTurn(): Unit = {
    val cPlayer: Player = currentPlayer
    _currentPlayer = _otherPlayer
    _otherPlayer = Some(cPlayer)
  }
  /** Sets a deck with 25 cards.
   *
   * Chooses 25 cards (4 weather cards and 21 unit cards) from a card list,
   * allowing unit cards to be repeated on the new deck.
   *
   * @param uCards a list of unit cards
   * @param wCards a list of weather cards
   * @return a deck of 25 cards
   */
  private def setDeck(uCards: List[Card], wCards: List[Card]): List[Card] = {
    var deck: List[Card] = List()
    wCards.foreach(i => deck = i :: deck)
    for (i <- 0 until 21) {
      val card: Card = uCards(Random.nextInt(uCards.length))
      deck = card :: deck
    }
    deck
  }
  def startGame(name1: String, name2: String,
                unitCards: List[Card], weatherCards: List[Card]): Unit = {
    state.startGame() // automáticamente tira exception si no esta en el estado correcto

    val player1: Player = new Player(name1, setDeck(unitCards, weatherCards))
    val player2: Player = new Player(name2, setDeck(unitCards, weatherCards))

    _board = Some(new Board(player1, player2))
    for (p <- board.players) {
      _isPlaying += (p -> true)
      p.shuffleDeck()
      for (i <- 0 until 10)
        p.drawCard()
    }

    _isPlaying = scala.util.Random.shuffle(isPlaying)
    _currentPlayer = Some(players.head)
    _otherPlayer = Some(players(1))
  }
  def playCard(c: Int): Unit = {
    val player: Player = currentPlayer
    if (isPlaying(player)) {
      val hand: List[Card] = player.hand
      if (hand.isEmpty) {
        endTurn()
      } else {
        if (c > hand.length) {
          throw new InvalidNumberException(s"The number must be less than ${hand.length}.")
        }
        board.playCard(player, hand(c))
        if (isPlaying(otherPlayer)) changeTurn()
      }
    }
  }
  def endTurn(): Unit = {
    _isPlaying += (currentPlayer -> false)
    state.endTurn()
  }
  def isInStart: Boolean = {
    state.isInStart
  }
  def isInTurn: Boolean = {
    state.isInTurn
  }
  def isInAlone: Boolean = {
    state.isInAlone
  }
  def isInCount: Boolean = {
    state.isInCount
  }
  /*
  def isInRound: Boolean = {
    state.isInRound
  }
  def isInFinal: Boolean = {
    state.isInFinal
  }*/
}