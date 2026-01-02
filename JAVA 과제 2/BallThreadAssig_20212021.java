import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.math.*;
public class BallThreadAssig_20212021 extends Frame implements ActionListener{ 
	private Canvas canvas; 
	public BallThreadAssig_20212021(){ 
		canvas = new Canvas();
		add("Center", canvas);
		Panel p = new Panel();
		Button s = new Button("Start");
		Button c = new Button("Close");
		p.add(s); p.add(c);
		s.addActionListener(this);
		c.addActionListener(this);
		add("South", p);
	}
	public void behaviorPerformed(ActionEvent evt){ 
		if (evt.getActionCommand() == "Start"){
			for(int j = 0; j<5; j++) {
				int radiusX = 16;
				int radiusY = 16;
				double angle = 2 * Math.PI / 5 * j; 
	            int x = (int) (canvas.getWidth()/2 + 16 * Math.cos(angle)); 
	            int y = (int) (canvas.getHeight()/2 + 16 * Math.sin(angle)); 
				Ball ball = new Ball(canvas, x, y, radiusX, radiusY);
	            ball.getDirection(Math.cos(angle), Math.sin(angle));
				Thread thread = new Thread(ball);
				BallManager.balls.add(ball);
				thread.start();
			}
		}
		else if (evt.getActionCommand() == "Close")
			System.exit(0);
	}
	public static void main(String[] args){ 
		Frame f = new BallThreadAssig_20212021();
		f.setSize(400, 300);
		WindowDestroyer listener = new WindowDestroyer(); 
		f.addWindowListener(listener);
		f.setVisible(true); 
	}
} 

class Ball extends Thread{
	private Canvas box;
	private int XSIZE;
	private int YSIZE;
	private int x;
	private int y;
	private double dx;
	private double dy; 

	public Ball(Canvas c, int x, int y, int radiusX, int radiusY) {  
		this.box = c;
		this.x = x;
		this.y = y;
		this.XSIZE = radiusX;
		this.YSIZE = radiusY;
		
	}
	public void getDirection(double dirX, double dirY) {
		this.dx = dirX;
		this.dy = dirY;
	}
	public void draw(){ 
		Graphics g = box.getGraphics();
		g.fillOval(x, y, XSIZE, YSIZE);
		g.dispose(); 
	}
	public void move() { 
		Graphics g = box.getGraphics();
		g.setXORMode(box.getBackground());
		g.fillOval(x, y, XSIZE, YSIZE);
		x += dx; y += dy;
		Dimension d = box.getSize();
		if (x < 0) { x = 0; dx = -dx; }
		if (x + XSIZE >= d.width) { x = d.width - XSIZE; dx = -dx; }
		if (y < 0) { y = 0; dy = -dy; }
		if (y + YSIZE >= d.height) { y = d.height - YSIZE; dy = -dy; }
		
		
		for(int j = 0; j < BallManager.balls.size(); j++) {
			Ball collideBall = BallManager.balls.get(j);
			if(this.isCollided(collideBall) && j != BallManager.balls.indexOf(this))
				this.collidedTwoBalls(collideBall);
		}
		g.fillOval(x, y, XSIZE, YSIZE);
		g.dispose(); 
		
	}
	public boolean isCollided(Ball ball) {
		double disX = this.x - ball.x;
		double disY = this.y - ball.y;
		double dis_ball = Math.sqrt(disX*disX + disY*disY);
		
		double rad1 = this.XSIZE/2;
		double rad2 = ball.XSIZE/2;
		
		if(rad1+rad2 >= dis_ball) {
			return true;
		}
		else return false;
	}
	public void collidedTwoBalls(Ball ball) {
		
		if(ball.XSIZE>1){
			Ball newballOne = new Ball(box, ball.x, ball.y, ball.XSIZE, ball.YSIZE);
			Ball newballTwo = new Ball(box, ball.x, ball.y, ball.XSIZE, ball.YSIZE);
			
			newballOne.XSIZE = this.XSIZE/2;
			newballOne.YSIZE = this.YSIZE/2;
			newballOne.x = this.x;
			newballOne.y = this.y;
			newballOne.dx = -this.dx;
			newballOne.dy = -this.dy;

			newballTwo.XSIZE = this.XSIZE/2;
			newballTwo.YSIZE = this.YSIZE/2;
			newballTwo.x = this.x;
			newballTwo.y = this.y;
			newballTwo.dx = -this.dx;
			newballTwo.dy = -this.dy;

			BallManager.balls.add(newballOne);
			BallManager.balls.add(newballTwo);

			BallManager.balls.remove(this);
		}
		
		else{
			BallManager.balls.remove(this);
		}
	}
	public void run(){ 	
		draw();
		while (true)
		{ 
			move();
			try 
			{ 
				Thread.sleep(5); 
			} 
			catch(InterruptedException e) {
			}
		} 
	}
}
class BallManager {
	 public static ArrayList<Ball> balls = new ArrayList<Ball>();
	 
}
