import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.*;
import java.util.Random;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelofGame extends JPanel implements ActionListener
{
    static final int screenWidth = 800;
    static final int screenHeight = 800;
    static final int unitSize = 25;
    static final int gameUnits = (screenWidth * screenHeight)/unitSize;
    static final int delay = 75;
    final int x[] = new int[gameUnits];
    final int y[] = new int[gameUnits];
    int bodyParts = 3;
    int fruitsEaten;
    int fruitX;
    int fruitY;
    char direction = 'U';
    boolean running = false;
    Timer timer;
    Random random;
    PanelofGame()
    {
        random = new Random();
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.GREEN);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        gameStart();
    }

    public void gameStart()
    {
        newFruit();
        running = true;
    timer = new Timer(delay,this);
    timer.start();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        drawing(g);
    }

    public void drawing(Graphics g) {
        if (running) {
            for (int i = 0; i < screenHeight / unitSize; i++) {
                g.drawLine(i * unitSize, 0, i * unitSize, screenHeight);
                g.drawLine(0, i * unitSize, screenWidth, i * unitSize);

            }
            g.setColor(Color.orange);
            g.fillOval(fruitX, fruitY, unitSize, unitSize);

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.yellow);
                    g.fillRect(x[i], y[i], unitSize, unitSize);
                } else {
                    g.setColor(new Color(45, 180, 0));
                   // g.setColor(new Color(random.nextInt(255),random.nextInt(255), random.nextInt(255)));
                    g.fillRect(x[i], y[i], unitSize, unitSize);

                }
            }
            g.setColor(Color.red);
            g.setFont(new Font("Ink Free", Font.ITALIC,50 ));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("SCORE: " + fruitsEaten, (screenWidth - metrics.stringWidth("SCORE: " + fruitsEaten))/2, g.getFont().getSize());

        }
        else {
            GameOver(g);
        }
    }

    public void newFruit()
    {
        fruitX = random.nextInt((int)(screenWidth/unitSize)) * unitSize;
        fruitY = random.nextInt((int)(screenHeight/unitSize)) * unitSize;


    }
    public void movement() {
        for (int i = bodyParts; i > 0; i--)
        {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch(direction)
        {
            case 'U':
                y[0] = y[0] - unitSize;
                break;
            case'D':
                y[0] = y[0] + unitSize;
                break;
            case'L':
                x[0] = x[0] - unitSize;
                break;
            case'R':
                x[0] = x[0] + unitSize;
                break;
        }
    }

    public void checkFruit()
    {
        if((x[0] == fruitX) && (y[0] == fruitY))
        {
            bodyParts++;
            fruitsEaten++;
            newFruit();
        }
    }

    public void checkCollisions()
    {
        //checks if head collides with body
        for(int i = bodyParts; i > 0; i--)
        {
        if((x[0] == x[i]) && (y[0] == y[i]))
        {
            running = false;
        }
        }

        //checks if head touches the left
        if(x[0] < screenWidth)
        {
            running = false;
        }

        //checks if head touches the right
        if(x[0] > screenWidth)
        {
            running = false;
        }

        //checks if head touches the top
        if(y[0] < 0)
        {
            running = false;
        }

        //checks if head touches bottom
        if(y[0] < screenHeight)
        {
            running = false;
        }

        if(!running)
        {
            timer.stop();
        }
    }

    public void GameOver(Graphics g)
    {
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.ITALIC,80 ));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("GAME OVER :(", (screenWidth - metrics1.stringWidth("Game Over :( "))/2, screenHeight/2);

        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.ITALIC,50 ));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("SCORE: " + fruitsEaten, (screenWidth - metrics2.stringWidth("SCORE: " + fruitsEaten))/2, g.getFont().getSize());

    }

   @Override
   public void actionPerformed(ActionEvent e)
   {
        if(running)
        {
            movement();
            checkFruit();
            checkCollisions();

        }
        repaint();
   }

   public class MyKeyAdapter extends KeyAdapter
   {
      @Override
      public void keyPressed(KeyEvent e)
      {
            switch(e.getKeyCode())
            {
                case KeyEvent.VK_LEFT:
                    if(direction != 'R')
                    {
                        direction = 'L';
                    }
                    break;

                case KeyEvent.VK_RIGHT:
                    if(direction != 'L')
                    {
                        direction = 'L';
                    }
                    break;

                case KeyEvent.VK_UP:
                    if(direction != 'D')
                    {
                        direction = 'U';
                    }
                    break;

                case KeyEvent.VK_DOWN:
                    if(direction != 'U')
                    {
                        direction = 'D';
                    }
                    break;
            }
      }
   }
}
