package cl.uchile.dcc
package gwent

import gwent.cards.*
import cl.uchile.dcc.gwent.cards.effects.unit.*
import cl.uchile.dcc.gwent.cards.effects.weather.*
import org.junit.Assert

class BoardTest extends munit.FunSuite {
  val sc1 = new SiegeCombatCard("Ballista", NoEffect(),
    "Does nothing c:", 6)
  val wc1 = new WeatherCard("Biting Frost", BitingFrost(),
    "Sets the strength of all Close Combat cards to 1 for both players.")
  val wc2 = new WeatherCard("Impenetrable Fog", ImpenetrableFog(),
    "Sets the strength of all Ranged Combat cards to 1 for both players.")

  val d1: List[Card] = List(wc1)
  val d2: List[Card] = List(sc1, wc2)

  val h1: List[Card] = List(sc1, wc2)
  val h2: List[Card] = List(wc1)
  val s: Section = new Section()

  val p1 = new Player("player1", _deck = d1, _hand = h1)
  val p2 = new Player("player2", _deck = d2, _hand = h2)
  val p3 = new Player("player3", _deck = d1, _hand = h1)
  val board = new Board(p1, p2)

  test("well defined board") {
    val e1 = Assert.assertThrows(classOf[InvalidNameException], () => new Board(p1, p1))
    assertEquals("the players must have different names.", e1.getMessage)

    assertEquals(board.player1, p1)
    assertEquals(board.player2, p2)
    assertEquals(board.weatherZone, List())
  }
  test("playCard") {
    val e2 = Assert.assertThrows(classOf[InvalidPlayerException], () => board.playCard(p3, sc1))
    assertEquals("the player must be on the board.", e2.getMessage)

    board.playCard(board.player1, sc1)
    assertEquals(p1.section.siegeCombatZone, List(sc1))
  }
  test("playWeatherCard") {
    board.playWeatherCard(wc1)
    assertEquals(board.weatherZone, List(wc1))
  }
}
