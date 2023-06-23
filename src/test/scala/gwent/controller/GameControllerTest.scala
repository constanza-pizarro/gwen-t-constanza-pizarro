package cl.uchile.dcc
package gwent.controller

import gwent.Player
import gwent.cards.Card
import gwent.controller.states.StartState

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
  }

  test("setDeck") {
    assertEquals(gameC.players.head.deck.length, 15)
  }

  test("changePlayer") {
    val cPlayer: Player = gameC.currentPlayer.get
    val oPlayer: Player = gameC.otherPlayer.get
    gameC.changePlayer()
    assertEquals(gameC.otherPlayer.get, cPlayer)
    assertEquals(gameC.currentPlayer.get, oPlayer)
  }

  test("turn state") {
    assert(gameC.isInTurn)
  }

  test("playCard") {
    val player: Player = gameC.currentPlayer.get
    val i: Int = player.hand.length
    val e = Assert.assertThrows(classOf[InvalidNumberException], () => gameC.playCard(i+1))
    assertEquals(s"The number must be less than $i.", e.getMessage)
    gameC.playCard(0)
    assertEquals(player.hand.length, i-1)
    gameC.endTurn()
    assert(gameC.isInAlone)
    val n: Int = gameC.currentPlayer.get.hand.length
    for (i <- 0 until n+1)
      gameC.playCard(0)
    assert(gameC.isInCount)
  }
  test("alone state") {
    gameC.endTurn()
    assert(gameC.isInAlone)
  }
  /*test("endTurn") {
    gameC.startGame("p1", "p2")
    val player1: Player = gameC.currentPlayer.get
    val player2: Player = gameC.otherPlayer.get
    gameC.endTurn()
    assertEquals(gameC.currentPlayer.get, player2)
  }*/
}
