Feature: Add product to the shopping cart
  In order to purchase a product as part of a future order
  As a consumer
  I want to place the product in the shopping cart

  Scenario: Select valid options for product characteristics such as color and size
    Given user is on the product’s description page
    When user selects valid options for product characteristics
    And user adds the product to cart
    Then the product is added to the shopping cart

  Scenario: Select invalid options for product characteristics such as color and size
    Given user is on the product’s description page
    When user selects invalid options for product characteristics
    And user adds the product to cart
    Then the product is not added to the shopping cart

  Scenario: Add a product on the Today’s Deals page to the shopping cart
    Given user is on the Today’s Deals page
    When user adds the product to cart
    Then the corresponding product is added to the shopping cart