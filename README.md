# FitDay_Rada_Mishurova

What is FitDay?

FitDay.com is a free web site designed to help track and analyze the important aspects of diet and fitness. A personal FitDay account serves as a food and exercise journal. Users can track the foods they eat and the exercises they do on a daily basis. Based on journal entries, FitDay analyzes diet, exercise, and weight, generating graphical reports on the important aspects of fitness, including fat, carbohydrates, and protein intakes, calorie expenditure, weight loss and etc.

Prerequisites installed
1. Java JDK 17.0.3.1
2. Maven
3. Allure

Libraries used in the project
1. selenium version 3.141.59;
2. webdrivermanager version 5.2.0;
3. testNG version 7.4.0;
4. lombok version 1.18.24;
5. log4j-core version 2.18.0;
6. allure-testng version 2.18.1;

Setting up config
1. Clone this repository.
2. Enter your email and password in config.properties.


Checklist
1. Verify that user can log in the system.
2. Verify that user can't log in the system with invalid password or email data.
3. Verify that user can add food data to food log.
4. Verify that user can not add invalid food data to food log.
5. Verify that user can remove food data from food log.
6. Verify that user can edit food data in food log.
7. Verify that user can add activity data to activity log.
8. Verify that user can not add invalid activity data to activity log.
9. Verify that user can remove activity data from activity log.
10. Verify that user can edit activity data in activity log.
11. Verify food search relevance in food log.
12. Verify that user can add weight data to weight log.
13. Verify that user can add body parameters to body log.
14. Verify that user can edit mood diary in mood log.

Test suites

Regression tests
This suite includes tests from files: LoginTest, ActivityLogTest, FoodLogTest.
How to run the suit
To execute the test suite, simply run:
mvn -Dtest=regression.xml test


Smoke tests
This suite includes tests from files: LoginTest, ActivityLogTest, FoodLogTest, WeightLogTest, BodyLogTest.
How to run the suit
To execute the test suite, simply run:
mvn -Dtest=smoke.xml test


Negative tests
This suite includes tests from files: LoginTest(Data-driven), ActivityLogTest, FoodLogTest.
How to run the suit
To execute the test suite, simply run:
mvn -Dtest=negative.xml test

In order to run a single test use command mvn -Dtest=LogoutTest test
