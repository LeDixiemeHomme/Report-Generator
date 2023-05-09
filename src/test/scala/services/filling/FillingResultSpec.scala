package fr.valle.report_generator
package services.filling

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen, PrivateMethodTester}

class FillingResultSpec extends AnyFlatSpec with PrivateMethodTester with BeforeAndAfterEach with GivenWhenThen with Matchers {

  val completionMessage = "message"
  val filledDocRelativePath = "docPath"
  val outputFilePath = "outputPath"

  var fillingResult: FillingResult = _

  override def beforeEach(): Unit = {
    fillingResult = FillingResult(completionMessage = completionMessage,
      filledDocRelativePath = filledDocRelativePath, outputFilePath = outputFilePath)
  }

  it should "have a correct string representation" in {
    fillingResult.toString shouldEqual "ReceptionReportData{Completion Message: message, Filled Doc Relative Path: docPath, Output File Path: outputPath}"
  }

  it should "return correct values for its properties" in {

    Then("all properties have the right value")
    fillingResult.completionMessage shouldEqual completionMessage
    fillingResult.filledDocRelativePath shouldEqual filledDocRelativePath
    fillingResult.outputFilePath shouldEqual outputFilePath
  }
}
