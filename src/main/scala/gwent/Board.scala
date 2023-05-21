package cl.uchile.dcc
package gwent

import gwent.Player
import gwent.cards.Card
/** Class representing a board in the Gwen't game.
 *
 * The boards has 2 players, each one has their respective combat section defined on the Player class.
 * The board also has a weather zone, shared by the players.
 *
 * @constructor Create a new board with 2 players.
 * @param player1 The player one of the game
 * @param player2 The player two of the game
 * @param weatherZone The weather zone shared by the players
 *
 * @author Constanza Pizarro
 */
class Board(var player1: Player, var player2: Player, var weatherZone: List[Card]=List()) {
  player1.board = this
  player2.board = this
  /** The given player plays the given card.
   *
   * Checks if the player is on the current game and plays the card.
   * */
  def playCard(player: Player, card: Card): Unit = {
    require(player.equals(player1) || player.equals(player2), "the player must be on this game.")
    card.playCard(this, player)
  }
}
