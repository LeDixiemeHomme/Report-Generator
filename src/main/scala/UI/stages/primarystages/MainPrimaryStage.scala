package fr.valle.report_generator
package UI.stages.primarystages

import UI.sections._
import UI.sections.navbarsection.{IsANavBarSectionTrait, NavBarSection}
import UI.sections.pagesection.pages.{IsAPageTrait, OtherReportFormPage, ReceptionReportFormPage}
import UI.sections.pagesection.{IsAPageSectionTrait, PageSection}
import UI.sections.titlesection.TitleLogoSection
import UI.sections.titlesection.titles.{IsATitleTrait, ReportGeneratorTitle}
import UI.stages.logsstages.{IsALogsStageTrait, LogsStage}
import UI.stages.primarystages.MainPrimaryStage.APP_TITLE
import UI.{DebugBorder, Shaper}

import scalafx.application.{JFXApp3, Platform}
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.image.Image
import scalafx.scene.layout.{Background, BackgroundFill, HBox, VBox}
import scalafx.scene.paint.{Color, CycleMethod, LinearGradient}

class MainPrimaryStage extends IsAPrimaryStageTrait {

  private class StageContent() {
    private val receptionReportDataFormPage: IsAPageTrait = ReceptionReportFormPage()
    private val otherReportFormPage: IsAPageTrait = OtherReportFormPage()

    private val logsStage: IsALogsStageTrait = LogsStage()

    private val titleSection: IsASectionTrait = TitleLogoSection(title = APP_TITLE)
    private val pageSection: IsAPageSectionTrait = PageSection(pageList = receptionReportDataFormPage :: otherReportFormPage :: Nil)
    private val navBarSection: IsANavBarSectionTrait = NavBarSection(pageSection = pageSection, logsStage = logsStage)

    private val listSection: List[IsASectionTrait] = List(titleSection, navBarSection, pageSection)

    private val contentVBox: VBox = new VBox {
      border = DebugBorder(Color.Green).border
      alignment = Pos.Center
      padding = Insets(10)
      if (Shaper.smallWidthScreenMode) {
        prefWidth = 1400
      } else {
        prefWidth = 1600
      }
      children = listSection.map(_.mySection)
    }

    val myContent: HBox = new HBox {
      border = DebugBorder(Color.Blue).border
      alignment = Pos.Center
      if (Shaper.smallWidthScreenMode) {
        prefWidth = 1400
      } else {
        padding = Insets(5, 80, 5, 80)
        prefWidth = 1600
      }
      children = contentVBox
      background = new Background(Array(
        new BackgroundFill(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NoCycle),
          null, null)))
    }
  }

  private val mainContent = new StageContent().myContent

  private val appIcon = new Image(getClass.getResource("/images/rgGeorgia100.png").toString)

  override def myPrimaryStage: JFXApp3.PrimaryStage = new JFXApp3.PrimaryStage {
    title = APP_TITLE.toTitle
    scene = new Scene {
      icons += appIcon
      fill = Color.rgb(38, 38, 38)
      root = mainContent
    }

    onCloseRequest = _ => {
      Platform.exit()
    }
  }
}

object MainPrimaryStage {
  final val APP_TITLE: IsATitleTrait = ReportGeneratorTitle()

  def apply(): MainPrimaryStage = new MainPrimaryStage()
}
