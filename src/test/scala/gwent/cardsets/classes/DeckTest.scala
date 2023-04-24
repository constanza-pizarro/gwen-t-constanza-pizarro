package cl.uchile.dcc
package gwent.cardsets.classes

import scala.collection.mutable
import gwent.cards.classes.{UnitCard, WeatherCard}

class DeckTest extends munit.FunSuite {
  var card1 = new UnitCard("c1", "asedio", 20)
  var card2 = new UnitCard("c2", "cc", 20)
  var card3 = new UnitCard("c1", "asedio", 20)

  var card5 = new WeatherCard("c5", "a")
  var card6 = new WeatherCard("c6", "a")
  var card7 = new WeatherCard("c5", "a")

  var d1 = new Deck(mutable.Set(card1, card1, card5, card2, card3))
  var d2 = new Deck(mutable.Set(card5, card2, card1, card3, card1))
  var d3 = new Deck(mutable.Set(card5, card6, card1))
  var d4 = new Deck()

  override def beforeEach(context: BeforeEach): Unit = {
    d1 = new Deck(mutable.Set(card1, card1, card5, card2, card3))
    d2 = new Deck(mutable.Set(card5, card2, card1, card3, card1))
    d3 = new Deck(mutable.Set(card5, card6, card1))
    d4 = new Deck()
  }

  test("equals") {
    assertEquals(d1, d1)
    assertEquals(d2, d1)
    assertEquals(d1, d2)
    assertEquals(d1, new Deck(mutable.Set(card1, card1, card5, card2, card3)))
    assertEquals(new Deck(mutable.Set(card1, card1, card5, card2, card3)), d1)
    assert(!d1.equals(d3))
    assert(!d3.equals(d1))
    assert(!d1.equals(d4))
    assert(!d4.equals(d1))
    assert(!d1.equals(card1))
    assert(!card5.equals(d2))
  }

  test("equals after setDeck") {
    d1.setDeck()
    assertEquals(d1, d1)
    assertEquals(d2, d1)
    d2.setDeck()
    assertEquals(d1, d2)
    assertEquals(d1, new Deck(mutable.Set(card1, card1, card5, card2, card3)))
    assertEquals(new Deck(mutable.Set(card1, card1, card5, card2, card3)), d1)
    assert(!d1.equals(d3))
    d3.setDeck()
    assertEquals(d3, new Deck(mutable.Set(card5, card6, card1)))
    assert(!d1.equals(d4))
    assert(!d4.equals(d1))
  }

  test("drawCard") {
    assert(d1.set.contains(card1))
    val s1 = d1.set.size
    d1.drawCard()
    assertEquals(d1.set.size, s1-1)
    d1.drawCard()
  }

  test("hashCode") {
    assertEquals(d1.hashCode(), d1.hashCode())
    assertEquals(d1.hashCode(), d2.hashCode())
    assertEquals(d2.hashCode(), d1.hashCode())
    assertEquals(d2.hashCode(), new Deck(mutable.Set(card1, card1, card5, card2, card3)).hashCode())
    assertEquals(new Deck(mutable.Set(card1, card1, card5, card2, card3)).hashCode(), d2.hashCode())
  }
}
