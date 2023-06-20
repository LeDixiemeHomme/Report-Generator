package fr.valle.report_generator
package domain.docx.writer

class WriteResult(val outputPath: String, val outputMessage: String) {
  override def toString: String = s"WriteResult{outputPath: $outputPath, outputMessage: $outputMessage}"
}