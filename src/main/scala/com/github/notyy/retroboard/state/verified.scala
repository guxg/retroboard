package com.github.notyy.retroboard.state

import net.liftweb.http.SessionVar

object verified extends SessionVar[Map[String, Boolean]](Map())
