package com.github.notyy.retroboard.snippet

import net.liftweb.http.LiftScreen
import net.liftweb.common.Loggable
import com.github.notyy.retroboard.model.{RetroStatus, Retro, RetroDB}
import net.liftweb.http.js.JsCmds.{Replace, Alert}
import net.liftweb.http.js.jquery.JqJsCmds._
import scala.xml.NodeSeq
import net.liftweb.util.Helpers._
import net.liftweb.http.js.JsCmds.Replace

class RetroScreen extends LiftScreen with Loggable {
  override def defaultToAjax_? = true

  var id: Long = 0;
  val title = field("请输入主题", "", trim, valMinLen(5, "主题至少5个字符"), valMaxLen(50, "主题最多50个字符"),
    "class" -> "string_field", "id" -> "tagInput")
  val nickName = field("请输入昵称", "")

  override def finish() {
    id = RetroDB.retros.insert(new Retro(title, nickName)).id
  }

  override def calcAjaxOnDone = {
    logger.info("calcAjax")
    FadeOut("createRetroForm", 100 millis, 500 millis) &
      Replace("newRetro", newRetroItem) & Show("retro-" + id, 300 millis) &
      Hide("alert")
  }

  def newRetroItem: NodeSeq = {
    <tr id="newRetro" style="display: block">
      <!-- 新回顾的占位符 -->
    </tr>
    <tr class="warning retro-line" style="display: none" id={"retro-" + id}>
      <td><a href={s"detail?id=${id}"}>{title}</a></td>
      <td>{nickName}</td>
      <td>{RetroStatus.Waiting}</td>
    </tr>
  }
}
