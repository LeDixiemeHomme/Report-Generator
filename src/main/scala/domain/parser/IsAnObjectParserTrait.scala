package fr.valle.report_generator
package domain.parser

trait IsAnObjectParserTrait[A] {
  def parse(reader: Object): List[A]
}
