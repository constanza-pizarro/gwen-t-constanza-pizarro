package cl.uchile.dcc
package gwent.cards

import gwent.cards.effects.unit.*
import gwent.cards.effects.weather.*

class CardTest extends munit.FunSuite {
  val cc1 = new CloseCombatCard("Blue Stripes Commando", TightBond(),
    "Adds +1 to all units in the row (excluding itself).", 4)
  val cc2 = new CloseCombatCard("Blueboy Lugos", NoEffect(),
                "Has no effect.", 6)

  val rc1 = new RangedCombatCard("Albrich", NoEffect(),
                "Has no effect.", 2)
  val rc2 = new RangedCombatCard("Milva", MoraleBoost(),
                "Morale boost", 10)

  val sc1 = new SiegeCombatCard("Ballista", NoEffect(),
                "Does nothing c:", 6)
  val sc2 = new SiegeCombatCard("Catapult", TightBond(),
                "Tight Bond", 8)

  val wc1 = new WeatherCard("Biting Frost", BitingFrost(),
                "Sets the strength of all Close Combat cards to 1 for both players.")
  val wc2 = new WeatherCard("Impenetrable Fog", ImpenetrableFog(),
                "Sets the strength of all Ranged Combat cards to 1 for both players.")

  test("well defined cards") {
    assertEquals(cc1.name,"Blue Stripes Commando")
    assertEquals(cc1.effect, TightBond())
    assertEquals(cc1.description,"Adds +1 to all units in the row (excluding itself).")
    assertEquals(cc1.power,4)
    assertEquals(cc1.currentPower,4)

    assertEquals(rc1.name,"Albrich")
    assertEquals(rc1.effect, NoEffect())
    assertEquals(rc1.description,"Has no effect.")
    assertEquals(rc1.power,2)
    assertEquals(rc1.currentPower,2)

    assertEquals(sc1.name,"Ballista")
    assertEquals(sc1.description,"Does nothing c:")
    assertEquals(sc1.power,6)
    assertEquals(sc1.currentPower,6)

    assertEquals(wc1.name,"Biting Frost")
    assertEquals(wc1.effect, BitingFrost())
    assertEquals(wc1.description,"Sets the strength of all Close Combat cards to 1 for both players.")
  }

  test("unit equals") {
    assertEquals(cc1, cc1)
    assertEquals(rc1, rc1)
    assertEquals(sc1, sc1)

    assertEquals(cc2, new CloseCombatCard("Blueboy Lugos", NoEffect(),
      "Has no effect.", 6))
    assertEquals(new CloseCombatCard("Blueboy Lugos", NoEffect(),
      "Has no effect.", 6), cc2)
    assertEquals(rc2, new RangedCombatCard("Milva", MoraleBoost(),
      "Morale boost", 10))
    assertEquals(new RangedCombatCard("Milva", MoraleBoost(),
      "Morale boost", 10), rc2)
    assertEquals(sc2, new SiegeCombatCard("Catapult", TightBond(),
      "Tight Bond", 8))
    assertEquals(new SiegeCombatCard("Catapult", TightBond(),
      "Tight Bond", 8), sc2)

    assert(!cc1.equals(cc2))
    assert(!cc2.equals(cc1))
    assert(!rc1.equals(rc2))
    assert(!rc2.equals(rc1))
    assert(!sc1.equals(sc2))
    assert(!sc2.equals(sc1))

    assert(!cc1.equals(wc1))
    assert(!rc1.equals(wc1))
    assert(!sc1.equals(wc1))
  }

  test("weather equals") {
    assertEquals(wc1, wc1)

    assertEquals(wc2, new WeatherCard("Impenetrable Fog",
      "Sets the strength of all Ranged Combat cards to 1 for both players."))
    assertEquals(new WeatherCard("Impenetrable Fog",
      "Sets the strength of all Ranged Combat cards to 1 for both players."), wc2)

    assert(!wc1.equals(wc2))
    assert(!wc2.equals(wc1))

    assert(!wc1.equals(cc1))
    assert(!wc2.equals(rc1))
  }

  test("unit hash code") {
    assertEquals(cc1.hashCode(), cc1.hashCode())
    assertEquals(rc1.hashCode(), rc1.hashCode())
    assertEquals(sc1.hashCode(), sc1.hashCode())

    assertEquals(cc2.hashCode(), new CloseCombatCard("Imlerith",
      "Hero: Not affected by any Special Cards or abilities.", 10).hashCode())
    assertEquals(new CloseCombatCard("Imlerith",
      "Hero: Not affected by any Special Cards or abilities.", 10).hashCode(), cc2.hashCode())
    assertEquals(rc2.hashCode(), new RangedCombatCard("Eithné",
      "Hero: Not affected by any Special Cards or abilities.", 10).hashCode())
    assertEquals(new RangedCombatCard("Eithné",
      "Hero: Not affected by any Special Cards or abilities.", 10).hashCode(), rc2.hashCode())
    assertEquals(sc2.hashCode(), new SiegeCombatCard("Morvran Voorhis",
      "Hero: Not affected by any Special Cards or abilities.", 10).hashCode())
    assertEquals(new SiegeCombatCard("Morvran Voorhis",
      "Hero: Not affected by any Special Cards or abilities.", 10).hashCode(), sc2.hashCode())

    assert(cc1.hashCode()!=cc2.hashCode())
    assert(cc2.hashCode()!=cc1.hashCode())
    assert(rc1.hashCode()!=rc2.hashCode())
    assert(rc2.hashCode()!=rc1.hashCode())
    assert(sc1.hashCode()!=sc2.hashCode())
    assert(sc2.hashCode()!=sc1.hashCode())

    assert(cc1.hashCode()!=wc1.hashCode())
    assert(rc1.hashCode()!=wc1.hashCode())
    assert(sc1.hashCode()!=wc1.hashCode())
  }

  test("weather hash code") {
    assertEquals(wc1.hashCode(), wc1.hashCode())

    assertEquals(wc2.hashCode(), new WeatherCard("Impenetrable Fog",
      "Sets the strength of all Ranged Combat cards to 1 for both players.").hashCode())
    assertEquals(new WeatherCard("Impenetrable Fog",
      "Sets the strength of all Ranged Combat cards to 1 for both players.").hashCode(), wc2.hashCode())

    assert(wc1.hashCode() != wc2.hashCode())
    assert(wc2.hashCode() != wc1.hashCode())

    assert(wc1.hashCode() != cc1.hashCode())
    assert(wc2.hashCode() != rc1.hashCode())
  }
}
