package fr.valle.report_generator
package domain.path

import customexceptions.WrongFileFormatException
import domain.path.Extensions.Extension

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen, PrivateMethodTester}

class FilePathSpec extends AnyFlatSpec with PrivateMethodTester with BeforeAndAfterEach with GivenWhenThen with Matchers with TableDrivenPropertyChecks {
  private var filePath: FilePath = _
  private var stringFilePath: String = _

  it should "afficher l'objet normalement avec toString" in {
    filePath = FilePath(basePath = "C:basePath", fileName = FileName(value = "a/file/name"), extension = Extensions.csv)

    filePath.toString shouldEqual "FilePath{ basePath: C:basePath/, fileName: a/file/name, extension: csv }"
  }

  it should "seulement nom fichier" in {
    filePath = FilePath(basePath = "C:basePath", fileName = FileName(value = "a/file/name.docx"), extension = Extensions.csv)

    filePath.fileName.value shouldEqual "a/file/name"
  }

  it should "stringToFilePath1" in {
    stringFilePath = "C:basePath/a/file/name.csv"
    filePath = FilePath(basePath = "C:basePath/a/file", fileName = FileName(value = "name"), extension = Extensions.csv)

    FilePath.stringToFilePath(stringValue = stringFilePath).toString shouldEqual filePath.toString
    FilePath.stringToFilePath(stringValue = stringFilePath).constructFinalPath shouldEqual filePath.constructFinalPath
  }

  it should "stringToFilePath2" in {
    stringFilePath = "C:basePath/a/file/name.csv"
    filePath = FilePath(basePath = "C:basePath/a/file/", fileName = FileName(value = "name"), extension = Extensions.csv)

    FilePath.stringToFilePath(stringValue = stringFilePath).constructFinalPath shouldEqual filePath.constructFinalPath
  }

  it should "stringToFilePath3" in {
    stringFilePath = "C:basePath/a/file/name.txt"

    val caughtException = intercept[WrongFileFormatException] {
      FilePath.stringToFilePath(stringValue = stringFilePath).constructFinalPath
    }

    caughtException.getMessage shouldEqual "Mauvais format de fichier: txt"
  }

  it should "afficher l'objet normalement avec toString param" in {

    val testData = Table(("basePath", "fileName", "extension", "constructFinalPath"),
      ("C:basePath", "a/file/name", Extensions.csv, "C:basePath/a/file/name.csv"),
      ("C:basePath", "a/file/name", Extensions.docx, "C:basePath/a/file/name.docx"),
      ("outputDirPath", "fileName", Extensions.csv, "outputDirPath/fileName.csv"),
      ("outputDirPath/", "/fileName", Extensions.csv, "outputDirPath/fileName.csv"),
      ("outputDirPath/", "fileName", Extensions.csv, "outputDirPath/fileName.csv"),
      ("outputDirPath", "/fileName", Extensions.csv, "outputDirPath/fileName.csv"),
      ("outputDirPath", "/fileName.csv", Extensions.csv, "outputDirPath/fileName.csv"),
      ("outputDirPath\\", "\\fileName", Extensions.csv, "outputDirPath\\fileName.csv"),
      ("outputDirPath\\", "fileName.csv", Extensions.csv, "outputDirPath/fileName.csv"),
      ("outputDirPath", "\\fileName.csv", Extensions.csv, "outputDirPath\\fileName.csv"),
      ("outputDirPath", "fileName", Extensions.csv, "outputDirPath/fileName.csv"),
    )

    forAll(testData) {
      (basePath: String, fileName: String, extension: Extension, constructFinalPath: String) => {
        filePath = FilePath(basePath = basePath, fileName = FileName(value = fileName), extension = extension)
        filePath.constructFinalPath shouldEqual constructFinalPath
      }
    }
  }
}
