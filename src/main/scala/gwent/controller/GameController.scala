package cl.uchile.dcc
package gwent.controller

import gwent.cards.*
import gwent.cards.effects.weather.*
import gwent.cards.effects.unit.*
import gwent.controller.states.*
import gwent.{Board, Player}

import scala.collection.mutable
import scala.collection.mutable.{ListBuffer, Map}
import scala.collection.mutable.Map
import scala.util.Random

class GameController {
  // Estado actual del juego
  var state: GameState = new StartState(this)
  var round: Int = 1
  private var _board: Option[Board] = None
  private val _isPlaying: mutable.Map[Player, Boolean] = mutable.Map()
  private var _currentPlayer: Option[Player] = None
  private var _otherPlayer: Option[Player] = None

  private val weatherCards = List[Card](
    new WeatherCard("Biting Frost", BitingFrost(),
      "Sets the strength of all Close Combat cards to 1 for both players."),
    new WeatherCard("Impenetrable Fog", ImpenetrableFog(),
      "Sets the strength of all Ranged Combat cards to 1 for both players."),
    new WeatherCard("Torrential Rain", TorrentialRain(),
      "Sets the strength of all Siege Combat cards to 1 for both players."),
    new WeatherCard("Clear Weather", ClearWeather(),
      "Removes all Weather Card (Biting Frost, Impenetrable Fog and Torrential Rain) effects."))
  private val unitCards = List[Card](
    new CloseCombatCard("Blue Stripes Commando", TightBond(),
      "When placed with the same card, doubles the strength of both (or more) cards", 4),
    new CloseCombatCard("Blueboy Lugos", NoEffect(),
            "Has no effect.", 6),
    new CloseCombatCard("Botchling", NoEffect(),
            "Has no effect.", 4),
    new CloseCombatCard("Bovine Defense Force", NoEffect(),
            "Has no effect.", 8),
    new CloseCombatCard("Clan Drummond Shield Maiden", TightBond(),
      "When placed with the same card, doubles the strength of both (or more) cards", 4),

    new RangedCombatCard("Albrich", NoEffect(),
            "Has no effect.", 2),
    new RangedCombatCard("Assire var Anahid", NoEffect(),
            "Has no effect.", 6),
    new RangedCombatCard("Crinfrid Reavers Dragon Hunter", TightBond(),
      "When placed with the same card, doubles the strength of both (or more) cards", 5),
    new RangedCombatCard("Cynthia", NoEffect(),
            "Has no effect.", 4),
    new RangedCombatCard("Milva", MoraleBoost(),
      "Adds +1 to all units in the row (excluding itself).", 10),

    new SiegeCombatCard("Ballista", NoEffect(),
            "Has no effect.",6),
    new SiegeCombatCard("Catapult", TightBond(),
      "When placed with the same card, doubles the strength of both (or more) cards", 8),
    new SiegeCombatCard("Fire Elemental", NoEffect(),
            "Has no effect.", 6),
    new SiegeCombatCard("Kaedweni Siege Expert", MoraleBoost(),
      "Adds +1 to all units in the row (excluding itself).", 1),
    new SiegeCombatCard("Siege Engineer", NoEffect(),
      "Has no effect.", 6))
  /** Accessor method for the board */
  def board: Option[Board] = {
    val b = _board
    b
  }
  /** Accessor method for the map of players and their status */
  def isPlaying: mutable.Map[Player, Boolean] = {
    val m = _isPlaying
    m
  }
  /** Accessor method for the list of players */
  def players: List[Player] = {
    val p = isPlaying.keys.toList
    p
  }
  /** Accessor method for the current player */
  def currentPlayer: Option[Player] = {
    val cPlayer = _currentPlayer
    cPlayer
  }
  /** Accessor method for the other player */
  def otherPlayer: Option[Player] = {
    val oPlayer = _otherPlayer
    oPlayer
  }
  /** Sets the current player to the other player and the other player to the current player. */
  def changeTurn(): Unit = {
    val cPlayer: Option[Player] = currentPlayer
    _currentPlayer = otherPlayer
    _otherPlayer = cPlayer
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
    wCards.foreach(i => deck = i::deck)
    for (i <- 0 until 21) {
      val card: Card = uCards(Random.nextInt(uCards.length))
      deck = card :: deck
    }
    deck
  }
  def startGame(name1: String, name2: String): Unit = {
    //println("Player 1 name?")
    //val name1: String = scala.io.StdIn.readLine()
    //println("Player 2 name?")
    //val name2: String = scala.io.StdIn.readLine()

    val player1: Player = new Player(name1)
    val player2: Player = new Player(name2)
    player1.deck_=(setDeck(unitCards, weatherCards))
    player2.deck_=(setDeck(unitCards, weatherCards))

    _board = Some(new Board(player1, player2))
    for (p <- board.get.players) {
      _isPlaying += (p -> true)
      p.shuffleDeck()
      for (i <- 0.until(10))
        p.drawCard()
    }

    scala.util.Random.shuffle(_isPlaying)
    _currentPlayer = Some(players.head)
    _otherPlayer = Some(players(1))
    state.startGame()
  }
  def playCard(c: Int): Unit = {
    val player: Player = currentPlayer.get
    val hand: ListBuffer[Card] = player.hand
    if (hand.isEmpty) {
      endTurn()
    } else {
      if (c > hand.length) {
        throw new InvalidNumberException(s"The number must be less than ${hand.length}.")
      }
      //println(s"${player.name}: Choose a card")
        //hand.indices.foreach(i => println(s"$i: ${hand(i)}"))
        //val c: Int = scala.io.StdIn.readInt()
      board.get.playCard(player, hand(c))
      if (isPlaying(otherPlayer.get)) changeTurn()
    }
  }
  def endTurn(): Unit = {
    _isPlaying.update(currentPlayer.get, false)
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
