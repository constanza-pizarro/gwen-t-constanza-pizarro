package cl.uchile.dcc
package gwent.cardsets.classes

import scala.collection.mutable
//import gwent.cards.ICard
import gwent.cards.classes.{UnitCard, WeatherCard}

class HandTest extends munit.FunSuite {
  val card1 = new UnitCard("c1", "asedio", 20)
  val card2 = new UnitCard("c2", "cc", 20)
  var card3 = new UnitCard("c1", "asedio", 20)

  val card5 = new WeatherCard("c5", "a")
  val card6 = new WeatherCard("c6", "a")
  val card7 = new WeatherCard("c5", "a")

  var d1 = new Deck(mutable.Set(card1, card1, card5, card2, card3))
  var d2 = new Deck(mutable.Set(card5, card2, card1, card3))
  var d3 = new Deck(mutable.Set(card5, card6, card7))

  val h1 = new Hand(mutable.Set(card1, card2))
  val h2 = new Hand(mutable.Set(card2, card1))
  val h3 = new Hand(mutable.Set(card6, card5))
  val h4 = new Hand()
  h4.setHand(d2,2)

  test("equals") {
    assertEquals(h1, h1)
    assertEquals(h1, new Hand(mutable.Set(card1, card2)))
    assertEquals(new Hand(mutable.Set(card1, card2)), h1)
    assertEquals(h1, h2)
    assert(!h1.equals(h3))
    assert(!h1.equals(card1))
    assert(!card1.equals(h4))
    assert(!card5.equals(h3))
  }

  test("hashCode") {
    assertEquals(h1.hashCode(), h1.hashCode())
    assertEquals(h1.hashCode(), new Hand(mutable.Set(card1, card2)).hashCode())
    assertEquals(new Hand(mutable.Set(card1, card2)).hashCode(), h1.hashCode())
    assertEquals(h1.hashCode(), h2.hashCode())
    assert(h1.hashCode()!=h3.hashCode())
    assert(h1.hashCode()!=card1.hashCode())
    assert(card1.hashCode()!=h4.hashCode())
    assert(card5.hashCode()!=h3.hashCode())
  }

}
