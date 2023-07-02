package fr.valle.report_generator
package app.path


class FileName(val value: String) {
  override def toString: String = s"FileName{ value: $value }"
}

object FileName {
  def apply(value: String): FileName = {
    val parts = value.split('.')
    val finalValue: String = if (parts.length > 1) parts.dropRight(1).mkString(".") else parts(0)
    new FileName(value = finalValue)
  }
}