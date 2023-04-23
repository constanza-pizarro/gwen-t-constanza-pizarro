package cl.uchile.dcc
package gwent.cards.classes

import gwent.cards.AbstractCard
import java.util.Objects

/** Represents a weather card. */
class WeatherCard(name: String, ability: Option[String]) extends AbstractCard(name, ability){
  //override def playCard(): Unit = {}
  override def canEqual(obj: Any): Boolean = {
    obj.isInstanceOf[WeatherCard]
  }
  /** Compare a WeatherCard object with an object of any type.
   *
   * @param obj object to compare.
   */
  override def equals(obj: Any): Boolean = obj match {
    case other: WeatherCard =>
      super.equals(other)
    case _ => false
  }
  //DOCUMENTAR!!!
  override def hashCode(): Int = {
    Objects.hash(classOf[WeatherCard], name, ability)
  }
}