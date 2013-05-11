package com.github.notyy.retroboard.snippet

import scala.xml.Unparsed
object Html5Shiv {
  def render = Unparsed("""<!--[if lt IE 9]>
    <script src="/vendor/js/html5shiv.js">
    </script><![endif]-->""")
}
