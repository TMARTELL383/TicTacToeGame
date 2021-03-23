My Tic-Tac-Toe Game!
By: Tyler Martell

Introduction:

I made this Tic-Tac-Toe game following a tutorial I found on YouTube. Although I used it for a lot of guidance, 
I did create many of the functions on my own, such as InitializeMenuBar(), resetBoard(), switchPlayer(), tiedGame(), 
and hasWinner(). This project was a great way to reinforce GUI programming concepts and ideas such as    content 
panes, JFrames, and the Event Dispatch Thread (EDT). An analysis of each function is below:


Method Overview:

main() -- standard in all java programs, I used this to create the actual Game and utilized the Event Dispatch 
Thread for safe thread handling. Although my knowledge of the EDT and threads in general is a bit foggy, I have a 
basic understanding of what it is doing. The EDT is in charge of handling the events that occur in my GUI application,
and it is safe practice to handle all the threads with the EDT rather than other threads. The invokeLater will put 
our Runnable in a queue with other Runnables (although we only have one, so we don't see this), the EDT will then take 
Runnables from that queue and call their run method. Again, a bit of a foggy explanation and mostly following what
people say on stack overflow. I would like to explore this more in order to truly understand it.


Game() -- Creates the JFrame and obtains the content pane of that frame, setting its layout to be a simple 3x3 grid.
The hasWinner and hasTied are initialized to false to start. The board and menu on the GUI are set up.


initializeMenuBar() -- Creates the MenuBar with options "New Game" and "Quit". Action Listeners are established for
when the user clicks on either of these options. The board will be reset (via the resetBoard method) (New Game) or the
program will terminate, closing the GUI (Quit).


resetBoard() -- Restarts the game. hasWinner and hasTied are set to false once again, and the text for each button
in our grid is set to an empty string (meaning no player has "made a move" there).


initializeBoard() -- This was by far the most difficult method to write in this program, as well as the most confusing.
Here is where I needed some guidance from the tutorial I was watching. When initializing the board, each button's text
has to be set to an empty string because no one has made any moves yet. Then, at least to my understanding, we need
an actionlistener establishes for EACH button. This actionlistener will listen for when the button is pressed
(e.getSource()) and look at its text. If the text has an empty string and there is no winner or tied game yet, that
is a valid move and the player's X or O will replace the empty string. After the player makes the move, we need to check
if that player won with that move. If not, we then check for a tied game. If there's no tied game, the game is still
ongoing, so we switch the player's turn and add the new button to our content pane.


switchPlayer() -- Simple method that switches the player's turn. If the currentPlayer is "X", we switch to "O". If it 
is "O", we switch to "X".


tiedGame() -- The board is looped through to see if there are any buttons with empty string text. If there is no empty
string text and all the buttons' texts are filled with "X" or "O" (and there's no winner), we have a tied game. A
dialog will pop up on the screen and the game will reset. If there is at least one empty string, we return.


hasWinner() -- In total, there are eight possible ways to win at tic-tac-toe. Three ways horizontal and vertical and
two ways diagonal. All eight ways are checked by obtaining the text from each button for each individual winning
method. For example, to check if "X" has won in the left most vertical column, the text for the buttons located
at (0,0), (0,1), and (0,2) on the board must contain "X". If it does, "X" has won and the game will be reset. This
applies for the other seven methods of winning as well. Although there is repeated code:

			JOptionPane.showMessageDialog(frame, "Player " + currentPlayer + " has won.");
			hasWinner = true;
			resetBoard();

I think it is necessary to make the if statements as clear and concise as possible. I could have bundled all the if
statements into one giant if statement that checks for any of the methods to see if "X" or "O" won. Then I could write
these three lines only once. However, this would make things very complicated and quite messy. Additionally, if there's
a mistake in the if statement condition, finding it would be a nightmare. This way, the condition for each way to win
is laid out in a nice way, despite the fact that the three lines above must be repeated. I'll take it.
