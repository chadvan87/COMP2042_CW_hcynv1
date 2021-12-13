# COMP2042_CW_hcynv1 
# How to run
There are two ways to run this project<br>
1. Run the jar file (CourseWork-3.0) in the following directory : COMP2042_CW_hcynv1\target <br>
2. Run the PlayGame.java in the following directory: COMP2042_CW_hcynv1\src\main\java <br>
# Refactoring Activities
### 1 Meaningful Package naming/organization
Change the class GraphicMain.java to PlayGame.java to to help users know the class that runs the game.
Organize all the classes into meaningful package.
### 2 Breaking up big classes
Classes like Wall and Ball are broken down into small classes like Levels and Crack to follow the Single Responsibility Principle.
### 3 Improve Encapsulation
All variables for each class have been encapsulated by making them private rather than public. 
Classes that receive and set data from other classes must utilize the get and set methods.
### 4 Use of MVC Design Pattern
MVC is created by dividing the main classes into three packages: Model, View and Controller. The benefit of this is that it's easy to modify
### 5 Use of Singleton Pattern
Classes like ScoreController, Sounds and Leaderboard implemented Singleton Pattern to ensure there's only one instance from them.
This avoids making the program more difficult by instantiating the same controller many times.
### 6 Adding 10 JUnit Test Classes
To make sure the program runs perfectly.
### 7 Correct use of Maven build tools
Generate the jar file and write dependencies in pom.xml file 
# Additions
### 1 Making new buttons
Info and Score buttons were created to do the following tasks:<br>
Info Button: Show the player how to play the game<br>
Score Button: Show the leaderboard that contains all of the previous high scores and highest score at the top<br>
### 2 Creating permanent high score list and high score pop up
all_highscore.dat: Store all of the previous player's high scores.<br>
leaderboard.dat: Rearrange the above file in order from highest to lowest<br>
A pop up screen will appear when you get a new high score.<br>
### 3 Adding 5 playable levels
The game has 9 levels with 5 new ones.
### 4. Exciting features and reward
Fast Brick will increase the ball and bar speed for 5 seconds. It also change the bar to red<br>
Slow Brick will slow the ball speed for 5 seconds. It also change the bar to yellow<br>
Breaking different Brick will give you different scores
### 5. Simple features
Adding different sounds to the game<br>
Adding background to the main menu
### 6. Java Docs
Javadocs have been added to all classes to help with code comprehension.

