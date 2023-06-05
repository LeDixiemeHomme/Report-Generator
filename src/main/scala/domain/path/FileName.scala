package fr.valle.report_generator
package domain.path

case class FileName(value: String) {
  override def toString: String = s"FilePath{ value: $value }"
}
