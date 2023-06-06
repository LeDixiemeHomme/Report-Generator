package fr.valle.report_generator
package features.services

import domain.path.FilePath

import java.io.File
import scala.sys.process._

class OpenDocxReport {
  def open(fileLocation: String) = {
    val filePath: FilePath = FilePath.stringToFilePath(stringValue = fileLocation)
    Seq("cmd", "/c", "start", s"'$fileLocation'").!
  }
}

object OpenDocxReport {
  def apply(): OpenDocxReport = new OpenDocxReport()
}