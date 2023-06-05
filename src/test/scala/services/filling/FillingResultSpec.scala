package fr.valle.report_generator
package services.filling

import features.results.FillingResult

import fr.valle.report_generator.domain.path.{Extensions, FileName, FilePath}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen, PrivateMethodTester}

class FillingResultSpec extends AnyFlatSpec with PrivateMethodTester with BeforeAndAfterEach with GivenWhenThen with Matchers {

  val completionMessage = "message"
  val filledDocRelativePath = "docPath"
  val outputFilePath = "outputPath.docx"

  var fillingResult: FillingResult = _

  override def beforeEach(): Unit = {
    fillingResult = FillingResult(isSuccess = true, popUpMessage = completionMessage,
      filledDocRelativePath = Some(FilePath.stringToFilePath(outputFilePath)))
  }

  it should "have a correct string representation" in {
    fillingResult.toString shouldEqual "FillingResult{isSuccess: true, popUpMessage: message, filledDocRelativePath: FilePath{ basePath: /, fileName: outputPath, extension: docx }}"
  }

  it should "return correct values for its properties" in {

    Then("all properties have the right value")
    fillingResult.popUpMessage shouldEqual completionMessage
    fillingResult.filledDocRelativePath.toString shouldEqual
      Some(FilePath(basePath = "", fileName = FileName(value = outputFilePath), extension = Extensions.docx)).toString
  }
}
