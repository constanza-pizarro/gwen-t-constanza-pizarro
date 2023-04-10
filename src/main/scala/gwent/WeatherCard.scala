package cl.uchile.dcc
package gwent
/** A class that represents a weather card. */
class WeatherCard(val cardName: String, val cardClass: String) extends Card{
  override def equals(other: Any): Boolean = {
    other.isInstanceOf[WeatherCard] &&
      cardName == other.asInstanceOf[WeatherCard].cardName &&
      cardClass == other.asInstanceOf[WeatherCard].cardClass
  }
}