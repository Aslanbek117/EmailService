package handler

import akka.actor.ActorSystem
import akka.event.LoggingAdapter
import com.sendgrid.SendGrid
import models.Response
import serializer.JSONSerializer
import spray.json._

import scala.concurrent.ExecutionContext

case class EmailService(sendGrid: SendGrid)(implicit val system: ActorSystem, ex: ExecutionContext, log: LoggingAdapter) extends JSONSerializer {
  def sendEmail(to: Array[String], from: String, subject: String, text: String): Response = {

    //we sending the request to SendGrid API - this service provider allow us to send Email via their applications
    log.debug("[SendEmail]")


    val mail = new SendGrid.Email();
    mail.setFrom(from)
    mail.setTo(to)
    mail.setText(text)
    mail.setSubject(subject)


    val responseFromSg = sendGrid.send(mail)


    //status 200 is OK - operation completed successfully
    // status code 404 is something went wrong
    val response: Response = Response(responseFromSg.getMessage, responseFromSg.getCode, responseFromSg.getStatus)
    log.debug(s"[SendEmail] response from SendGrid: ${response}")
    response
  }
}
