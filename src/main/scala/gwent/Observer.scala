package cl.uchile.dcc
package gwent

trait Observer[T] {
  def update(observable: Subject[T], value: T): Unit
}
