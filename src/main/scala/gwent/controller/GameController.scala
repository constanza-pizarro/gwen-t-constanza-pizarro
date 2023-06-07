package cl.uchile.dcc
package gwent.controller

import gwent.cards.*
import gwent.controller.states.*
import gwent.{Board, Player}
import scala.util.Random

class GameController {
  var board: Board
  var players: List[Player] = List()
  // Estado actual del juego
  var state: GameState = new StartState(this)
  val weatherCards: List[Card] = List(
    new WeatherCard("Biting Frost",
      "Sets the strength of all Close Combat cards to 1 for both players."),
    new WeatherCard("Impenetrable Fog",
      "Sets the strength of all Ranged Combat cards to 1 for both players."),
    new WeatherCard("Torrential Rain",
      "Sets the strength of all Siege Combat cards to 1 for both players."),
    new WeatherCard("Clear Weather",
      "Removes all Weather Card (Biting Frost, Impenetrable Fog and Torrential Rain) effects."))
  val unitCards: List[Card] = List(
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
  // agrega las 4 cartas de clima y después elige una random de unidad y la agrega. 21 veces
  def setDeck(uCards: List[Card], wCards: List[Card]): List[Card] = {
    val deck: List[Card] = List()
    for (i <- wCards) {
      deck :+ i
    }
    for (i <- 0 until 21) {
      deck :+ uCards(Random.nextInt(uCards.length))
    }
    deck
  }
  def startGame(): Unit = {
    println("Player 1 name?")
    val name1: String = scala.io.StdIn.readLine()
    println("Player 2 name?")
    val name2: String = scala.io.StdIn.readLine()
    val player1: Player = new Player(name1, _deck = setDeck(unitCards, weatherCards))
    val player2: Player = new Player(name2, _deck = setDeck(unitCards, weatherCards))
    board = new Board(player1, player2)
    players = List(player1, player2)
    state.startGame()
  }
}
