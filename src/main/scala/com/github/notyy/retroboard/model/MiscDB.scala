package com.github.notyy.retroboard.model

import org.squeryl.{Schema, KeyedEntity, Session}
import org.squeryl.PrimitiveTypeMode._
import net.liftweb.common.Loggable
import com.github.notyy.retroboard.lib.SqlLog


object MiscDB extends Schema with Loggable {
  val stuffTags = table[StuffTag]

  on(stuffTags)(tags =>
    declare(
      tags.name is(unique, indexed("stufftag_name_idx"))
    ))

  def initdb(): Unit = {
    transaction {
//      Session.currentSession.setLogger(s => logger.info(s))
      MiscDB.drop
      MiscDB.create
      stuffTags.insert(
        new StuffTag("tag1") :: new StuffTag("tag2") :: new StuffTag("tag3") :: Nil
      )
    }
  }
}

class StuffTag(val name: String) extends KeyedEntity[Long] {
  val id: Long = 0
}

object StuffTag extends Loggable {

  import MiscDB._

  def listAll = {
    Session.currentSession.setLogger(s => SqlLog.logSql(s))
    from(stuffTags)(select(_)).toList
  }

  def findByName(name: String): Option[StuffTag] = {
    logger.info("find by name")
    from(stuffTags)(t =>
      where(t.name === name)
        select (t)).toList.headOption
  }

  def insert(tag: StuffTag) = {
    stuffTags.insert(tag)
  }
}

