package fr.valle.report_generator
package domain.filler

import customexceptions.EmptyXWPFDocumentException

import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen, PrivateMethodTester}

import java.io.{File, FileInputStream}

class DocxFillerSpec extends AnyFlatSpec with PrivateMethodTester with BeforeAndAfterEach with GivenWhenThen with Matchers {
  private var templateFilePath: String = _
  private var document: XWPFDocument = _

  private var docxFiller: DocxFiller = _
  private val valuesMap: Map[String, String] = Map("#ReplaceMe#" -> "Replaced")
  private val doNotReplaceValue: String = "A text Replaced to test.A text #DoNotReplaceMe# to test."

  override def beforeEach(): Unit = {
    docxFiller = DocxFiller()
  }

  "An XWPFDocument" should "have its tags inside its paragraphs, footers, tables replaced by the value in the map" in {
    templateFilePath = getClass.getResource("/template-test.docx").getPath
    document = new XWPFDocument(new FileInputStream(new File(templateFilePath)))

    Given("a paragraph with the key to replace")

    When("using the fillDocx method")
    docxFiller.fillDocx(document, valuesMap)

    Then("the value was not replaced")
    document.getParagraphs.get(0).getText shouldEqual doNotReplaceValue
    document.getFooterList.get(0).getParagraphs.get(0).getText shouldEqual doNotReplaceValue
    document.getTables.get(0).getRows.get(1).getCell(0).getText shouldEqual doNotReplaceValue
  }

  "An empty XWPFDocument" should "when filled should throws a EmptyXWPFDocumentException" in {
    templateFilePath = getClass.getResource("/template-test-empty.docx").getPath

    Given("an empty document")
    document = new XWPFDocument(new FileInputStream(new File(templateFilePath)))

    When("using the fillDocx method")
    val caughtException = intercept[EmptyXWPFDocumentException] {
      docxFiller.fillDocx(document, valuesMap)
    }

    Then("the caught exception should be correct")
    caughtException.getMessage shouldEqual "Le document word template est vide."
  }
}
