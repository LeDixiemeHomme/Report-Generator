package fr.valle.report_generator
package app.path

import app.os.LocalOS
import app.path.Extensions.Extension
import app.path.{Extensions, FileName, FilePath}
import customexceptions.WrongFileFormatException

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen, PrivateMethodTester}

class FilePathSpec extends AnyFlatSpec with PrivateMethodTester with BeforeAndAfterEach with GivenWhenThen with Matchers with TableDrivenPropertyChecks {
  private var filePathWindows: FilePath = _
  private var stringFilePathWindows: String = _
  private var filePathOthers: FilePath = _
  private var stringFilePathOthers: String = _

  it should "afficher l'objet normalement avec toString" in {
    filePathWindows = FilePath(basePath = "C:basePath", fileName = FileName(value = "a/file/name"), extension = Extensions.csv)
    filePathOthers = FilePath(basePath = "C:basePath", fileName = FileName(value = "a\\file\\name"), extension = Extensions.csv)

    TestFilePathProvider.assertByOs(
      expectedWindows = filePathWindows.toString, actualWindows = "FilePath{ basePath: C:basePath\\, fileName: a\\file\\name, extension: csv }",
      expectedOthers = filePathOthers.toString, actualOthers = "FilePath{ basePath: C:basePath/, fileName: a/file/name, extension: csv }"
    )
  }

  it should "seulement nom fichier" in {
    filePathWindows = FilePath(basePath = "C:basePath", fileName = FileName(value = "a\\file\\name.docx"), extension = Extensions.csv)
    filePathOthers = FilePath(basePath = "C:basePath", fileName = FileName(value = "a/file/name.docx"), extension = Extensions.csv)

    TestFilePathProvider.assertByOs(
      expectedWindows = filePathWindows.fileName.value, actualWindows = "a\\file\\name",
      expectedOthers = filePathOthers.fileName.value, actualOthers = "a/file/name"
    )
  }

  it should "stringToFilePath1" in {
    stringFilePathWindows = "C:basePath\\a\\file\\name.csv"
    stringFilePathOthers = "C:basePath/a/file/name.csv"
    filePathWindows = FilePath(basePath = "C:basePath\\a\\file", fileName = FileName(value = "name"), extension = Extensions.csv)
    filePathOthers = FilePath(basePath = "C:basePath/a/file", fileName = FileName(value = "name"), extension = Extensions.csv)

    TestFilePathProvider.assertByOs(
      expectedWindows = FilePath.stringToFilePath(stringValue = stringFilePathWindows).toString, actualWindows = filePathWindows.toString,
      expectedOthers = FilePath.stringToFilePath(stringValue = stringFilePathOthers).toString, actualOthers = filePathOthers.toString
    )

    TestFilePathProvider.assertByOs(
      expectedWindows = FilePath.stringToFilePath(stringValue = stringFilePathWindows).constructFinalPath, actualWindows = FilePath(basePath = "C:basePath/a/file", fileName = FileName(value = "name"), extension = Extensions.csv).constructFinalPath,
      expectedOthers = FilePath.stringToFilePath(stringValue = stringFilePathOthers).constructFinalPath, actualOthers = FilePath(basePath = "C:basePath\\a\\file", fileName = FileName(value = "name"), extension = Extensions.csv).constructFinalPath
    )
  }

  it should "stringToFilePath2" in {
    stringFilePathWindows = "C:basePath\\a\\file\\name.csv"
    stringFilePathOthers = "C:basePath/a/file/name.csv"
    filePathWindows = FilePath(basePath = "C:basePath\\a\\file\\", fileName = FileName(value = "name"), extension = Extensions.csv)
    filePathOthers = FilePath(basePath = "C:basePath/a/file", fileName = FileName(value = "name"), extension = Extensions.csv)

    FilePath.stringToFilePath(stringValue = stringFilePathOthers).constructFinalPath shouldEqual filePathWindows.constructFinalPath

    TestFilePathProvider.assertByOs(
      expectedWindows = FilePath.stringToFilePath(stringValue = stringFilePathWindows).constructFinalPath, actualWindows = filePathWindows.constructFinalPath,
      expectedOthers = FilePath.stringToFilePath(stringValue = stringFilePathOthers).constructFinalPath, actualOthers = filePathOthers.constructFinalPath
    )
  }

  it should "stringToFilePath3" in {
    val caughtException = intercept[WrongFileFormatException] {
      FilePath.stringToFilePath(stringValue = "C:basePath/a/file/name.txt").constructFinalPath
    }

    caughtException.getMessage shouldEqual "Mauvais format de fichier: txt"
  }

  it should "afficher l'objet normalement avec toString param" in {

    var testData = Table(("basePath", "fileName", "extension", "constructFinalPath"),
      ("C:basePath", "a/file/name", Extensions.csv, "C:basePath/a/file/name.csv"))

    if (LocalOS.os.equals(LocalOS.OSs.WINDOWS)) {
      testData = Table(("basePath", "fileName", "extension", "constructFinalPath"),
        ("C:basePath", "a\\file\\name", Extensions.csv, "C:basePath\\a\\file\\name.csv"),
        ("C:basePath", "a\\file\\name", Extensions.docx, "C:basePath\\a\\file\\name.docx"),
        ("outputDirPath", "fileName", Extensions.csv, "outputDirPath\\fileName.csv"),
        ("outputDirPath\\", "\\fileName", Extensions.csv, "outputDirPath\\fileName.csv"),
        ("outputDirPath\\", "fileName", Extensions.csv, "outputDirPath\\fileName.csv"),
        ("outputDirPath", "\\fileName", Extensions.csv, "outputDirPath\\fileName.csv"),
        ("outputDirPath", "\\fileName.csv", Extensions.csv, "outputDirPath\\fileName.csv"),
      )
    } else {
      testData = Table(("basePath", "fileName", "extension", "constructFinalPath"),
        ("C:basePath", "a/file/name", Extensions.csv, "C:basePath/a/file/name.csv"),
        ("C:basePath", "a/file/name", Extensions.docx, "C:basePath/a/file/name.docx"),
        ("outputDirPath", "fileName", Extensions.csv, "outputDirPath/fileName.csv"),
        ("outputDirPath/", "/fileName", Extensions.csv, "outputDirPath/fileName.csv"),
        ("outputDirPath/", "fileName", Extensions.csv, "outputDirPath/fileName.csv"),
        ("outputDirPath", "/fileName", Extensions.csv, "outputDirPath/fileName.csv"),
        ("outputDirPath", "/fileName.csv", Extensions.csv, "outputDirPath/fileName.csv"),
      )
    }

    forAll(testData) {
      (basePath: String, fileName: String, extension: Extension, constructFinalPath: String) => {
        filePathWindows = FilePath(basePath = basePath, fileName = FileName(value = fileName), extension = extension)
        filePathWindows.constructFinalPath shouldEqual constructFinalPath
      }
    }
  }
}
