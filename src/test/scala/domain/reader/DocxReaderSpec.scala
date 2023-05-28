package fr.valle.report_generator
package domain.reader

import customexceptions.TemplateFileNotFoundException

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen, PrivateMethodTester}

class DocxReaderSpec extends AnyFlatSpec with PrivateMethodTester with BeforeAndAfterEach with GivenWhenThen with Matchers {
  private var templateFilePath: String = _

  private var docxReader: DocxReader = _

  override def beforeEach(): Unit = {
    docxReader = DocxReader()
  }

  "An XWPFDocument" should "be read" in {
    Given("a file path to a none existing file")
    templateFilePath = getClass.getResource("/template-test.docx").getPath

    When("using the readDocx method")
    val document = docxReader.readDocx(templateFilePath = templateFilePath)

    Then("the caught exception should be correct")
    document shouldEqual document
  }

  "An none existing XWPFDocument" should "when read should throws a TemplateFileNotFoundException" in {
    Given("a file path to a none existing file")
    templateFilePath = getClass.getResource("/template-test-empty.docx").getPath

    When("using the readDocx method")
    val caughtException = intercept[TemplateFileNotFoundException] {
      docxReader.readDocx(templateFilePath = templateFilePath + "/does-not-exist")
    }

    Then("the caught exception should be correct")
    caughtException.getMessage shouldEqual "Le fichier template \"" + getClass.getResource("/template-test-empty.docx").getPath + "/does-not-exist\" est introuvable."
  }
}
