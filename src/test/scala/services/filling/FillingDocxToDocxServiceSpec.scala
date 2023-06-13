package fr.valle.report_generator
package services.filling

import app.LocalOS
import domain.path.{Extensions, FileName, FilePath}
import features.results.FillingResult
import features.services.filling.FillingDocxToDocxService

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen, PrivateMethodTester}

class FillingDocxToDocxServiceSpec extends AnyFlatSpec with PrivateMethodTester with BeforeAndAfterEach with GivenWhenThen with Matchers with TableDrivenPropertyChecks {
  var myFillingDocxToDocxService: FillingDocxToDocxService = _
  var filePath: FilePath = _
  var outputFilePath: FilePath = _
  var valuesMap: Map[String, String] = _

  override def beforeEach(): Unit = {
    myFillingDocxToDocxService = FillingDocxToDocxService()
  }

  it should "fill correctly parametrized" in {
    val appBasePath: String = getClass.getResource("/").getPath

    var testData = Table(("filePath", "outputFilePath", "valuesMap", "expectedFillingResult"),
      (
        FilePath.stringToFilePath(getClass.getResource("/reception-report-data-test-missing-column.csv").getPath),
        FilePath.stringToFilePath(s"$appBasePath/result.docx"),
        Map("" -> ""),
        FillingResult(isSuccess = false, popUpMessage = "Mauvais format de fichier: csv", filledDocRelativePath = None))
    )

    if (LocalOS.os.equals(LocalOS.OSs.WINDOWS)) {
      testData = Table(("filePath", "outputFilePath", "valuesMap", "expectedFillingResult"),
        (
          FilePath.stringToFilePath(getClass.getResource("/reception-report-data-test-missing-column.csv").getPath),
          FilePath.stringToFilePath(s"$appBasePath/result.docx"),
          Map("" -> ""),
          FillingResult(isSuccess = false, popUpMessage = "Mauvais format de fichier: csv", filledDocRelativePath = None)),
        (
          FilePath.stringToFilePath(getClass.getResource("/template-test-empty.docx").getPath),
          FilePath.stringToFilePath(s"$appBasePath/result.docx"),
          Map("" -> ""),
          FillingResult(isSuccess = false, popUpMessage = s"Le document word template ${appBasePath.replace('/', '\\')}template-test-empty.docx est vide.", filledDocRelativePath = None)),
        (
          FilePath.stringToFilePath(s"$appBasePath/none-existing.docx"),
          FilePath.stringToFilePath(s"$appBasePath/result.docx"),
          Map("" -> ""),
          FillingResult(isSuccess = false, popUpMessage = s"Le document word template \"${appBasePath.replace('/', '\\')}none-existing.docx\" est introuvable.", filledDocRelativePath = None)),
        (
          FilePath.stringToFilePath(s"$appBasePath/template-test.docx"),
          FilePath.stringToFilePath(s"${appBasePath}none-existing/result.docx"),
          Map("" -> ""),
          FillingResult(isSuccess = false, popUpMessage = "Le dossier de destination " +
            s"\"${appBasePath.replace('/', '\\')}none-existing\\\" est introuvable.", filledDocRelativePath = None)),
        (
          FilePath.stringToFilePath(s"$appBasePath/template-test.docx"),
          FilePath.stringToFilePath(s"${appBasePath}/result.docx"),
          Map("" -> ""),
          FillingResult(isSuccess = true, popUpMessage = s"Successfully written result.docx in ${FilePath.stringToFilePath(s"${appBasePath}test.docx").constructBasePathAntiSlash}",
            filledDocRelativePath = Option(FilePath(basePath = appBasePath, fileName = FileName(value = "result"), extension = Extensions.docx)))),
      )
    } else {
      testData = Table(("filePath", "outputFilePath", "valuesMap", "expectedFillingResult"),
        (
          FilePath.stringToFilePath(getClass.getResource("/reception-report-data-test-missing-column.csv").getPath),
          FilePath.stringToFilePath(s"$appBasePath/result.docx"),
          Map("" -> ""),
          FillingResult(isSuccess = false, popUpMessage = "Mauvais format de fichier: csv", filledDocRelativePath = None)),
        (
          FilePath.stringToFilePath(getClass.getResource("/template-test-empty.docx").getPath),
          FilePath.stringToFilePath(s"$appBasePath/result.docx"),
          Map("" -> ""),
          FillingResult(isSuccess = false, popUpMessage = s"Le document word template ${appBasePath}template-test-empty.docx est vide.", filledDocRelativePath = None)),
        (
          FilePath.stringToFilePath(s"$appBasePath/none-existing.docx"),
          FilePath.stringToFilePath(s"$appBasePath/result.docx"),
          Map("" -> ""),
          FillingResult(isSuccess = false, popUpMessage = s"Le document word template \"${appBasePath}none-existing.docx\" est introuvable.", filledDocRelativePath = None)),
        (
          FilePath.stringToFilePath(s"$appBasePath/template-test.docx"),
          FilePath.stringToFilePath(s"${appBasePath}none-existing/result.docx"),
          Map("" -> ""),
          FillingResult(isSuccess = false, popUpMessage = "Le dossier de destination " +
            s"\"${appBasePath}none-existing/\" est introuvable.", filledDocRelativePath = None)),
        (
          FilePath.stringToFilePath(s"$appBasePath/template-test.docx"),
          FilePath.stringToFilePath(s"${appBasePath}/result.docx"),
          Map("" -> ""),
          FillingResult(isSuccess = true, popUpMessage = s"Successfully written result.docx in ${FilePath.stringToFilePath(s"${appBasePath}test.docx").constructBasePathAntiSlash}",
            filledDocRelativePath = Option(FilePath(basePath = appBasePath, fileName = FileName(value = "result"), extension = Extensions.docx)))),
      )
    }

    forAll(testData) {
      (templateFilePath: FilePath, outputFilePath: FilePath, valuesMap: Map[String, String], expectedFillingResult: FillingResult) => {
        Given("a file path to a template and an output dir")
        println(s"templateFilePath = ${templateFilePath.constructFinalPath}")
        println(s"outputFilePath = ${outputFilePath.constructFinalPath}")

        When("using the fill method with the myFillingDocxToDocxService")
        val fillingResult: FillingResult = myFillingDocxToDocxService.fill(templateFilePath, valuesMap, outputFilePath)

        Then("the fillingResult should be correct")
        fillingResult.toString shouldEqual expectedFillingResult.toString
      }
    }
  }
}
