package Runner;

import java.util.ArrayList;

import org.omg.CORBA.portable.ApplicationException;

import Domain.Ball;
import Domain.Player;
import Domain.Ball;
import Domain.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application{

	GraphicsContext gc;
	public static boolean cpuAssistant1 = true, cpuAssistant2 = true;
	public static int width = 600;
	public static int length = 900;
	public static Ball ball;
	public static int PlayBallSize = 6;
	public static int startcountdown = 5;
	public static int Score1 = 0;
	public static int Score2 = 0;
	public static boolean p1Up = false;
	public static boolean p2Up = false;
	public static boolean p1Down = false;
	public static boolean p2Down = false;
	public static boolean GameOver = false;
	public static Timeline timer1, timer2;
	public static int gamespeed = 8;
	//highter slower 
		public static int winner = 0;
	public static ArrayList<Player> playersList = new ArrayList<Player>();

	public static int CpuAssistantD = 4;
	public static void main(String[] args) {
		launch(args);
		
	}

	@Override
	public void start(Stage stage) throws Exception {
		Group root = new Group();
		Scene scene = new Scene(root, length, width, Color.BLACK);

		// Create Game Area
		
		Canvas canvas = new Canvas(length, width);
		gc = canvas.getGraphicsContext2D();

		Player p1 = new Player(75, 200, 20, 100, Color.BLUE);

		Player p2 = new Player(825, 200, 20, 100, Color.RED);

		ball = new Ball((length / 2)-6, width / 2,6,6);

		playersList.add(p1);
		playersList.add(p2);

	

		scene.setOnKeyPressed(e -> {
			switch (e.getCode()) {
			case P:
				cpuAssistant2=true;
				
				cpuAssistant1=true;
				
				break;
			case W:
				p1Up = true;
				cpuAssistant1 = false;
				break;
			case S:
				p1Down = true;
				cpuAssistant1 = false;
				break;
			case UP:
				p2Up = true;
				cpuAssistant2 = false;
				break;
			case O:
				cpuAssistant2=false;
				cpuAssistant1=false;
				
			case DOWN:
				p2Down = true;
				cpuAssistant2 = false;
				break;
			}

		});

		scene.setOnKeyReleased(e -> {
			switch (e.getCode()) {
			case W:
				p1Up = false;
				break;
			case S:
				p1Down = false;
				break;
			case UP:
				p2Up = false;
				break;
			case DOWN:
				p2Down = false;
				break;
			
				
			case R:
					restart(gc);
				break;
			}
		});

		timer1 = new Timeline(new KeyFrame(Duration.millis(gamespeed), e -> {

			if (startcountdown < -1) {
				ball.moveBall();
			}
			ball.CheckhitWall();
			redraw(gc);

			if (cpuAssistant2) {

				if ((ball.getY() < playersList.get(1).getY() + playersList.get(1).getSizeY() / 2)
						&& ball.gettoPlayer() == 1 && ball.getX() > 450) {

					playersList.get(1).goUpCpu();
				} else if ((ball.getY() > playersList.get(1).getY() - playersList.get(1).getSizeY() / 2)
						&& ball.gettoPlayer() == 1 && ball.getX() > 450) {
					playersList.get(1).goDowCpu();
				}
			}

			if (cpuAssistant1) {
				if ((ball.getY() < playersList.get(0).getY() + playersList.get(0).getSizeY() / 2)
						&& ball.gettoPlayer() == 0 && ball.getX() < 450) {
					playersList.get(0).goUpCpu();
				} else if ((ball.getY() > playersList.get(0).getY() - playersList.get(0).getSizeY() / 2)
						&& ball.gettoPlayer() == 0 && ball.getX() < 450) {
					playersList.get(0).goDowCpu();
				}
			}
		}));

		timer1.setCycleCount(Timeline.INDEFINITE);

		timer2 = new Timeline(new KeyFrame(Duration.seconds(1), e -> {

			drawStart(gc);
			if (startcountdown == -1) {

				timer1.play();
			}

		}));

		timer2.setCycleCount(7);

		timer2.play();

		root.getChildren().add(canvas);

		gc.clearRect(0, 0, length, width);

		stage.setScene(scene);

		stage.setTitle("Ping Pong ");
	
		stage.show();
	}

	// Method to restart the Game
	public static void restart(GraphicsContext gc) {
		gc.clearRect(0, 0, length, width);
		startcountdown = 5;
		Score1 = 0;
		Score2 = 0;
		playersList.get(0).setY(width / 3);
		playersList.get(1).setY(width / 2);
		GameOver = false;
		timer2.play();
	}

	public static void drawStart(GraphicsContext gc) {
		gc.clearRect(0, 0, length, width);
		gc.setFill(Color.LIME);
		gc.setFont(new Font(60));
		gc.fillText(String.valueOf(startcountdown) + "...", 400, 300);
		gc.setFill(Color.GOLD);
		gc.setFont(new Font(40));
		gc.fillText("Controls: Player 1-> W/S | Player 2-> Up/Down", length / 30, width /1.5);
		startcountdown--;
	}

	// Player wins
	public static void PlayerWinScreen(GraphicsContext gc) {
		gc.clearRect(0, 0, length, width);
		
		timer1.stop();
		if (winner == 1) {
			gc.setFill(Color.BLUE);
			gc.setFont(new Font(50));
			gc.fillText("Player 1 won!!! ~ Press R to restart", 60, 280);
		} else if (winner == 2) {
			gc.setFill(Color.RED);
			gc.setFont(new Font(50));
			gc.fillText("Player 2 won!!! ~ Press R to restart", 60, 280);
		}
	}

	// draw each score
	public static void redraw(GraphicsContext gc) {
		gc.clearRect(0, 0, length, width);
		for (int i = 0; i < playersList.size(); i++) {
			gc.setFill(playersList.get(i).getColor());
			gc.fillRect(playersList.get(i).getX(), playersList.get(i).getY(), playersList.get(i).getSizeX(),
					playersList.get(i).getSizeY());
		}

		gc.setFill(Color.GOLD);
		gc.fillRect(ball.getX(), ball.getY(), ball.getBallSizeX(), ball.getBallSizeY());

		gc.setStroke(Color.GRAY);
		gc.setLineDashOffset(10);
		gc.setLineWidth(10);
		gc.strokeLine((length / 2) - 10, -5, (length / 2) - 10, width + 10);

		gc.setFill(Color.AZURE);
		gc.setFont(new Font(80));
		gc.fillText(String.valueOf(Score1), length / 4.5, width / 2);
		gc.fillText(String.valueOf(Score2), length / 1.38, width / 2);
		gc.setFont(new Font(30));
		int i = 0;
		if (cpuAssistant1&& i==0) {
			gc.fillText("cpu assistant is on\nPress W/S to play", length / 6, width / 1.5);
		i++;}
		if (cpuAssistant2) {
			gc.fillText("cpu assistant is on \n Press UP/DOWN to play", length / 1.8, width / 1.5);
		i++;}
		gc.setFill(Color.BROWN);
		gc.fillRect(0, 0, length, 10);

		if (p1Up) {
			playersList.get(0).goUp();
		}
		if (p2Up) {
			playersList.get(1).goUp();
		}
		if (p1Down) {
			playersList.get(0).goDown();
		}
		if (p2Down) {
			playersList.get(1).goDown();
		}
		if (GameOver) {
			timer1.stop();
			PlayerWinScreen(gc);
		}
	}
}
