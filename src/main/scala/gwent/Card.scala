package cl.uchile.dcc
package gwent

class Card(private val name: String, private val classification: String) extends ICard {
  override def playCard(card: Card): Unit = {
  }

  override def equals(obj: Any): Boolean = {
    if (obj.isInstanceOf[Card]) {
      val other = obj.asInstanceOf[Card]
      (this eq other) || (name == other.name && classification == other.classification)
    } else
        false
  }
}
