package cl.uchile.dcc
package gwent

import gwent.cards.*
import gwent.cards.effects.unit.*
import gwent.cards.effects.weather.*

import org.junit.Assert

import scala.+:
import scala.collection.mutable.ListBuffer

class PlayerTest extends munit.FunSuite {
  val mBoost = "Adds +1 to all units in the row (excluding itself)."
  val tBond = "When placed with the same card, doubles the strength of both (or more) cards"

  val cc1 = new CloseCombatCard("Blue Stripes Commando", TightBond(), tBond, 4)
  val cc2 = new CloseCombatCard("Blueboy Lugos", NoEffect(), "Has no effect.", 6)

  val rc1 = new RangedCombatCard("Albrich", NoEffect(), "Has no effect.", 2)
  val rc2 = new RangedCombatCard("Milva", MoraleBoost(), mBoost, 10)

  val sc1 = new SiegeCombatCard("Ballista", NoEffect(), "Does nothing c:", 6)
  val sc2 = new SiegeCombatCard("Catapult", TightBond(), tBond, 8)

  val wc1 = new WeatherCard("Biting Frost", BitingFrost(),
    "Sets the strength of all Close Combat cards to 1 for both players.")
  val wc2 = new WeatherCard("Impenetrable Fog", ImpenetrableFog(),
    "Sets the strength of all Ranged Combat cards to 1 for both players.")

  val d1: ListBuffer[Card] = ListBuffer(cc1, rc1, wc1)
  val d2: ListBuffer[Card] = ListBuffer(cc2, rc2, sc2, wc2)
  val h2: ListBuffer[Card] = ListBuffer(cc1, wc1, rc1, sc1)

  var p1: Player = _
  var p2: Player = _
  var board: Board = _
  var s1: Section = new Section

  override def beforeEach(context: BeforeEach): Unit = {
    p1 = new Player("player1")
    p2 = new Player("player2", d2, ListBuffer(cc1, wc1, rc1, sc1))
    board = new Board(p1, p2)
  }

  test("well defined player") {
    assertEquals(p1.name, "player1")
    assertEquals(p1.section, s1)
    assertEquals(p1.gemCounter, 2)
    p1.loseGem()
    assertEquals(p1.gemCounter,1)
    p1.loseGem()
    val e = Assert.assertThrows(classOf[IllegalArgumentException], () => p1.loseGem())
    assertEquals("requirement failed: the gemCounter must be non-negative.", e.getMessage)
    assertEquals(p1.deck, ListBuffer())
    assertEquals(p2.deck, d2)
    assertEquals(p1.hand, ListBuffer())
    assertEquals(p2.hand, h2)
    println(d2)
  }

  test("deck setter") {
    p1.deck_=(d1)
    assertEquals(p1.deck, ListBuffer[Card](cc1, rc1, wc1))
  }

  test("well defined hand and deck") {
    p2.shuffleDeck()
    assertEquals(p2.deck.length, d2.length)
    assertEquals(p2.hand, h2)

    val d = p2.deck.tail
    p2.drawCard()
    assertEquals(p2.deck, d)
    assertEquals(p2.hand.length, h2.length+1)
  }

  test("playCard") {
    val e = Assert.assertThrows(classOf[InvalidCardException], () => p1.playCard(sc1, board))
    assertEquals("the card must be on the player's hand.", e.getMessage)
    p2.playCard(cc1, board)
    p2.playCard(wc1, board)
    p2.playCard(rc1, board)
    p2.playCard(sc1, board)
    assertEquals(p2.section.closeCombatZone, List(cc1))
    assertEquals(p2.section.rangedCombatZone, List(rc1))
    assertEquals(p2.section.siegeCombatZone, List(sc1))
    assertEquals(board.weatherZone, List(wc1))
  }

  test("equals") {
    assertEquals(p1, p1)
    assertEquals(p2, p2)
    assertEquals(p1, new Player("player1"))
    assertEquals(p2, new Player("player2", d2, h2))

    assert(!p1.equals(cc1))
  }
}
