Feature: Google Various Search Terms

	Scenario: Google Kitten Images
		Given That I can access google
		When I search for kittens
		And I select the images tab
		Then I should be able to view images of kittens