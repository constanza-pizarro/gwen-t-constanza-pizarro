package cl.uchile.dcc
package gwent.controller

import gwent.{Board, Player}
import gwent.cards.*
import gwent.cards.effects.unit.*
import gwent.cards.effects.weather.*
import gwent.controller.states.*
import gwent.exceptions.{InvalidNumberException, InvalidTransitionException}

import org.junit.Assert

class GameControllerTest extends munit.FunSuite {
  var gameC1: GameController = _
  var gameC2: GameController = _

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
    gameC1 = new GameController
    gameC1.startGame("player1", "player2", unitCards, weatherCards)

    gameC2 = new GameController
  }

  test("changeTurn") {
    val cPlayer: Player = gameC1.currentPlayer
    val oPlayer: Player = gameC1.otherPlayer
    gameC1.changeTurn()
    assertEquals(gameC1.otherPlayer, cPlayer)
    assertEquals(gameC1.currentPlayer, oPlayer)
  }

  test("setDeck") {
    assert(gameC1.players.forall(_.deck.length == 15))
    assert(gameC1.players.forall(_.hand.length == 10))
  }

  test("start state") {
    assert(gameC2.isInStart)
    val e1 = Assert.assertThrows(classOf[InvalidTransitionException], () => gameC2.countPoints())
    assertEquals(e1.getMessage,s"Cannot transition from StartState to RoundState")
    val e2 = Assert.assertThrows(classOf[InvalidTransitionException], () => gameC2.endTurn())
    assertEquals(e2.getMessage,s"Cannot transition from StartState to AloneState or CountState")
    assert(!gameC1.isInStart)
  }

  test("startGame") {
    gameC2.startGame("player1", "player2", unitCards, weatherCards)
    assertEquals(gameC2.player1.name, "player1")
    assertEquals(gameC2.player2.name, "player2")
    assertEquals(gameC2.players.length, 2)
    assert(gameC2.players.contains(gameC2.player1))
    assert(gameC2.players.contains(gameC2.player2))
  }

  test("turn state") {
    assert(gameC1.isInTurn)
    assert(!gameC2.isInTurn)
    val e = Assert.assertThrows(classOf[InvalidTransitionException],
      () => gameC1.state.playAgain())
    assertEquals(e.getMessage,s"Cannot transition from TurnState to StartState")
  }

  test("playCard") {
    val player: Player = gameC1.currentPlayer
    val i: Int = player.hand.length
    val e = Assert.assertThrows(classOf[InvalidNumberException], () => gameC1.playCard(i))
    assertEquals(e.getMessage,s"The number must be less than $i.")
    gameC1.playCard(0)
    assertEquals(gameC1.otherPlayer, player)

    gameC1.endTurn()
    assertEquals(gameC1.currentPlayer, player)
    for (i <- player.hand.indices) {
      gameC1.playCard(0)
    }
    assert(gameC1.isInAlone)
    gameC1.playCard(0)
    assert(gameC1.isInCount)
    assertEquals(gameC1.currentPlayer, player)
  }

  test("endTurn") {
    val player1: Player = gameC1.currentPlayer
    val player2: Player = gameC1.otherPlayer
    gameC1.endTurn()
    assert(gameC1.isInAlone)
    assertEquals(gameC1.currentPlayer, player2)
    assertEquals(gameC1.otherPlayer, player1)
    gameC1.endTurn()
    assert(gameC1.isInCount)
    assertEquals(gameC1.currentPlayer, player2)
    assertEquals(gameC1.otherPlayer, player1)
  }

  test("alone state") {
    gameC1.endTurn()
    assert(gameC1.isInAlone)
    assert(!gameC2.isInAlone)
    val e1 = Assert.assertThrows(classOf[InvalidTransitionException], () => gameC1.state.startGame())
    assertEquals(e1.getMessage, s"Cannot transition from AloneState to TurnState")
    val e2 = Assert.assertThrows(classOf[InvalidTransitionException], () => gameC1.state.startRound())
    assertEquals(e2.getMessage,s"Cannot transition from AloneState to TurnState")
  }

  test("count state") {
    val player: Player = gameC1.currentPlayer
    gameC1.endTurn()
    assert(gameC1.isInAlone)
    val n: Int = gameC1.currentPlayer.hand.length
    for (i <- 0 until n+1) {
      gameC1.playCard(0)
    }
    assert(gameC1.isInCount)

    assert(!gameC2.isInCount)
    val e = Assert.assertThrows(classOf[InvalidTransitionException], () => gameC2.state.declareWinner())
    assertEquals(e.getMessage,s"Cannot transition from StartState to FinalState")
  }

  test("countPoints") {
    gameC1.endTurn()
    val player1: Player = gameC1.currentPlayer
    val player2: Player = gameC1.otherPlayer
    for (i <- player1.hand.indices) {
      gameC1.playCard(0)
    }
    gameC1.endTurn()
    gameC1.countPoints()
    assertEquals(player1.gemCounter, 2)
    assertEquals(player2.gemCounter, 1)
  }

  test("round state") {
    gameC1.endTurn()
    gameC1.endTurn()
    assert(gameC1.isInCount)
    gameC1.countPoints()
    assert(gameC1.isInRound)
    assert(!gameC2.isInRound)
  }

  test("A") {
    assert(gameC1.isInTurn)
    gameC1.endTurn()
    assert(gameC1.isInAlone)
    val player: Player = gameC1.currentPlayer
    for (i <- player.hand.indices) {
      gameC1.playCard(0)
    }
    gameC1.endTurn()
    assert(gameC1.isInCount)
    gameC1.countPoints()
    assert(gameC1.isInRound)

    gameC1.startRound(3, 3)
    for (p <- gameC1.players) {
      if (p eq player) {
        for (i <- p.hand.indices) {
          gameC1.playCard(0)
        }
        gameC1.endTurn()
      } else {
        gameC1.endTurn()
      }
    }
    gameC1.countPoints()
  }

  test("f") {
    gameC1.endTurn()
    gameC1.endTurn()
    gameC1.countPoints()
    gameC1.startRound(1, 1)
    gameC1.endTurn()
    gameC1.endTurn()
    gameC1.countPoints()
  }
  test("declareWinner") {
    gameC1.endTurn()
    gameC1.endTurn()
    assert(gameC1.isInCount)
    gameC1.countPoints()
    assert(gameC1.isInRound)
    assert(!gameC2.isInRound)
  }


  test("startRound") {
    gameC1.endTurn()
    gameC1.endTurn()
    gameC1.state.newRound()
    assert(gameC1.state.isInRound)
    gameC1.state.startRound()
    assert(gameC1.isInTurn)
  }
}
