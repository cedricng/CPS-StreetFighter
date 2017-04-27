package proj.test;

import static org.junit.Assert.*;

import org.junit.Test;

import proj.contract.CharacterContract;
import proj.contract.EngineContract;
import proj.impl.CharacterImpl;
import proj.impl.EngineImpl;
import proj.service.*;
import proj.service.Character;

public class CharacterTest1 extends AbstractCharacterTest {

	

	@Override
	public void beforeTest() {
		// TODO Auto-generated method stub
		char1=new CharacterContract(new CharacterImpl());
		char2=new CharacterContract(new CharacterImpl());
		// puisqu'on aura à tester engine on instancie la version sans contrat
		eng=new EngineImpl();
		
	}

}
