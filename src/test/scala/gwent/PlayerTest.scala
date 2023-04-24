package cl.uchile.dcc
package gwent

import gwent.cards.classes.{UnitCard, WeatherCard}

import cl.uchile.dcc.gwent.cardsets.classes.{Deck, Hand}

import scala.collection.mutable

class PlayerTest extends munit.FunSuite {
  val card1 = new UnitCard("c1", "asedio", 20)
  val card2 = new UnitCard("c2", "cc", 20)
  var card3 = new UnitCard("c1", "asedio", 20)

  val card5 = new WeatherCard("c5", "a")
  val card6 = new WeatherCard("c6", "a")
  val card7 = new WeatherCard("c5", "a")

  var d1 = new Deck(mutable.Set(card1, card1, card5, card2, card3))
  var d2 = new Deck(mutable.Set(card5, card2, card1, card3, card1))
  var d3 = new Deck(mutable.Set(card5, card6, card1))
  var d4 = new Deck()

  val h1 = new Hand(mutable.Set(card1, card2))
  val h2 = new Hand(mutable.Set(card2, card1))
  val h3 = new Hand(mutable.Set(card6, card5))
  val h4 = new Hand()

  val p1 = new Player("player1",3, d1, h1)
  val p2 = new Player("player1",3, d1, h1)
  val p3 = new Player("player2",3, d2, h2)
  val p4 = new Player("player4",3, d2, h4)
  p4.setHand(1)

  test("well defined player") {
    assertEquals(p1.name, "player1")
    assertEquals(p1.gemCount, 3)
    assertEquals(p1.cardDeck, d1)
    assertEquals(p1.cardHand, h1)
  }

  test("equals") {
    assertEquals(p1.name, p2.name)
    assertEquals(p1.gemCount, p3.gemCount)
    assertEquals(p1.cardDeck, p2.cardDeck)

    assertEquals(p1, p1)
    assertEquals(p1, p2)
    assertEquals(p2, p1)
    assert(!p3.equals(p2))
    assert(!p2.equals(p3))
    assert(!p2.equals(h1))
    assert(!h1.equals(p1))
  }

  test("drawCard") {
    val s1 = p4.cardDeck.set.size
    val s2 = p4.cardHand.set.size
    p4.drawCard()
    assertEquals(p4.cardDeck.set.size, s1 - 1)
    assertEquals(p1.cardHand.set.size, s2 + 1)
  }
}
