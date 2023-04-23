package cl.uchile.dcc
package gwent.cards.classes

import gwent.cards.AbstractCard
import java.util.Objects

/** Represents an unit card. */
class UnitCard(name: String, ability: Option[String] = null, val classification: String, val strength: Int) extends AbstractCard(name, ability) {
  //override def playCard: Unit = {}
  override def canEqual(obj: Any): Boolean = {
    obj.isInstanceOf[UnitCard]
  }
  /** Compare an UnitCard object with an object of any type.
   *
   * @param obj object to compare.
   */
  override def equals(obj: Any): Boolean = obj match {
    case other: UnitCard =>
      super.equals(other) && classification == other.classification
                          && strength == other.strength
    case _ => false
  }
  //DOCUMENTAR!!!
  override def hashCode(): Int = {
    Objects.hash(classOf[UnitCard], name, ability, classification, strength)
  }
}
