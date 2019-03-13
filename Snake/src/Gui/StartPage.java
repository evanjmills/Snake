package Gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;

public class StartPage extends JComponent{
	private static final long serialVersionUID = -7607434559511109306L;
	private JButton startBtn;
	private JButton highScoreBtn;
	private PageListener listener;
	private JButton exitBtn;

	public StartPage(){
		startBtn = new JButton("Start");
		highScoreBtn = new JButton("High Score");
		exitBtn = new JButton("Exit");
		
		startBtn.setPreferredSize(new Dimension(500, 80));
		highScoreBtn.setPreferredSize(new Dimension(500, 80));
		exitBtn.setPreferredSize(new Dimension(500, 80));
		
		startBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		highScoreBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		exitBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		
		highScoreBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(highScoreBtn)){
					listener.textEmitted("highScore");
				}
			}
		});
		
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.textEmitted("start");
			}
		});
		
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.textEmitted("exit");				
			}
		});
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridy = 0;
		add(startBtn, gc);
		
		gc.gridy++;
		add(highScoreBtn, gc);
		
		gc.gridy ++;
		add(exitBtn, gc);
	}
	
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setFont(new Font("Comic Sans MS", Font.BOLD, 150));
		g2.setColor(Color.red);
		g2.drawString("Snake", 550, 250);
	}



	public void setListener(PageListener listener){
		this.listener = listener;
	}
}
