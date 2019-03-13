package Gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class Game extends JComponent{
	private static final long serialVersionUID = 1459986115636962596L;
	private Color backgroundColor = new Color(247, 220, 140);
	private Color borderColor = new Color(193, 74, 9);
	private Rectangle2D.Double background = new Rectangle2D.Double(15, 75, 1500, 840);
	private Rectangle2D.Double apple;
	private ArrayList<SnakeBody> snake = new ArrayList<SnakeBody>();
	private ArrayList<DirectionCordinate> dirs = new ArrayList<DirectionCordinate>();
	private boolean play = true;
	private int score = 0;
	private int counter = 0;
	private double tempx;
	private double tempy;
	private String tempDir;
	private PageListener listener;
	private boolean paused = false;
	
	public Game(){
		for(int x = 0; x < 5; x++){
			snake.add(new SnakeBody(new Rectangle2D.Double(300, 300 + x * 15, 15, 15), "up"));
		}
		
		placeApple();
		
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
			public boolean dispatchKeyEvent(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_UP && !snake.get(0).getDirection().equals("down")){
					dirs.add(new DirectionCordinate("up", snake.get(0).getRect().getX(), snake.get(0).getRect().getY()));
				}
				else if(e.getKeyCode() == KeyEvent.VK_DOWN && !snake.get(0).getDirection().equals("up")){
					dirs.add(new DirectionCordinate("down", snake.get(0).getRect().getX(), snake.get(0).getRect().getY()));
				}
				else if(e.getKeyCode() == KeyEvent.VK_LEFT && !snake.get(0).getDirection().equals("right")){
					dirs.add(new DirectionCordinate("left", snake.get(0).getRect().getX(), snake.get(0).getRect().getY()));
				}
				else if(e.getKeyCode() == KeyEvent.VK_RIGHT && !snake.get(0).getDirection().equals("left")){
					dirs.add(new DirectionCordinate("right", snake.get(0).getRect().getX(), snake.get(0).getRect().getY()));
				}
				else if(e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == KeyEvent.VK_SPACE){
					System.out.println("Pressed");
					if(paused == true){
						listener.textEmitted("resume");
						paused = false;
					}
					else if(paused == false){
						listener.textEmitted("pause");
						paused = true;
					}
				}
				return false;
			}
		});
	}
	
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(borderColor);
		g2.fillRect(0, 0, getWidth(), getHeight());
		
		g2.setColor(backgroundColor);
		g2.fill(background);
		
		g2.setColor(Color.white);
		g2.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		g2.drawString("Apples Eaten: " + score, 550, 60);
		
		g2.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		g2.drawString("Press SPACE to pause", 50, 50);
		
		g2.setColor(Color.red);
		g2.fill(apple);
		
		g2.setColor(Color.BLACK);
		for(SnakeBody temp: snake){
			g2.fill(temp.getRect());
		}
	}
	
	public void update(){
		boolean keep = false;
		
		for(SnakeBody temp: snake){
			for(DirectionCordinate dir: dirs){
				if(temp.getRect().getX() == dir.getX() && temp.getRect().getY() == dir.getY()){
					temp.setDirection(dir.getDir());
				}
			}
		}
		
		for(int x = 1; x < snake.size(); x++){
			if(snake.get(0).getRect().intersects(snake.get(x).getRect())){
				play = false;
			}
		}
		
		 for(SnakeBody temp: snake){
			 if(temp.getDirection().equals("up")){
				 temp.getRect().y -=15;
			 }
			 else if(temp.getDirection().equals("down")){
				 temp.getRect().y +=15;
			 }
			 else if(temp.getDirection().equals("right")){
				 temp.getRect().x +=15;
			 }
			 else{
				 temp.getRect().x -=15;
			 }
		 }
		 
		 if(snake.get(0).getRect().intersects(apple)){
			 tempx = snake.get(snake.size() - 1).getRect().getX();
			 tempy = snake.get(snake.size() - 1).getRect().getY();
			 tempDir = snake.get(snake.size() - 1).getDirection();
			 counter += 5;
			 score++;
			 placeApple();
		 }
		 
		 if (counter > 0){
			 snake.add(new SnakeBody(new Rectangle2D.Double(tempx, tempy, 15, 15), tempDir));
			 counter --;
		 }
		 
		 for(int x = 0; x < dirs.size(); x++)
		 {
			 for(SnakeBody temp: snake){
				 if(temp.getRect().getX() == dirs.get(x).getX() && temp.getRect().getY() == dirs.get(x).getY()){
					 keep = true;
				 }
			 }
			 
			 if(keep == false){
				dirs.remove(x);
			 }
		 }
		 
		 if(!snake.get(0).getRect().intersects(background)){
			 play = false;
		 }
		 
		 repaint();
	}
	
	public boolean getPlay(){
		return play;
	}
	
	public int getScore(){
		return score;
	}
	
	private void placeApple(){
		int x = (int)(Math.random() * 100) * 15 + 15;
		int y = (int)(Math.random() * 56) * 15 + 75;
		apple = new Rectangle2D.Double(x, y, 15, 15);
		
		for(SnakeBody temp: snake){
			if(apple.intersects(temp.getRect())){
				placeApple();
			}
		}
	}
	
	public void restart(){
		snake.clear();
		score = 0;
		for(int x = 0; x < 5; x++){
			snake.add(new SnakeBody(new Rectangle2D.Double(300, 300 + x * 15, 15, 15), "up"));
		}
		counter = 0;
		play = true;
		
		placeApple();
	}
	
	public void setListener(PageListener listener){
		this.listener = listener;
	}
}
