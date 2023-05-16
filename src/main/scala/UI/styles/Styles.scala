package fr.valle.report_generator
package UI.styles

trait Styles {

  protected val whiteBackGColorProp: String = backGroundColorProperty("white")
  protected val transparentBackGColorProp: String = backGroundColorProperty("transparent")

  protected val whiteBorderColorProp: String = borderColorProperty("white")
  protected val blackBorderColorProp: String = borderColorProperty("black")

  protected val whiteTextFillProperty: String = textFillProperty("white")
  protected val blackTextFillProperty: String = textFillProperty("black")

  protected val fontSize15Property: String = fontSizeProperty("15px")
  protected val fontSize20Property: String = fontSizeProperty("20px")

  protected val borderRadius20Property: String = borderRadiusProperty("20px")
  protected val backgroundRadius20Property: String = backgroundRadiusProperty("20px")
  protected val borderWidth2Property: String = borderWidthProperty("2px")

  private def createFxProperty(propertyName: String)(value: String): String = s" -fx-$propertyName: $value;"

  private def backGroundColorProperty = createFxProperty(propertyName = "background-color") _
  private def borderColorProperty = createFxProperty(propertyName = "border-color") _
  private def textFillProperty = createFxProperty(propertyName = "text-fill") _
  private def fontSizeProperty = createFxProperty(propertyName = "font-size") _
  private def borderRadiusProperty = createFxProperty(propertyName = "border-radius") _
  private def backgroundRadiusProperty = createFxProperty(propertyName = "background-radius") _
  private def borderWidthProperty = createFxProperty(propertyName = "border-width") _
}
