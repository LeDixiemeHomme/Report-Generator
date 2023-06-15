package fr.valle.report_generator
package UI.styles

object StageButtonStyles extends Styles {

  def unselectedEnteredButtonStyle: String = fontSize15Property + transparentBackGColorProp +
    whiteTextFillProperty + whiteBorderColorProp + borderWidth2Property + borderRadius20Property

  def unselectedExitedButtonStyle: String = fontSize15Property + transparentBackGColorProp +
    whiteTextFillProperty + blackBorderColorProp + borderWidth2Property + borderRadius20Property

  def unselectedEnteredCloseButtonStyle: String = fontSize15Property + transparentBackGColorProp +
    redTextFillProperty + whiteBorderColorProp + borderWidth2Property + borderRadius20Property

  def unselectedExitedCloseButtonStyle: String = fontSize15Property + transparentBackGColorProp +
    redTextFillProperty + blackBorderColorProp + borderWidth2Property + borderRadius20Property

}
