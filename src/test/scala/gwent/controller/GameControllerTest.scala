package cl.uchile.dcc
package gwent.controller

import gwent.Player
import gwent.cards.*
import gwent.cards.effects.unit.*
import gwent.cards.effects.weather.*
import gwent.controller.states.*

import org.junit.Assert

class GameControllerTest extends munit.FunSuite {
  var gameC: GameController = _
  var gameC2: GameController = new GameController

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
      "Has no effect.", 6),
    new SiegeCombatCard("Catapult", TightBond(),
      "When placed with the same card, doubles the strength of both (or more) cards", 8),
    new SiegeCombatCard("Fire Elemental", NoEffect(),
      "Has no effect.", 6),
    new SiegeCombatCard("Kaedweni Siege Expert", MoraleBoost(),
      "Adds +1 to all units in the row (excluding itself).", 1),
    new SiegeCombatCard("Siege Engineer", NoEffect(),
      "Has no effect.", 6))
  private val weatherCards = List[Card](
    new WeatherCard("Biting Frost", BitingFrost(),
      "Sets the strength of all Close Combat cards to 1 for both players."),
    new WeatherCard("Impenetrable Fog", ImpenetrableFog(),
      "Sets the strength of all Ranged Combat cards to 1 for both players."),
    new WeatherCard("Torrential Rain", TorrentialRain(),
      "Sets the strength of all Siege Combat cards to 1 for both players."),
    new WeatherCard("Clear Weather", ClearWeather(),
      "Removes all Weather Card (Biting Frost, Impenetrable Fog and Torrential Rain) effects."))
  override def beforeEach(context: BeforeEach): Unit = {
    gameC = new GameController
    gameC.startGame("p1", "p2", unitCards, weatherCards)
  }

  test("start state") {
    assert(gameC2.isInStart)
    val e1 = Assert.assertThrows(classOf[InvalidTransitionException], () => gameC2.state.newRound())
    assertEquals(s"Cannot transition from StartState to RoundState", e1.getMessage)
    val e2 = Assert.assertThrows(classOf[InvalidTransitionException], () => gameC2.state.endTurn())
    assertEquals(s"Cannot transition from StartState to AloneState or CountState", e2.getMessage)
    assert(!gameC.isInStart)
  }

  test("setDeck") {
    assertEquals(gameC.players.head.deck.length, 15)
  }

  test("changeTurn") {
    val cPlayer: Player = gameC.currentPlayer
    val oPlayer: Player = gameC.otherPlayer
    gameC.changeTurn()
    assertEquals(gameC.otherPlayer, cPlayer)
    assertEquals(gameC.currentPlayer, oPlayer)
    val e = Assert.assertThrows(classOf[InvalidTransitionException], () => gameC.state.newRound())
    assertEquals(s"Cannot transition from TurnState to RoundState", e.getMessage)
  }

  test("turn state") {
    assert(gameC.isInTurn)
    assert(!gameC2.isInTurn)
    val e = Assert.assertThrows(classOf[InvalidTransitionException], () => gameC.state.playAgain())
    assertEquals(s"Cannot transition from TurnState to StartState", e.getMessage)
    assert(!gameC.state.isInRound)
  }

  test("alone state") {
    gameC.endTurn()
    assert(gameC.isInAlone)
    assert(!gameC2.isInAlone)
    val e1 = Assert.assertThrows(classOf[InvalidTransitionException], () => gameC.state.startGame())
    assertEquals(s"Cannot transition from AloneState to TurnState", e1.getMessage)
    val e2 = Assert.assertThrows(classOf[InvalidTransitionException], () => gameC.state.startRound())
    assertEquals(s"Cannot transition from AloneState to TurnState", e2.getMessage)
  }

  test("playCard") {
    val player: Player = gameC.currentPlayer
    val i: Int = player.hand.length
    val e = Assert.assertThrows(classOf[InvalidNumberException], () => gameC.playCard(i))
    assertEquals(s"The number must be less than $i.", e.getMessage)
    gameC.playCard(0)
    assertEquals(gameC.otherPlayer, player)
  }

  test("endTurn") {
    val player1: Player = gameC.currentPlayer
    val player2: Player = gameC.otherPlayer
    gameC.endTurn()
    assertEquals(gameC.currentPlayer, player2)
    assertEquals(gameC.otherPlayer, player1)
  }

  test("count state") {
    val player: Player = gameC.currentPlayer
    gameC.endTurn()
    assert(gameC.isInAlone)
    val n: Int = gameC.currentPlayer.hand.length
    for (i <- 0 until n + 1)
      gameC.playCard(0)
    assert(gameC.isInCount)
    assert(!gameC2.isInCount)
    val e = Assert.assertThrows(classOf[InvalidTransitionException], () => gameC2.state.declareWinner())
    assertEquals(s"Cannot transition from StartState to FinalState", e.getMessage)
  }

  test("declareWinner") {
    gameC.endTurn()
    gameC.endTurn()
    assert(gameC.isInCount)
    gameC.state.declareWinner()
    assert(gameC.state.isInFinal)
    assert(!gameC2.state.isInFinal)
  }

  test("round state") {
    gameC.endTurn()
    gameC.endTurn()
    assert(gameC.isInCount)
    gameC.state.newRound()
    assert(gameC.state.isInRound)
    assert(!gameC2.state.isInRound)
  }

  test("startRound") {
    gameC.endTurn()
    gameC.endTurn()
    gameC.state.newRound()
    assert(gameC.state.isInRound)
    gameC.state.startRound()
    assert(gameC.isInTurn)
  }
}
