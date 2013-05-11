package com.github.notyy.retroboard.snippet

import net.liftweb.http.{S, LiftScreen}
import net.liftweb.util.FieldError
import com.github.notyy.retroboard.model.RetroDB
import com.github.notyy.retroboard.state.verified
import org.squeryl.PrimitiveTypeMode._

class VerifyScreen extends LiftScreen {
//  override def defaultToAjax_? = true
  val retroId:Long = S.param("retroId").map(_.toLong).getOrElse(0)
  val passCode = field("请输入passCode", "", valMinLen(3, "最少3个字符"))

  override def validations: List[() => List[FieldError]] = {() =>
      RetroDB.retros.lookup(retroId) match {
        case Some(r) if (passCode.get == r.passCode) => Nil
        case _ => FieldError(passCode, "passCode错误") :: Nil
      }
  } :: Nil

  override def finish() {
    verified(verified.get + (retroId.toString -> true))
    S.redirectTo(s"detail?id=$retroId")
  }
}
