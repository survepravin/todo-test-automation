# QA Automation

## Framework architecture and design
Hybrid (Page Object Model and Test driven) with TestNG. According to the Page Object Model, you should keep the tests and element locators separately. This will keep the code clean and easy to understand and maintain.The Page Object approach makes automation framework in a testing programmer friendly, more durable and comprehensive. Another important advantage is our Page Object Repository is Independent of Automation Tests. If you keep a separate repository for page objects, it helps us to use this repository for different purposes with different frameworks like you will be able to integrate this repository with other tools like JUnit/TestNG/Cucumber/etc.
As this is single page application, we couldn't completely make use of POM framework, So I had to make it test driven.

## To run the automated tests
This automation framework is using *Selenium Webdriver* tool with *TestNG* test framework. To run the tests on your windows machine do the following.

#### STEPS
##### Follow commands to run tests via cmd
1. `https://github.com/survepravin/todo-test-automation.git`
2. `cd todo-test-automation`
3. `mvn install -DskipTests`
4. `mvn test -Dheadless=on` or `mvn test` (will take values from config.properties)

##### Follow commands to run tests from eclipse or intelliJ
1. `git clone https://github.com/survepravin/todo-test-automation.git`
2. Import project in IDE as a maven project
3. Right click on pom.xml -> Run As -> Maven install


#### REPORTS
Local report will be generated in `todo-test-automation\test-output\index.html`

#### HOW IT IS TESTED
Test suites are mainly divided in two, positive and negative tests. When tests are executed from POM.xml or testng.xml, Execution will start from BaseTest.java because of the @BeforeSuite hook. in this before driver will be intialised and base url will be opened in the specified browser in config.properties, after the program will start executing methods with @Test annotations.
#### WHAT IS BEING TESTED
To-Do application is being tested with positive and negative scenarios. 
1. **POSTIVE tests**
- User should be able to create to-do list
- User should be able to delete item from the list
- Increase/Decrease should be change item count
- Newly added item should be at the bottom
- Completed item should be striked out and decrease the item count
2. **NEGATIVE tests**
- Item is not untill user presses enter key
- Blank should be added as a item in the list
- Editing marked item should remain in completed status
- Item should handle really long paragraphs

## Project Structure

### src/test/resources/config.properties
Web configurations are stored in this file as key value pair. _**EnvironmentVariables.java**_ class will access all the values.

### src/main/java/utils
Utility classes that can be used across the project. *BrowserFactory.java* all browser can be configured here. *WebdriverWrapper.java* has common selenium common methods like explicit waits, etc.

### src/test/java/pages
Pages represents Application page, UI elements and methods are defined in these classes.

### src/test/java/testcases
All the tests in this package. e.g PostiveTest.java and NegativeTest.java.
BaseTest.java has hooks as a pre-requiste for tests.

#### CI/CD with Github-Actions
As PR is created or PR is merged to Main branch, github-actions will be trigged. This will help in regression tests and continuous deployment.
_**https://github.com/survepravin/todo-test-automation/actions/workflows/maven.yml**_
