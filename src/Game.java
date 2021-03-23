import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Game extends JFrame {
	private JFrame frame;
	private Container pane;
	private String currentPlayer;
	private JButton[][] board;
	private boolean hasWinner;
	private boolean hasTied;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem quit;
	private JMenuItem newGame;

	public static void main(String[] args) {
		//causes our game to be run on the Event Dispatch Thread (EDT), instead of the current thread
		//all updates on our GUI must be done through the EDT
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Game();
			}
		});
	}

	public Game() {
		frame = new JFrame();
		pane = frame.getContentPane(); //grab the content pane layer of our JFrame and adjust the layout
		pane.setLayout(new GridLayout(3,3)); //set the layout of our window to be a 3x3 grid
		frame.setTitle("Tic-Tac-Toe");
		frame.setSize(500,500);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);

		currentPlayer = "X";
		board = new JButton[3][3];
		hasWinner = false;
		hasTied = false;
		initializeBoard();
		initializeMenuBar();
	}



	//create a menubar with JMenu and JMenubar, then add it to our JFrame
	private void initializeMenuBar() {
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		newGame = new JMenuItem("New Game");
		//action listener for clicking "New Game" - reset board
		newGame.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				resetBoard();
			}
		});
		quit = new JMenuItem("Quit");
		//action listener for clicking "Quit", ends the session/program
		quit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		//need to add newGame and quit options to our menu, then add our menu to our menubar
		menu.add(newGame);
		menu.add(quit);
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);

	}

	//loop through board and reset text of buttons back to empty strings
	private void resetBoard() {
		currentPlayer = "X";
		hasWinner = false;
		hasTied = false;
		for(int i=0; i < 3; i++) {
			for(int j=0; j < 3; j++) {
				board[j][i].setText("");
			}
		}
	}

	//our starting board - all buttons have empty text and are added to our content pane
	private void initializeBoard() {
		for(int i=0; i < 3; i++) {
			for(int j=0; j < 3; j++) {
				JButton button = new JButton("");
				button.setFont(new Font(Font.SANS_SERIF,Font.BOLD,100)); //type, bold or not, size
				board[j][i] = button;
				/*
					For every button in our board, an action listener is put in place for it, so that when the user
					clicks on it, it responds by replacing the text with X or O

					Also, if user clicks a button, getSource() obtains the object on which the event occurred, so we performed
					the "clicking" event on "this" button, so we grab this button, get its text and check if it's equal to ""
				 */
				button.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						//checks to see if this is a valid move or not
						//get source obtains the object on which the event occurred, so we performed the "clicking" event on "this" button, so we grab this button, get its text and check if
						//it's equal to ""
						//if((JButton) e.getSource() == button (aka equal to an empty string, we can make the move) &&
						if(((JButton) e.getSource()).getText().equals("") && !hasWinner && !hasTied) { //will return 'who clicked me?', when player clicks the button, it will return itself
							button.setText(currentPlayer);
							hasWinner();
							tiedGame();
							switchPlayer();
						}
					}

				});
				pane.add(button);
			}

		}
	}
	//simple method to switch a player's turn
	private void switchPlayer() {
		if(currentPlayer.equals("X")) {
			currentPlayer = "O";
			return;
		}
		currentPlayer = "X";
	}

	//loops through our board to see if there are any empty spaces. If there are, return, if not display "Tied Game" and reset board
	private void tiedGame() {
		for(int i = 0; i < 3; i++) {
			for(int j =0; j < 3; j++) {
				if(board[j][i].getText().equals("")) { //if there are still spaces left and no winner, then the game is still going on
					return;
				}
			}
		}
		JOptionPane.showMessageDialog(frame, "Tied Game!");
		hasTied = true;
		resetBoard();
	}
	
	/*
	 0,0  1,0  2,0
	 0,1  1,1  2,1
	 0,2  1,2  2,2
	 */

	//checks each possible way to win to see if our current player has their symbol in all three spots, if so, hasWinner = true, reset the board
	private void hasWinner() {
		// 0,0 1,0 2,0 top horizontal
		if(board[0][0].getText().equals(currentPlayer) && board[1][0].getText().equals(currentPlayer) && board[2][0].getText().equals(currentPlayer)) {
			//parentComponent determines the Frame in which the dialog is displayed, in our case, we want the frame we created - f
			JOptionPane.showMessageDialog(frame, "Player " + currentPlayer + " has won.");
			hasWinner = true;
			resetBoard();
		}
		// 0,1 1,1 2,1 middle horizontal
		else if(board[0][1].getText().equals(currentPlayer) && board[1][1].getText().equals(currentPlayer) && board[2][1].getText().equals(currentPlayer)) {
			JOptionPane.showMessageDialog(frame, "Player " + currentPlayer + " has won.");
			hasWinner = true;
			resetBoard();
		}
		// 0,2 1,2 2,2 bottom horizontal 
		else if(board[0][2].getText().equals(currentPlayer) && board[1][2].getText().equals(currentPlayer) && board[2][2].getText().equals(currentPlayer)) {
			JOptionPane.showMessageDialog(frame, "Player " + currentPlayer + " has won.");
			hasWinner = true;
			resetBoard();
		}
		// 0,0 0,1 0,2 left vertical
		else if(board[0][0].getText().equals(currentPlayer) && board[0][1].getText().equals(currentPlayer) && board[0][2].getText().equals(currentPlayer)) {
			JOptionPane.showMessageDialog(frame, "Player " + currentPlayer + " has won.");
			hasWinner = true;
			resetBoard();
		}
		// 1,0 1,1 1,2 middle vertical
		else if(board[1][0].getText().equals(currentPlayer) && board[1][1].getText().equals(currentPlayer) && board[1][2].getText().equals(currentPlayer)) {
			JOptionPane.showMessageDialog(frame, "Player " + currentPlayer + " has won.");
			hasWinner = true;
			resetBoard();
		}
		// 2,0 2,1 2,2 right vertical
		else if(board[2][0].getText().equals(currentPlayer) && board[2][1].getText().equals(currentPlayer) && board[2][2].getText().equals(currentPlayer)) {
			JOptionPane.showMessageDialog(frame, "Player " + currentPlayer + " has won.");
			hasWinner = true;
			resetBoard();
		}
		// 0,2 1,1 2,0 bottom-left to top-right diagonal
		else if(board[0][2].getText().equals(currentPlayer) && board[1][1].getText().equals(currentPlayer) && board[2][0].getText().equals(currentPlayer)) {
			JOptionPane.showMessageDialog(frame, "Player " + currentPlayer + " has won.");
			hasWinner = true;
			resetBoard();
		}
		// 0,0 1,1 2,2 top-left to bottom-right diagonal
		else if(board[0][0].getText().equals(currentPlayer) && board[1][1].getText().equals(currentPlayer) && board[2][2].getText().equals(currentPlayer)) {
			JOptionPane.showMessageDialog(frame, "Player " + currentPlayer + " has won.");
			hasWinner = true;
			resetBoard();
		}

	/*
		Note: This could be simplified, however each condition in these if statements are extremely long, so cramming them into a single if statement
		(just to avoid duplicating three lines of code) would be very messy and I feel like I'd make a mistake somewhere.
		This allows me to clearly see the conditions needed for the person to win (all the possibilities of getting three in a row)
	*/
		
		
		
	}
}