package cl.uchile.dcc
package gwent.controller

import gwent.Player
import gwent.cards.Card
import gwent.controller.states.StartState

class GameControllerTest extends munit.FunSuite {
  var gameC: GameController = _
  var p1: Player = _
  var p2: Player = _

  override def beforeEach(context: BeforeEach): Unit = {
    gameC = new GameController()
  }

  test("start state") {
    assert(gameC.isInStart())
  }
  test("setDeck") {
    gameC.startGame("p1", "p2")
    assertEquals(gameC.players.head.deck.length, 15)
  }
  test("changePlayer") {
    gameC.startGame("p1", "p2")
    val cPlayer: Player = gameC.currentPlayer.get
    val oPlayer: Player = gameC.otherPlayer.get
    gameC.changePlayer()
    assertEquals(gameC.otherPlayer.get, cPlayer)
    assertEquals(gameC.currentPlayer.get, oPlayer)
  }
  test("turn state") {
    gameC.startGame("p1", "p2")
    assert(gameC.isInTurn())
  }
  test("playCard") {
    gameC.startGame("p1", "p2")
    val player: Player = gameC.currentPlayer.get
    val i: Int = player.hand.length
    gameC.playCard(0)
    assertEquals(player.hand.length, i-1)
  }
  test("alone state") {
    gameC.startGame("p1", "p2")
    gameC.endTurn()
    assert(gameC.isInAlone())
  }
  test("endTurn") {
    gameC.startGame("p1", "p2")
    val player1: Player = gameC.currentPlayer.get
    val player2: Player = gameC.otherPlayer.get
    gameC.endTurn()
    assertEquals(gameC.currentPlayer.get, player2)
  }
}
