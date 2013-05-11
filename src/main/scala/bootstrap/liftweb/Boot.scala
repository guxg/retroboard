package bootstrap.liftweb

import net.liftweb._
import squerylrecord.SquerylRecord
import util._
import Helpers._

import common._
import http._
import sitemap._
import Loc._
import java.sql.DriverManager
import org.squeryl.Session
import org.squeryl.adapters.H2Adapter
import net.liftweb.squerylrecord.RecordTypeMode._
import com.github.notyy.retroboard.model.{RetroDB, MiscDB}
import com.github.notyy.retroboard.lib.SqlLog
import com.github.notyy.retroboard.state.currUser
import scala.xml.Null

/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot extends Loggable {
  def boot {
    logger.info(s"currently run in ${Props.mode} mode")
    Class.forName("org.h2.Driver")

    SquerylRecord.initWithSquerylSession(
      Session.create(DriverManager.getConnection(
        Props.get("db_url").get,
        "", ""), new H2Adapter))

    S.addAround(new LoanWrapper {
      override def apply[T](f: => T): T = inTransaction {
        Session.currentSession.setLogger(s => SqlLog.logSql(s))
        f
      }
    })

    RetroDB.initDB()

    // where to search snippet
    LiftRules.addToPackages("com.github.notyy.retroboard")

    // Build SiteMap
    val entries = List(
      Menu.i("Home") / "index", // the simple way to declare a menu
      Menu.i("Detail") / "detail",
      // more complex because this menu allows anything in the
      // /static path to be visible
      Menu(Loc("Static", Link(List("static"), matchHead_? = true, "/static/index"),
        "Static Content")))

    // set the sitemap.  Note if you don't want access control for
    // each page, just comment this line out.
    LiftRules.setSiteMap(SiteMap(entries: _*))

    //Show the spinny image when an Ajax call starts
    LiftRules.ajaxStart =
      Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)

    // Make the spinny image go away when it ends
    LiftRules.ajaxEnd =
      Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    // Force the request to be UTF-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    // Use HTML5 for rendering
    LiftRules.htmlProperties.default.set((r: Req) =>
      new Html5Properties(r.userAgent))

    //config notice style
    LiftRules.noticesAutoFadeOut.default.set((noticeType: NoticeType.Value) => Full((1 seconds, 2 seconds)))

    LiftScreenRules.messageStyles.default.set({ nt: NoticeType.Value => nt match {
      case NoticeType.Notice => Null
      case NoticeType.Warning => Null
      case NoticeType.Error => Null
    }})
  }
}
