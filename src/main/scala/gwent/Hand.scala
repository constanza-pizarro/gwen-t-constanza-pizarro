package cl.uchile.dcc
package gwent

import scala.collection.mutable.ListBuffer

class Hand() extends ICardList {
  val deck: ListBuffer[ICard] = ListBuffer()
}
