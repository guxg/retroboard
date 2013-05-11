package com.github.notyy.retroboard.snippet

import net.liftweb.util.Helpers._
import scala.xml.{Text, NodeSeq}
import com.github.notyy.retroboard.model.{RetroStatus, RetroDB, Retro}
import net.liftweb.http.{S, SHtml}
import com.github.notyy.retroboard.state.currUser


class Retros {

  //  def generateDonationView(myDonations: List[(Donation, User)]): CssSel = {
  //    ".stuff-block *" #> myDonations.map {
  //      Function.tupled((d, u) => {
  //        val state = if(d.stuffState.get == StuffState.Processing) "processing" else "over"
  //        showBasicInfo(d, u) &
  //          ".description *" #> d.stuffStory.get &
  //          ".sender-tel *+" #> d.tel.get &
  //          ".fetch-day *+" #> d.fetchDay.get &
  //          ".stuff-state [class+] " #> state
  //      }
  //      )
  //    }
  //  }

  def list = {
    import RetroStatus._
    val retroStatusMap = Map(Waiting -> "warning", OnGoing -> "error", Complete -> "success")
    val allRetros: List[Retro] = Retro.listAll
    if (allRetros.isEmpty) {
      "*" #> Text("还没有人进行回顾")
    } else {
      ".retro-line" #> allRetros.map {
        retro =>
          ".retro-line [class]" #> retroStatusMap(retro.status) &
            ".retro-title *" #> retro.id &
            ".retro-creator *" #> retro.creator &
            ".retro-status *" #> retro.status.toString
      }
    }
  }

  def createButton = {
    "* [onclick]" #> createNewRetro
  }

  def createNewRetro = SHtml.ajaxInvoke(() => {
    currUser.remove()
    S.redirectTo("/index")
  }
  )
}
