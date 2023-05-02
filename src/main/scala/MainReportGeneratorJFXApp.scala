package fr.valle.report_generator

import UI.main._
import UI.sections._
import UI.sections.logssection.{IsALogsSectionTrait, LogsSection}
import UI.sections.pagesection.pages.{IsAPageTrait, OtherReportFormPage, ReportDataV1FormPage}
import UI.sections.pagesection.{IsAPageSectionTrait, PageSection}
import UI.sections.titlesection.TitleSection
import UI.sections.titlesection.titles.{IsATitleTrait, ReportGeneratorTitle}
import logging.LogsKeeper

import org.apache.logging.log4j.scala.Logging
import scalafx.application.{JFXApp3, Platform}
import scalafx.scene.Scene
import scalafx.scene.paint.Color
import scalafx.stage.Screen

object MainReportGeneratorJFXApp extends Logging with JFXApp3 {
  Platform.startup(runnable = () => {})

  private val appTitle: IsATitleTrait = ReportGeneratorTitle()

  private val reportDataV1FormPage: IsAPageTrait = ReportDataV1FormPage()
  private val otherReportFormPage: IsAPageTrait = OtherReportFormPage()

  private val titleSection: IsASectionTrait = TitleSection(title = appTitle)

  private val pageSection: IsAPageSectionTrait = PageSection(pageList = reportDataV1FormPage :: otherReportFormPage :: Nil)
  private val navBarSection: IsASectionTrait = NavBarSection(pageSection = pageSection)

  private val logsSection: IsALogsSectionTrait = LogsSection()

  private val mainVBox: MainVBox = MainVBox(sectionSeq = Seq(
    titleSection, navBarSection, pageSection, logsSection
  ))

  private val mainContent = MainContent(mainVBox = mainVBox).mainContent

  override def start(): Unit = {
    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, "Starting app", classFrom = getClass)
    stage = new JFXApp3.PrimaryStage {
      title = appTitle.toTitle
      scene = new Scene {
        fill = Color.rgb(38, 38, 38)
        content = mainContent
        stylesheets = List("style.css")
      }
      //     center content when the window is maximized
      maximized.addListener { (_, _, isMaximized) =>
        if (isMaximized) {
          val bounds = Screen.primary.bounds
          mainContent.translateX = (bounds.width - mainContent.width()) / 2
          mainContent.translateY = (bounds.height - mainContent.height()) / 3.5
        } else {
          mainContent.translateX = 0
          mainContent.translateY = 0
        }
      }
    }
  }
}
