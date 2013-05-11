package com.github.notyy.retroboard.util

import net.liftweb.util.Helpers._
import net.liftweb.http.{OkResponse, BadResponse, ResponseWithReason}
import net.liftweb.util.Props
import java.io.{FileOutputStream, File}
import com.github.notyy.retroboard.state.currUser
import net.liftweb.common.{Loggable, Full}
import java.io.File

object FileUploadUtil extends Loggable {

  def uploadFile(userNickName: String, fileName: String, data: Array[Byte]): String = {
    val repoPath = Props.get("image_repo_path").getOrElse("/var/tmp/img")
    val outputFilePath = assignFileName(userNickName, fileName)
    saveFile(s"$repoPath/$outputFilePath", data)
    outputFilePath
  }

  def assignFileName(userNickName: String, fileName: String): String = {
    val (name, ext) = fileName.splitAt(fileName.lastIndexOf('.'))
    s"$userNickName/${name + millis + ext}"
  }

  def saveFile(path: String, data: Array[Byte]) {
    val outputFile = new File(path)
    val folder = outputFile.getParentFile
    if(!folder.exists()) {
      folder.mkdirs()
    }
    if (!outputFile.exists()) {
      outputFile.createNewFile()
    }
    val out = new FileOutputStream(outputFile)
    out.write(data)
    out.flush()
    out.close()
    //          logger.info(s"file write to ${outputFile.getAbsolutePath}")
    //    logger.info("uploading")
    //    if (req.uploadedFiles.exists(file => !(file.mimeType).startsWith("image"))) {
    //      logger.info("upload failed, because not a image file")
    //      ResponseWithReason(BadResponse(), "Only images")
    //    }
    //    val imgRepoPath = Props.get("image_repo_path", "/var/tmp/test")
    //    logger.info(s"imgRepoPath=$imgRepoPath")
    //    val repoDir = new File(imgRepoPath)
    //    val rs = currUser.get match {
    //      case Full(u) => {
    //        val userImgFolder = new File(s"$imgRepoPath/${u.nickName}")
    //        if (!userImgFolder.exists() || !userImgFolder.isDirectory) {
    //          userImgFolder.mkdirs()
    //        }
    //        for (file <- req.uploadedFiles) {
    //          val fileName = file.fileName
    //          logger.info(s"Received: $fileName")
    //          val outputFile = new File(assignFileName(imgRepoPath, u.nickName.get, fileName))
    //          logger.info(s"try to save file to ${outputFile.getAbsoluteFile()}")
    //          if (!outputFile.exists() || outputFile.isDirectory) {
    //            outputFile.createNewFile()
    //          }
    //          val out = new FileOutputStream(outputFile)
    //          file.file.foreach(out.write(_))
    //          out.flush()
    //          out.close()
    //          logger.info(s"file write to ${outputFile.getAbsolutePath}")
    //        }
    //        OkResponse()
    //      }
    //      case _ => {
    //        logger.warn("unlogged user can't upload pics")
    //        ResponseWithReason(BadResponse(), "Only logged in user can upload images")
    //      }
    //    }
    //    logger.info(rs.toString)
    //    rs
  }
}
