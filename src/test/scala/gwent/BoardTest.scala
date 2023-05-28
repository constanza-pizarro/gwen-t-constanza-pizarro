package cl.uchile.dcc
package gwent

import gwent.cards.*

class BoardTest extends munit.FunSuite {
  var p1: Player = _
  var p2: Player = _

  var board: Board = _

  val section1: Section = new Section(board)
  val section2: Section = new Section(board)

  override def beforeEach(context: BeforeEach): Unit = {
    p1 = new Player("player1", section1, 2)
    p2 = new Player("player2", section2, 2)
    board = new Board(p1, p2)
  }

  test("well defined board") {
    assertEquals(board.player1, p1)
    assertEquals(board.player2, p2)
    assertEquals(board.weatherZone, List())
  }
}
