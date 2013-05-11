package com.github.notyy.retroboard
package snippet

import net.liftweb.util._
import net.liftweb.common._
import java.util.Date
import Helpers._
import lib.DependencyFactory

class HelloWorld extends Loggable {

  // replace the contents of the element with id "time" with the date
//  def howdy = { (x:NodeSeq) =>
//    logger.info("time input = ")
//    logger.info(x.mkString)
//      val f = ("#time *" #> date.map(_.toString))
//      f(x)
////    <p>time</p>
//  }

   lazy val date: Date = DependencyFactory.time.vend // create the date via factory

   def howdy = ".time *+" #> date.toString

}

