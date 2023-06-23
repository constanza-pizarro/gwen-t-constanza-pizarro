package cl.uchile.dcc
package gwent.cards.effects

import gwent.cards.*
import gwent.cards.effects.unit.*
import gwent.cards.effects.weather.*

import cl.uchile.dcc.gwent.{Board, Player, Section}

class UnitEffectTest extends munit.FunSuite {
  val mBoost = "Adds +1 to all units in the row (excluding itself)."
  val tBond = "When placed with the same card, doubles the strength of both (or more) cards"

  val cc1 = new CloseCombatCard("Blue Stripes Commando", TightBond(), tBond, 4)
  val cc2 = new CloseCombatCard("Blueboy Lugos", NoEffect(), "Has no effect.", 6)

  val rc1 = new RangedCombatCard("Albrich", NoEffect(), "Has no effect.", 2)
  val rc2 = new RangedCombatCard("Milva", MoraleBoost(), mBoost, 10)
  val rc3 = new RangedCombatCard("Cynthia", NoEffect(), "Has no effect.", 4)
  val rc4 = new RangedCombatCard("Milva", MoraleBoost(), mBoost, 10)

  val sc1 = new SiegeCombatCard("Ballista", NoEffect(), "Does nothing c:", 6)
  val sc2 = new SiegeCombatCard("Catapult", TightBond(), tBond, 8)
  val sc3 = new SiegeCombatCard("Catapult", TightBond(), tBond, 8)

  val d1: List[Card] = List(cc2, rc1, sc1)
  val d2: List[Card] = List(cc2, sc2, sc3)

  val h1: List[Card] = List(cc1, cc2, rc2, sc2, sc3)
  val h2: List[Card] = List(rc1, rc2, rc3, sc1, rc4)

  val p1 = new Player("player1", _deck = d1, _hand = h1)
  val p2 = new Player("player2", _deck = d2, _hand = h2)
  val board = new Board(p1, p2)

  test("morale boost") {
    board.playCard(p2, rc1)
    assertEquals(rc1.currentPower, rc1.power)
    board.playCard(p2, rc2)
    assertEquals(rc1.currentPower, rc1.power+1)
    board.playCard(p2, rc3)
    assertEquals(rc3.currentPower, rc3.power)
    board.playCard(p2, rc4) // le sube 1 a rc1, rc2 y rc3
    assertEquals(rc1.currentPower, rc1.power+2)
    assertEquals(rc2.currentPower, rc2.power+1)
    assertEquals(rc3.currentPower, rc3.power+1)
  }

  test("no effect") {
    board.playCard(p1, cc1)
    board.playCard(p1, cc2)
    assertEquals(cc1.currentPower, cc1.power)
    assertEquals(cc2.currentPower, cc2.power)
  }

  test("tight bond") {
    board.playCard(p1, sc2)
    assertEquals(sc2.currentPower, sc2.power)
    board.playCard(p1, sc3)
    assertEquals(sc2.currentPower, sc2.power*2)
    assertEquals(sc3.currentPower, sc3.power*2)
  }
}
