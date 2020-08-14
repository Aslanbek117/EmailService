package routes
import akka.actor.ActorSystem
import akka.event.LoggingAdapter
import akka.http.scaladsl.server.Directives.{as, complete, concat, entity, path, pathPrefix, post}
import akka.stream.Materializer
import com.sendgrid.SendGrid
import handler.EmailService
import models.EmailDTO
import serializer.JSONSerializer

import scala.concurrent.ExecutionContext


case class EmailRouting(sendGrid: SendGrid)(implicit system: ActorSystem, materializer: Materializer, ec: ExecutionContext, log: LoggingAdapter) extends JSONSerializer {
  //v2/sendEmail
  //here you need to define all the pathes that another services should be able to access method that sending emails
  val route =
    concat(
        post {
          concat(
            pathPrefix("v5") {
              concat(
                path("email") {
                  // entity is EmailDTO
                  entity(as[EmailDTO]) { email =>
                    complete(EmailService(sendGrid).sendEmail(email.to, email.from, email.subject, email.text))
                  }
                }
              )
            }
          )
        }
    )
}
