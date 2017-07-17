Feature: To test the functionality of addition and deletion of hotel bookings within hotel bookings system

  Background:
    Given I am on the hotel bookings site

  Scenario: Able to add a hotel booking successfully
    When I populate all the booking fields with valid values
    And click on the save button
    Then I check the new hotel booking is added to the booking list

  Scenario: Able to delete an existing hotel booking successfully
    Given atleast one hotel booking entry is already existing
    When I delete a hotel booking
    Then I check the deleted hotel booking is removed from the booking list

