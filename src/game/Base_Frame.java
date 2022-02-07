package game;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Base_Frame extends JFrame {
		private JMenuBar mb;
		private Game_Frame otherInst;
		ImageIcon im;
		private JMenu options;
		private JMenuItem restart;
		private JMenuItem load;
		private JMenuItem save;
		private JMenuItem exit;
		/**
		 * Constructs the base frame for the game
		 */
		public Base_Frame() {
			this.setSize(600,600);
			this.setTitle("Rubik Clock");
			//creating the menu Bar
			mb = new JMenuBar();
			options = new JMenu("Options");
			restart  = new JMenuItem("Restart");
			load = new JMenuItem("Load");
			save  = new JMenuItem("Save");
			exit = new JMenuItem("Exit");
			options.add(restart);
			options.add(load);
			options.add(save);
			options.add(exit);
			mb.add(options);
			this.setJMenuBar(mb);

			im = new ImageIcon("/pics/gameIcon.png");
			this.setIconImage(im.getImage());
			restart.addActionListener( e ->{
				this.dispose();
				otherInst = new Game_Frame();
			});
			exit.addActionListener(e->{
				
				System.exit(1);
			});
			this.setBackground(new Color(0,102,0));
			this.setLayout(null);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setResizable(false);
			this.setVisible(true);
			
			
		}
	
}
