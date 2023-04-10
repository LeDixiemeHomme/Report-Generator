package fr.valle.report_generator

import UI.main._
import UI.sections._
import UI.sections.pagesection.pages.{PageOne, PageTwo}
import UI.sections.pagesection.{IsAPageSectionTrait, PageSection}

import scalafx.application.{JFXApp3, Platform}
import scalafx.scene.Scene
import scalafx.scene.paint.Color
import scalafx.stage.Screen

object MainReportGeneratorJFXApp extends JFXApp3 {
  Platform.startup(runnable = () => {})

  private val mainTitle: MainTitle = new MainTitle(words = "Report" :: "Generator" :: Nil)

  private val stageOne: PageOne = new PageOne
  private val stageTwo: PageTwo = new PageTwo

  private val titleSection: IsASectionTrait = new TitleSection(mainTitle = mainTitle)

  private val pageSection: IsAPageSectionTrait = new PageSection(stageList = stageOne :: stageTwo :: Nil)
  private val navBarSection: IsASectionTrait = new NavBarSection(pageSection = pageSection)

  private val buttonSection = new ButtonSection()

  private val mainVBox: MainVBox = new MainVBox(sectionSeq = Seq(
    titleSection, navBarSection, pageSection, buttonSection
  ))

  private val mainContent = new MainContent(mainVBox = mainVBox).mainContent

  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      title = mainTitle.toTitle
      scene = new Scene {
        fill = Color.rgb(38, 38, 38)
        content = mainContent
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
