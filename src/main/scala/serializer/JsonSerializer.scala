package serializer

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import models.{EmailDTO, Response}
import spray.json.DefaultJsonProtocol

//i'm spray - its a java and scala package that help you witn object serialization and desialization

trait JSONSerializer extends SprayJsonSupport  with DefaultJsonProtocol {
  implicit val emailJsonFormat = jsonFormat4(EmailDTO) // 4 field
  implicit val responseFormat = jsonFormat3(Response)
}
