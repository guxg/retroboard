package com.github.notyy.retroboard.snippet

import net.liftweb.util._
import Helpers._
import com.github.notyy.retroboard.state.currUser
import net.liftweb.http.{SHtml, S}

class Logout {
  def render = {
    "* [onclick]" #> SHtml.ajaxInvoke(() => {
      currUser.remove()
      S.redirectTo("/index")
    }
    )
  }
}
