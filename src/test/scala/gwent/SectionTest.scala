package cl.uchile.dcc
package gwent

import gwent.cards.*
import gwent.cards.effects.unit.*
import gwent.cards.effects.weather.*

class SectionTest extends munit.FunSuite {
  val cc1 = new CloseCombatCard("Blue Stripes Commando", TightBond(),
    "When placed with the same card, doubles the strength of both (or more) cards", 4)
  val cc2 = new CloseCombatCard("Blueboy Lugos", NoEffect(),
    "Has no effect.", 6)

  val rc1 = new RangedCombatCard("Albrich", NoEffect(),
    "Has no effect.", 2)
  val rc2 = new RangedCombatCard("Milva", MoraleBoost(),
    "Adds +1 to all units in the row (excluding itself).", 10)

  val sc1 = new SiegeCombatCard("Ballista", NoEffect(),
    "Does nothing c:", 6)
  val sc2 = new SiegeCombatCard("Catapult", TightBond(),
    "When placed with the same card, doubles the strength of both (or more) cards", 8)

  val wc1 = new WeatherCard("Biting Frost", BitingFrost(),
    "Sets the strength of all Close Combat cards to 1 for both players.")
  val wc2 = new WeatherCard("Impenetrable Fog", ImpenetrableFog(),
    "Sets the strength of all Ranged Combat cards to 1 for both players.")

  val d1: List[Card] = List(cc1, cc2, rc1, rc2, sc1, wc1)
  val d2: List[Card] = List(cc2, rc2, rc1, sc2, sc1, wc2)

  val h1: List[Card] = List(sc2, wc2)
  val h2: List[Card] = List(cc1, wc1, rc1)

  var board: Board = _
  val s1: Section = new Section()
  val s2: Section = new Section()

  val p1 = new Player("player1", s1, d1, h1)
  val p2 = new Player("player2", s2, d2, h2)
  board = new Board(p1, p2)
  board.playCard(p2, cc1)

  test("well defined section and board") {
    assertEquals(s1.closeCombatZone, List())
    assertEquals(s1.rangedCombatZone, List())
    assertEquals(s1.siegeCombatZone, List())

    assertEquals(p2.section.closeCombatZone, List(cc1))

    assertEquals(board.player1, p1)
    assertEquals(board.player2, p2)
    assertEquals(board.weatherZone, List())
  }

  test("equals") {
    assertEquals(s1, new Section())
    assert(!s1.equals(p1))
  }
}
