package fr.valle.report_generator

import UI.main._
import UI.sections._
import UI.sections.pagesection.pages.{InterventionDataFormPage, LogsPage}
import UI.sections.pagesection.{IsAPageSectionTrait, PageSection}
import UI.sections.titlesection.titles.ReportGeneratorTitle
import UI.sections.titlesection.{IsATitleTrait, TitleSection}

import fr.valle.report_generator.logging.LogsKeeper
import org.apache.logging.log4j.scala.Logging
import scalafx.application.{JFXApp3, Platform}
import scalafx.scene.Scene
import scalafx.scene.paint.Color
import scalafx.stage.Screen

object MainReportGeneratorJFXApp extends Logging with JFXApp3 {
  Platform.startup(runnable = () => {})

  private val appTitle: IsATitleTrait = ReportGeneratorTitle()

  private val stageOne: InterventionDataFormPage = InterventionDataFormPage()
  private val stageTwo: LogsPage = LogsPage()

  private val titleSection: IsASectionTrait = TitleSection(title = appTitle)

  private val pageSection: IsAPageSectionTrait = PageSection(stageList = stageOne :: stageTwo :: Nil)
  private val navBarSection: IsASectionTrait = NavBarSection(pageSection = pageSection)

  private val logsSection: IsASectionTrait = LogsSection()

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
      // center content when the window is maximized
      maximized.addListener { (_, _, isMaximized) =>
        if (isMaximized) {
          val bounds = Screen.primary.bounds
          mainVBox.vBox.translateX = (bounds.width - mainVBox.vBox.width()) / 2
          mainVBox.vBox.translateY = (bounds.height - mainVBox.vBox.height()) / 2
        } else {
          mainVBox.vBox.translateX = 0
          mainVBox.vBox.translateY = 0
        }
      }
    }
  }
}
