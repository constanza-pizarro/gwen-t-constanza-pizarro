package cl.uchile.dcc
package gwent.cards.classes

import gwent.Player

class CardTest extends munit.FunSuite { //cuerpo a cuerpo, distancia, asedio
  var card1 = new UnitCard("c1", "asedio",20)
  var card2 = new UnitCard("c2","cc",20)
  var card3 = new UnitCard("c1","asedio",20)

  var card5 = new WeatherCard("c5", "a")
  var card6 = new WeatherCard("c6", "a")
  var card7 = new WeatherCard("c5", "a")

  test("well defined cards") {
    assertEquals(card1.name, "c1")
    assertEquals(card1.classification, "asedio")
    assertEquals(card1.strength, 20)

    assertEquals(card5.name, "c5")
    assertEquals(card5.ability, "a")
  }

  test("unit equals") {
    assertEquals(card1, card1)
    assertEquals(card1, card3)
    assertEquals(card3, card1)
    assertEquals(card1, new UnitCard("c1", "asedio",20))
    assertEquals(new UnitCard("c1", "asedio",20), card1)

    assert(!card1.equals(card2))
    assert(!card2.equals(card1))
  }

  test("weather equals") {
    assertEquals(card5, card5)
    assertEquals(card5, card7)
    assertEquals(card7, card5)
    assertEquals(card5, new WeatherCard("c5", "a"))
    assertEquals(new WeatherCard("c5", "a"), card5)

    assert(!card5.equals(card6))
    assert(!card6.equals(card5))
  }

  test("hashCode") {
    assertEquals(card1.hashCode(), card1.hashCode())
    assertEquals(card1.hashCode(), card3.hashCode())
    assertEquals(card3.hashCode(), card1.hashCode())
    assertEquals(card3.hashCode(), new UnitCard("c1","asedio",20).hashCode())
    assertEquals(new UnitCard("c1","asedio",20).hashCode(), card3.hashCode())
    assert(card1.hashCode()!=card2.hashCode())

    assertEquals(card5.hashCode(), card5.hashCode())
    assertEquals(card5.hashCode(), card7.hashCode())
    assertEquals(card7.hashCode(), card5.hashCode())
    assertEquals(card5.hashCode(), new WeatherCard("c5", "a").hashCode())
    assertEquals(new WeatherCard("c5", "a").hashCode(),card5.hashCode())
    assert(card5.hashCode()!=card6.hashCode())

    assert(card1.hashCode()!=card5.hashCode())
    assert(card5.hashCode()!=card1.hashCode())
  }
}
