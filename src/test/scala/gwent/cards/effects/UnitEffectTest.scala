package cl.uchile.dcc
package gwent.cards.effects

import gwent.cards.*
import gwent.cards.effects.unit.*
import gwent.cards.effects.weather.*

import cl.uchile.dcc.gwent.{Board, Player, Section}

class UnitEffectTest extends munit.FunSuite {
  val mBoost = "Adds +1 to all units in the row (excluding itself)."
  val tBond = "When placed with the same card, doubles the strength of both (or more) cards"

  val cc1: Card = new CloseCombatCard("Blue Stripes Commando", TightBond(), tBond, 4)
  val cc2: Card = new CloseCombatCard("Blueboy Lugos", NoEffect(), "Has no effect.", 6)

  val rc1: Card = new RangedCombatCard("Albrich", NoEffect(), "Has no effect.", 2)
  val rc2: Card = new RangedCombatCard("Milva", MoraleBoost(), mBoost, 10)

  val sc1: Card = new SiegeCombatCard("Ballista", NoEffect(), "Does nothing c:", 6)
  val sc2 = new SiegeCombatCard("Catapult", TightBond(), tBond, 8)
  val sc3 = new SiegeCombatCard("Catapult", TightBond(), tBond, 8)

  val wc1: Card = new WeatherCard("Biting Frost", BitingFrost(),
    "Sets the strength of all Close Combat cards to 1 for both players.")
  val wc2: Card = new WeatherCard("Impenetrable Fog", ImpenetrableFog(),
    "Sets the strength of all Ranged Combat cards to 1 for both players.")

  val d1: List[Card] = List(cc1, cc2, rc1, sc1, wc1)
  val d2: List[Card] = List(cc2, rc1, rc2, sc1, wc2)

  val h1: List[Card] = List(rc2, sc2, sc3, wc2)
  val h2: List[Card] = List(cc1, wc1, sc2)
  val s: Section = new Section()

  val p1 = new Player("player1", _deck = d1, _hand = h1)
  val p2 = new Player("player2", _deck = d2, _hand = h2)
  val p3 = new Player("player3", _deck = d1, _hand = h1)
  val board = new Board(p1, p2)

  test("tight bond") {
    board.playCard(p1, sc2)
    assertEquals(sc2.currentPower, sc2.power)
    board.playCard(p1, sc3)
    assertEquals(sc2.currentPower, sc2.power*2)
    assertEquals(sc3.currentPower, sc3.power*2)
  }
}
