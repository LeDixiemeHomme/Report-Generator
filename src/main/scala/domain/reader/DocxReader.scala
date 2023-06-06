package fr.valle.report_generator
package domain.reader

import customexceptions.{EmptyXWPFDocumentException, TemplateFileNotFoundException, WrongFileFormatException}
import domain.path.FilePath
import logging.LogsKeeper

import org.apache.logging.log4j.scala.Logging
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException
import org.apache.poi.xwpf.usermodel.XWPFDocument

import java.io.{File, FileInputStream, FileNotFoundException}
import scala.util.{Failure, Success, Try}

class DocxReader extends Logging {

  /**
   * @throws TemplateFileNotFoundException if the `templateFilePath` is not actually an existing file
   * @throws EmptyXWPFDocumentException    si le document `templateDoc` est vide
   * @throws WrongFileFormatException      si le document `templateDoc` est au mauvais format
   */
  @throws(classOf[TemplateFileNotFoundException])
  @throws(classOf[EmptyXWPFDocumentException])
  @throws(classOf[WrongFileFormatException])
  def readDocx(templateFilePath: FilePath): XWPFDocument = {

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, "Reading docx " + templateFilePath, classFrom = getClass)

    // Read the template file into a XWPFDocument object
    val templateFile: File = new File(templateFilePath.constructFinalPath)

    val templateDoc: XWPFDocument = tryReadDocxSafely(templateFile = templateFile) match {
      case Success(templateDoc: XWPFDocument) => templateDoc

      case Failure(fileNotFoundException: FileNotFoundException) => throw TemplateFileNotFoundException(filePath = templateFilePath.constructFinalPath, cause = Some(fileNotFoundException))
      case Failure(notOfficeXmlFileException: NotOfficeXmlFileException) => throw WrongFileFormatException(fileType = getFilePathExtension(templateFilePath = templateFilePath.constructFinalPath), cause = Some(notOfficeXmlFileException))

      case Failure(exception: Exception) => throw exception
    }


    if (isEmptyDoc(templateDoc = templateDoc)) throw EmptyXWPFDocumentException(templateFilePath = templateFilePath.constructFinalPath)
    templateDoc
  }

  private def getFilePathExtension(templateFilePath: String): String = {
    templateFilePath.split('.').last
  }

  private def isEmptyDoc(templateDoc: XWPFDocument): Boolean = {
    val hasNoParagraph: Boolean = templateDoc.getParagraphs.size() == 1 && templateDoc.getParagraphs.get(0).getText == ""
    hasNoParagraph && templateDoc.getFooterList.isEmpty && templateDoc.getTables.isEmpty
  }

  private def tryReadDocxSafely(templateFile: File): Try[XWPFDocument] = {
    Try {
      val templateStream: FileInputStream = new FileInputStream(templateFile)

      try {
        new XWPFDocument(templateStream)
      } finally {
        templateStream.close()
      }
    }
  }
}

object DocxReader {
  def apply(): DocxReader = new DocxReader()
}