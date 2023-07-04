package cl.uchile.dcc
package gwent

import gwent.cards.*
import gwent.cards.effects.unit.*
import gwent.cards.effects.weather.*

import scala.collection.mutable.ListBuffer

class SectionTest extends munit.FunSuite {
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

  val deck1: ListBuffer[Card] = ListBuffer(ccCard1, ccCard2, rcCard1, rcCard2, scCard1, wCard1)
  val deck2: ListBuffer[Card] = ListBuffer(ccCard2, rcCard2, rcCard1, scCard2, scCard1, wCard2)

  val hand1: ListBuffer[Card] = ListBuffer(scCard2, wCard2)
  val hand2: ListBuffer[Card] = ListBuffer(ccCard1, wCard1, rcCard1)

  var board: Board = _
  val section1: Section = new Section
  val section2: Section = new Section
  val section3: Section = new Section

  val player1 = new Player("player1", deck1, hand1)
  val player2 = new Player("player2", deck2, hand2)
  board = new Board(player1, player2)

  test("well defined section") {
    assertEquals(section1.closeCombatZone, List())
    assertEquals(section1.rangedCombatZone, List())
    assertEquals(section1.siegeCombatZone, List())
  }

  test("playCloseCombatCard") {
    section1.playCloseCombatCard(ccCard1)
    assertEquals(section1.closeCombatZone, List[UnitCard](ccCard1))
  }

  test("playRangedCombatCard") {
    section2.playRangedCombatCard(rcCard2)
    assertEquals(section2.rangedCombatZone, List(rcCard2))
  }

  test("equals") {
    assertEquals(section3, new Section)
    assert(!section2.equals(player1))
  }
}
