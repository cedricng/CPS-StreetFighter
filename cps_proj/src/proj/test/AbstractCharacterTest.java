package proj.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import proj.contract.EngineContract;
import proj.contract.InvariantError;
import proj.contract.PreconditionError;
import proj.service.Engine;
import proj.service.Character;
import proj.service.Commande;
import proj.service.Player;

public abstract class AbstractCharacterTest {
	protected Character char1;
	protected Engine eng;
	protected Character char2;
	protected Player pl1;
	protected Player pl2;

	@Before
	public abstract void beforeTest();
	@After
	public void afterTest(){
		pl1=null;
		pl2=null;
		eng=null;
	}

	@Test
	public void testpreInitPos() {
		eng.init(500,400,50,pl1,pl2);
		char1.init(2,10,true,eng,10,10);

		assertTrue("testinitpos:postconditionerror",
				char1.getLife()==2&&char1.faceRight()&&char1.getSpeed()==10&&
				char1.getEngine()==eng&&
				char1.getCharBox()!=null);

	}

	@Test
	public void testpreInitprecond() {
		try {
			char1.init(-10,10,true,eng,10,10);
			eng.init(500,400,50,pl1,pl2);

			fail("testpreInitNeg:une exception de type PreconditionError aurait du etre levee");
		}
		catch(PreconditionError e){	}
		try {
			char1.init(10,-10,true,eng,10,10);
			eng.init(500,400,50,pl1,pl2);

			fail("testpreInitNeg:une exception de type PreconditionError aurait du etre levee");
		}
		catch(PreconditionError e){	}
	}

	@Test
	public void testSteppredcpositif(){
		eng.init(500,400,50,pl1,pl2);
		char1.init(2,10,true,eng,10,10);

		assertTrue("testinitpos:postconditionerror",
				char1.getLife()==2&&char1.faceRight()&&char1.getSpeed()==10&&
				char1.getEngine()==eng&&
				char1.getCharBox()!=null);
		try{
			char1.step(Commande.RIGHT);
		}catch (PreconditionError e) {
			fail("il n'aurait pas dû avoir une PreconditionError ");
		}
	}


	@Test
	public void testStepPredcNeg(){
		eng.init(500,400,50,pl1,pl2);
		char1.init(2,10,true,eng,10,10);
		char2.init(2,10,true,eng,10,10);


		assertTrue("testinitpos:postconditionerror",
				char1.getLife()==2&&char1.faceRight()&&char1.getSpeed()==10&&
				char1.getEngine()==eng&&
				char1.getCharBox()!=null);
		//on met la vie a zero 
		char1.setLife(0);
		char2.setLife(0);
		try{
			char1.step(Commande.LEFT);
			fail("il aurait dû avoir une PreconditionError ");
		}catch (PreconditionError e) {}
		
	}

	/*@Test
	public void testStepPostPosi(){
		eng.init(500,400,50,char1,char2);
		char1.init(2,10,true,eng,10,10);
		char2.init(2,10,true,eng,10,10);


		assertTrue("testinitpos:postconditionerror",
				char1.getLife()==2&&char1.faceRight()&&char1.getSpeed()==10&&
				char1.getEngine()==eng&&
				char1.getCharBox()!=null);
			char1.step(Commande.LEFT);
		assertTrue("testStepPostPosi:postcontionerror",)
		
	}*/

}
















