package fr.valle.report_generator
package app.path

import app.os.LocalOS
import app.os.LocalOS.OSs
import app.path.Extensions.Extension
import customexceptions.WrongFileFormatException
import logging.{Levels, Log, LogsKeeper}

import org.apache.logging.log4j.scala.Logging

class FilePath(val basePath: String, val fileName: FileName, val extension: Extension) extends Logging {
  def constructFinalPath: String = {
    val basePathHasSlashAtEnd = basePath.last == '\\' || basePath.last == '/'
    val fileNameHasSlashAtBeginning = fileName.value.charAt(0) == '\\' || fileName.value.charAt(0) == '/'
    val bothHaveSlash = basePathHasSlashAtEnd && fileNameHasSlashAtBeginning
    val neitherHaveSlash = !basePathHasSlashAtEnd && !fileNameHasSlashAtBeginning

    val basePathClean: String = basePath.replace('\\', '/')
    val finalPathStringBuffer: StringBuffer = new StringBuffer()

    if (bothHaveSlash) finalPathStringBuffer.append(s"${basePathClean.dropRight(1)}${fileName.value}")
    else if (neitherHaveSlash) finalPathStringBuffer.append(s"$basePathClean/${fileName.value}")
    else finalPathStringBuffer.append(s"$basePathClean${fileName.value}")

    if (!fileName.value.endsWith(extension.toString)) finalPathStringBuffer.append(s".$extension")

    val result = FilePath.slashesByOS(finalPathStringBuffer.toString)

    LogsKeeper.keepAndLog(extLogger = logger, log = Log(message = s"constructFinalPath apply: $result", level = Levels.DEBUG), classFrom = getClass)

    result
  }

  def constructFinalPathAntiSlash: String = {
    val result = this.constructFinalPath.replace("/", "\\")

    LogsKeeper.keepAndLog(extLogger = logger, log = Log(message = s"constructFinalPathAntiSlash apply: $result", level = Levels.DEBUG), classFrom = getClass)

    result
  }

  def constructBasePathAntiSlash: String = {
    val result = this.basePath.replace("/", "\\")

    LogsKeeper.keepAndLog(extLogger = logger, log = Log(message = s"constructBasePathAntiSlash apply: $result", level = Levels.DEBUG), classFrom = getClass)

    result
  }

  override def toString: String = s"FilePath{ basePath: $basePath, fileName: ${fileName.value}, extension: $extension }"
}

object FilePath extends Logging {
  def apply(basePath: String, fileName: FileName, extension: Extension): FilePath = {
    val finalBasePath: String = if ((basePath.endsWith("/") || basePath.endsWith("\\")) && !basePath.equals(""))
      basePath
    else
      s"$basePath/"

    val finalBasePathByOS: String = FilePath.slashesByOS(stringPath = finalBasePath)
    val fileNameByOS: FileName = FileName(value = FilePath.slashesByOS(stringPath = fileName.value))

    val result = new FilePath(basePath = finalBasePathByOS, fileName = fileNameByOS, extension = extension)

    LogsKeeper.keepAndLog(extLogger = logger, log = Log(message = s"FilePath apply: $result", level = Levels.DEBUG), classFrom = getClass)

    result
  }

  def slashesByOS(stringPath: String): String = {

    val result = LocalOS.os match {
      case OSs.WINDOWS => stringPath.replace("/", "\\")
      case OSs.MACOS | OSs.LINUX | OSs.OTHER => stringPath.replace("\\", "/")
    }

    LogsKeeper.keepAndLog(extLogger = logger, log = Log(message = s"FilePath slashesByOS: $result", level = Levels.DEBUG), classFrom = getClass)

    result
  }

  /**
   * @throws WrongFileFormatException if the `stringValue` end with a value that is part of the Extensions Enum
   */
  @throws(classOf[WrongFileFormatException])
  def stringToFilePath(stringValue: String): FilePath = {

    val filePathElements: List[String] = stringValue.replace('\\', '/').split('/').toList

    val slashIfNotEmpty = if (filePathElements.dropRight(1).mkString("/").equals("")) "/" else ""

    val basePath: String = filePathElements.dropRight(1).mkString("/") + slashIfNotEmpty

    val finalFilePathElement: String = filePathElements.last
    val fileNameElements: List[String] = finalFilePathElement.split('.').toList

    val fileName: FileName = FileName(value = fileNameElements.head)

    val someExtension: Option[Extension] = Extensions.fromString(fileNameElements.last)

    val extension: Extension = someExtension match {
      case Some(extension) => extension
      case None => throw WrongFileFormatException(fileType = fileNameElements.last, cause = None)
    }

    val result = FilePath(basePath = basePath, fileName = fileName, extension = extension)

    LogsKeeper.keepAndLog(extLogger = logger, log = Log(message = s"FilePath stringToFilePath: $result", level = Levels.DEBUG), classFrom = getClass)

    result
  }
}
