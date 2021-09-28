# Avdiji's Chess
<br><br>
## 1. Rules

In this section the Rules of this game are briefly explained. For more detailed information check
out http://www.sakkpalota.hu/index.php/en/chess/rules.
<br><br>

### 1.1 Starting the Game

In the starting position, square a1 is always black. The queen is always on her own color, next to the king in the
middle. White starts the game, afterwards the players alternately move.
<br><br>

### 1.2 Chess Piece Moves
Every piece has its very own, individual movement-set, however no matter what, the king can never be captured. Therefore, the Game will limit the player's options to move, to prevent the king from being captured by a hostile piece. Also, a piece can not capture another piece of its own color.

#### 1.2.1 The King

The King can move one square in any direction

#### 1.2.2 The Queen

The Queen can move any amount of fields straight or diagonally in any direction

#### 1.2.3 The Rook

The Rook can move any amount of fields in a straight line horizontally or vertically

#### 1.2.4 The Bishop

The Bishop can move any amount of fields diagonally in any direction

#### 1.2.5 The Knight

The Knight is the only piece that can jump over other pieces independently of it being a piece of its own or one of the
enemy.

Knight movement set:

- Forward, Forward, Forward, Left/Right
- Back, Back, Back, Left/Right
- Left, Left, Left, Forward/Back
- Right, Right, Right, Forward/Back

#### 1.2.6 The Pawn

The Pawn can move 2 fields (assuming that's his first move) or 1 field forward. It can also move 1 field forward diagonally, if
an enemy piece is located in said field.
<br><br>

### 1.3 Special Moves

#### 1.3.1 Upgrading the Pawn

If the pawn reaches the other side of the Field it can be upgraded to one of the following pieces:

- Queen
- Rook
- Knight
- Bishop

#### 1.3.2 En Passant

The Pawn can move forward diagonally while also capturing another Pawn of the enemy, which is sitting left or right next
to it. The rules to perform this move are as follows:

- the Black Pawn must be located on the 4th column. The white Pawn can execute the move, when sitting on the 5th column.
- Only if the enemy Pawn moves to the 5th/4th row by moving 2 Fields from the starting position the move can be executed
- The enemy pawn must move next to another, hostile Pawn, located in corresponding column (this means the pawn, executing the En Passant must already be at column 4/5 before the enemy pawn moves two steps forward)

#### 1.3.3 Rochade

The King is able to move 2 Fields to the left or right, by "switching" places with one of the Rooks. Again there are a
few rules to perform this move:

- The King hasn't moved once from his starting position and neither has the rook
- The King is not in an endangered position (neither before, nor after the Rochade)
- There is no piece located between the King and the Rook
  <br><br>
  
### 1.4 Ending the Game

#### 1.4.1 Check

The King can never be captured, therefore the Player is forced to move the King out of danger, if it is about to be
captured. This can be done by:

- Moving the King
- Capturing the piece that gave the check
- Blocking the check

#### 1.4.2 Checkmate

If none of the moves above can be performed the King is not able to escape from the check, and the game is therefore
finished.

#### 1.4.3 Patt

If the King of the Player is not in a check, but all the possible moves he can execute are illegal, or if all the pieces of both players, except for their kings, have been captured, the game is considered to be a Stalemate (draw), and the game is therefore finished.
<br><br>

## 2. Graphical User Interface (GUI)

The program will provide a [GUI](Chess_GUI_Template.pdf) for the Players. The GUI is divided in several frames:
<br><br>

## 2.1 Main Menu

The Main Menu is the "starting point" of the GUI it provides multiple game mode options such as:

- Multiplayer (local)
- Multiplayer (online)
- Player vs AI (not to sure if I will program an AI yet)

### 2.1.1 Server Login

The server login allows the players to insert a hostname, and a port to connect to a server.
The Window allows the Player to cancel the Game and disconnect from the server.

#### 2.1.2 Loading Window

This window will appear, if only 1 Player has logged into the server and needs to wait for the other player to connect.

#### 2.1.3 Error Screen

This window will appear, if the input of the Player is invalid. It includes an error message, and a button which leads
the player back to the server login.
<br><br>

## 2.2 Game Window

This window is where the players can actually play the game. It consists of the Game field, which inherits all the game
logic. two Graveyards, where the captured pieces of the players will be displayed. And two several buttons, that allow
each player to forfeit the game.

### 2.2.1 Scoreboard

The Scoreboard consists of a Message, which declared the winner of the Game and the circumstances of the victory. It
also includes two buttons one of it leads back to the Main Menu, the other exits the game.
<br><br>

## 3. Playing the Game
This section is all about how to get the game, as well as the server, running
<br><br>

### 3.1 Downloading and executing the Game

1. Download the runnable jar files in the "out" - folder of this repository
2. Download the Latest JDK Version (at least jdk 16.0.2)
3. Add JDK to your environment variables (PATH)
    - Copy the path to the bin folder of the installed jdk version
    - Go to advanced system setting
    - Click environment variables
    - find the PATH environment variable and select it
    - click new and paste the Path to your previously copied bin folder
4. You can now run the Game by double-clicking the previously downloaded jar file, which you can find in the "out" - folder

### 3.2 Running the Server and Playing online

1. Download, install and setup ngrok (https://dashboard.ngrok.com/get-started/setup)
2. Run the Server in the "out" - folder with the following cmd command (windows): "java -jar Chess_Server.jar"
3. Run ngrok with the following cmd command (windows): "ngrok http 80"
4. Start the Game and select the "Online" gamemode
5. For the hostname you need to insert the ip, which is generated by ngrok: "xxxx-xx-xxx-xxx-xx.ngrok.io"
6. Insert "80" as your port, and you're ready to play, as soon as you press the "start" - button
7. Stop the server, and ngrok after you finished playing, by pressing "ctrl + c"
