@ignore
  Feature: GET USER

    Background:
      * def userJson = read('file:src/test/java/petstore/request/user-request.json')
      * def getUser = userJson.get

    @getuser
    Scenario: send http request (get)
      * def path = username
      Given url userJson.base_url
      And path userJson.path + path
      When headers getUser.headers
      Then method GET
      And status 200
      * print response
      * def responseGet = response