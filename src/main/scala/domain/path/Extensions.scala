package fr.valle.report_generator
package domain.path

object Extensions extends Enumeration {
  type Extension = Value
  val csv, docx = Value

  def fromString(s: String): Option[Extension] = {
    Extensions.values.find(_.toString == s)
  }
}
