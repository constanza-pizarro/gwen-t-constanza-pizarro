package cl.uchile.dcc
package gwent.cards

//import cl.uchile.dcc.gwent.cards.*

class CardTest extends munit.FunSuite {
  val cc1 = new CloseCombatCard("Berserker",
                "Transforms into a bear when a Mardroeme card is on its row.", 4)
  val cc2 = new CloseCombatCard("Imlerith",
                "Hero: Not affected by any Special Cards or abilities.", 10)

  val rc1 = new RangedCombatCard("Clan Dimun Pirate",
                "Scorch: Kills the strongest card(s) on the battlefield.", 6)
  val rc2 = new RangedCombatCard("Eithné",
                "Hero: Not affected by any Special Cards or abilities.", 10)

  val sc1 = new SiegeCombatCard("Kaedweni Siege Expert",
                "Morale boost: Adds +1 to all units in the row (excluding itself).", 1)
  val sc2 = new SiegeCombatCard("Morvran Voorhis",
                "Hero: Not affected by any Special Cards or abilities.", 10)

  val wc1 = new WeatherCard("Skellige Storm",
                "Reduces the Strength of all Range and Siege Units to 1.")
  val wc2 = new WeatherCard("Impenetrable Fog",
                "Sets the strength of all Ranged Combat cards to 1 for both players.")

  test("well defined cards") {
    assertEquals(cc1.name,"Berserker")
    assertEquals(cc1.description,"Transforms into a bear when a Mardroeme card is on its row.")
    assertEquals(cc1.power,4)
    assertEquals(cc1.currentPower,4)

    assertEquals(rc1.name,"Clan Dimun Pirate")
    assertEquals(rc1.description,"Scorch: Kills the strongest card(s) on the battlefield.")
    assertEquals(rc1.power,6)
    assertEquals(rc1.currentPower,6)

    assertEquals(sc1.name,"Kaedweni Siege Expert")
    assertEquals(sc1.description,"Morale boost: Adds +1 to all units in the row (excluding itself).")
    assertEquals(sc1.power,1)
    assertEquals(sc1.currentPower,1)

    assertEquals(wc1.name,"Skellige Storm")
    assertEquals(wc1.description,"Reduces the Strength of all Range and Siege Units to 1.")
  }

  test("unit equals") {
    assertEquals(cc1, cc1)
    assertEquals(rc1, rc1)
    assertEquals(sc1, sc1)

    assertEquals(cc2, new CloseCombatCard("Imlerith",
      "Hero: Not affected by any Special Cards or abilities.", 10))
    assertEquals(new CloseCombatCard("Imlerith",
      "Hero: Not affected by any Special Cards or abilities.", 10), cc2)
    assertEquals(rc2, new RangedCombatCard("Eithné",
      "Hero: Not affected by any Special Cards or abilities.", 10))
    assertEquals(new RangedCombatCard("Eithné",
      "Hero: Not affected by any Special Cards or abilities.", 10), rc2)
    assertEquals(sc2, new SiegeCombatCard("Morvran Voorhis",
      "Hero: Not affected by any Special Cards or abilities.", 10))
    assertEquals(new SiegeCombatCard("Morvran Voorhis",
      "Hero: Not affected by any Special Cards or abilities.", 10), sc2)

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
