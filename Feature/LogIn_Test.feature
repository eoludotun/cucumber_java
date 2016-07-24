Feature: Login Action

Scenario: Successful Login with Valid Credentials
	Given User is on Home Page
	When User Navigate to Billing Information Page
	And User enters UserName and PassWord
	Then User LogOut from the Application

