Feature: Filpkart application validation

  @FunctionalTest
  Scenario Outline: Validate flipkart application
    Given Launch "<Browser>" browser
    When I launch the Flipkart Application
    And Login with "<Email>" and "<Password>"
    And I search for "<ProductName>" in the product listing page
    And I get the number of products listed
    And I Add one random item to the shopping cart with "<Zipcode>"
    And I checkout
    Then Validate the Name in checkout page
    Then Validate the Price in checkout page
    Then I close the browser
    Examples:
      |Browser  |Email              | Password      | ProductName |Zipcode|
#      |Chrome   |testing@gmail.com  |test123test123 | camera      |560035 |
      #Milk- as the camera product is not available in the site
      |Chrome   |9952000712  |Vaadi@123 |  Milk       |560035 |
#      |firefox  |testing@gmail.com  |test123test123 |  camera      |560035 |
#      |ie       |testing@gmail.com  |test123test123 |  camera     |560035 |
