package cl.uchile.dcc
package gwent.cardsets

import gwent.cards.ICard
import scala.collection.mutable

/** A class representing a generic card set in the game, not to be instanced.
 *
 * This card set is defined by its set of cards and it can contain both weather and unit cards.
 *
 * @param set The set of cards.
 *
 * @constructor Creates a new card set with the specified set of cards.
 *
 * @author Constanza Pizarro
 */
abstract class AbstractCardSet(val set: mutable.Set[ICard]) extends ICardSet with Equals {
  /** Compares a card set with an object of any type.
   *
   * @param that object to compare with this instance.
   * @return true if the object and this instance are equal (structurally or referentially).
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
