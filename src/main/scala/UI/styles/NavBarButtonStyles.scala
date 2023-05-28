package fr.valle.report_generator
package UI.styles

object NavBarButtonStyles extends Styles {

  def selectedEnteredButtonStyle: String = fontSize20Property + whiteBackGColorProp +
    blackTextFillProperty + borderWidth2Property + borderRadius20Property + backgroundRadius20Property

  def unselectedEnteredButtonStyle: String = fontSize20Property + transparentBackGColorProp +
    whiteTextFillProperty + whiteBorderColorProp + borderWidth2Property + borderRadius20Property

  def unselectedExitedButtonStyle: String = fontSize20Property + transparentBackGColorProp +
    whiteTextFillProperty + blackBorderColorProp + borderWidth2Property + borderRadius20Property
}
