import javax.swing.JFrame;
public class FrameofGame extends JFrame
{
    FrameofGame()
    {
        //PanelofGame panel = new PanelofGame();

        this.add(new PanelofGame());
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
