package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JFrame;

public class GameWindow {
	private JFrame jframe ;

	public GameWindow(GamePanel gamePanel) {

		jframe = new JFrame();
                jframe.setTitle("Game_101");
                jframe.setSize(700,700);//position
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jframe.setLocationRelativeTo(null);
		jframe.setResizable(false);
                jframe.setVisible(true);
                jframe.add(gamePanel);
		jframe.pack();
		
                jframe.addWindowFocusListener(new WindowFocusListener() {
                    @Override
                    public void windowGainedFocus(WindowEvent e) {
                    }

                    @Override
                    public void windowLostFocus(WindowEvent e) {
                        gamePanel.getGame().windowFocusLost();
                    }
                });
	}

}
