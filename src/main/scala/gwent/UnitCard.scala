package cl.uchile.dcc
package gwent
/** A class that represents an unit card. */
class UnitCard(val cardName:String, val cardClass: String, val cardAbility: String, val cardStrength: Int) extends Card{
  override def equals(other: Any): Boolean = {
    other.isInstanceOf[UnitCard] &&
      cardName == other.asInstanceOf[UnitCard].cardName && cardClass == other.asInstanceOf[UnitCard].cardClass &&
      cardAbility == other.asInstanceOf[UnitCard].cardAbility && cardStrength == other.asInstanceOf[UnitCard].cardStrength
  }
}
