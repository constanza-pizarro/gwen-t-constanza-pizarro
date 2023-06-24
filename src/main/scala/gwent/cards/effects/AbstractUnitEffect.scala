package cl.uchile.dcc
package gwent.cards.effects

import gwent.cards.Card
import gwent.Section

abstract class AbstractUnitEffect extends Effect {
  override def apply(self: Card, section1: Section, section2: Section): Unit = {
    // do nothing c:
  }
}
