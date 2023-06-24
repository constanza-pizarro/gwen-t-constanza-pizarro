package cl.uchile.dcc
package gwent.controller

import gwent.Player
import gwent.cards.Card
import gwent.controller.states.*

import org.junit.Assert

class GameControllerTest extends munit.FunSuite {
  var gameC: GameController = _
  var gameC2: GameController = new GameController
  //var p1: Player = _
  //var p2: Player = _

  override def beforeEach(context: BeforeEach): Unit = {
    gameC = new GameController
    gameC.startGame("p1", "p2")
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
    val cPlayer: Player = gameC.currentPlayer.get
    val oPlayer: Player = gameC.otherPlayer.get
    gameC.changeTurn()
    assertEquals(gameC.otherPlayer.get, cPlayer)
    assertEquals(gameC.currentPlayer.get, oPlayer)
    val e = Assert.assertThrows(classOf[InvalidTransitionException], () => gameC.state.newRound())
    assertEquals(s"Cannot transition from TurnState to RoundState", e.getMessage)
  }

  test("turn state") {
    assert(gameC.isInTurn)
    assert(!gameC2.isInTurn)
    val e = Assert.assertThrows(classOf[InvalidTransitionException], () => gameC.state.playAgain())
    assertEquals(s"Cannot transition from TurnState to StartState", e.getMessage)
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
    val player: Player = gameC.currentPlayer.get
    val i: Int = player.hand.length
    val e = Assert.assertThrows(classOf[InvalidNumberException], () => gameC.playCard(i+1))
    assertEquals(s"The number must be less than $i.", e.getMessage)
    gameC.playCard(0)
    assertEquals(gameC.otherPlayer.get, player)
  }

  test("endTurn") {
    val player1: Player = gameC.currentPlayer.get
    val player2: Player = gameC.otherPlayer.get
    gameC.endTurn()
    assert(!gameC.isPlaying(player1))
    assertEquals(gameC.currentPlayer.get, player2)
  }

  test("count state") {
    val player: Player = gameC.currentPlayer.get
    gameC.endTurn()
    assert(gameC.isInAlone)
    val n: Int = gameC.currentPlayer.get.hand.length
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
  }
}
