import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class LeaderBoard {
    public ArrayList<Player> playerList(){
        ArrayList<Player> playerList = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url ="jdbc:mysql://localhost:3307/cargame";
            Connection conn = DriverManager.getConnection(url,"root","root");

//                String query1 = "SELECT TOP 15 player, score from player ORDER BY score desc )";
            String query1 = "SELECT player, score FROM player ORDER BY score DESC LIMIT 15";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query1);
            Player player;
            while (rs.next()) {
                player = new Player(rs.getString("player"), rs.getInt("score"));
                playerList.add(player);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        System.out.println("connection success");
        return playerList;
    }


    public void leaderBoardTable() {
        ArrayList<Player> list = playerList();

        int sn = 1;
        Object[][] row = new Object[list.size()][3];
        for (int i = 0; i<list.size(); i++){
            row[i][0]= sn;
            sn++;
            row[i][1]=list.get(i).getName();
            row[i][2]=list.get(i).getScore();
        }

        String[] columnNames = {"SN","Player Name", "Score"};

//         Create the JTable and populate it with data
        JTable leaderboardTable = new JTable(row, columnNames);
        leaderboardTable.setFont(new Font("Verdana", Font.PLAIN, 14));

        JTableHeader header = leaderboardTable.getTableHeader();
        Font headerFont = new Font("Arial",Font.BOLD,14);
        header.setFont(headerFont);
        header.setBackground(Color.green);

        int rowHeight = 20; // Adjust the height as needed
        leaderboardTable.setRowHeight(rowHeight);


        JScrollPane scrollPane = new JScrollPane(leaderboardTable);
        scrollPane.setBounds(10, 10, 300, 400); // Adjust the bounds as needed
        scrollPane.setBackground(Color.LIGHT_GRAY);

        JFrame frame2 = new JFrame("LeaderBoard");
        frame2.add(scrollPane);
        frame2.setSize(500, 400);
        frame2.setBackground(Color.white);
        frame2.setVisible(true);
        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame2.setLocationRelativeTo(null);
    }
}
