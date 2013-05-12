
package com.github.notyy.retroboard.snippet

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.{BeforeAndAfter, FunSpec}
import scala.xml.NodeSeq
import com.github.notyy.retroboard.util.XmlUtil._
import net.liftweb.squerylrecord.SquerylRecord
import org.squeryl.Session
import java.sql.DriverManager
import org.squeryl.adapters.H2Adapter
import net.liftweb.common.{Empty, Loggable}
import com.github.notyy.retroboard.lib.SqlLog
import org.squeryl.PrimitiveTypeMode._
import net.liftweb.http.{LiftSession, S, LiftRules}
import net.liftweb.mockweb.MockWeb._
import net.liftweb.util.StringHelpers

class RetrosSpec extends FunSpec with ShouldMatchers with BeforeAndAfter with Loggable {
  describe("Retro列表控件") {
    it("可以显示所有的retro列表") {
      val template =
        <tbody data-lift="Retros.list">
          <tr class="retro-line">
            <td class="retro-title">20130512上海scala爱好者聚会回顾</td>
            <td class="retro-creator">杨云</td>
            <td class="retro-status">等待中</td>
          </tr>
        </tbody>
      val retroSnippet = new Retros
      transaction {
        val rs: NodeSeq = retroSnippet.list(template)
        //        logger.debug(s"rs=\n$rs")
        (findByTagAndAttr(rs, "tr", "class", "warning retro-line") \ "td")(0).text should be === "等待中的回顾"
        (findByTagAndAttr(rs, "tr", "class", "error retro-line") \ "td")(0).text should be === "进行中的回顾"
        (findByTagAndAttr(rs, "tr", "class", "success retro-line") \ "td")(0).text should be === "结束的回顾"
        //检查一下第一个tr的内容是否符合期望
        findByTagAndAttr((rs \\ "tr")(0), "td", "class", "retro-title").text should be === "等待中的回顾"
        findByTagAndAttr((rs \\ "tr")(0), "td", "class", "retro-creator").text should be === "用户1"
        findByTagAndAttr((rs \\ "tr")(0), "td", "class", "retro-status").text should be === "等待中"
      }
    }
    it("也可以显示指定id的retro详情") {
      testS("http://localhost:8080/detail?id=1") {
        S.param("id").get should be === "1"
        val session: LiftSession = new LiftSession("", StringHelpers.randomString(20), Empty)
        S.initIfUninitted(session) {
          //test session here
        }
      }
    }
  }

  before {
    import com.github.notyy.retroboard.model._
    import org.squeryl.PrimitiveTypeMode._
    Class.forName("org.h2.Driver")

    SquerylRecord.initWithSquerylSession(
      Session.create(DriverManager.getConnection(
        "jdbc:h2:~/dev/h2/testdb",
        "", ""), new H2Adapter))
    transaction {
      Session.currentSession.setLogger(SqlLog.logSql)
      RetroDB.initDB()
      RetroDB.retros.insert(
        List(
          new Retro(title = "结束的回顾", creator = "用户3", passCode = "12345", status = RetroStatus.Complete),
          new Retro(title = "进行中的回顾", creator = "用户2", passCode = "12345", status = RetroStatus.OnGoing),
          new Retro(title = "等待中的回顾", creator = "用户1", passCode = "12345", status = RetroStatus.Waiting)
        )
      )
    }
  }

  after {
    LiftRules.unloadHooks.toList.foreach(_())
  }
}
