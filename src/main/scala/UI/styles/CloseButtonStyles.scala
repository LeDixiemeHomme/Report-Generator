package fr.valle.report_generator
package UI.styles

object CloseButtonStyles extends Styles {

  def unselectedEnteredButtonStyle: String = fontSize15Property + transparentBackGColorProp +
    whiteTextFillProperty + whiteBorderColorProp + borderWidth2Property + borderRadius20Property

  def unselectedExitedButtonStyle: String = fontSize15Property + transparentBackGColorProp +
    whiteTextFillProperty + blackBorderColorProp + borderWidth2Property + borderRadius20Property

}
