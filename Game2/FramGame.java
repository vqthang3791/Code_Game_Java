package Game.Game2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;

public class FramGame {
    private static int sec = 0;
    private static Timer timer = new Timer();
    private static JLabel lblTime;
    private static JButton btnStart;
    private static Board board;

    public static void main(String[] args) {
        board = new Board();
        board.setEndGame(new EndGame() {
            @Override
            public void end(String Player, int st) {
                if (st == Board.ST_WIN){
                    JOptionPane.showMessageDialog(null, "Player " + Player + " WIN ");
                    StopGame();
                } else if (st == Board.ST_DRAW) {
                    JOptionPane.showMessageDialog(null, "Co hoa");
                    StopGame();
                }
            }
        });
        JPanel jPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(jPanel, BoxLayout.Y_AXIS);
        jPanel.setLayout(boxLayout);
        board.setPreferredSize(new Dimension(300, 300));
        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT, 0, 0);
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(flowLayout);

        btnStart = new JButton("Start");

        lblTime = new JLabel("00:00");
        bottomPanel.add(lblTime);
        bottomPanel.add(btnStart);

        btnStart.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnStart.getText().equals("Start")) {
                    StartGame();
                } else {
                    StopGame();
                }
            }
        });

        jPanel.add(board);
        jPanel.add(bottomPanel);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

        JFrame jFrame = new JFrame("Game Show Ảnh");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(true);
        jFrame.add(jPanel);

        int x = ((int) dimension.getWidth() / 2 - (jFrame.getWidth() / 2));
        int y = ((int) dimension.getHeight() / 2 - (jFrame.getHeight() / 2));

        jFrame.setLocation(x, y);
        jFrame.pack();
        //show ra Jframe
        jFrame.setVisible(true);
    }

    private static void StartGame() {
        // hoi ai di trc
        // dem nguoc
        int choice = JOptionPane.showConfirmDialog(null, "X player", "Player A or B", JOptionPane.YES_NO_OPTION);
        String Player = (choice == 0) ? Square.X_VALUE : Square.O_VALUE;
        board.resetGame();
        board.setPlayer(Player);

        //game dang start set gia tri ve 0
        sec = 0;
        lblTime.setText("00:00");
        timer.cancel();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                sec++;
                String value = sec / 60 + " : " + sec % 60;
                lblTime.setText(value);
            }
        }, 1000, 1000);
        btnStart.setText("Stop");
    }

    private static void StopGame() {
        btnStart.setText("Start");
        sec = 0;
        lblTime.setText("00:00");
        timer.cancel();
        timer = new Timer();
        board.resetGame();
    }
}
