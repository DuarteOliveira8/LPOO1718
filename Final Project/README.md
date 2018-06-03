# GeoDash
Our project consists on an application that runs on android and desktop. It's a platforming game in which you have to jump to avoid obstacles.

## Setup and installation procedure
To install the development environment, first, download our code from GitHub and open Android Studio and choose the option to import project. Locate the folder you just downloaded and click "ok". If a message pops up to update Gradle, refuse it! The development environment should now be ready.

To install on Android, the APK should be transferred to the phone and be opened. The Android should install the app quickly.

On PC, open the jar file should be enough.

## Development documentation
### UML diagram

<br><img src="https://imgur.com/5xAjKD3.jpg" width="900"><br>

### Design patterns
We used the STATE design pattern in our project to find a solution for two problems. The block's state during the game and the application's state to cycle through the menus and game.
The block has two states: JUMPING and SLIDING. The first one is used to define when the block is on the air and the second state is for when the block is on the ground. This help solve the problem when the block's is on the air and you want to prevent the user from jumping again.
The application's state is more complex. It has one state for every screen on the app to cycle through them when the user clicks on the buttons that call for a change of scenery. There's one more state called NEWGAME used for when the user clicks on the play button on the main menu, the retry and restart buttons on the game over and pause menus and the next level on the level complete menu. This state calls for the start game function and changes the state to GAME right after.
We also use the TEMPLATE METHOD design pattern for the menus. All the menus are the same. They all have buttons and background images so we created an abstract class to help with elements created for every menu.

### Major difficulties along the way
We didn't have many difficulties along the way. LibGDX was very easy to learn and very useful. But one difficulty we can both agree on is how hard it was to understand box2d. We spent most of our time trying to figure out how it works but once it was implemented, it was very useful to work with, specially for game collisions.

### Lessons learned
With this project, we definitely learned how important it is to keep our code clean and organised and to better understand what we are doing and to discover bugs and problems with our application. This has changed us on how we look at organisation and will definitely help with future projects!

### Overall time spent developing
We spent from one to two weeks developing the game. We wish we could spend more time on it to make our application the best it could be.

### Work distribution amongst team members
The work was distributed equally between both members. We always worked together.

## User manual
The instructions for our application are very straight forward.

MAIN MENU
<br><img src="https://imgur.com/dR90IuF.png" width="500"><br>

To play the game click on the play button (centre) of the main menu. It's a platforming game in which you have to jump to avoid obstacles and the tutorial is on the settings menu.
The square moves on its own. You must click on the screen or on the spacebar, in case you are using the desktop application, to jump. To win the game reach the end of the map by avoiding the obstacles and jumping on the platforms.


<br><img src="https://imgur.com/nnSan8n.png width="500"><br>

There is also a level picker menu on the left button of the main menu in which you can click on the level you want to play next. The first level is unlocked but the other two levels have to be unlocked by completing the previous ones.

<br><img src="https://imgur.com/38H9jHx.png width="500"><br>

You can also go to the settings menu on the right button of the main menu to see the game tutorial, as previously said, and to change the block's skin. Each skin is themed after his own level.


<br><img src="https://imgur.com/DAO2Lpy.png width="500"><br>

<br><img src="https://imgur.com/vPBpDUg.png width="500"><br>
