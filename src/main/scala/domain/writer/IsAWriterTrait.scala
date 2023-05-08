package fr.valle.report_generator
package domain.writer

trait IsAWriterTrait {
  def fileExtension(): String

  protected def constructFinalPath(outputDirPath: String, fileName: String, fileExtension: String): String = {
    val outputDirPathHasSlashAtEnd = outputDirPath.last == '\\' || outputDirPath.last == '/'
    val fileNameHasSlashAtBeginning = fileName.charAt(0) == '\\' || fileName.charAt(0) == '/'
    val bothHaveSlash = outputDirPathHasSlashAtEnd && fileNameHasSlashAtBeginning
    val neitherHaveSlash = !outputDirPathHasSlashAtEnd && !fileNameHasSlashAtBeginning

    var finalPath = outputDirPath + fileName

    if (bothHaveSlash) {
      val outputDirPathWithoutSlash = outputDirPath.dropRight(1)
      finalPath = outputDirPathWithoutSlash + fileName
    } else if (neitherHaveSlash) {
      finalPath = outputDirPath + '/' + fileName
    }

    if (!finalPath.endsWith(fileExtension))
      return finalPath + fileExtension

    finalPath
  }
}
