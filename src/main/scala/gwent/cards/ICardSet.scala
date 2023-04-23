package cl.uchile.dcc
package gwent.cards

import scala.collection.mutable

trait ICardSet {
  val deck: mutable.Set[ICard]
}
