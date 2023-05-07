package fr.valle.report_generator
package UI.sections.navbarsection.navbarcontent.navbarbutton

import scalafx.scene.control.Button

protected class NavBarButton(textButton: String, buttonStyle: String, val pageStateMachineInputValue: String) extends IsANavBarButtonTrait {
  override def myButton: Button = new Button {
    text = textButton
    style = buttonStyle
  }
}

object NavBarButton {

  def apply(textButton: String, buttonStyle: String, pageStateMachineInputValue: String): NavBarButton = {
    new NavBarButton(textButton, buttonStyle, pageStateMachineInputValue)
  }
}