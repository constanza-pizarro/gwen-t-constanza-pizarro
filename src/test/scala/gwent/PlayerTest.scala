package cl.uchile.dcc
package gwent

import gwent.cards.*

class PlayerTest extends munit.FunSuite {
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

  val d2: List[Card] = List(cc2, rc2, sc2, wc2)

  val h2: List[Card] = List(cc1, wc1, rc1, sc1)

  var p1: Player = _
  var p2: Player = _

  var board: Board = _

  var s1: Section = new Section()

  override def beforeEach(context: BeforeEach): Unit = {
    p1 = new Player("player1", s1)
    p2 = new Player("player2", s1, d2, h2)
    board = new Board(p1, p2)
  }

  test("well defined player, deck and hand") {
    assertEquals(p1.name, "player1")
    assertEquals(p1.section, s1)
    assertEquals(p1.gemCounter, 2)
    p1.loseGem()
    assertEquals(p1.gemCounter,1)
    assertEquals(p1.deck, List())
    assertEquals(p2.deck, d2)
    assertEquals(p1.hand, List())
    assertEquals(p2.hand, h2)

    p2.shuffleDeck()
    p2.deck == d2

    val d = p2.deck.tail
    val c1 = p2.drawCard()

    assertEquals(p2.deck, d)
    assertEquals(c1 :: h2, p2.hand)
    println(d2)
  }

  test("play card") {
    p2.playCard(cc1, board)
    p2.playCard(wc1, board)
    p2.playCard(rc1, board)
    p2.playCard(sc1, board)
  }

  test("equals") {
    assertEquals(p1, p1)
    assertEquals(p2, p2)
    assertEquals(p1, new Player("player1", s1))
    assertEquals(p2, new Player("player2", s1, d2, h2))

    assert(!p1.equals(cc1))
  }
}
