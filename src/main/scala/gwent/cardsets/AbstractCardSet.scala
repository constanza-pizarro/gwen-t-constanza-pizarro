package cl.uchile.dcc
package gwent.cardsets

import gwent.cards.ICard
import gwent.cardsets.ICardSet

import scala.collection.mutable

/** Represents a generic card in the game. Not to be instanced
 *
 * @param set A set of cards */
abstract class AbstractCardSet(val set: mutable.Set[ICard]) extends ICardSet with Equals {
  /** Compares a card set object with an object of any type.
   *
   * @param that object to compare.
   */
  override def equals(that: Any): Boolean = {
    if (canEqual(that)) {
      val other = that.asInstanceOf[AbstractCardSet]
      (this eq other) || (set == other.set)
    } else {
      false
    }
  }
}
