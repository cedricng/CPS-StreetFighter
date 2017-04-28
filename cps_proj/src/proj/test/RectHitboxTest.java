package proj.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import proj.contract.PostconditionError;
import proj.contract.PreconditionError;
import proj.contract.RectHitboxContract;
import proj.impl.RectHitboxImpl;
import proj.service.RectHitbox;

public class RectHitboxTest {
	RectHitbox rh;
	@After
	public void afterTest(){
		rh=null;
	}
	@Before
	public void beforeTest(){
		rh=new RectHitboxContract(new RectHitboxImpl());
	}

	@Test
	public void testpreInitPos() {
		try{
		rh.init(10, 2,3,4);
		}catch(PreconditionError e){
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testpreInitNeg() {
		try{
		rh.init(10, 2,-3,4);
		fail("pas d'exception");
		}catch(PreconditionError e){
			
		}
	}
	
	@Test
	public void testpreResizePos() {
		try{
		rh.init(10, 2,3,4);
		rh.resize(6, 8);
		}catch(PreconditionError e){
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testpreResizeNeg() {
		try{
		rh.init(10, 2,3,4);
		rh.resize(6, -8);
		fail("pas d'exception");
		}catch(PreconditionError e){
			
		}
	}
	
	@Test
	public void postmoveToPos(){
		try{
			rh.init(10, 2,3,4);
			rh.MoveTo(6, 9);
			
		}catch(PostconditionError e){
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}
	
	@Test
	public void testpostInitPos(){
		try{
			rh.init(10, 2,3,4);
			
			
			}catch(PostconditionError e){
				fail(e.getMessage());
			}
	}
	
	@Test
	public void testpostResizePost() {
		try{
		rh.init(10, 2,3,4);
		rh.resize(6, 8);
		
		}catch(PreconditionError e){
			fail(e.getMessage());
		}
	}
	
	
}
