package fr.valle.report_generator
package domain.writer

import fr.valle.report_generator.domain.path.FilePath
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen, PrivateMethodTester}

class DocxWriterSpec extends AnyFlatSpec with PrivateMethodTester with BeforeAndAfterEach with GivenWhenThen with Matchers {

  private val sourcePath: String = getClass.getResource("/").getPath

  private var docxWriter: DocxWriter = _
  private var document: XWPFDocument = _
  private var outputDirPath: String = _
  private var fileName: String = _

  override def beforeEach(): Unit = {
    docxWriter = DocxWriter()
    document = new XWPFDocument()
  }

  it should "write a XWPFDocument at the right place with the right file name" in {
    Given("an outputDirPath and a fileName")
    outputDirPath = sourcePath
    fileName = "file-result.docx"

    When("using the write method")
    val result: WriteResult = docxWriter.write(templateDoc = document, outputFilePath = FilePath.stringToFilePath(outputDirPath + fileName))

    Then("the result should be correct")
    result.outputMessage shouldEqual "Successfully written " + fileName + " in " + outputDirPath
  }
}
