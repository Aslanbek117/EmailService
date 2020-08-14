package serializer

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import models.{EmailDTO, Response}
import spray.json.DefaultJsonProtocol

trait JSONSerializer extends SprayJsonSupport  with DefaultJsonProtocol {
  implicit val emailJsonFormat = jsonFormat4(EmailDTO)
  implicit val responseFormat = jsonFormat3(Response)
}
