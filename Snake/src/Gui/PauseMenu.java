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

public class PauseMenu extends JComponent{
	private static final long serialVersionUID = 1L;
	private JButton resumeBtn;
	private JButton quitBtn;
	private PageListener listener;
	
	public PauseMenu(){
		resumeBtn = new JButton("Resume");
		quitBtn = new JButton("Quit");
		
		resumeBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		quitBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		
		resumeBtn.setPreferredSize(new Dimension(500, 80));
		quitBtn.setPreferredSize(new Dimension(500, 80));
		
		resumeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.textEmitted("resume");				
			}
		});
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.gridy = 1;
		gc.gridx = 1;
		
		add(resumeBtn, gc);
		
		gc.gridy ++;
		
		add(quitBtn, gc);
	}

	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("Comic Sans MS", Font.BOLD, 150));
		g2.drawString("Paused", 525, 300);
	}
	
	public void setListener(PageListener listener){
		this.listener = listener;
	}
}
