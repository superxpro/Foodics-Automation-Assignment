# Foodics-Automation-Assignment
Automation Tasks For Foodics

## Introduction

This repository contains automation scripts for two tasks:

1.  A web automation task using Selenium and Java to test the functionality of the Amazon website. The task includes logging in, navigating to the video games section, filtering and sorting the results, adding products to the cart, and verifying the total cost of the items.
    
2.  A REST API automation task using Rest Assured to test the Foodics payment API. The task includes logging in and retrieving merchant information using the provided endpoints.
    

## Prerequisites

To run the scripts, you will need the following tools and libraries:

-   [Java](https://www.java.com/en/download/) (version 8 or higher)
-   [Maven](https://maven.apache.org/install.html) (for building and running the Selenium scripts)
-   [TestNG](https://testng.org/doc/download.html) (for running the Selenium scripts)
-   [Allure](https://docs.qameta.io/allure/#_installing_a_commandline) (for generating the test reports for the Selenium scripts)
-   [Selenium](https://www.selenium.dev/downloads/) (for automating the web browser)
-   [Extent Reports](https://extentreports.com/docs/versions/4/java/) (for generating the test reports for the Selenium scripts)
- [Shaft Engine](https://github.com/ShaftHQ/SHAFT_ENGINE) (for Java)
-   [Rest Assured](http://rest-assured.io/) (for testing the REST API)
- [Docker](https://docs.docker.com/compose/install/) (Run Selenium Tests on Chrome Inside the Docker Container) 
> **While Building These Tasks:** I Applied **Page Object Model** and **Anonymous Fluent POMs** and Also while dealing with Elements I applied **Fluent Element Actions**.

## Running the Selenium scripts

To run the Selenium scripts, follow these steps:

1.  Clone this repository to your local machine.
2.  Navigate to the `Amazon-task` directory.
3.  Build the project using the following command:

`mvn clean install` 

4.  Run the tests using the following command:

`mvn test` 

This will run the tests and generate an Allure report in the `target/allure-report` directory. To view the report, use the Allure command line tool:

`allure serve target/allure-report` 

### Also, This Task Can run with docker, you will need to have Docker installed on your machine. Then, navigate to the `resources` folder and run the following command:

`docker-compose -f selenium4.yml up --scale chrome=4 --scale edge=0 --scale firefox=0 -d`

## Running the REST API scripts

To run the REST API scripts, follow these steps:

1.  Clone this repository to your local machine.
2.  Navigate to the `Foodics-api-task` directory.
3.  Run the tests using the following command:

`mvn clean test` 

This will run the tests and generate an Allure report in the `target/allure-report` directory. To view the report, use the Allure command line tool:

`allure serve target/allure-report
`` `

## Task 1 (Testing framework: Selenium in Java)
### The task involves automating the following scenario on the Amazon Egypt website:


1.  Open the Amazon.eg website and log in.
2.  Open the "All" menu from the left side.
3.  Click on "Video Games" and then choose "All Video Games".
4.  From the filter menu on the left side, add the "Free Shipping" filter and the "New" condition filter.
5.  In the right side, open the sort menu and sort by price (high to low).
6.  Add 2-3 products that cost less than 15k EGP to the cart. If there are no products that cost less than 15k EGP, move to the next page.
7.  Make sure that the products have been added to the cart.
8.  Add an address and choose cash as the payment method.
9.  Make sure that the total amount of all items is correct, including the shipping fees if they exist.

Note: The order was not completed.

## Task 2: Testing the Foodics API using Rest Assured and Java

In this task, I used the following tools and technologies:

-   Testing framework: Rest Assured
-   Reporting: Allure Report
-   Data-driven testing: Excel files

I created three test cases using the following endpoints:

-   [https://pay.foodics.dev/public-api/v1/App/Login](https://pay.foodics.dev/public-api/v1/App/Login)
-   [https://pay.foodics.dev/public-api/v1/App/GetMerchantInfo](https://pay.foodics.dev/public-api/v1/App/GetMerchantInfo)

I read the credentials for the login API from an Excel file. The username is "[omarfoodics2+test2@gmail.com](mailto:omarfoodics2+test2@gmail.com)" and the password is "sk190517225LM@$*".


#### Why using Shaft 
Shaft is a simple, lightweight framework that makes it easy to write and maintain functional tests for web applications.


#### Extra note if using Intllij
Due to a known issue with IntelliJ you need to edit your run configuration templates before running your tests by following these steps:  
- Open 'Edit Run/Debug Configurations' dialog > Edit Configurations... > Edit configuration templates...  
- Select **TestNG** > Listeners > and add these listeners one by one:  
`com.shaft.tools.listeners.AlterSuiteListener`, `com.shaft.tools.listeners.SuiteListener`, `com.shaft.tools.listeners.InvokedMethodListener`  
	
- After saving the changes, remember to delete any old test runs you may have triggered by mistake before adding the needed config.


## Authors

-   **Alaa Emad** 

