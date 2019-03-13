package Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class HighScorePage extends JComponent{
	private static final long serialVersionUID = -4530283297832214305L;
	private Player[] hs = new Player[5];
	private JButton exitBtn;
	private PageListener listener;
	
	public HighScorePage(){
		try {
			load();
		} catch (IOException e1) {
			
		}
		try {
			save();
		} catch (IOException e1) {
			
		}
		exitBtn = new JButton("Exit to Start Screen");
		
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.textEmitted("exit");
			}
		});
		
		exitBtn.setPreferredSize(new Dimension(500, 80));
		exitBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		
		setLayout(new BorderLayout());
		add(exitBtn, BorderLayout.SOUTH);
	}
	
	public void load() throws IOException{
		FileInputStream fis = new FileInputStream("ThisIsASaveFileDoNotOpen.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		try {
			hs = (Player[])ois.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ois.close();
	}
	
	public void save() throws IOException{
		FileOutputStream fos = new FileOutputStream("ThisIsASaveFileDoNotOpen.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(hs);
		
		oos.close();
	}
	
	public void addPlayer(int score){
		try {
			load();
		} catch (IOException e) {
			
		}
		String name = (String)JOptionPane.showInputDialog(this, "Enter your Name. (Only the first 3 characters will appear)");
		if(name.length() > 3){
			name = name.substring(0, 3);
		}
		if(name == null || name.equals("")){
			listener.textEmitted("exit");
			return;
		}
		
		int index = 6;
		Player player = new Player(name, score);
		for(int x = hs.length - 1; x >= 0; x--){
			if(hs[0] == null){
				index = 0;
			}
			else if(hs[x] != null){
				System.out.println("Player:" + player.getScore() + "Board" + x + ":" + hs[x].getScore() + (player.getScore() >= hs[x].getScore()));
				if(player.getScore() >= hs[x].getScore()){
					index = x;
				}
			}
		}
		
		if(index < 6){
			System.out.println(index);
			if(index == 0){
				hs[4] = hs[3];
				hs[3] = hs[2];
				hs[2] = hs[1];
				hs[1] = hs[0];
				hs[0] = player;
			}
			else if(index == 1){
				hs[4] = hs[3];
				hs[3] = hs[2];
				hs[2] = hs[1];
				hs[1] = player;
			}
			else if(index == 2){
				hs[4] = hs[3];
				hs[3] = hs[2];
				hs[2] = player;
			}
			else if(index == 3){
				hs[4] = hs[3];
				hs[3] = player;
			}
			else{
				hs[4] = player;
			}
		}
		
		try {
			save();
		} catch (IOException e) {
			
		}
	}

	public void setListener(PageListener listener) {
		this.listener = listener;
	}

	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setFont(new Font("Comic Sans MS", Font.BOLD, 80));
		g2.drawString("High Scores", 550, 100);
		
		g2.setFont(new Font("Comic Sans MS", Font.BOLD, 60));
		for(int x = 0; x < hs.length; x++){
			if(hs[x] != null){
				g2.drawString((x + 1) + ": " + hs[x].getName() + " - " + hs[x].getScore(), 600, 280 + 60 * x);
			}
		}
	}
	
	
}
