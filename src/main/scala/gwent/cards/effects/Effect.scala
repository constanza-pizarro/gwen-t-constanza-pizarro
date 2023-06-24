package cl.uchile.dcc
package gwent.cards.effects

import gwent.cards.*
import gwent.Section

trait Effect {
  def apply(self: Card, target: List[UnitCard]): Unit
  def apply(self: Card, section1: Section, section2: Section): Unit
}
