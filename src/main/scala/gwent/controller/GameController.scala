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
  private var _isPlaying: Map[Player, Boolean] = Map()
  private var _currentPlayer: Option[Player] = None
  private var _otherPlayer: Option[Player] = None

  /** Accessor method for the board */
  def board: Board = {
    val b = _board.get
    b
  }
  /** Accessor method for the map of players and their status */
  def isPlaying: Map[Player, Boolean] = {
    val m = _isPlaying
    m
  }
  /** Accessor method for the list of players */
  def players: List[Player] = {
    val p = isPlaying.keys.toList
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
    val player1: Player = new Player(name1, setDeck(unitCards, weatherCards))
    val player2: Player = new Player(name2, setDeck(unitCards, weatherCards))

    _board = Some(new Board(player1, player2))
    for (p <- board.players) {
      _isPlaying += (p -> true)
      p.shuffleDeck()
      for (i <- 0.until(10))
        p.drawCard()
    }

    _isPlaying = scala.util.Random.shuffle(isPlaying)
    _currentPlayer = Some(players.head)
    _otherPlayer = Some(players(1))
    state.startGame()
  }
  def playCard(c: Int): Unit = {
    val player: Player = currentPlayer
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
