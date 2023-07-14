package cl.uchile.dcc
package gwent

import gwent.cards.*
import gwent.cards.effects.unit.*
import gwent.cards.effects.weather.*

import cl.uchile.dcc.gwent.exceptions.InvalidCardException
import org.junit.Assert

import scala.+:
import scala.collection.mutable.ListBuffer

class PlayerTest extends munit.FunSuite {
  val moraleBoost = "Adds +1 to all units in the row (excluding itself)."
  val tightBond = "When placed with the same card, doubles the strength of both (or more) cards"

  val ccCard1 = new CloseCombatCard("Blue Stripes Commando", TightBond(), tightBond, 4)
  val ccCard2 = new CloseCombatCard("Blueboy Lugos", NoEffect(), "Has no effect.", 6)

  val rcCard1 = new RangedCombatCard("Albrich", NoEffect(), "Has no effect.", 2)
  val rcCard2 = new RangedCombatCard("Milva", MoraleBoost(), moraleBoost, 10)

  val scCard1 = new SiegeCombatCard("Ballista", NoEffect(), "Does nothing c:", 6)
  val scCard2 = new SiegeCombatCard("Catapult", TightBond(), tightBond, 8)

  val wCard1 = new WeatherCard("Biting Frost", BitingFrost(),
    "Sets the strength of all Close Combat cards to 1 for both players.")
  val wCard2 = new WeatherCard("Impenetrable Fog", ImpenetrableFog(),
    "Sets the strength of all Ranged Combat cards to 1 for both players.")

  val deck2: List[Card] = List(ccCard2, rcCard2, scCard2, wCard2)
  val hand2: List[Card] = List(ccCard1, wCard1, rcCard1, scCard1)

  var player1: Player = _
  var player2: Player = _
  var board: Board = _
  var section1: Section = new Section

  override def beforeEach(context: BeforeEach): Unit = {
    player1 = new Player("player1")
    player2 = new Player("player2", deck2, List(ccCard1, wCard1, rcCard1, scCard1))
    board = new Board(player1, player2)
  }

  test("well defined player") {
    assertEquals(player1.name, "player1")
    assertEquals(player1.section, section1)
    assertEquals(player1.gemCounter, 2)
    player1.loseGem()
    assertEquals(player1.gemCounter,1)
    assertEquals(player1.deck, List())
    assertEquals(player2.deck, deck2)
    assertEquals(player1.hand, List())
    assertEquals(player2.hand, hand2)
    println(deck2)
  }

  test("well defined hand and deck") {
    player2.shuffleDeck()
    assertEquals(player2.deck.length, deck2.length)
    assertEquals(player2.hand, hand2)

    val d = player2.deck.tail
    player2.drawCard()
    assertEquals(player2.deck, d)
    assertEquals(player2.hand.length, hand2.length+1)
  }

  test("playCard") {
    val e = Assert.assertThrows(classOf[InvalidCardException], () => player1.playCard(scCard1, board))
    assertEquals("the card must be on the player's hand.", e.getMessage)
    player2.playCard(ccCard1, board)
    player2.playCard(wCard1, board)
    player2.playCard(rcCard1, board)
    player2.playCard(scCard1, board)
    assertEquals(player2.section.closeCombatZone, List(ccCard1))
    assertEquals(player2.section.rangedCombatZone, List(rcCard1))
    assertEquals(player2.section.siegeCombatZone, List(scCard1))
    assertEquals(board.weatherZone, List(wCard1))
  }

  test("sumPoints") {
    player2.playCard(ccCard1, board)
    player2.playCard(rcCard1, board)
    player2.playCard(scCard1, board)

    assertEquals(player2.sumPoints(),
      ccCard1.currentPower + rcCard1.currentPower + scCard1.currentPower)
  }

  test("equals") {
    assertEquals(player1, player1)
    assertEquals(player2, player2)
    assertEquals(player1, new Player("player1"))
    assertEquals(player2, new Player("player2", deck2, hand2))

    assert(!player1.equals(ccCard1))
  }
}
