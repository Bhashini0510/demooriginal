@register
Feature: Register User to dsportal app
  I want to use this template for my feature file
  
  @success
 	Scenario Outline: Successful registration
    Given User is in Register page
    When User enters credentials and click on Register button
    | username  | password   |
		| User76985 | P@ssword21 |
    Then  Navigates to home page 
    And User able to register successfully
  
  @failure
  Scenario Outline: User is not able to register into the dsportalapp
    When User fills credentials and click on Register button 
    |username|password  |confirm    |   		        message              |
    |        |Password12|Password12.|Please fill out this field.         |
    |tester2 |          |Password12.|Please fill out this field.         |
    |tester2 |Password12|           |Please fill out this field.         |
    |tester  |Password12|Password12.|Invalid User Name	                 |
    |tester2 |Password  |Password   |Invalid password                    |
    |tester2 |Password12|Password   |password_mismatch:The two password fields didn’t match.|
		|nump1234|Password12|Welcome1	  |User already registered		         |
		|tester2 |Pass      |Pass    	  |password should have more characters|
		|tester2 |1234      |1234    	  |password cannot be only numbers     |
		|tester2 |tester2   |tester2    |password cannot be same as user name|
		|tester2 |Welcome1  |Welcome1   |password cannot be a common password|
		
		Then User is not able to register and alert is displayed

		@navigation
    Scenario: Navigate to login page from Register page
    Given User is in Register page
    When User click Login button
    Then User navigates to login page
		
		@navigation
    Scenario: Signout validation
    Given User is in home page with valid "User62345" and "P@ssword21"
    When User clicks the Signout button
    Then Able to Signout and land in home page with Signin link