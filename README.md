# FitDay_Rada_Mishurova

What is FitDay?
FitDay.com is a free web site designed to help track and analyze the important aspects of diet and fitness. A personal FitDay account serves as a food and exercise journal. Users can track the foods they eat and the exercises they do on a daily basis. Based on journal entries, FitDay analyzes diet, exercise, and weight, generating graphical reports on the important aspects of fitness, including fat, carbohydrates, and protein intakes, calorie expenditure, weight loss and etc.

Prerequisites installed
1. Java JDK 17.0.3.1
2. Maven
3. Allure

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

Test suites
Regression tests
This suite includes tests from files: LoginTest, ActivityLogTest, FoodLogTest.

Running the suit
To execute the test suite, simply run:
mvn -Dtest=regression.xml test


Smoke tests
This suite includes tests from files: LoginTest, ActivityLogTest, FoodLogTest.

Running the suit
To execute the test suite, simply run:
mvn -Dtest=smoke.xml test


Negative tests
This suite includes tests from files: LoginTest(Data-driven), ActivityLogTest, FoodLogTest.

Running the suit
To execute the test suite, simply run:
mvn -Dtest=negative.xml test

Running tests
Run tests using mvn clean test command
mvn clean test

Running a single test use command mvn -Dtest=LogoutTest test
