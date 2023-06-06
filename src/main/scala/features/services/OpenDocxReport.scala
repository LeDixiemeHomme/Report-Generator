package fr.valle.report_generator
package features.services

import domain.path.FilePath

import java.io.File
import sys.process._

class OpenDocxReport {
  def open(fileLocation: String): Int = {
    val filePath: FilePath = FilePath.stringToFilePath(stringValue = fileLocation)
    Seq("powershell", "/c", s"start '${filePath.constructBasePathAntiSlash()}${filePath.fileName.value}.${filePath.extension}'").!
  }
}

object OpenDocxReport {
  def apply(): OpenDocxReport = new OpenDocxReport()
}