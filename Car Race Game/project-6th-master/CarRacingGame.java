import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class CarRacingGame extends JFrame {
    JFrame frame1 = null;
    JFrame frame;
    String input;

    private JButton startButton;
    //private JButton settingsButton;
    private JButton exitButton;
    private JButton ldButton; // leaderboard button
    JLabel label = new JLabel();
    private JTable leaderboardTable;

    public CarRacingGame() {
        setTitle("GAME");
        setSize(500, 500);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

       JPanel mainPanel = new JPanel();
        //mainPanel.setBackground(Color.BLUE);
        mainPanel.setBounds(0, 32, 500, 500);
        mainPanel.setLayout(null);

        JLabel titleLabel = new JLabel("2-D CAR GAME");
        titleLabel.setForeground(Color.darkGray);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 36));
        titleLabel.setBounds(100, 27, 300, 50);
        mainPanel.add(titleLabel);

        //JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        startButton = new JButton("START");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.WHITE);
        startButton.setFont(new Font("Verdana", Font.BOLD, 10));
        startButton.setBounds(190, 100, 120, 40);
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == startButton) {
                    game();
                }
            }
        });

        ldButton = new JButton("LEADERBOARD");
        ldButton.setBackground(Color.BLUE);
        ldButton.setForeground(Color.WHITE);
        ldButton.setFont(new Font("Verdana", Font.BOLD, 10));
        ldButton.setBounds(190, 150, 120, 40);
        ldButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == ldButton) {
                    LeaderBoard lb = new LeaderBoard();
                    lb.leaderBoardTable();
                }
            }
        });

        exitButton = new JButton("EXIT");
        exitButton.setFont(new Font("Verdana", Font.BOLD, 10));
        exitButton.setBackground(Color.red);
        exitButton.setForeground(Color.WHITE);
        exitButton.setBounds(190, 210, 120, 40);
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == exitButton) {
                    int confirmExit = JOptionPane.showConfirmDialog(mainPanel, "Do you really want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
                    if (confirmExit == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                }
            }
        });

        mainPanel.add(startButton);
        mainPanel.add(ldButton);
        //mainPanel.add(settingsButton);
        mainPanel.add(exitButton);
        ImageIcon image = new ImageIcon();
        label.setIcon(loadImage("images/CAR.jpg"));
        label.setBounds(0, 0, this.getWidth(), this.getHeight());
        mainPanel.add(label);
        getContentPane().add(mainPanel);
        setVisible(true);
    }

    private ImageIcon loadImage(String path) {
        Image image = new ImageIcon("images/CAR.jpg").getImage();
        Image scaled = image.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    public void game() {
        final String playerName;
        input = JOptionPane.showInputDialog("Player name:");
        if (input == null || input.isEmpty()){
            input = "Player";
        }
        playerName = input;
        Thread gameThread = new Thread(() -> {
            Game game = new Game(playerName);
            frame = new JFrame("Car Racing Game");
            frame.add(game);
            frame.setSize(500, 500);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            GameController gc = new GameController(game);
            gc.runGameLoop();

        });
        gameThread.start();

    }

    public void restartGame() {
            frame.dispose();

    }

    public static void main(String[] args) {
        CarRacingGame crg = new CarRacingGame();
    }
}

