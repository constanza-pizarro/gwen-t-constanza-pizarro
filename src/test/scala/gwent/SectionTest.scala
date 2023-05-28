package cl.uchile.dcc
package gwent

import gwent.cards.*

class SectionTest extends munit.FunSuite {
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

  val d1: List[Card] = List(cc1, cc2, rc1, rc2, sc1, wc1)
  val d2: List[Card] = List(cc2, rc2, rc1, sc2, sc1, wc2)

  val h1: List[Card] = List(sc2, wc2)
  val h2: List[Card] = List(cc1, wc1, rc1)

  var board: Board = _

  val s1: Section = new Section(board)
  val s2: Section = new Section(board)

  val p1 = new Player("player1", s1, 2, d1, h1)
  val p2 = new Player("player2", s2, 2, d2, h2)
  board = new Board(p1, p2)
  p2.playCard(cc1)

  test("well defined section and board") {
    assertEquals(s1.board, board)
    assertEquals(s1.closeCombatZone, List())
    assertEquals(s1.rangedCombatZone, List())
    assertEquals(s1.siegeCombatZone, List())

    assertEquals(p2.section.closeCombatZone, List(cc1))

    assertEquals(board.player1, p1)
    assertEquals(board.player2, p2)
    assertEquals(board.weatherZone, List())
  }
}
