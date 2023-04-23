package cl.uchile.dcc
package gwent.cards

trait ICard {
  /** Place a selected card from your hand on the board.
   *
   * @param card the card you choose to put on the board.
   */
  def playCard(card: Card): Unit
}
