package com.github.notyy.retroboard.lib

import net.liftweb.common.Loggable

object SqlLog extends Loggable {
  def logSql(sql: String) = logger.info(sql)
}
