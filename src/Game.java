import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Game extends JFrame {
	private Container pane;
	private String currentPlayer;
	private JButton[][] board;
	private boolean hasWinner;
	private boolean hasTied;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem quit;
	private JMenuItem newGame;
	
	public Game() {
		super(); //calling JFrame's default constructor
		pane = getContentPane();
		pane.setLayout(new GridLayout(3,3)); //set the layout of your window
		setTitle("Tic-Tac-Toe"); //set a title for your window
		setSize(500,500); //set the size of your window
		setResizable(false); //make sure the user cannot resize the screen
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		currentPlayer = "X";
		board = new JButton[3][3];
		hasWinner = false;
		hasTied = false;
		initializeBoard();
		initializeMenuBar();
	}
	
	//have a method that tells the user if it is a tied game
	
	private void initializeMenuBar() {
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		newGame = new JMenuItem("New Game");
		newGame.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				resetBoard();
			}
		});
		quit = new JMenuItem("Quit");
		quit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		//need to add newGame and quit options to our menu, then add our menu to our menubar
		menu.add(newGame);
		menu.add(quit);
		menuBar.add(menu);
		setJMenuBar(menuBar);

	}
	//when user clicks the new game button
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
	
	private void initializeBoard() {
		for(int i=0; i < 3; i++) {
			for(int j=0; j < 3; j++) {
				JButton button = new JButton("");
				button.setFont(new Font(Font.SANS_SERIF,Font.BOLD,100)); //type, bold or not, size
				board[j][i] = button;
				button.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						//checks to see if this is a valid move or not
						if(((JButton) e.getSource()).getText().equals("") && hasWinner == false && hasTied == false) { //will return 'who clicked me?', when player clicks the button, it will return itself
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
	//change move
	private void switchPlayer() {
		if(currentPlayer.equals("X")) {
			currentPlayer = "O";
		}
		else {
			currentPlayer = "X";
		}
	}
	
	private void tiedGame() {
		//if we don't have a winner yet, we're going to loop through the entire board to see if there are any moves left to be made, if there are, return false, no tie
		
		for(int i = 0; i < 3; i++) {
			for(int j =0; j < 3; j++) {
				if(board[j][i].getText().equals("")) { //if there are still spaces left and no winner, then the game is still going on
					return;
				}
			}
		}
		JOptionPane.showMessageDialog(null, "Tied Game!");
		hasTied = true;
		resetBoard();
		
		//if there is no winner, and no spaces left to be filled, we have a tie game - return true
	}
	
	/*
	 0,0  1,0  2,0
	 0,1  1,1  2,1
	 0,2  1,2  2,2
	 */
	private void hasWinner() {
		// 0,0 1,0 2,0 top horizontal
		if(board[0][0].getText().equals(currentPlayer) && board[1][0].getText().equals(currentPlayer) && board[2][0].getText().equals(currentPlayer)) {
			//parentComponent determines the Frame in which the dialog is displayed, we don't want it to display in the parent frame, we want to display it in our frame we just created
			JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won.");
			hasWinner = true;
			resetBoard();
		}
		// 0,1 1,1 2,1 middle horizontal
		else if(board[0][1].getText().equals(currentPlayer) && board[1][1].getText().equals(currentPlayer) && board[2][1].getText().equals(currentPlayer)) {
			//parentComponent determines the Frame in which the dialog is displayed, we don't want it to display in the parent frame, we want to display it in our frame we just created
			JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won.");
			hasWinner = true;
			resetBoard();
		}
		// 0,2 1,2 2,2 bottom horizontal 
		else if(board[0][2].getText().equals(currentPlayer) && board[1][2].getText().equals(currentPlayer) && board[2][2].getText().equals(currentPlayer)) {
			//parentComponent determines the Frame in which the dialog is displayed, we don't want it to display in the parent frame, we want to display it in our frame we just created
			JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won.");
			hasWinner = true;
			resetBoard();
		}
		// 0,0 0,1 0,2 left vertical
		else if(board[0][0].getText().equals(currentPlayer) && board[0][1].getText().equals(currentPlayer) && board[0][2].getText().equals(currentPlayer)) {
			//parentComponent determines the Frame in which the dialog is displayed, we don't want it to display in the parent frame, we want to display it in our frame we just created
			JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won.");
			hasWinner = true;
			resetBoard();
		}
		// 1,0 1,1 1,2 middle vertical
		else if(board[1][0].getText().equals(currentPlayer) && board[1][1].getText().equals(currentPlayer) && board[1][2].getText().equals(currentPlayer)) {
			//parentComponent determines the Frame in which the dialog is displayed, we don't want it to display in the parent frame, we want to display it in our frame we just created
			JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won.");
			hasWinner = true;
			resetBoard();
		}
		// 2,0 2,1 2,2 right vertical
		else if(board[2][0].getText().equals(currentPlayer) && board[2][1].getText().equals(currentPlayer) && board[2][2].getText().equals(currentPlayer)) {
			//parentComponent determines the Frame in which the dialog is displayed, we don't want it to display in the parent frame, we want to display it in our frame we just created
			JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won.");
			hasWinner = true;
			resetBoard();
		}
		// 0,2 1,1 2,0 bottom-left to top-right diagonal
		else if(board[0][2].getText().equals(currentPlayer) && board[1][1].getText().equals(currentPlayer) && board[2][0].getText().equals(currentPlayer)) {
			//parentComponent determines the Frame in which the dialog is displayed, we don't want it to display in the parent frame, we want to display it in our frame we just created
			JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won.");
			hasWinner = true;
			resetBoard();
		}
		// 0,0 1,1 2,2 top-left to bottom-right diagonal
		else if(board[0][0].getText().equals(currentPlayer) && board[1][1].getText().equals(currentPlayer) && board[2][2].getText().equals(currentPlayer)) {
			//parentComponent determines the Frame in which the dialog is displayed, we don't want it to display in the parent frame, we want to display it in our frame we just created
			JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won.");
			hasWinner = true;
			resetBoard();
		}
		
		
		
		
	}
}