package cl.uchile.dcc
package gwent.controller

import gwent.cards.*
import gwent.cards.effects.weather.*
import gwent.cards.effects.unit.*
import gwent.controller.states.*
import gwent.{Board, Player, Section}

import cl.uchile.dcc.gwent.exceptions.InvalidNumberException
import cl.uchile.dcc.gwent.observer.{Observer, Subject}

import scala.util.Random

/** Class representing a game controller in the Gwen't game.
 *
 * This controller represents the possible actions that can be made
 * in an instance of the game, such as starting the game and initializing
 * the players and the board to be used, and more.
 *
 * @constructor Creates a new `GameController`
 *
 * @author Constanza Pizarro
 */
class GameController extends Observer[Player] {
  /** The current state of the game. */
  var state: GameState = new StartState(this)
  //var round: Int = 1
  /** The game's board. */
  private var _board: Option[Board] = None
  /** Player 1 of the game. */
  private var _player1: Option[Player] = None
  /** Player 2 of the game. */
  private var _player2: Option[Player] = None
  /** The current player of the game.
   * Represents the player in turn.
   */
  private var _currentPlayer: Option[Player] = _player1
  /** The other player of the game.
   * Represents the player waiting for their turn.
   */
  private var _otherPlayer: Option[Player] = _player2
  /** The winner of the game.
   * Represents the player who won the game.
   */
  private var _winner: Option[Player] = None
  /** The loser of the game.
   * Represents the player who lost the game.
   */
  private var _loser: Option[Player] = None
  /** The list of players. */
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
  /** Accessor method for player 2 */
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
  /** Exchanges the current player with the other player.
   *
   * Sets the current player to the other player and the other player to the current player.
   */
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
      val card: Card = uCards(Random.nextInt(uCards.length)).copy()
      deck = card :: deck
    }
    deck
  }
  def startGame(name1: String, name2: String,
                unitCards: List[Card], weatherCards: List[Card]): Unit = {
    state.startGame() // automáticamente tira exception si no esta en el estado correcto

    _player1 = Some(new Player(name1, setDeck(unitCards, weatherCards)))
    _player2 = Some(new Player(name2, setDeck(unitCards, weatherCards)))
    _board = Some(new Board(player1, player2))
    _players = board.players
    for (p <- players) {
      p.addObserver(this)
      p.shuffleDeck()
      for (i <- 0 until 10)
        p.drawCard()
    }
    _players = scala.util.Random.shuffle(players)
    _currentPlayer = Some(players.head)
    _otherPlayer = Some(players(1))
  }
  def startRound(player1n: Int, player2n: Int): Unit = {
    state.startRound()
    if (player1n > 3 || player2n > 3) {
      throw new InvalidNumberException("the number must be less than 3")
    }

    _board = Some(new Board(player1, player2))
    player1 section_= new Section
    player2 section_= new Section

    for (p <- players) p.shuffleDeck()

    var n1: Int = player1n
    var n2: Int = player2n

    if (player1.hand.length + player1n > 10)
      n1 -= (player1.hand.length + player1n) % 10
    if (player2.hand.length + player2n > 10)
      n2 -= (player2.hand.length + player2n) % 10

    for (i <- 0 until n1) player1.drawCard()
    for (i <- 0 until n2) player2.drawCard()
  }
  def playCard(c: Int): Unit = {
    val player: Player = currentPlayer
    val hand: List[Card] = player.hand
    if (hand.isEmpty) {
      endTurn()
    } else { // seguimos en el mismo estado, ya sea Turn o Alone
      if (c >= hand.length) {
        throw new InvalidNumberException(s"The number must be less than ${hand.length}.")
      }
      board.playCard(player, hand(c))
      changeTurn()
    }
  }
  def endTurn(): Unit = {
    _currentPlayer = Some(otherPlayer) // ambos son el mismo jugador ahora
    state.endTurn()
  }
  def countPoints(): Unit = {
    val player1Points: Int = player1.sumPoints()
    val player2Points: Int = player2.sumPoints()

    if (player1Points > player2Points) {
      player2.loseGem()
    } else if (player1Points < player2Points) {
      player1.loseGem()
    } else {
      player1.loseGem()
      player2.loseGem()
    }
    state.newRound()
  }
  override def update(observable: Subject[Player], value: Player): Unit = {
    _loser = Some(value)
    _winner = Some(players.filterNot(_ eq value).head)
    state.declareWinner()
    println(s"Player $observable has no gems left")
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