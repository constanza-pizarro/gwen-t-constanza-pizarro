package cl.uchile.dcc
package gwent.cards.effects

import gwent.cards.{Card, UnitCard}

trait UnitEffect extends Effect{
  //var targetSelf: Boolean
  def apply(self: Card, target: List[UnitCard]): Unit
}
