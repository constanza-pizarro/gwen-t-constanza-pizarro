package cl.uchile.dcc
package gwent

import cards.{Card, WeatherCard}
import cards.effects.Effect
import scala.collection.mutable.ListBuffer

/** Class representing a board in the Gwen't game.
 *
 * The boards has 2 players, each one has their respective combat section defined on the Player class.
 * The board also has a weather zone, shared by the players, a list of the players and a list of 
 * weather cards' effects.
 *
 * @constructor Create a new board with 2 players and a weather zone.
 * @param player1 The player one of the game
 * @param player2 The player two of the game
 * @param weatherZone The weather zone shared by the players
 *
 * @author Constanza Pizarro
 */
class Board(var player1: Player, var player2: Player, var weatherZone: List[WeatherCard]=List()) {
  /** The list of players. */
  val players: List[Player] = List(player1, player2)
  /** List of weather cards placed on the board. */
  val applied: ListBuffer[Effect] = ListBuffer()
  
  if (player1.name == player2.name) {
    throw new InvalidNameException("the players must have different names.")
  }
  /** A player plays a card, putting it on its respective zone of the board.
   *
   * @param player the player playing the card
   * @param card the card that will be added to the board
   */
  def playCard(player: Player, card: Card): Unit = {
    if (!players.contains(player)) {
      throw new InvalidPlayerException("the player must be on the board.")
    }
    player.playCard(card, this)
  }
  /** Places a weather card on the weather zone of the board and applies the card's effect.
   *
   * @param wCard the weather card that will be added to the weather zone of the board
   */
  def playWeatherCard(wCard: WeatherCard): Unit = {
    weatherZone = wCard :: weatherZone
    wCard.effect(wCard, this)
  }
}
