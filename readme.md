# WeatherMate
Team members working on the proposal:
- Dylan Weitz
- Jonathan Luc
- Edward Vong

Team members working on the presentation:
- Dylan Weitz
    - presentation slides
- Jonathan Luc
    - UML Diagram
- Edward Vong
    - Demo
    - UML, Sequence, State, Scenario Diagrams

Team memebers working on the project and code: 
- Dylan Weitz
    - Report
    - Code
        - weatherVisualContoller
        - weatheVisual
        - updated Visuals for home
        - WeatherCard
- Jonathan Luc
    - Report
- Edward Vong
    - Report
    - Code
        - App.java
        - HomeUIManager
        - WeatherContoller
        - All contents in ContainerManager (BaseContainter, CityContainer, CoordinateContainter, Search Container Manager, APIManager folder, Utilities folder, etc.)

Problem Statement:
- Currently, there are few weather apps that do not employ mal-business practices such as predatory and misinformative advertisements to provide information to users on the web. 

What is currently on market (References):
- Weather.com

Assumptions:
- The API that we are using will have the weather data for all locations that the user can input, and that the information is accurate.

Operating Environment:
- We can use a graphical user interface like javafx, and we can also deploy the program onto a webpage that can be publicly accessed. 

Intended Usage:
- To find data on present and future weather conditions. 

Diagrams:
- Link to Diagram readme.md [Readme Link](./diagrams/README.md)

Functionality:
- The application will pull information from an API to be processed and displayed.
- Utilizes JavaFX to employ GUI for user interaction with the application
- Most Information will be processed directly through Java with some supplemental help from other languages/resources if required.

Operations: 
- Searching weather by City, State, Country
- Searching weather by Zip
- Searching weather by Coordinates

Solution:
- By utilizing a free API we managed to provide weather information to users on that run this program without the need of ads that misinform and distract users from infromation they desire.

Steps to run code:
- In VScode
    - Go to MAVEN drop down on bottom of explorer menu
    - Go futher into weathermate drop down
    - Under the Plugins folder
        - click clean under the clean folder
        - then click run under the javafx folder
- In intellij
    - Go into App.java file [App File Link](./weathermate/src/main/java/app/App.java)
    - Click the Green arrow at line 17
    - For the project our SDK was java 22. We are not totally sure if other Javas maybe able to run it

Snapshot of running program:
[alt text](https://github.com/Edward-Vong/CS151-WeatherMate/blob/main/runningProgram.png)

