package com.github.notyy.retroboard.util

import scala.xml.{Utility, NodeSeq}

object XmlUtil {
  def findByTagAndAttr(src: NodeSeq, tagName: String, attrName: String, attrValue: String) = {
    (src \\ tagName) filter (e => (e \ s"@$attrName").text == attrValue)
  }

  def trim(nodes: NodeSeq): NodeSeq = nodes.map(Utility.trim)
}
