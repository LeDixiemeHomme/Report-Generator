package fr.valle.report_generator
package UI.sections.navbarsection.navbarcontent.navbarbutton

object NavBarButtonStyles {

  def selectedEnteredButtonStyle: String = fontSize20Property + whiteBackGColorProp +
    blackTextFillProperty + whiteBorderColorProp + borderWidth2Property

  def unselectedEnteredButtonStyle: String = fontSize20Property + transparentBackGColorProp +
    whiteTextFillProperty + whiteBorderColorProp + borderWidth2Property

  def unselectedExitedButtonStyle: String = fontSize20Property + transparentBackGColorProp +
    whiteTextFillProperty + blackBorderColorProp + borderWidth2Property

  private val backGroundColorProperty = createFxProperty(propertyName = "background-color") _
  private val borderColorProperty = createFxProperty(propertyName = "border-color") _
  private val textFillProperty = createFxProperty(propertyName = "text-fill") _

  private val whiteBackGColorProp = backGroundColorProperty("white")
  private val transparentBackGColorProp = backGroundColorProperty("transparent")

  private val whiteBorderColorProp = borderColorProperty("white")
  private val blackBorderColorProp = borderColorProperty("black")

  private val whiteTextFillProperty = textFillProperty("white")
  private val blackTextFillProperty = textFillProperty("black")

  private val fontSize20Property: String = " -fx-font-size: 20px;"
  private val borderWidth2Property: String = " -fx-border-width: 2px;"

  private def createFxProperty(propertyName: String)(color: String): String = s" -fx-$propertyName: $color;"
}
