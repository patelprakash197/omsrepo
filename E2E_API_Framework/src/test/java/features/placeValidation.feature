Feature: Validating Place APIs

	@AddPlace @Regression
  Scenario Outline: Verify if a place is being succesfully added using AddPlaceAPI
    Given AddPlace payload with "<Name>" "<Language>" "<Address>"
    When user calls "addPlaceAPI" with "POST" http request
    Then API call is successful with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to "<Name>" using "getPlaceAPI"
    
  Examples:
  |Name      | Language | Address                           |
  |TestName  | German   | 29, 5th street, World cross centre|
#  |TestName2 | Spanish  | Sea cross centre                  |

	@DeletePlace @Regression
	Scenario Outline: Verify if deletePlace funtionality is working
		Given DeletePlace payload
		When user calls "deletePlaceAPI" with "POST" http request
		Then API call is successful with status code 200
		And "status" in response body is "OK" 
    