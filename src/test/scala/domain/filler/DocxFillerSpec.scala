package fr.valle.report_generator
package domain.filler

import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen, PrivateMethodTester}

import java.io.{File, FileInputStream}

class DocxFillerSpec extends AnyFlatSpec with PrivateMethodTester with BeforeAndAfterEach with GivenWhenThen with Matchers {
  private var document: XWPFDocument = _
  private var docxFiller: DocxFiller = _
  private val valuesMap: Map[String, String] = Map("#ReplaceMe#" -> "Replaced")

  private val doNotReplaceValue: String = "A text Replaced to test.A text #DoNotReplaceMe# to test."

  private val templateFilePath: String = getClass.getResource("/template-test.docx").getPath

  override def beforeEach(): Unit = {
    val templateFile: File = new File(templateFilePath)
    val templateStream: FileInputStream = new FileInputStream(templateFile)

    docxFiller = DocxFiller()
    document = new XWPFDocument(templateStream)
  }

  "An XWPFDocument" should "have its tags replaced by the value in the map" in {

    Given("a paragraph with the key to replace")

    When("using the fillDocx method")
    docxFiller.fillDocx(document, valuesMap)

    Then("the value was not replaced")
    document.getParagraphs.get(0).getText shouldEqual doNotReplaceValue
  }

  "An XWPFDocument" should "have its tags inside its footer replaced by the value in the map" in {

    Given("a footer's paragraph with the key to replace")

    When("using the fillDocx method")
    docxFiller.fillDocx(document, valuesMap)

    Then("the value in the footer was not replaced")
    document.getFooterList.get(0).getParagraphs.get(0).getText shouldEqual doNotReplaceValue
  }

  "An XWPFDocument" should "have its tags inside its tables replaced by the value in the map" in {

    Given("a footer's paragraph with the key to replace")

    When("using the fillDocx method")
    docxFiller.fillDocx(document, valuesMap)

    Then("the value in the footer was not replaced")
    document.getTables.get(0).getRows.get(1).getCell(0).getText shouldEqual doNotReplaceValue
  }
}
