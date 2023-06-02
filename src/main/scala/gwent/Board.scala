package cl.uchile.dcc
package gwent

import gwent.cards.{Card, WeatherCard}

/** Class representing a board in the Gwen't game.
 *
 * The boards has 2 players, each one has their respective combat section defined on the Player class.
 * The board also has a weather zone, shared by the players.
 *
 * @constructor Create a new board with 2 players and a weather zone.
 * @param player1 The player one of the game
 * @param player2 The player two of the game
 * @param weatherZone The weather zone shared by the players
 *
 * @author Constanza Pizarro
 */
class Board(var player1: Player, var player2: Player, var weatherZone: List[Card]=List()) {
  require(player1.name!=player2.name, "the players must be different.")
  def playCard(player: Player, card: Card): Unit = {
    require(player==player1 || player==player2, "the player must be on the board.")
    player.playCard(card, this)
  }
  def playWeatherCard(wCard: WeatherCard): Unit = {
    require(weatherZone.isEmpty, "only one weather card can be placed on the board.")
    weatherZone = wCard :: weatherZone
  }
}
