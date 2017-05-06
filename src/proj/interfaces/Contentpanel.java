package proj.interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import proj.service.Character;
import proj.service.Commande;
import proj.service.Engine;
import proj.service.Player;
import proj.service.RectHitbox;


public class Contentpanel extends JPanel {


	Commande c1=Commande.NEUTRAL;
	Commande c2=Commande.NEUTRAL;
	Engine eng;


	public Commande getC1() {
		return c1;
	}

	public void setC1(Commande c1) {
		this.c1 = c1;
	}

	public Commande getC2() {
		return c2;
	}

	public void setC2(Commande c2) {
		this.c2 = c2;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	int w,h;
	KeyListener j1=new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {System.out.println("keyTyped");

		if(e.getKeyChar()=='q'){


		}
		if(e.getKeyChar()=='d'){

		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {

		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

		}

		}

		@Override
		public void keyReleased(KeyEvent e) {System.out.println("keyReleased");

		}

		@Override
		public void keyPressed(KeyEvent e) {System.out.println("keyPressed");

		if(e.getKeyChar()=='q'){

			c1=Commande.LEFT;
		}
		if(e.getKeyChar()=='d'){
			c1=Commande.RIGHT;

		}
		if(e.getKeyChar()=='w'){
			c1=Commande.GUARD;

		}
		if(e.getKeyChar()=='c'){
			c1=Commande.PUNCH;

		}
		if(e.getKeyChar()=='s'){
			c1=Commande.DOWN;

		}
		if(e.getKeyChar()=='z'){
			c1=Commande.UP;

		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {

			c2=Commande.LEFT;

		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

			c2=Commande.RIGHT;
		}
		if (e.getKeyCode() == KeyEvent.VK_NUMPAD0) {

			c2=Commande.GUARD;
		}
		if (e.getKeyCode() == KeyEvent.VK_NUMPAD1) {

			c2=Commande.PUNCH;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {

			c2=Commande.DOWN;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {

			c2=Commande.UP;
		}
		//repaint();
		}
	};


	public Contentpanel(Engine eng) {
		this.eng=eng;
		h=eng.getHeight();
		w=eng.getWidth();
		setPreferredSize(new Dimension(eng.getWidth(),eng.getHeight()));
		this.setFocusable(true);
		this.addKeyListener(j1);

	}

	@Override
	public void paintComponent(Graphics g){
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.black);
		g.drawString("Ryu", 15, 20);
		g.drawString("Ken", this.getWidth()-250, 20);
		g.setColor(Color.red);
		g.fillRect(15, 30, eng.getChar(1).getLife(), 10);
		g.fillRect(this.getWidth()-250, 30, eng.getChar(2).getLife(), 10);
		g.setColor(Color.blue);
		System.out.println("p1,y->"+(eng.getChar(1).getPositionY()));
		System.out.println("p2,y->"+eng.getChar(2).getPositionY());
		System.out.println("p1,x->"+eng.getChar(1).getPositionX());
		System.out.println("p2,x->"+eng.getChar(2).getPositionX());
		g.fillRect(eng.getChar(1).getPositionX(), 
				h-eng.getChar(1).getPositionY()-eng.getChar(1).getHeight(),
				eng.getChar(1).getWidth() , 
				eng.getChar(1).getHeight());
		if(eng.getChar(1).getTechHitbox()!=null){

			g.fillRect(eng.getChar(1).getTechHitbox().getPositionX(),
					h-eng.getChar(1).getTechHitbox().getPositionY()
					-((RectHitbox)(eng.getChar(1).getTechHitbox())).getHeight(),
					((RectHitbox)(eng.getChar(1).getTechHitbox())).getWidth(), 
					((RectHitbox)(eng.getChar(1).getTechHitbox())).getHeight());
			System.out.println("tech1 "+eng.getChar(1).getTechHitbox().getPositionY());

		}

		g.setColor(Color.yellow);
		g.fillRect(eng.getChar(2).getPositionX(), 
				h-eng.getChar(2).getPositionY()-eng.getChar(2).getHeight(),
				eng.getChar(2).getWidth() , 
				eng.getChar(2).getHeight());
		if(eng.getChar(2).getTechHitbox()!=null){				

			g.fillRect(eng.getChar(2).getTechHitbox().getPositionX(),
					h-eng.getChar(2).getTechHitbox().getPositionY()-
					((RectHitbox)(eng.getChar(2).getTechHitbox())).getHeight(),
					((RectHitbox)(eng.getChar(2).getTechHitbox())).getWidth(), 
					((RectHitbox)(eng.getChar(2).getTechHitbox())).getHeight());
			System.out.println("tech2 "+eng.getChar(2).getTechHitbox().getPositionY());

			if(eng.getChar(2).getLife()==0){
				g.setColor(Color.white);
				g.fillRect(0, 0, this.getWidth(), this.getHeight());g.setColor(Color.yellow);
				g.fillRect(eng.getChar(2).getPositionX()-eng.getChar(2).getInitHeight(), 
						h-eng.getChar(2).getPositionY()-eng.getChar(2).getHeight(), 
						eng.getChar(2).getHeight(),eng.getChar(2).getWidth() );
			}
			if(eng.getChar(1).getLife()==0){
				g.setColor(Color.white);
				g.fillRect(0, 0, this.getWidth(), this.getHeight());g.setColor(Color.blue);
				g.fillRect(eng.getChar(1).getPositionX()-eng.getChar(1).getInitHeight(), 
						h-eng.getChar(1).getPositionY()-eng.getChar(1).getHeight(), 
						eng.getChar(1).getHeight(),eng.getChar(1).getWidth() );
			}
		}

	}
	



	void step(){
		eng.step(c1, c2);
		c1=Commande.NEUTRAL;
		c2=Commande.NEUTRAL;
		repaint();
	}




}
