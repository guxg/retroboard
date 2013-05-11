package com.github.notyy.retroboard.util

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FunSpec
import net.liftweb.common.Loggable
import java.io.File

class FileUploadUtilSpec extends FunSpec with ShouldMatchers with Loggable{
  describe("FileUploadUtil"){
    it("should automatically decide a unique name for user's upload file") {
      val userNickName: String = "notyy"
      val fileName: String = "img.png"
      val filePreName = "img"
      val fstUpload = FileUploadUtil.assignFileName(userNickName, fileName)
      logger.info(s"fstUpload=$fstUpload")
      fstUpload.startsWith(s"$userNickName/$filePreName") should be === true
      val sndUpload = FileUploadUtil.assignFileName(userNickName, fileName)
      sndUpload.startsWith(s"$userNickName/$filePreName") should be === true
      fstUpload should not equal sndUpload
    }

    it("should save file to disk") {
      val path = "/var/tmp/img/testuser/test.png"
      var file = new File(path)
      try{
        file.exists() should not be true

        FileUploadUtil.saveFile(path, new Array[Byte](5))

        file = new File(path)
        file.exists() should be === true

      }finally{
        file.delete()
      }
    }

  }
}
