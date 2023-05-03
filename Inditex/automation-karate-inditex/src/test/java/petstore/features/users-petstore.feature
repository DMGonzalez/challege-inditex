@petstore @run
Feature: USER REQUEST IN PET STORE

  Background:
    * def postUser = 'file:src/test/java/utils/reusable_steps/create-user.feature'
    * def getUser = 'file:src/test/java/utils/reusable_steps/get-user.feature'
    * def getPet = 'file:src/test/java/utils/reusable_steps/get-pets.feature'
    * def random = call read('file:src/test/java/utils/util/randoms.feature')
    * def userJson = read('file:src/test/java/petstore/request/user-request.json')
    * def petJson = read('file:src/test/java/petstore/request/pet-request.json')
    * def bodyPost = userJson.post.body
    * def id = random.number(3)
    * def username = random.string(9)

  @petstore_001 @run
  Scenario: POST AND GET USER IN PET STORE
    Given def requestBody = {body : '#(bodyPost)', id: '#(id)', username: '#(username)'}
    When def createUser = call read(postUser+'@createuser') requestBody
    Then def getUser = call read(getUser+'@getuser') {username : '#(username)'}
    * match getUser.responseGet contains createUser.requestSend

  @petstore_002 @run
  Scenario: GET PET SOLD
    Given def findStatus = 'sold'
    When def getPet = call read(getPet+'@getpet') {findStatus : '#(findStatus)'}
    Then print getPet.JSONResponse

  @petstore_003 @run
  Scenario: GET PET SOLD - ID AND NAME
    Given def findStatus = 'sold'
    When def getIdAndName = call read(getPet+'@getIdAndName') {findStatus : '#(findStatus)'}
    Then print getIdAndName.result

  @petstore_004 @run
  Scenario: GET AMOUNT OF PETS WITH SAME NAME
    Given def findStatus = 'sold'
    When def getIdAndName = call read(getPet+'@getIdAndName') {findStatus : '#(findStatus)'}
    Then def getAmountNames = call read(getPet+'@getAmountNames') {listOfNames : '#(getIdAndName.result)'}