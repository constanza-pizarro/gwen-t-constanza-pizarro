package cl.uchile.dcc
package gwent.controller

import gwent.cards.*
import gwent.controller.states.*
import gwent.{Board, Player}
import scala.util.Random

class GameController {
  // Estado actual del juego
  var state: GameState = new StartState(this)
  var currentPlayer: Option[Player] = None
  var otherPlayer: Option[Player] = None
  private var board: Option[Board] = None
  var players: List[Player] = List()

  private val weatherCards: List[Card] = List(
    new WeatherCard("Biting Frost",
      "Sets the strength of all Close Combat cards to 1 for both players."),
    new WeatherCard("Impenetrable Fog",
      "Sets the strength of all Ranged Combat cards to 1 for both players."),
    new WeatherCard("Torrential Rain",
      "Sets the strength of all Siege Combat cards to 1 for both players."),
    new WeatherCard("Clear Weather",
      "Removes all Weather Card (Biting Frost, Impenetrable Fog and Torrential Rain) effects."))
  private val unitCards: List[Card] = List(
    new CloseCombatCard("Blue Stripes Commando", "Tight Bond", 4),
    new CloseCombatCard("Blueboy Lugos", "", 6),
    new CloseCombatCard("Botchling", "", 4),
    new CloseCombatCard("Bovine Defense Force", "", 8),
    new CloseCombatCard("Clan Drummond Shield Maiden", "Tight Bond", 4),

    new RangedCombatCard("Albrich", "", 2),
    new RangedCombatCard("Assire var Anahid", "", 6),
    new RangedCombatCard("Crinfrid Reavers Dragon Hunter", "Tight Bond", 5),
    new RangedCombatCard("Cynthia", "", 4),
    new RangedCombatCard("Milva", "Morale boost", 10),

    new SiegeCombatCard("Ballista", "", 6),
    new SiegeCombatCard("Catapult", "Tight Bond", 8),
    new SiegeCombatCard("Fire Elemental", "", 6),
    new SiegeCombatCard("Kaedweni Siege Expert", "Morale boost", 1),
    new SiegeCombatCard("Siege Engineer", "", 6))

  /** Sets the current player to the other player and the other player to the current player. */
  def changePlayer(): Unit = {
    val cPlayer: Option[Player] = currentPlayer
    currentPlayer = otherPlayer
    otherPlayer = cPlayer
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
    wCards.foreach(i=> deck = i :: deck)
    for (i <- 0 until 21) {
      deck = uCards(Random.nextInt(uCards.length)) :: deck
    }
    deck
  }
  def startGame(name1: String, name2: String): Unit = {
    //println("Player 1 name?")
    //val name1: String = scala.io.StdIn.readLine()
    //println("Player 2 name?")
    //val name2: String = scala.io.StdIn.readLine()
    val player1: Player = new Player(name1, _deck = setDeck(unitCards, weatherCards))
    val player2: Player = new Player(name2, _deck = setDeck(unitCards, weatherCards))
    board = Some(new Board(player1, player2))
    players = List(player1, player2)
    for (p <- players) {
      p.shuffleDeck()
      p.drawCard(10)
    }
    currentPlayer = Some(players(Random.nextInt(2)))
    if (currentPlayer.get == player1) {
      otherPlayer = Some(player2)
    } else {
      otherPlayer = Some(player1)
    }
    state.startGame()
  }
  def playCard(c: Int): Unit = {
    val player: Player = currentPlayer.get
    val hand: List[Card] = player.hand
    if (hand.isEmpty) {
      endTurn()
    } else {
      //println(s"${player.name}: Choose a card")
      //hand.indices.foreach(i => println(s"$i: ${hand(i)}"))
      //val c: Int = scala.io.StdIn.readInt()
      board.get.playCard(player, hand(c))
      changePlayer()
    }
  }
  def endTurn(): Unit = {
    changePlayer()
    state.endTurn()
  }
  def isInStart(): Boolean = {
    state.isInStart()
  }
  def isInTurn(): Boolean = {
    state.isInTurn()
  }
  def isInAlone(): Boolean = {
    state.isInAlone()
  }
}
