package Domain;

import Runner.Main;
import javafx.scene.paint.Color;

public class Player extends Main{
	private int sizex;
	private int sizey;
	private int positionX;
	private int positionY;
	private Color color;

	public Player(int positionX, int positionY, int sizex, int sizey, Color c) {
		this.sizex = sizex;
		this.sizey = sizey;
		this.positionX = positionX;
		this.positionY = positionY;
		this.color = c;
	}

	public void goDown() {
		if (positionY <=  width - 100) {
			positionY += 3;
		}
	}

	public void goUp() {
		if (positionY >=  PlayBallSize / 2) {
			positionY -= 3;
		}
	}

	public void goDowCpu() {
		if (positionY <=  width - 100) {
			positionY +=  CpuAssistantD;
		}
	}

	public void goUpCpu() {
		if (positionY >=   PlayBallSize / 2) {
			positionY -=  CpuAssistantD;
		}
	}

	public int getX() {
		return positionX;
	}

	public int getY() {
		return positionY;
	}

	public int getSizeX() {
		return sizex;
	}

	public int getSizeY() {
		return sizey;
	}

	public void setY(int y) {
		this.positionY = y;
	}

	public Color getColor() {
		return color;
	}
}
