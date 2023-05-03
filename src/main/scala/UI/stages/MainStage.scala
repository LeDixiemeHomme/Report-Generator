package fr.valle.report_generator
package UI.stages

import UI.DebugBorder
import UI.sections._
import UI.sections.logssection.{IsALogsSectionTrait, LogsSection}
import UI.sections.pagesection.pages.{IsAPageTrait, OtherReportFormPage, ReportDataV1FormPage}
import UI.sections.pagesection.{IsAPageSectionTrait, PageSection}

import MainStage.APP_TITLE
import UI.sections.titlesection.TitleSection
import UI.sections.titlesection.titles.{IsATitleTrait, ReportGeneratorTitle}

import fr.valle.report_generator.UI.sections.navbars.NavBarSection
import scalafx.application.JFXApp3
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.layout.{HBox, VBox}
import scalafx.scene.paint.Color
import scalafx.stage.Screen

class MainStage extends IsAStageTrait {

  private class StageContent() {
    private val reportDataV1FormPage: IsAPageTrait = ReportDataV1FormPage()
    private val otherReportFormPage: IsAPageTrait = OtherReportFormPage()

    private val titleSection: IsASectionTrait = TitleSection(title = APP_TITLE)
    private val pageSection: IsAPageSectionTrait = PageSection(pageList = reportDataV1FormPage :: otherReportFormPage :: Nil)
    private val navBarSection: IsASectionTrait = NavBarSection(pageSection = pageSection)
    private val logsSection: IsALogsSectionTrait = LogsSection()

    private val listSection: List[IsASectionTrait] = List(titleSection, navBarSection, pageSection, logsSection)

    private val contentVBox: VBox = new VBox {
      border = DebugBorder(Color.Green).border
      alignment = Pos.Center
      padding = Insets(10)
      prefWidth = 1600
      children = listSection.map(_.mySection)
    }

    val myContent: HBox = new HBox {
      border = DebugBorder(Color.Blue).border
      alignment = Pos.Center
      padding = Insets(5, 80, 5, 80)
      prefWidth = 1600
      children = contentVBox
    }
  }

  private val mainContent = new StageContent().myContent

  override def myStage: JFXApp3.PrimaryStage = new JFXApp3.PrimaryStage {
    title = APP_TITLE.toTitle
    scene = new Scene {
      fill = Color.rgb(38, 38, 38)
      content = mainContent
      stylesheets = List("style.css")
    }
    // center content when the window is maximized
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

object MainStage {
  final val APP_TITLE: IsATitleTrait = ReportGeneratorTitle()

  def apply(): MainStage = new MainStage()
}
