package fr.valle.report_generator
package domain.path

import customexceptions.WrongFileFormatException
import domain.path.Extensions.Extension

class FilePath(val basePath: String, val fileName: FileName, val extension: Extension) {
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

    finalPathStringBuffer.toString
  }

  def constructFinalPathAntiSlash(): String = {
    this.constructFinalPath.replace("/", "\\")
  }

  def constructBasePathAntiSlash(): String = {
    this.basePath.replace("/", "\\")
  }

  override def toString: String = s"FilePath{ basePath: $basePath, fileName: ${fileName.value}, extension: $extension }"
}

object FilePath {
  def apply(basePath: String, fileName: FileName, extension: Extension): FilePath = {
    val finalBasePath: String = if ((basePath.endsWith("/") || basePath.endsWith("\\")) && !basePath.equals(""))
      basePath
    else
      s"$basePath/"
    new FilePath(basePath = finalBasePath, fileName = fileName, extension = extension)
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

    FilePath(basePath = basePath, fileName = fileName, extension = extension)
  }
}
