package game;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Win_Loss_Frame {
	private Game_Frame play_again;
	private Base_Frame bs;
	private JPanel wlPanel ;
	private JLabel wlLabel;
	private JLabel stepsLabel;
	private JLabel timeLabel;
	private JLabel game_starts;
	/**
	 * create the wining or losing frame for the game
	 * @param steps_cnt how many steps it took to finish the game
	 * @param time the time spent solving the puzzle
	 */
	public Win_Loss_Frame(int steps_cnt,int time) {
		bs = new Base_Frame();
		wlPanel = new JPanel();
		wlLabel = new JLabel("",SwingConstants.CENTER);
		stepsLabel = new JLabel("",SwingConstants.CENTER);
		timeLabel = new JLabel("",SwingConstants.CENTER);
		game_starts  = new JLabel("",SwingConstants.CENTER);
		wlLabel.setForeground(Color.white);
		stepsLabel.setForeground(Color.white);
		timeLabel.setForeground(Color.white);
		game_starts.setForeground(Color.white);
       wlLabel.setText("Congrats, you won!");
		stepsLabel.setText("total number of steps is "+ steps_cnt);
		timeLabel.setText("the time spent was "+ time+" Seconds");
		Thread starts_again=  new Thread(()->{
			for(int i = 15 ; i >= 0  ; i -- )
			{
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
			
					e.printStackTrace();
				}
				game_starts.setText("a new game will start in "+ i+" Seconds");
				if(i == 0)
				{
					bs.dispose();
					play_again = new Game_Frame();
				}
			}
			
		});
		starts_again.start();
		wlPanel.setBackground(new Color(0,102,0));
		wlPanel.add(wlLabel);
		wlPanel.add(stepsLabel);
		wlPanel.add(timeLabel);
		wlPanel.add(game_starts);
		wlPanel.setLayout(new GridLayout(4, 1));
		bs.add(wlPanel);
		bs.setLayout(new GridLayout(1,1));
		
	}
}
