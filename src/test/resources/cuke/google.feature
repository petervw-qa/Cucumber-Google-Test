Feature: Google Various Search Terms

#	Scenario: Google Kitten Images
#		Given That I can access google
#		When I search for kittens
#		And I select the images tab
#		Then I should be able to view images of kittens
		
		Scenario Outline: Google "<Animal>" Images
		Given That I can access "<domain>"
		When I search for "<Animal>"
		And I select the images tab
		Then I should be able to view images of "<Animal>"
		
		Examples:
		| Animal | Domain |
		| Kittens | www.google.com |
		| Puppies | www.google.com |
		| Turtles | www.google.com |
		
		@ignore
		Examples:
		| Animal | Domain |
		| Rats | www.google.com |
		