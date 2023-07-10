package cl.uchile.dcc
package gwent.cards.effects

import gwent.cards.*
import gwent.cards.effects.unit.*
import gwent.cards.effects.weather.*

import cl.uchile.dcc.gwent.*

import scala.collection.mutable.ListBuffer

class UnitEffectTest extends munit.FunSuite {
  val moraleBoost = "Adds +1 to all units in the row (excluding itself)."
  val tightBond = "When placed with the same card, doubles the strength of both (or more) cards"

  val ccCard1 = new CloseCombatCard("Blue Stripes Commando", TightBond(), tightBond, 4)
  val ccCard2 = new CloseCombatCard("Blueboy Lugos", NoEffect(), "Has no effect.", 6)

  val rcCard1 = new RangedCombatCard("Albrich", NoEffect(), "Has no effect.", 2)
  val rcCard2 = new RangedCombatCard("Milva", MoraleBoost(), moraleBoost, 10)
  val rcCard3 = new RangedCombatCard("Cynthia", NoEffect(), "Has no effect.", 4)
  val rcCard4 = new RangedCombatCard("Milva", MoraleBoost(), moraleBoost, 10)

  val scCard1 = new SiegeCombatCard("Ballista", NoEffect(), "Does nothing c:", 6)
  val scCard2 = new SiegeCombatCard("Catapult", TightBond(), tightBond, 8)
  val scCard3 = new SiegeCombatCard("Catapult", TightBond(), tightBond, 8)

  val deck1: List[Card] = List(ccCard2, rcCard1, scCard1)
  val deck2: List[Card] = List(ccCard2, scCard2, scCard3)

  val hand1: List[Card] = List(ccCard1, ccCard2, rcCard2, scCard2, scCard3)
  val hand2: List[Card] = List(rcCard1, rcCard2, rcCard3, scCard1, rcCard4)

  val player1 = new Player("player1", deck1, hand1)
  val player2 = new Player("player2", deck2, hand2)
  val board = new Board(player1, player2)

  test("morale boost") {
    board.playCard(player2, rcCard1)
    assertEquals(rcCard1.currentPower, rcCard1.power)
    board.playCard(player2, rcCard2)
    assertEquals(rcCard1.currentPower, rcCard1.power+1)
    board.playCard(player2, rcCard3)
    assertEquals(rcCard3.currentPower, rcCard3.power)
    MoraleBoost()(ccCard1, board)
    board.playCard(player2, rcCard4) // le sube 1 a rc1, rc2 y rc3
    assertEquals(rcCard1.currentPower, rcCard1.power+2)
    assertEquals(rcCard2.currentPower, rcCard2.power+1)
    assertEquals(rcCard3.currentPower, rcCard3.power+1)
  }

  test("no effect") {
    board.playCard(player1, ccCard1)
    board.playCard(player1, ccCard2)
    assertEquals(ccCard1.currentPower, ccCard1.power)
    assertEquals(ccCard2.currentPower, ccCard2.power)
  }

  test("tight bond") {
    board.playCard(player1, scCard2)
    assertEquals(scCard2.currentPower, scCard2.power)
    board.playCard(player1, scCard3)
    assertEquals(scCard2.currentPower, scCard2.power*2)
    assertEquals(scCard3.currentPower, scCard3.power*2)
  }
}
