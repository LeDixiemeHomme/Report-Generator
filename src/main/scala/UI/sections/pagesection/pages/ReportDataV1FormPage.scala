package fr.valle.report_generator
package UI.sections.pagesection.pages

import org.apache.logging.log4j.scala.Logging

class ReportDataV1FormPage extends Logging with IsAPageTrait {
  override def myPage: Page = ???

  override def myPageID: String = ???

  override def myPageName: String = ???
}

object ReportDataV1FormPage {
  def apply(): ReportDataV1FormPage = new ReportDataV1FormPage()

  final val REPORT_DATA_V1_FORM_PAGE_ID: String = "PageThree"
}
