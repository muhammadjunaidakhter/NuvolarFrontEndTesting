# Nuvolar FrontEnd Testing Task

This document demonstrates the use of Selenium WebDriver with Java and the Cucumber framework for automated testing task. The project provides automation testing task the add to cart functionality of amazon web application.

**Table of Contents**
* Author
* Prerequisites
* Setup
* Files Structure
* Running the Tests
* Reporting
* Contributing 

**Author**

Name: Muhammad Junaid Akhter

Designation: Sr. Automation Test Engineer

Purpose: Automate testing task

**Prerequisites**

Before running the tests, ensure that the following software is installed on your machine:
* Java Development Kit (JDK) 19
* Chrome Web Browser
 
**Setup**

1. Clone this repository to your local machine with below command.
git clone https://github.com/muhammadjunaidakhter/NuvolarFrontEndTesting.git
3. Open the project in IDE, my preferred IDE is IntelliJ
4. Install the maven project dependencies by running the following command in the project directory:
    1. Open project in directory and run 
mvn install 
    2. IntelliJ, Eclipse, and NetBeans IDE have built-in Maven integration. You can import your Maven project into the IDE, and it will automatically resolve and download the dependencies specified in the pom.xml file


**Files Structure**

Feature Files: The BDD scenarios are defined in the src/test/resources/features directory. These feature files provide a clear understanding of the expected behavior and serve as executable specifications. 
Test Runner: The src/test/java/testRunner class acts as the test runner, allowing you to execute the test cases defined in the feature files. It coordinates the execution of scenarios and generates test reports. 
Step Definitions: The src/test/java/stepDefinitions directory contains step definition files, including Hooks and the amazonShopping feature file. Step definitions map the Gherkin steps to the corresponding code implementation, providing the automation logic for each scenario. 
Amazon Shopping Page: The src/test/java/pages/amazonShoppingPage class represents a Page Object Model (POM) class. It encapsulates the locators and functions related to the Amazon shopping page, allowing for better organization and maintenance of the test automation code.
<img src="/readmePicture/fileStructure.png" alt="Cucumber-Reports-Picture">


**Running the Tests**

To execute the automated tests, follow these steps:
1. Through TestRunner
    1. Go to the src/test/java/testRunner file and click JUnit TestRunner icon.
2. Through Feature File
    1. Go to the src/test/resources/features/*.feature file and click cucumber TestRunner icon. This will run the Cucumber scenarios defined in the feature files (src/test/resources/features/*.feature) using the Selenium WebDriver.

**Reporting**

This project uses Cucumber's built-in reporting feature to generate detailed test reports. After running the tests, you can find the HTML reports in the target/cucumber-reports.html directory. Open the index.html file in a web browser to view the test execution summary, including the number of passed and failed scenarios, along with detailed step-by-step results.
<img src="/readmePicture/Cucumber-Reports-Picture.png" alt="Files-Picture">

**Contributing**

If you'd like to contribute to this project, please follow these steps:
1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Make your changes and test them thoroughly.
4. Commit your changes with descriptive commit messages.
5. Push your changes to your forked repository.
6. Submit a pull request, explaining the purpose and details of your changes.
