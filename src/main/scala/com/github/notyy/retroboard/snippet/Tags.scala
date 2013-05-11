package com.github.notyy.retroboard.snippet

import net.liftweb.common.Loggable
import net.liftweb.util.Helpers
import Helpers._
import com.github.notyy.retroboard.model.StuffTag
import net.liftweb.http.js.JsCmds.SetValById

class Tags extends Loggable {
  def render = {
    "*" #> StuffTag.listAll.map {
      tag =>
        <button class="tag-button" onclick={SetValById("tagInput", tag.name)}>
          {tag.name}
        </button>
    }
  }
}
