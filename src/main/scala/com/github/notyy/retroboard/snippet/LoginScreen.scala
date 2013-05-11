package com.github.notyy.retroboard.snippet

import net.liftweb.http.{SHtml, S, LiftScreen}
import net.liftweb.common._
import net.liftweb.util.Helpers._
import scala.xml.NodeSeq

class LoginScreen  extends LiftScreen with Loggable {

//  object user extends ScreenVar(User.createRecord)
//  addFields(() => (user.nickName))
//
//  val password = new Field {
//    type ValueType = String
//    override def name = "密码"
//    override implicit def manifest = buildIt[String]
//    override def default = ""
//    override def toForm: Box[NodeSeq] = SHtml.password(is, set _, "tabindex" -> "2")
//    override def validations =
//      valMinLen(2,"密码最短不能少于2个字符") :: super.validations
//  }
//
//  override def finish() {
//    User.login(user.nickName.get, password) match {
//      case Full(_) =>
//      case Failure(msg, _, _) => S.error(msg); S.redirectTo("/login")
//      case _ => S.error("登录失败，未知错误")
//    }
//  }
  override def finish(){}
}
