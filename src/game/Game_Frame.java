package game;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Game_Frame {

	private Base_Frame gameFrame;
	private Win_Loss_Frame wlf;
	JPanel upperPanel;
	JPanel downPanel;
	JButton[][] timeBtns;
	JButton[] clickableBtns;
	JLabel steps_cnt_label;
	JLabel time_passed;
	static int game_timer;
	static int steps;
	private boolean win = false;
	private Game_Frame other_try;
	/**
	 * Constructs the game frame with all neccessay requirements
	 */
	public Game_Frame() {
		gameFrame = new Base_Frame();
		steps_cnt_label = new JLabel("",SwingConstants.CENTER);
		steps = 0;
		game_timer = 0;
		time_passed = new JLabel("",SwingConstants.CENTER);
		upperPanel = new JPanel();
		downPanel = new JPanel();
		upperPanel.add(steps_cnt_label);
		
		steps_cnt_label.setForeground(Color.white);
		time_passed.setForeground(Color.white);
		steps_cnt_label.setText(steps + " Steps");
		Thread time_thread = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i <= 4000; i++) {

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					time_passed.setText(game_timer + " Seconds");
					game_timer++;
					if (i == 1000) {
						time_passed.setText("too much time, a new game will start in  5 seconds");
						try {
							Thread.sleep(5*1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						gameFrame.dispose();
						other_try=new Game_Frame();
						
					}

				}
			}
		};
		time_thread.start();

		upperPanel.add(time_passed);
		upperPanel.setBackground(new Color(0, 102, 0));
		upperPanel.setLayout(new GridLayout(1, 2));
		gameFrame.add(upperPanel);
		timeBtns = new JButton[3][3];
		clickableBtns = new JButton[4];
		setTimesBtns();
		setClickableBtns();

		downPanel.setLayout(new GridLayout(3, 3));
		gameFrame.add(downPanel);
		gameFrame.setLayout(new GridLayout(2, 1));

	}
/**
 * Checks if the there is a win
 */
	private void checkWin() {
		for (int i = 0; i < timeBtns.length; i++) {
			for (int j = 0; j < timeBtns.length; j++) {
				if (!timeBtns[i][j].getText().equals("12"))
					return;
			}
		}
		this.win = true;
	}

	/**
	 * Sets all increasiable  buttons and they are randomly set
	 */
	private void setTimesBtns() {
		for (int i = 0; i < timeBtns.length; i++) {
			for (int j = 0; j < timeBtns.length; j++) {
				Random rand = new Random();
				int randTime = rand.nextInt(13 - 1) + 1;
				timeBtns[i][j] = new JButton(Integer.toString(randTime));
				timeBtns[i][j].setBackground(new Color(102, 51, 0));
				timeBtns[i][j].setForeground(Color.white);
				timeBtns[i][j].setFocusable(false);
				timeBtns[i][j].setRolloverEnabled(false);
//				timeBtns[i][j].setEnabled(false);
				downPanel.add(timeBtns[i][j]);
			}
		}

	}
/**
 * Sets all  corner buttons on game board and adjusting buttons so when clicking on button the neighbouring buttons increases  
 */
	private void setClickableBtns() {
		for (int i = 0; i < clickableBtns.length; i++) {
			clickableBtns[i] = new JButton(Integer.toString(i));
			clickableBtns[i].setBackground(new Color(102, 0, 153));
			clickableBtns[i].setForeground(Color.white);
			clickableBtns[i].setFocusable(false);
			clickableBtns[i].setRolloverEnabled(false);
			clickableBtns[i].addActionListener(e -> {
				Boolean allSurroundingClicked = false;
				int max_clock_surrouding_cnt = 0;
				int leftNeighbourColumn;
				int rightNeighbourColumn;
				int topNeighbourRow;
				int bottomNeighbourRow;
				if (((JButton) e.getSource()).getText().equals("0")) {
					topNeighbourRow=0-1;
					bottomNeighbourRow=0+1;
					leftNeighbourColumn=0-1;
					rightNeighbourColumn=0+1;
					increaseSurrondingClocks(topNeighbourRow,bottomNeighbourRow,leftNeighbourColumn,rightNeighbourColumn,max_clock_surrouding_cnt);
					checkWin();
				} else if (((JButton) e.getSource()).getText().equals("1")) {
					topNeighbourRow=0-1;
					bottomNeighbourRow=0+1;
					leftNeighbourColumn=2-1;
					rightNeighbourColumn=2+1;
					increaseSurrondingClocks(topNeighbourRow,bottomNeighbourRow,leftNeighbourColumn,rightNeighbourColumn,max_clock_surrouding_cnt);
					checkWin();

				} else if (((JButton) e.getSource()).getText().equals("2")) {
					topNeighbourRow=2-1;
					bottomNeighbourRow=2+1;
					leftNeighbourColumn=0-1;
					rightNeighbourColumn=0+1;
					increaseSurrondingClocks(topNeighbourRow,bottomNeighbourRow,leftNeighbourColumn,rightNeighbourColumn,max_clock_surrouding_cnt);

					checkWin();

				} else if (((JButton) e.getSource()).getText().equals("3")) {
					topNeighbourRow=2-1;
					bottomNeighbourRow=2+1;
					leftNeighbourColumn=2-1;
					rightNeighbourColumn=2+1;
					increaseSurrondingClocks(topNeighbourRow,bottomNeighbourRow,leftNeighbourColumn,rightNeighbourColumn,max_clock_surrouding_cnt);
//					for (int k = 1; k < 3; k++) {
//						for (int j = 1; j < 3; j++) {
//
//							int num = Integer.parseInt(timeBtns[k][j].getText());
//							if (num == 12)
//							{
//								max_clock_surrouding_cnt++;
////								num=0;
//							}
//							if (num < 12)
//								num++;
//							timeBtns[k][j].setText(Integer.toString(num));
//							if (max_clock_surrouding_cnt == 4) {
//								allSurroundingClicked = true;
//								((JButton)e.getSource()).setEnabled(false);
//									for(int m = 1; m<3 ; m++) {
//										for(int n =1 ; n<3 ;n++)
//										{
//											timeBtns[m][n].setEnabled(false);
//										}
//									}
//							}
//						}
//					}

					checkWin();
				}
				
				if (win) {
					gameFrame.dispose();
					wlf = new Win_Loss_Frame(steps, game_timer);
				}

				if (!allSurroundingClicked) {
					steps_cnt_label.setText(++steps + " Steps");

				}
				
			});
		}

		timeBtns[0][0].add(clickableBtns[0]);
		timeBtns[0][2].add(clickableBtns[1]);
		timeBtns[2][0].add(clickableBtns[2]);
		timeBtns[2][2].add(clickableBtns[3]);

	}
	/**
	 * 
	 * increases the adjacent time buttons by one 
	 * @param topNeighbourRow the top neighbour row of the buttons
	 * @param bottomNeighbourRow the bottom neighbour row of the buttons
	 * @param leftNeighbourColumn the left neighbour Column  of the buttons
	 * @param rightNeighbourColumn the right neighbour Column  of the buttons
	 * @param maxSurrondingClicked number of time buttons reached to 12 
	 */
	private void increaseSurrondingClocks(int topNeighbourRow,int bottomNeighbourRow,int leftNeighbourColumn,int rightNeighbourColumn,int maxSurrondingClicked) {
		
		for (int k = topNeighbourRow; k <= bottomNeighbourRow; k++) {
			for (int j = leftNeighbourColumn; j <= rightNeighbourColumn; j++) {
				if(k<0||j<0||k>=timeBtns.length||j>=timeBtns.length) continue;
				
				int num = Integer.parseInt(timeBtns[k][j].getText());
				if (num == 12)
				{
					maxSurrondingClicked++;
					num=0;
				}
				if (num < 12)
					num++;
				timeBtns[k][j].setText(Integer.toString(num));
				if (maxSurrondingClicked == 4) {
				}
			}
		}
	}
	
	
}
