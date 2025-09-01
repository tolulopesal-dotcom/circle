README
How to Run

Ensure  Java (JDK 11 or later) and Maven installed.

Open a terminal in the project folder (where pom.xml is located).

Compile and run with:

mvn clean javafx:run

Controls

Buttons:

Grow → increases circle size (lambda)

Shrink → decreases circle size (anonymous inner class)

Keyboard:

Up Arrow → Grow

Down Arrow → Shrink

Mouse:

Double-click anywhere → toggles animation (start/pause)

Features

Circle radius limited between 10 and 100.

Timeline animation moves the circle left ↔ right.

Status label shows messages when size limits are reached or animation state changes.
