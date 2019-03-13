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

public class EndPanel extends JComponent{
	private static final long serialVersionUID = 3095136412989118284L;
	private String question;
	private JButton yesBtn;
	private JButton noBtn;
	private PageListener listener;
	private int score;
	
	public EndPanel(){
		question = "Add Score to Leader Board?";
		yesBtn = new JButton("Yes");
		noBtn = new JButton("No");
		
		yesBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 70));
		noBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 70));
		
		yesBtn.setPreferredSize(new Dimension(500, 80));
		noBtn.setPreferredSize(new Dimension(500, 80));
		
		yesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.textEmitted("yes");
			}
		});
		
		noBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.textEmitted("no");
			}
		});
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridy = 0;
		add(yesBtn, gc);
		
		gc.gridy++;
		add(noBtn, gc);
	}

	protected void paintComponent(Graphics g) {
		Graphics2D g2 	= (Graphics2D)g;
		g2.setFont(new Font("Comic Sans MS", Font.BOLD, 70));
		g2.setColor(Color.black);
		g2.drawString("Game Over", 575, 150);
		g2.setColor(Color.red);
		g2.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		g2.drawString("You ate " + score + " apples.", 550, 230);
		g2.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		g2.setColor(Color.black);
		g2.drawString(question, 550, 380);
	}

	public void setListener(PageListener listener) {
		this.listener = listener;
	}
	
	public void setScore(int score){
		this.score = score;
	}
}
