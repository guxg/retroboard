package com.github.notyy.retroboard.snippet

import net.liftweb.common.Loggable


class Donations extends Loggable {
//  def list = {
//    logger.info("listing donations")
//
//    val tagId: Box[String] = S.param("tagId")
//    val donations = tagId.map(tid => Donation.listByTag(tid.toLong)).getOrElse(Donation.listAll)
//
//    val tagNode = if (tagId.isEmpty) {
//      ".tag-tip *" #> "全部物品"
//    } else {
//      ".tag-name *" #> MiscDB.stuffTags.lookup(tagId.get.toLong).get.name
//    }
//
//    val donationNode = if (donations.size > 0) {
//      generateDonationView(donations)
//    } else {
//      logger.info("no donation yet")
//      ".stuff-block" #> <h3 style="font-weight:normal;color: #999; background-color:white; font-size: 16px">目前还没有人捐献</h3>
//    }
//    tagNode & donationNode
//  }
//
//  def myList = {
//    logger.info("my donations list")
//    val myDonations = Donation.myList
//    if (myDonations.size > 0) {
//      generateDonationView(myDonations)
//    } else {
//      logger.info("no donation yet")
//      ".stuff-block" #> <h3 style="font-weight:normal;color: #999;font-size: 16px">您还没有发布物品</h3>
//    }
//  }
//
//
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
//
//  def detail = {
//    S.param("id") match {
//      case Full(donationId) => {
//        logger.info(s"show detail information of donation who's id = ")
//        val (donation, user, tag) = Donation.lookUp(donationId.toLong)
//        lastDonationDetail(Full(donation)) //save to requestVar for later usage
//        val applyButton =
//          if (donation.stuffState.get == StuffState.Processing) {
//            ".apply-button [href]" #> s"/apply-stuff?id=$donationId" &
//            ".apply-button [class+]" #> "btn-primary"
//          } else {
//            ".apply-button !!" #> <button class="btn">已结束</button>
//          }
//
//        showBasicInfo(donation, user.get) &
//          ".stuff-tag *" #> {
//            tag.map(t => <a href={s"/index?tagId=${t.id}"}>
//              {t.name}
//            </a>).getOrElse(NodeSeq.Empty)
//          } &
//          ".description *" #> donation.stuffStory.get &
//          ".content-img *" #> donation.imgUrl.get.map(url => <img src={"img/" + url}></img>).getOrElse(scala.xml.NodeSeq.Empty) &
//          ".donation-edit" #> {
//            if (currUser.get.map(_.id).getOrElse(0) == donation.senderId.get) <a href={s"/publish?id=$donationId"}>编辑</a> else NodeSeq.Empty
//          } &
//          applyButton
//      }
//      case _ => {
//        S.error("没有找到donationId")
//        "*" #> <p></p>
//      }
//    }
//  }
//
//  def shareToSinaWeibo = {
//    val (donationId, stuffName): (Long, String) = lastDonationDetail.get.map(d => (d.id, d.stuffName.get)).getOrElse((-1, ""))
//    val w = 106
//    val h = 28
//    val path = S.hostAndPath + s"/detail?id=$donationId"
//    logger.debug(s"path=$path")
//    val params = List("url" -> path, "type" -> "5", "appkey" -> "",
//      "title" -> s"我刚捐赠了物品$stuffName,快来看看吧！",
//      "pic" -> "",
//      "ralateUid" -> "",
//      "language" -> "zh_cn",
//      "rnd" -> millis.toString
//    )
//    val srcUrl = appendParams("http://hits.sinajs.cn/A1/weiboshare.html", params)
//
//    "*" #> <iframe allowTransparency="true" frameborder="0" scrolling="no"
//                   src={srcUrl} width={w.toString} height={h.toString}></iframe>
//  }

}
