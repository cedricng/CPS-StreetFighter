package proj.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import proj.contract.PostconditionError;
import proj.contract.PreconditionError;
import proj.service.Character;
import proj.service.Commande;
import proj.service.Engine;
import proj.service.Player;

public abstract class  AbstractEngineTest {
	protected Engine eng;
	protected Player p1;
	protected Player p2;
	protected Character c1;
	protected Character c2;

	@Before
	public abstract void beforeTests();

	@Test
	public void testinitpos(){
		eng.init(500, 400, 20, p1, p2);
		assertTrue("testinitpos:postconditionerror ",eng.getHeight()==500&&eng.getWidth()==400
				&&eng.getPlayer(1)==p1&&eng.getPlayer(2)==p2
				&&eng.getChar(1).getPositionX()==400/2-20/2&&eng.getChar(2).getPositionX()==400/2+20/2
				&&eng.getChar(1).getPositionY()==0&&eng.getChar(2).getPositionY()==0
				&&eng.getChar(1).faceRight()&&!eng.getChar(2).faceRight());
	}
	@Test
	public void testinitneg(){
		try{
			eng.init(-500, 400, 20, p1, p2);
			fail("testinitneg1: il aurait dû avoir une  PreconditionError");
		}catch(PreconditionError p){}
		
/*		try{
			eng.init(500, 400, 20, p1, p1);
			fail("testinitneg1: il aurait dû avoir une  PreconditionError");
		}catch(PreconditionError p){}*/
		
	}
	
	@Test
	public void teststeppreNeg(){
		try{
			eng.init(500, 400, 20, p1, p2);
			eng.getChar(1).setLife(0);
			eng.step(Commande.NEUTRAL, Commande.GUARD);
			fail("testinitneg1: il aurait dû avoir une  PreconditionError");
		}catch(PreconditionError p){
		//	System.out.println(p.getMessage());
		}
		
	}
	
	//test coup de poing
	@Test
	public void testStepTrans1(){
		
		try{
		eng.init(500, 400, 20, p1, p2);
		eng.step(Commande.PUNCH, Commande.NEUTRAL);
		eng.step(Commande.NEUTRAL,Commande.NEUTRAL);
		eng.step(Commande.NEUTRAL,Commande.NEUTRAL);
		}catch(PostconditionError e){
			e.printStackTrace();
			fail("il devait pas avoir d'exception");
		}
	}
	
	//test coup de poing joueur1 et garde joueur2 
	@Test
	public void testStepTrans2(){
		eng.init(500, 400, 20, p1, p2);
		eng.step(Commande.PUNCH, Commande.NEUTRAL);
		eng.step(Commande.NEUTRAL,Commande.GUARD);
		eng.step(Commande.NEUTRAL,Commande.NEUTRAL);

	}
	//test coup de poing joueur2 et garde joueur1 
	@Test
	public void testStepTrans4(){
		eng.init(500, 400, 20, p1, p2);
		eng.step(Commande.NEUTRAL, Commande.PUNCH);
		eng.step(Commande.GUARD,Commande.NEUTRAL);
		eng.step(Commande.NEUTRAL,Commande.NEUTRAL);

	}
	
	//test coup de poing joueur1 et joueur2 en même temps
	@Test
	public void testStepTrans3(){
		eng.init(500, 400, 20, p1, p2);
		eng.step(Commande.PUNCH, Commande.PUNCH);
		eng.step(Commande.NEUTRAL,Commande.NEUTRAL);
		eng.step(Commande.NEUTRAL,Commande.NEUTRAL);

	}
	
	//test coup de
	



	@After
	public final void afterTests(){
		eng=null;
		p1=null;
		p2=null;
	}
}
