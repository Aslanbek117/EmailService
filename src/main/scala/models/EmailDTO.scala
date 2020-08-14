package models


//email dto - email data transfer object
final case class EmailDTO(to: Array[String], from: String, subject: String, text: String)
//json

// {
//"to": ["a.sekenov117@gmail.com"],
//"from": "a.sekenov117@gmail.com",
//"subject": "demonstrate",
//"text": "test"
//}


final case class Response(var message: String, var result: Int, var status: Boolean)