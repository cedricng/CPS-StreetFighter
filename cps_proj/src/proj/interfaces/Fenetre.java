package proj.interfaces;

import java.awt.Dimension;
import java.awt.HeadlessException;

import javax.swing.JFrame;

import proj.contract.CharacterContract;
import proj.contract.EngineContract;
import proj.contract.InvariantError;
import proj.contract.PlayerContract;
import proj.contract.PostconditionError;
import proj.impl.CharacterImpl;
import proj.impl.EngineImpl;
import proj.impl.PlayerImpl;
import proj.service.Character;
import proj.service.*;

public class Fenetre extends JFrame {


	public Fenetre() {
		int w=900;
		int h=700;
		Engine eng=new EngineContract(new EngineImpl());
		Character c1 =new CharacterContract(new CharacterImpl());
		Character c2=new CharacterContract(new CharacterImpl());
		Player p1=new PlayerContract(new PlayerImpl());
		Player p2=new PlayerContract(new PlayerImpl());
		p1.init(c1);
		p2.init(c2);
		eng.init(h, w, 50, p1, p2);
		this.setTitle("Abstract Street Fighter");
		this.setSize(w, h);
		this.setLocationRelativeTo(null);               
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Contentpanel pan=new Contentpanel(eng);

		this.setContentPane(pan);
		this.setVisible(true);

	}

	public static void main(String[] args) {
		Fenetre f=new Fenetre();

		Contentpanel pan=(Contentpanel) f.getContentPane();
		int i=0;
		while(true){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();

			}

			System.out.println("tour "+i+"<"+pan.getC1()+","+pan.getC2()+">");
			try{
				pan.step();
			}
			catch(PostconditionError pe){
				pe.printStackTrace();
				
			}
			catch(InvariantError ie){
				ie.printStackTrace();
			}
			i++;
		}
	}


}
