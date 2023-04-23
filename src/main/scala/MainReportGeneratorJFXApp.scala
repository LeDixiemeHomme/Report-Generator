package fr.valle.report_generator

import UI.main._
import UI.sections._
import UI.sections.logssection.{IsALogsSectionTrait, LogsSection}
import UI.sections.pagesection.pages.{InterventionDataFormPage, LogsPage}
import UI.sections.pagesection.{IsAPageSectionTrait, PageSection}
import UI.sections.titlesection.TitleSection
import UI.sections.titlesection.titles.{IsATitleTrait, ReportGeneratorTitle}
import logging.LogsKeeper

import org.apache.logging.log4j.scala.Logging
import scalafx.application.{JFXApp3, Platform}
import scalafx.scene.Scene
import scalafx.scene.paint.Color

object MainReportGeneratorJFXApp extends Logging with JFXApp3 {
  Platform.startup(runnable = () => {})

  private val appTitle: IsATitleTrait = ReportGeneratorTitle()

  private val stageOne: InterventionDataFormPage = InterventionDataFormPage()
  private val stageTwo: LogsPage = LogsPage()

  private val titleSection: IsASectionTrait = TitleSection(title = appTitle)

  private val pageSection: IsAPageSectionTrait = PageSection(stageList = stageOne :: stageTwo :: Nil)
  private val navBarSection: IsASectionTrait = NavBarSection(pageSection = pageSection)

  private val logsSection: IsALogsSectionTrait = LogsSection()
  LogsKeeper.setMyLogsSection(aLogsSection = logsSection)

  private val mainVBox: MainVBox = MainVBox(sectionSeq = Seq(
    titleSection, navBarSection, pageSection, logsSection
  ))

  private val mainContent = MainContent(mainVBox = mainVBox).mainContent

  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      title = appTitle.toTitle
      scene = new Scene {
        fill = Color.rgb(38, 38, 38)
        content = mainContent
        stylesheets = List("style.css")
      }
    }
  }
}
