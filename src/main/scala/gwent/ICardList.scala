package cl.uchile.dcc
package gwent
import scala.collection.mutable.ListBuffer

trait ICardList {
  val deck: ListBuffer[ICard]
}
