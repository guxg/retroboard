package com.github.notyy.retroboard.model

import org.squeryl.{Session, KeyedEntity, Schema}
import org.squeryl.PrimitiveTypeMode._
import net.liftweb.common.Loggable
import com.github.notyy.retroboard.lib.SqlLog

object RetroDB extends Schema with Loggable {
  val retros = table[Retro]

  def initDB() {
    transaction {
      Session.currentSession.setLogger(SqlLog.logSql)
      this.drop
      this.create
    }
  }
}

object RetroStatus extends Enumeration {
  type RetroStatus = Value
  val Waiting = Value(1, "等待中")
  val OnGoing = Value(2, "进行中")
  val Complete = Value(3, "已结束")
}

class Retro(val title: String, val creator: String, var status: RetroStatus.RetroStatus = RetroStatus.Waiting) extends KeyedEntity[Long]{
  val id: Long = 0
  def this() = this("", "", RetroStatus.Waiting)
}

object Retro {
  import RetroDB._

  def listAll: List[Retro] = from(retros)(r => select(r) orderBy(r.id desc)).toList
}
