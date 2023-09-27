Feature: Posts endpoint e2e
  @ApiRegression
  Scenario: GET request to posts endpoint
    Given User sends GET request to posts URL and validates status code

  @ApiRegression
  Scenario: POST request to posts endpoint
    Given User sends POST request to posts URL
    And User validates status code 201
    And User retrieves ID
  @ApiRegression
  Scenario: Verification of payload after post call
    Given User sends GET request to posts URL with the id of POST call

  @ApiRegression
  Scenario: PUT request to posts endpoint
    Given User sends PUT request with body to posts URL id

  @ApiRegression
  Scenario: Verification of payload after PUT call
    Given User sends GET request to posts URL with the id of POST call

    Then User validates body provided on PUT call
  @ApiRegression
  Scenario: PATCH request to posts endpoint
    Given User sends PATCH request with change of element to posts URL id

  @ApiRegression
  Scenario: Verification of payload after PATCH call
    Given User sends GET request to posts URL with the id of PATCH call

  @ApiRegression
  Scenario: DELETE request verification to posts URL with id
    Given User sends DELETE request to posts URL with id
    Then User validates payload id is not existing

#    These are Scenarios in CUCUMBER

