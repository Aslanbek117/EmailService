import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import com.sendgrid.SendGrid
import com.typesafe.config.{Config, ConfigFactory}
import models.Response
import routes.EmailRouting
import serializer.JSONSerializer

import scala.io.StdIn

object Boot extends App  with JSONSerializer {
  implicit val system: ActorSystem = ActorSystem( "my-system")
  implicit val executionContext = system.dispatcher
  implicit val config: Config = ConfigFactory.load()
  implicit val log = Logging(system, "emailService")
  // loading env variables
  // Try(config.getString("email.user").getOrElse("a.sekenov117@gmail.com") - best practice
  // the env variables stored in application.conf file - you can Edit it for your requirements
  final val email = config.getString("email.user")
  final val password = config.getString("email.password")
  final val applicationPort = config.getInt("application.port")
  final val applicationHost = config.getString("application.host")
  final val sendGridService = new SendGrid(email, password)

  val mail = new SendGrid.Email();
  mail.setFrom("a.sekenov117@gmail.com")
  val from: Array[String] = Array("a.sekenov117@gmail.com")
  mail.setTo(from)
  mail.setText("Marcel test")
  mail.setSubject("Marcel")

  val responseFromSg = sendGridService.send(mail)


  //status 200 is OK - operation completed successfully
  // status code 404 is something went wrong
  val response: Response = Response(responseFromSg.getMessage, responseFromSg.getCode, responseFromSg.getStatus)

  println(response)
  final val emailRoutes = EmailRouting(sendGridService)
  final val bindingFuture = Http().newServerAt(applicationHost, applicationPort).bind(emailRoutes.route)


  // 0.0.0.0 address listening for all interfaces - you can accept it by using 0.0.0.0:8080 or localhost:8080 or 127.0.0.1:8080
  log.debug(s"Server online at http://${applicationHost}:${applicationPort}/\nPress RETURN to stop...")
  StdIn.readLine() // let it run until user presses return
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => system.terminate()) // and shutdown when done
}

// JSON


// you have an scala applicaton that uses Akka Http as server
// you can send emails via next path localhost:8080/v1/email method is POST
// JSON
// {
//"to": ["a.sekenov117@gmail.com"],
//"from": "a.sekenov117@gmail.com",
//"subject": "demonstrate",
//"text": "test"
//}

