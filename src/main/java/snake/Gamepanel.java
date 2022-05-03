package snake;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.util.Random;

public class Gamepanel extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;

	static final int w = 600,h = 600,u = 25,gu = w*h/u,delay = 50;
	
	final int x[] = new int[gu];
	final int y[] = new int[gu];
	
	int bodyparts = 6;
	int applex,appley,appleseaten ;
	char dir = 'R';
	boolean running = false;
	Timer t;
	Random r;
	
	
Gamepanel(){
	r = new Random();
	this.setPreferredSize(new Dimension(w,h));
	this.setBackground(Color.black);
	this.setFocusable(true);
	this.addKeyListener(new Gkey());
	
	start();
		
	}

	
public void start() {
	newapple();
	running=true;
	t = new Timer(delay,this);
	t.start();
	
		
	}
public void paint(Graphics g) {
	super.paintComponent(g);
	draw(g);
	
		
}

public void draw(Graphics g) {
	if (running) {
					for (int i =0; i< w/u ;i++) {
				g.drawLine(i*u, 0, i*u, w);
				g.drawLine(0, i*u, w, i*u);
				
			}
			
			g.setColor(Color.red);
			g.fillOval(applex,appley ,u,u);
			
			for( int i =0 ;i< bodyparts;i++) {
				if (i==0) {
					g.setColor(Color.GREEN);
					g.fillRect(x[i], y[i], u, u);
					
				}
				else {
					g.setColor(new Color(45,180,0));
					g.fillRect(x[i], y[i], u, u);
				}
				
			}
			
	}
	else {
		gameover(g);
		
	}

}

public void newapple() {
	applex = r.nextInt(w/u)*u;
	appley = r.nextInt(w/u)*u;
	
}

public void move() {
	for(int i = bodyparts ; i>0;i--) {
		x[i] = x[i-1];
		y[i] = y[i-1];
	}
	
	switch (dir) {
	case 'U' : y[0] = y[0] -u;break;
	case 'D' : y[0] = y[0] +u;break;
	case 'L' : x[0] = x[0] -u;break;
	case 'R' : x[0] = x[0] +u;break;
	
	}
	
}

public void checkapple() {
	
	if ((x[0] == applex) && (y[0] == appley)) {
		bodyparts++;
		appleseaten++;
		newapple();
		
	}
	
}

public void colision() {
	for (int i = bodyparts ;i>0;i--) {
		if ((x[0] == x[i]) && (y[0] == y[i])) {
			running  = false;
		}
		
	}
	if (x[0]<0) {
		running = false;

	}

	if (x[0]>w) {
		running = false;

	}
	if (y[0]<0) {
		running = false;

	}

	if (y[0]>h) {
		running = false;

	}
	
	if(!running) {
		t.stop();
	}
}
public void gameover(Graphics g) {
	g.setColor(Color.red);
	g.setFont(new Font ("Ink Free",Font.BOLD,75));
	FontMetrics metrics = getFontMetrics(g.getFont());
	g.drawString("Game Over", (w-metrics.stringWidth("Game Over"))/2,w/2);
}


public void actionPerformed(ActionEvent e) {
		if (running) {
			move();
			checkapple();
			colision();
			
		}
		repaint();
		
	}

public class Gkey extends KeyAdapter {
	

    @Override
    public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT : if(dir !='R') {dir = 'L';};break;
		case KeyEvent.VK_RIGHT : if(dir !='L') {dir = 'R';};break;
		case KeyEvent.VK_UP : if(dir !='D') {dir = 'U';};break;
		case KeyEvent.VK_DOWN : if(dir !='U') {dir = 'D';};break;
		}
	}

}



}
