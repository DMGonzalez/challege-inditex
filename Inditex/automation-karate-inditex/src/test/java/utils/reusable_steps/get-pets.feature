@ignore
Feature: GET USER

  Background:
    * def petJson = read('file:src/test/java/petstore/request/pet-request.json')
    * def JSONCommands = Java.type('JsonCommands')

  @getpet
  Scenario: send http request (get)
    * def status = findStatus
    Given url petJson.base_url + status
    When headers petJson.headers
    Then method GET
    And status 200
    * def JSONResponse = response

  @getIdAndName
  Scenario: get id and names from response
    * def status = findStatus
    Given url petJson.base_url + status
    When headers petJson.headers
    Then method GET
    And status 200
    * print response
    * def fun = function(array){ return { id: array.id, name: array.name } }
    * def result = karate.map(response, fun)

  @getAmountNames
  Scenario: get amount of pets with the same name
    * def list = listOfNames
    * print list
    * def jc = new JSONCommands()
    * def fun = function(array){ return array.name }
    * def result = karate.map(listOfNames, fun)
    * print '### CANTIDAD DE NOMBRES ENCONTRADOS ###'
    * print result
    * string names = result
    * def namesOfPets = jc.getAmountsNames(names)