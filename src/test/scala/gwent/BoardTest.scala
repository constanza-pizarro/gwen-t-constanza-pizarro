package cl.uchile.dcc
package gwent

import gwent.cards.*
import gwent.cards.effects.unit.*
import gwent.cards.effects.weather.*

import cl.uchile.dcc.gwent.exceptions.{InvalidNameException, InvalidPlayerException}
import org.junit.Assert

class BoardTest extends munit.FunSuite {
  val scCard1 = new SiegeCombatCard("Ballista", NoEffect(),
    "Does nothing c:", 6)
  val wCard1 = new WeatherCard("Biting Frost", BitingFrost(),
    "Sets the strength of all Close Combat cards to 1 for both players.")
  val wCard2 = new WeatherCard("Impenetrable Fog", ImpenetrableFog(),
    "Sets the strength of all Ranged Combat cards to 1 for both players.")

  val deck1: List[Card] = List(wCard1)
  val deck2: List[Card] = List(scCard1, wCard2)

  val hand1: List[Card] = List(scCard1, wCard2)
  val hand2: List[Card] = List(wCard1)
  val section: Section = new Section

  val player1 = new Player("player1", deck1, hand1)
  val player2 = new Player("player2", deck2, hand2)
  val player3 = new Player("player3", deck1, hand1)
  val board = new Board(player1, player2)

  test("well defined board") {
    val e = Assert.assertThrows(classOf[InvalidNameException], () => new Board(player1, player1))
    assertEquals("the players must have different names.", e.getMessage)

    assertEquals(board.player1, player1)
    assertEquals(board.player2, player2)
    assertEquals(board.weatherZone, List())
  }
  test("playCard") {
    val e = Assert.assertThrows(classOf[InvalidPlayerException], () => board.playCard(player3, scCard1))
    assertEquals("the player must be on the board.", e.getMessage)

    board.playCard(board.player1, scCard1)
    assertEquals(player1.section.siegeCombatZone, List(scCard1))
  }
  test("playWeatherCard") {
    board.playWeatherCard(wCard1)
    assertEquals(board.weatherZone, List(wCard1))
  }
}
