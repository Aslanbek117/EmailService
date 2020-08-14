## **Hello, Marcel.**

## I'm using SendGrid Email service provider to send a Emails. [https://sendgrid.com/](https://sendgrid.com/)

You need to create a free account to use this service provider and bind your email that will send a emails

After all, paste your email (test_email@gmail.com) and password (qwerty123) in application.conf file. 
Also you can configure application host and port. By default it is 0.0.0.0 and 8080
Thats all.

## request
    curl --location --request POST 'localhost:8080/v1/email' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    	"to": ["a.sekenov117@gmail.com"],
    	"from": "a.sekenov117@gmail.com",
    	"subject": "LATEST TESTING",
    	"text": "Hello test"
    }'

## response
    {
    "message": "{\"message\":\"success\"}",
    "result": 200,
    "status": true
    }

## You are great.

