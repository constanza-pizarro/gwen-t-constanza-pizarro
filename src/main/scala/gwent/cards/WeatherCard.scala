package cl.uchile.dcc
package gwent.cards

import gwent.Section
import java.util.Objects

/** Class representing a weather card in the Gwen't game.
 *
 * A `WeatherCard` is a type of [[Card]] that can be placed in the weather zone.
 * These cards have the ability to affect the battlefield and provide advantages or
 * disadvantages to players, depending on the type of weather that has been chosen.
 *
 * @constructor Creates a new `WeatherCard` with a specified name and description.
 * @param name The name of the card.
 * @param description The description of the card, explaining its specific effects.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author Constanza Pizarro
 */
class WeatherCard(val name: String, val description: String) extends Card {
  override def playCard(section: Section): Section = {
   playWeatherCard(section)
  }
  /** Puts the weather card on the weather zone of the board the player's playing in.
   *
   * @return the section with the added weather card. */
  def playWeatherCard(section: Section): Section = {
    var zone: List[Card] = section.board.weatherZone
    require(zone.isEmpty, "only one weather card can be placed on the board")
    section.board.weatherZone = this :: zone
    section
  }
  override def equals(obj: Any): Boolean = obj match {
    case other: WeatherCard =>
      (this eq other) || name == other.name && description == other.description
    case _ =>
      false
  }
  override def hashCode(): Int =
    Objects.hash(classOf[WeatherCard], name, description)
  override def toString =
    s"WeatherCard(name: $name, description: $description)"
}