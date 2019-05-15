package Domain;

import Runner.Main;

public class Ball extends Main {


	private int ballx;
	private int bally;
	private int ballSizex;
	private int ballSizey;
	private int dir = 0;
	private int toPlayer = 0;
	
	public Ball(int startx, int starty,int ballSizex,int ballSizey){
		this.ballx = startx;
		this.bally = starty;
		this.ballSizex = ballSizex;
		this.ballSizey = ballSizey;
	}

	public void moveBall(){
		if(toPlayer == 0){
			ballx-=2;
		}else if(toPlayer == 1){
			ballx+=2;
		}
		
		if(dir == 0){
			bally--;
		}else if(dir == 1){
			bally++;
		}
	}
	
	public int getX(){
		return ballx;
	}
	
	public int getY(){
		return bally;
	}
	
	public int getBallSizeX(){
		return ballSizex;
	}
	
	public int getBallSizeY(){
		return ballSizey;
	}
	
	
	public int gettoPlayer(){
		return toPlayer;
	}
	
	public void CheckhitWall(){
		if(((playersList.get(0).getX() - ballx <playersList.get(0).getSizeX() && playersList.get(0).getX()
				- ballx > -playersList.get(0).getSizeX()) && (playersList.get(0).getY() - bally < 5 
						&& playersList.get(0).getY() - bally > -playersList.get(0).getSizeY()))){
			toPlayer = 1;
		}else if(((playersList.get(1).getX() - ballx < ballSizex && playersList.get(1).getX()
				- ballx > -ballSizex) && (playersList.get(1).getY() - bally < ballSizey && playersList.get(1).getY() 
						- bally > -playersList.get(1).getSizeY()))){
			toPlayer = 0;
		}else if(bally >=  width-10- PlayBallSize/2){
			dir = 0;
		}else if(bally <=10){
			dir = 1;
		}
		if(ballx >  length){
			 Score1++;
			ballx = ( length/2)- PlayBallSize;
			bally = ( width/2)- PlayBallSize*2;
			toPlayer = 0;
			if( Score1 == 12){
				 GameOver = true;
				 winner = 1;
				 Score2 = 0;
				 Score1 = 0;
			}
		}else if(ballx < 0){
			 Score2++;
			ballx = ( length/2)- PlayBallSize;
			bally =  width/2;
			toPlayer = 1;
			if( Score2 == 12){
				 GameOver = true;
				 winner = 2;
				 Score2 = 0;
				 Score1 = 0;
			}
		}
	}

}
