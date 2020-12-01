package Game.Game2;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Board extends JPanel {

    private static final int n = 9;
    private static final int m = 9;

    public static final int ST_WIN = 1;
    public static final int ST_DRAW = 0;
    public static final int ST_NORMAL = 2;


    private EndGame endGame;
    private Image imgX;
    private Image imgO;
    private Square matrix[][] = new Square[n][m];
    private String Player = Square.EMPTY_VALUE;

    public Board(String player) {
        this();
        this.Player = player;
    }

    public Board() {
        this.MatrixSquare();
        //khoi tao mang 2 chieu
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int x = e.getX();
                int y = e.getY();

                if (Player.equals(Square.EMPTY_VALUE)) {
                    return;
                }
                SoundClick();
                //click x, y  sau do ve hinh x hoac o
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        Square square = matrix[i][j];
                        int ClickXStart = square.getX();
                        int ClickYStart = square.getY();

                        int ClickXEnd = ClickXStart + square.getW();
                        int ClickYEnd = ClickYStart + square.getH();

                        if (x >= ClickXStart && x <= ClickXEnd && y >= ClickYStart && y <= ClickYEnd) {
                            if (square.getValue().equals(Square.EMPTY_VALUE)) {
                                square.setValue(Player);
                                repaint();
                                int result = CheckWin(Player);
                                if (endGame != null){
                                    endGame.end(Player,result );
                                }

                                if (result == ST_NORMAL){
                                    Player = Player.equals(Square.O_VALUE) ? Square.X_VALUE : Square.O_VALUE;
                                }
                            }
                        }
                    }
                }

            }
        });

        try {
            imgX = ImageIO.read(getClass().getResource("x.png"));
            imgO = ImageIO.read(getClass().getResource("o.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private synchronized void SoundClick() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("click.wav"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private synchronized void MatrixSquare() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Square square = new Square();
                matrix[i][j] = square;

                System.out.println("i: " + i + "j: " + j);
            }
            System.out.println();
        }
    }



    public void resetGame() {
        this.MatrixSquare();
        this.setPlayer(Square.EMPTY_VALUE);
        repaint();
    }

    public int CheckWin(String Player) {
        //00
        if (this.matrix[0][0].getValue().equals(Player)
                && this.matrix[0][1].getValue().equals(Player)
                && this.matrix[0][2].getValue().equals(Player)
                && this.matrix[0][3].getValue().equals(Player)) {
            return 1;
        }
        //11
        if (this.matrix[1][0].getValue().equals(Player)
                && this.matrix[1][1].getValue().equals(Player)
                && this.matrix[1][2].getValue().equals(Player)
                && this.matrix[1][3].getValue().equals(Player)) {
            return 1;
        }
        //22
        if (this.matrix[2][0].getValue().equals(Player)
                && this.matrix[2][1].getValue().equals(Player)
                && this.matrix[2][2].getValue().equals(Player)
                && this.matrix[2][3].getValue().equals(Player)) {
            return 1;
        }
        //33
        if (this.matrix[3][0].getValue().equals(Player)
                && this.matrix[3][1].getValue().equals(Player)
                && this.matrix[3][2].getValue().equals(Player)
                && this.matrix[3][3].getValue().equals(Player)) {
            return 1;
        }
        //.
        if (this.matrix[0][0].getValue().equals(Player)
                && this.matrix[1][0].getValue().equals(Player)
                && this.matrix[2][0].getValue().equals(Player)
                && this.matrix[3][0].getValue().equals(Player)) {
            return 1;
        }
        if (this.matrix[0][1].getValue().equals(Player)
                && this.matrix[1][1].getValue().equals(Player)
                && this.matrix[2][1].getValue().equals(Player)
                && this.matrix[3][1].getValue().equals(Player)) {
            return 1;
        }
        if (this.matrix[0][2].getValue().equals(Player)
                && this.matrix[1][2].getValue().equals(Player)
                && this.matrix[2][2].getValue().equals(Player)
                && this.matrix[3][2].getValue().equals(Player)) {
            return 1;
        }
        if (this.matrix[0][3].getValue().equals(Player)
                && this.matrix[1][3].getValue().equals(Player)
                && this.matrix[2][3].getValue().equals(Player)
                && this.matrix[3][3].getValue().equals(Player)) {
            return 1;
        }
        if (this.matrix[0][0].getValue().equals(Player)
                && this.matrix[1][1].getValue().equals(Player)
                && this.matrix[2][2].getValue().equals(Player)
                && this.matrix[3][3].getValue().equals(Player)) {
            return 1;
        }
        if (this.matrix[0][3].getValue().equals(Player)
                && this.matrix[1][2].getValue().equals(Player)
                && this.matrix[2][1].getValue().equals(Player)
                && this.matrix[3][0].getValue().equals(Player)) {
            return 1;
        }
        if (this.isFull()){
            return 0;
        }
        return 2;
    }

    private boolean isFull() {
        int number = n * m;
        int Count = 0;
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < m; j++) {
                Square square = matrix[i][j];
               if (!square.getValue().equals(Square.EMPTY_VALUE)){
                    Count++;
               }
            }
        }

        return Count == number;
    }

    @Override
    protected void paintBorder(Graphics g) {
        int w = getWidth() / 12;
        int h = getHeight() / 12;

        Graphics2D graphics2D = (Graphics2D) g;

        int ColorWY = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int x = j * w, y = i * h;

                //Cap nhap ma tra
                Square square = matrix[i][j];
                square.setY(y);
                square.setX(x);
                square.setW(w);
                square.setH(h);

                Color color = ColorWY % 2 == 0 ? Color.WHITE : Color.YELLOW;
                graphics2D.setColor(color);
                graphics2D.fillRect(x, y, w, h);

                if (square.getValue().equals(Square.X_VALUE)) {
                    Image img = imgX;
                    graphics2D.drawImage(img, x, y, w, h, this);
                } else if (square.getValue().equals(Square.O_VALUE)) {
                    Image img = imgO;
                    graphics2D.drawImage(img, x, y, w, h, this);
                }
                ColorWY++;
            }
        }
    }

    public void setPlayer(String player) {
        Player = player;
    }

    public void setEndGame(EndGame endGame) {
        this.endGame = endGame;
    }

    public String getPlayer() {
        return Player;
    }
}
