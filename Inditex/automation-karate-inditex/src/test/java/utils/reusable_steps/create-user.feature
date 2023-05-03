@ignore
Feature: CREATE USER

  Background:
    * def userJson = read('file:src/test/java/petstore/request/user-request.json')
    * def postUser = userJson.post
    * def random = call read('file:src/test/java/utils/util/randoms.feature')
    * def firstName = random.string(9)
    * def lastName = random.string(9)
    * def email = random.email()
    * def password = random.string(9)
    * def phone = random.number(9)

  @createuser
  Scenario: send http request (post)
    * set body.id = id
    * set body.username = username
    * set body.firstName = firstName
    * set body.lastName = lastName
    * set body.email = email
    * set body.password = password
    * string phoneNumber = phone
    * set body.phone = phoneNumber
    * def requestSend = body
    Given url userJson.base_url
    And path userJson.path
    When headers postUser.headers
    And request body
    Then method POST
    And status 200
    * print response
    * def postResponse = response