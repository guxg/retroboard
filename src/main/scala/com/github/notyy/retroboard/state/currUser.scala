package com.github.notyy.retroboard.state

import net.liftweb.http.SessionVar
import net.liftweb.common.{Empty, Box}

object currUser extends SessionVar[Box[String]](Empty)
