all: 	test report  ##Run both tasks. 

test:   ##Run maven command that executes test
		mvn clean test

report:  ##Generate test report and opens up on new browser tab
		allure serve allure-results          	




