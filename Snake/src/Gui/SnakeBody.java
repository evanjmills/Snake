package Gui;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

public class SnakeBody {
	private Rectangle2D.Double rect;
	private String direction;
	
	public SnakeBody(Double rect, String direction) {
		super();
		this.rect = rect;
		this.direction = direction;
	}

	public Rectangle2D.Double getRect() {
		return rect;
	}

	public String getDirection() {
		return direction;
	}
	
	public void setDirection(String text){
		this.direction = text;
	}
}
