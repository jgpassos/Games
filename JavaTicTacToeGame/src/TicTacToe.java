import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame("TIC-TAC-TOE");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean endGame = false;
    int turns = 0;

    TicTacToe() {
        Color backgroundColor = new Color(255,228,225);

        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(backgroundColor);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("TIC-TAC-TOE");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(backgroundColor);
        frame.add(boardPanel);

        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                if(endGame) return;

                JButton tile = new JButton();
                board[row][col] = tile;
                boardPanel.add(tile);

                tile.setBackground(backgroundColor);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(endGame) return;
                        JButton tile = (JButton) e.getSource();
                        if(tile.getText() == "") {
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if(!endGame) {
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                textLabel.setText(currentPlayer + "'s turn.");
                            }  
                        }
                    }
                });
            }
        }
    }
    void checkWinner() {
        //Horizontal
        for(int row = 0; row < 3; row++) {
            if(board[row][0].getText() == "") continue;

            if(board[row][0].getText() == board[row][1].getText() 
                && board[row][1].getText() == board[row][2].getText()) {
                    for(int i = 0; i < 3; i++) setWinner(board[row][i]);
                    endGame = true;
                    return;
            }
        }

        //Vertical
        for(int col = 0; col < 3; col++) {
            if(board[0][col].getText() == "") continue;

            if(board[0][col].getText() == board[1][col].getText()
                && board[1][col].getText() == board[2][col].getText()) {
                    for(int i = 0; i < 3; i++) setWinner(board[i][col]);
                    endGame = true;
                    return;
                }
        }

        //Diagonally
        if(board[0][0].getText() == board[1][1].getText()
            && board[1][1].getText() == board[2][2].getText()
            && board[0][0].getText() != "") {
                for(int i = 0; i < 3; i++) setWinner(board[i][i]);
                endGame = true;
                return;
            }

        //Anti-diagonally
        if(board[0][2].getText() == board[1][1].getText()
            && board[1][1].getText() == board[2][0].getText()
            && board[0][2].getText() != "") {
                setWinner(board[0][2]);
                setWinner(board[1][1]);
                setWinner(board[2][0]);
                endGame = true;
                return;
            }

        if(turns == 9) {
            for(int row = 0; row < 3; row++) {
                for(int col = 0; col < 3; col++) {
                    setTie(board[row][col]);
                }
            }
            endGame = true;
        }
    }
  
    private void setWinner(JButton tile) {
        Color backgroundColor = new Color(255,228,225);

        tile.setForeground(Color.blue);
        tile.setBackground(backgroundColor);
        textLabel.setText(currentPlayer + " Won!");
    }

    private void setTie(JButton tile) {
        Color backgroundColor = new Color(255,228,225);

        tile.setForeground(Color.red);
        tile.setBackground(backgroundColor);
        textLabel.setText("Tie!");
    }
}
