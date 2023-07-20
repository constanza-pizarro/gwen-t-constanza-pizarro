package cl.uchile.dcc
package gwent.cards

import gwent.cards.effects.unit.*
import gwent.cards.effects.weather.*

class CardTest extends munit.FunSuite {
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

  test("well defined cards") {
    assertEquals(ccCard1.name,"Blue Stripes Commando")
    assertEquals(ccCard1.effect, TightBond())
    assertEquals(ccCard1.description, tightBond)
    assertEquals(ccCard1.power,4)
    assertEquals(ccCard1.currentPower,4)

    assertEquals(rcCard1.name,"Albrich")
    assertEquals(rcCard1.effect, NoEffect())
    assertEquals(rcCard1.description,"Has no effect.")
    assertEquals(rcCard1.power,2)
    assertEquals(rcCard1.currentPower,2)

    assertEquals(scCard1.name,"Ballista")
    assertEquals(scCard1.description,"Does nothing c:")
    assertEquals(scCard1.power,6)
    assertEquals(scCard1.currentPower,6)

    assertEquals(wCard1.name,"Biting Frost")
    assertEquals(wCard1.effect, BitingFrost())
    assertEquals(wCard1.description,"Sets the strength of all Close Combat cards to 1 for both players.")
  }

  test("unit equals") {
    assertEquals(ccCard1, ccCard1)
    assertEquals(rcCard1, rcCard1)
    assertEquals(scCard1, scCard1)

    assertEquals(ccCard2, new CloseCombatCard("Blueboy Lugos", NoEffect(),
      "Has no effect.", 6))
    assertEquals(new CloseCombatCard("Blueboy Lugos", NoEffect(),
      "Has no effect.", 6), ccCard2)
    assertEquals(rcCard2, new RangedCombatCard("Milva", MoraleBoost(), moraleBoost, 10))
    assertEquals(new RangedCombatCard("Milva", MoraleBoost(), moraleBoost, 10), rcCard2)
    assertEquals(scCard2, new SiegeCombatCard("Catapult", TightBond(), tightBond, 8))
    assertEquals(new SiegeCombatCard("Catapult", TightBond(), tightBond, 8), scCard2)

    assert(!ccCard1.equals(ccCard2))
    assert(!ccCard2.equals(ccCard1))
    assert(!rcCard1.equals(rcCard2))
    assert(!rcCard2.equals(rcCard1))
    assert(!scCard1.equals(scCard2))
    assert(!scCard2.equals(scCard1))

    assert(!ccCard1.equals(wCard1))
    assert(!rcCard1.equals(wCard1))
    assert(!scCard1.equals(wCard1))
  }

  test("weather equals") {
    assertEquals(wCard1, wCard1)

    assertEquals(wCard2, new WeatherCard("Impenetrable Fog", ImpenetrableFog(),
      "Sets the strength of all Ranged Combat cards to 1 for both players."))
    assertEquals(new WeatherCard("Impenetrable Fog", ImpenetrableFog(),
      "Sets the strength of all Ranged Combat cards to 1 for both players."), wCard2)

    assert(!wCard1.equals(wCard2))
    assert(!wCard2.equals(wCard1))

    assert(!wCard1.equals(ccCard1))
    assert(!wCard2.equals(rcCard1))
  }

  test("copy") {
    assertEquals(ccCard1.copy(), ccCard1)
    assertEquals(rcCard1.copy(), rcCard1)
    assertEquals(scCard1.copy(), scCard1)
    assertEquals(wCard1.copy(), wCard1)
  }

  test("unit hash code") {
    assertEquals(ccCard1.hashCode(), ccCard1.hashCode())
    assertEquals(rcCard1.hashCode(), rcCard1.hashCode())
    assertEquals(scCard1.hashCode(), scCard1.hashCode())

    assertEquals(ccCard2.hashCode(), new CloseCombatCard("Blueboy Lugos", NoEffect(),
      "Has no effect.", 6).hashCode())
    assertEquals(new CloseCombatCard("Blueboy Lugos", NoEffect(),
      "Has no effect.", 6).hashCode(), ccCard2.hashCode())
    assertEquals(rcCard2.hashCode(), new RangedCombatCard("Milva", MoraleBoost(),
      moraleBoost, 10).hashCode())
    assertEquals(new RangedCombatCard("Milva", MoraleBoost(),
      moraleBoost, 10).hashCode(), rcCard2.hashCode())
    assertEquals(scCard2.hashCode(), new SiegeCombatCard("Catapult", TightBond(),
      tightBond, 8).hashCode())
    assertEquals(new SiegeCombatCard("Catapult", TightBond(),
      tightBond, 8).hashCode(), scCard2.hashCode())

    assert(ccCard1.hashCode()!=ccCard2.hashCode())
    assert(ccCard2.hashCode()!=ccCard1.hashCode())
    assert(rcCard1.hashCode()!=rcCard2.hashCode())
    assert(rcCard2.hashCode()!=rcCard1.hashCode())
    assert(scCard1.hashCode()!=scCard2.hashCode())
    assert(scCard2.hashCode()!=scCard1.hashCode())

    assert(ccCard1.hashCode()!=wCard1.hashCode())
    assert(rcCard1.hashCode()!=wCard1.hashCode())
    assert(scCard1.hashCode()!=wCard1.hashCode())
  }

  test("weather hash code") {
    assertEquals(wCard1.hashCode(), wCard1.hashCode())

    assertEquals(wCard2.hashCode(), new WeatherCard("Impenetrable Fog", ImpenetrableFog(),
      "Sets the strength of all Ranged Combat cards to 1 for both players.").hashCode())
    assertEquals(new WeatherCard("Impenetrable Fog", ImpenetrableFog(),
      "Sets the strength of all Ranged Combat cards to 1 for both players.").hashCode(), wCard2.hashCode())

    assert(wCard1.hashCode() != wCard2.hashCode())
    assert(wCard2.hashCode() != wCard1.hashCode())

    assert(wCard1.hashCode() != ccCard1.hashCode())
    assert(wCard2.hashCode() != rcCard1.hashCode())
  }
}
