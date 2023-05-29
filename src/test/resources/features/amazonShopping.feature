@smokeTest
Feature: Amazon shopping feature

  Scenario Outline: Test add to cart functionality

    Given I am on the Amazon homepage "<footertext>"
    When I enter "<text_for_men>" in search box and click search button
    And I add first product to cart with "<quantity2>"
    Then I open cart and verify total price for quantity "<quantity2>" for men
    When I enter "<text_for_women>" in search box and click search button
    And I add first product to cart with "<quantity1>"
    Then I open cart and verify total price for quantity "<quantity1>" for women
    Then I change the quantity for first selected item from "<quantity2>" to "<quantity1>"
  Examples:
    |footertext                                     |text_for_men  | quantity2 | text_for_women | quantity1 |
    |© 1996-2023, Amazon.com, Inc. or its affiliates|hats for men  | 2         | hats for women | 1         |