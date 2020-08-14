package models

final case class EmailDTO(to: Array[String], from: String, subject: String, text: String)

final case class Response(var message: String, var result: Int, var status: Boolean)