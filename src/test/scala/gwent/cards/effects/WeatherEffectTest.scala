package cl.uchile.dcc
package gwent.cards.effects

import gwent.{Board, Player}
import gwent.cards.*
import gwent.cards.effects.unit.*
import gwent.cards.effects.weather.*

import scala.collection.mutable.ListBuffer

class WeatherEffectTest extends munit.FunSuite {
  val moraleBoost = "Adds +1 to all units in the row (excluding itself)."
  val tightBond = "When placed with the same card, doubles the strength of both (or more) cards"

  val ccCard1 = new CloseCombatCard("Blue Stripes Commando", TightBond(), tightBond, 4)
  val ccCard2 = new CloseCombatCard("Blueboy Lugos", NoEffect(), "Has no effect.", 6)
  val ccCard3 = new CloseCombatCard("Blueboy Lugos", NoEffect(), "Has no effect.", 6)

  val rcCard1 = new RangedCombatCard("Albrich", NoEffect(), "Has no effect.", 2)
  val rcCard2 = new RangedCombatCard("Milva", MoraleBoost(), moraleBoost, 10)
  val rcCard3 = new RangedCombatCard("Cynthia", NoEffect(), "Has no effect.", 4)
  val rcCard4 = new RangedCombatCard("Milva", MoraleBoost(), moraleBoost, 10)

  val scCard1 = new SiegeCombatCard("Ballista", NoEffect(), "Has no effect.", 6)
  val scCard2 = new SiegeCombatCard("Catapult", TightBond(), tightBond, 8)
  val scCard3 = new SiegeCombatCard("Catapult", TightBond(), tightBond, 8)


  val wCard1 = new WeatherCard("Biting Frost", BitingFrost(),
    "Sets the strength of all Close Combat cards to 1 for both players.")
  val wCard2 = new WeatherCard("Impenetrable Fog", ImpenetrableFog(),
    "Sets the strength of all Ranged Combat cards to 1 for both players.")
  val wCard3 = new WeatherCard("Torrential Rain", TorrentialRain(),
    "Sets the strength of all Siege Combat cards to 1 for both players.")
  val wCard4 = new WeatherCard("Clear Weather", ClearWeather(),
    "Removes all Weather Card (Biting Frost, Impenetrable Fog and Torrential Rain) effects.")

  val hand1: List[Card] = List(ccCard1, ccCard2, rcCard1, rcCard2, scCard1, scCard2, wCard1, wCard2)
  val hand2: List[Card] = List(ccCard3, rcCard3, rcCard4, scCard1, scCard2, scCard3, wCard3, wCard4)
  var player1: Player = _
  var player2: Player= _
  var board: Board = _

  override def beforeEach(context: BeforeEach): Unit = {
    player1 = new Player("player1", _hand = hand1)
    player2 = new Player("player2", _hand = hand2)
    board = new Board(player1, player2)
  }

  test("Biting Frost") {
    board.playCard(player1, ccCard1)
    board.playCard(player2, ccCard3)
    board.playCard(player1, ccCard2)
    val c1 = ccCard1.currentPower
    val c2 = ccCard3.currentPower
    board.playCard(player1, wCard1)
    assertEquals(ccCard1.currentPower, 1)
    assertEquals(ccCard1.previousPower, c1)
    assertEquals(ccCard3.previousPower, c2)
  }

  test("Impenetrable Fog") {
    board.playCard(player1, rcCard1)
    board.playCard(player2, rcCard3)
    val c1 = rcCard1.currentPower
    val c2 = rcCard3.currentPower
    board.playCard(player1, wCard2)
    assertEquals(rcCard1.currentPower, 1)
    assertEquals(rcCard1.previousPower, c1)
    assertEquals(rcCard3.previousPower, c2)
  }

  test("Torrential Rain") {
    board.playCard(player1, scCard1)
    board.playCard(player2, scCard2)
    val c1 = scCard1.currentPower
    val c2 = scCard2.currentPower
    board.playCard(player2, wCard3)
    assertEquals(scCard1.currentPower, 1)
    assertEquals(scCard1.previousPower, c1)
    assertEquals(scCard2.previousPower, c2)
  }

  test("Clear Weather") {
    val c1 = ccCard2.currentPower
    val r1 = rcCard3.currentPower
    val s1 = scCard2.currentPower
    board.playCard(player1, ccCard2)
    board.playCard(player2, rcCard3)
    board.playCard(player1, scCard1)
    board.playCard(player2, scCard2)
    board.playCard(player1, wCard1)
    assertEquals(ccCard2.previousPower, c1)
    board.playCard(player2, wCard3)
    assertEquals(scCard2.previousPower, s1)
    board.playCard(player1, wCard2)
    assertEquals(rcCard3.previousPower, r1)

    board.playCard(player2, wCard4)
    assertEquals(ccCard2.currentPower, c1)
    assertEquals(scCard2.currentPower, s1)
    assertEquals(rcCard3.currentPower, r1)
  }

  test("undo") {
    board.playCard(player1, ccCard1)
    board.playCard(player2, ccCard3)
    board.playCard(player1, wCard1)

    board.playCard(player2, rcCard4)
    board.playCard(player1, rcCard1)
    board.playCard(player1, wCard2)

    board.playCard(player2, scCard2)
    board.playCard(player1, scCard1)
    board.playCard(player2, wCard3)

    board.playCard(player2, wCard4)

    assertEquals(ccCard1.currentPower, ccCard1.previousPower)
    assertEquals(rcCard4.currentPower, rcCard4.previousPower)
    assertEquals(scCard2.currentPower, scCard2.previousPower)
  }
}
