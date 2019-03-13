package Gui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class MainFrame extends JFrame{
	private static final long serialVersionUID = 8670913281036349732L;
	private Game game;
	private CardLayout cards;
	private HighScorePage highScore;
	private Timer gameTimer;
	private StartPage start;
	private EndPanel end;
	private PauseMenu pause;
	
	public MainFrame(){
		game = new Game();
		highScore = new HighScorePage();
		start = new StartPage();
		end = new EndPanel();
		pause = new PauseMenu();
		
		gameTimer = new Timer(100, new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				game.update();
				if(game.getPlay() == false){
					gameTimer.stop();
					end.setScore(game.getScore());
					cards.show(MainFrame.this.getContentPane(), "end");
				}
			}
		});
		
		game.setListener(new PageListener() {
			public void textEmitted(String text) {
				if(text.equals("pause")){				
					gameTimer.stop();
					cards.show(MainFrame.this.getContentPane(), "pause");
				}
				else if(text.equals("resume")){
					cards.show(MainFrame.this.getContentPane(), "game");
					gameTimer.start();
				}
			}
		});
		
		pause.setListener(new PageListener() {
			public void textEmitted(String text) {
				if(text.equals("resume")){				
					cards.show(MainFrame.this.getContentPane(), "game");
					gameTimer.start();
				}
				else if(text.equals("quit")){
					cards.show(MainFrame.this.getContentPane(), "start");
				}
			}
		});
		
		start.setListener(new PageListener() {
			public void textEmitted(String text) {
				if(text.equals("start")){
					game.restart();
					cards.show(MainFrame.this.getContentPane(), "game");
					gameTimer.start();
				}
				else if(text.equals("highScore")){
					cards.show(MainFrame.this.getContentPane(), "highScore");
				}
				else if(text.equals("exit")){
					System.exit(1);
				}
			}
		});
		
		end.setListener(new PageListener(){
			public void textEmitted(String text) {
				if(text.equals("yes")){		
					highScore.addPlayer(game.getScore());
					cards.show(MainFrame.this.getContentPane(), "highScore");
				}
				else{
					cards.show(MainFrame.this.getContentPane(), "start");
				}
			}
		});
		
		highScore.setListener(new PageListener(){
			public void textEmitted(String text) {
				if(text.equals("exit")){
					cards.show(MainFrame.this.getContentPane(), "start");
					System.out.println(text);
				}
			}
		});
		
		cards = new CardLayout();
		
		setVisible(true);
		setSize(1550, 975);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(cards);
		
		add(game, "game");
		add(highScore, "highScore");
		add(start, "start");
		add(end, "end");
		add(pause, "pause");
		
		cards.show(this.getContentPane(), "start");
	}
}
