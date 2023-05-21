package cl.uchile.dcc
package gwent

import gwent.Player
import gwent.cards.Card
/** Class representing a board in the Gwen't game.
 *
 * The boards has 2 symmetric sections, one for each player, each section is divided by
 * 3 zones or rows. The board also has a weather zone, shared by the players.
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
  def playCard(player: Player, card: Card): Unit = {
    require(player.equals(player1) || player.equals(player2), "the player must be on this game.")
    card.playCard(this, player)
  }
}
