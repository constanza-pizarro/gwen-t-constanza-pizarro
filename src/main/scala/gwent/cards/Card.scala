package cl.uchile.dcc
package gwent.cards

import gwent.ICard

class Card(private val name: String, private val classification: String) extends ICard {
  override def playCard(card: Card): Unit = {
  }

  /** Compare a Card object with an object of any type.
   *
   * @param obj object to compare.
   */
  override def equals(obj: Any): Boolean = {
    if (obj.isInstanceOf[Card]) {
      val other = obj.asInstanceOf[Card]
      (this eq other) || (name == other.name && classification == other.classification)
    } else
        false
  }
}
