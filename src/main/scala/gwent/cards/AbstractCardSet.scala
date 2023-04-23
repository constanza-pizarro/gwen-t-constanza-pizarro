package cl.uchile.dcc
package gwent.cards

import scala.collection.mutable

abstract class AbstractCardSet(val deck: mutable.Set[ICard]) extends ICardSet with Equals {
  /** Compares a card set object with an object of any type.
   *
   * @param that object to compare.
   */
  override def equals(that: Any): Boolean = {
    if (canEqual(that)) {
      val other = that.asInstanceOf[AbstractCardSet]
      (this eq other) || (deck == other.deck)
    } else {
      false
    }
  }  
}
