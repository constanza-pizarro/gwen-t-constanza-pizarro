package cl.uchile.dcc
package gwent.cards.effects

import gwent.{Board, Player}
import gwent.cards.*
import gwent.cards.effects.unit.*
import gwent.cards.effects.weather.*

import scala.collection.mutable.ListBuffer

class WeatherEffectTest extends munit.FunSuite {
  val mBoost = "Adds +1 to all units in the row (excluding itself)."
  val tBond = "When placed with the same card, doubles the strength of both (or more) cards"

  val cc1 = new CloseCombatCard("Blue Stripes Commando", TightBond(), tBond, 4)
  val cc2 = new CloseCombatCard("Blueboy Lugos", NoEffect(), "Has no effect.", 6)
  val cc3 = new CloseCombatCard("Blueboy Lugos", NoEffect(), "Has no effect.", 6)

  val rc1 = new RangedCombatCard("Albrich", NoEffect(), "Has no effect.", 2)
  val rc2 = new RangedCombatCard("Milva", MoraleBoost(), mBoost, 10)
  val rc3 = new RangedCombatCard("Cynthia", NoEffect(), "Has no effect.", 4)
  val rc4 = new RangedCombatCard("Milva", MoraleBoost(), mBoost, 10)

  val sc1 = new SiegeCombatCard("Ballista", NoEffect(), "Does nothing c:", 6)
  val sc2 = new SiegeCombatCard("Catapult", TightBond(), tBond, 8)
  val sc3 = new SiegeCombatCard("Catapult", TightBond(), tBond, 8)


  val wc1 = new WeatherCard("Biting Frost", BitingFrost(),
    "Sets the strength of all Close Combat cards to 1 for both players.")
  val wc2 = new WeatherCard("Impenetrable Fog", ImpenetrableFog(),
    "Sets the strength of all Ranged Combat cards to 1 for both players.")
  val wc3 = new WeatherCard("Torrential Rain", TorrentialRain(),
    "Sets the strength of all Siege Combat cards to 1 for both players.")
  val wc4 = new WeatherCard("Clear Weather", ClearWeather(),
    "Removes all Weather Card (Biting Frost, Impenetrable Fog and Torrential Rain) effects.")

  val h1: ListBuffer[Card] = ListBuffer(cc1, cc2, sc1, sc2, sc3, rc1, rc2, rc3, rc4, wc1, wc2)
  val h2: ListBuffer[Card] = ListBuffer(cc1, cc3, sc1, sc2, sc3, rc1, rc2, rc3, rc4, wc3, wc4)

  var p1: Player = _
  var p2: Player= _
  var board: Board = _

  override def beforeEach(context: BeforeEach): Unit = {
    p1 = new Player("player1", _hand = h1)
    p2 = new Player("player2", _hand = h2)
    board = new Board(p1, p2)
  }

  test("Biting Frost") {
    board.player1.playCard(cc1, board)
    board.player2.playCard(cc3, board)
    board.player1.playCard(cc2, board)
    val c1 = cc1.currentPower
    val c2 = cc3.currentPower
    board.player1.playCard(wc1, board)
    assertEquals(cc1.currentPower, 1)
    assertEquals(cc1.lastPower, c1)
    assertEquals(cc3.lastPower, c2)
  }
}
