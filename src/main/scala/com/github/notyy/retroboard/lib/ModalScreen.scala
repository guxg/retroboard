package com.github.notyy.retroboard.lib

import net.liftweb.http.LiftScreen
import net.liftweb.util.Helpers._
import net.liftweb.common.Loggable

trait ModalScreen extends LiftScreen with Loggable{
  override protected def allTemplatePath = List("templates-hidden", "screen-modal")

  def modalTitle:String = "ModelHeader"

  override def toForm = {
    val transform = "#myModalLabel *" #> modalTitle

    val r = transform(super.toForm)
    logger.info("modal label transformed")
    r
  }
}
