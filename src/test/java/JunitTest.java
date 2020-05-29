import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static robot.rover.simulator.service.RobotService.EAST;
import static robot.rover.simulator.service.RobotService.NORTH;
import static robot.rover.simulator.service.RobotService.SOUTH;
import static robot.rover.simulator.service.RobotService.WEST;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import robot.rover.simulator.block.PITBlock;
import robot.rover.simulator.robotpojo.Robot;
import robot.rover.simulator.service.RobotService;


//Junit test class

public class JunitTest {
	
	Robot robot = null;
	List<PITBlock> pitBlocks = null;
	
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Before
	public void init() {
		robot = new Robot();
		robot.setDirection(EAST);
		robot.setxUnit(0);
		robot.setyUnit(0);
		
		pitBlocks = new ArrayList<PITBlock>();
		PITBlock pitBLock =new PITBlock();
		pitBLock.setxUnit(1);
		pitBLock.setyUnit(0);
		pitBlocks.add(pitBLock);
	}
	

	@Test
	public void testDeployPositive() {
		RobotService.deploy(0, 0, EAST);		
		assertEquals(robot.getxUnit()+""+robot.getyUnit()+""+robot.getDirection(),
				RobotService.getRobot().getxUnit()+""+RobotService.getRobot().getyUnit()+""+RobotService.getRobot().getDirection());
		
	}
	
	@Test
	public void testDeployNegative() {
		RobotService.deploy(1, 1, NORTH);		
		assertFalse((robot.getxUnit()+""+robot.getyUnit()+""+robot.getDirection()).equals(
				RobotService.getRobot().getxUnit()+""+RobotService.getRobot().getyUnit()+""+RobotService.getRobot().getDirection()));
	}
	
	@Test
	public void shouldThrowRuntimeExceptionWhenRobotCoordinatesInvalid() {
		expectedEx.expect(UnsupportedOperationException.class);
	    expectedEx.expectMessage("Outside zone: Ignored");
		RobotService.deploy(10, 10, NORTH);				
	}
	
	@Test
	public void testBlockPITBeforeTheDeploy() {
		RobotService.setRobot(null);
		expectedEx.expect(UnsupportedOperationException.class);
	    expectedEx.expectMessage("Outside zone: Ignored");
	    RobotService.blockRobot(1, 0);
	    		
	}
	
	@Test
	public void testBlockPITAfterTheDeploy() {
		RobotService.deploy(0, 0, EAST);
	    RobotService.blockRobot(1, 0);
	    assertEquals(pitBlocks.get(0).getxUnit()+""+pitBlocks.get(0).getyUnit(),
				RobotService.pitblocks.get(0).getxUnit()+""+RobotService.pitblocks.get(0).getyUnit());				
	}

	@Test
	public void shouldThrowRuntimeExceptionWhenPITCoordinatesInvalid() {
		expectedEx.expect(UnsupportedOperationException.class);
	    expectedEx.expectMessage("Outside zone: Ignored");
		RobotService.deploy(0, 0, EAST);	
		RobotService.blockRobot(10, 10);		
	}
	
	@Test
	public void shouldThrowRuntimeExceptionWhenRobotdetectPIT() {
		expectedEx.expect(UnsupportedOperationException.class);
	    expectedEx.expectMessage("PIT Detected: Ignored");
	    RobotService.getRobot().setDirection(EAST);RobotService.getRobot().setxUnit(0);RobotService.getRobot().setyUnit(0);
	    RobotService.blockRobot(1, 0);
		RobotService.deploy(1, 0, EAST);				
	}

	@Test
	public void testBlockNegative() {
		RobotService.deploy(1, 1, EAST);
		 RobotService.blockRobot(1, 2);
		assertFalse((robot.getxUnit()+""+robot.getyUnit()+""+robot.getDirection()).equals(
				RobotService.getRobot().getxUnit()+""+RobotService.getRobot().getyUnit()+""+RobotService.getRobot().getDirection()));
	}
	
	@Test
	public void shouldThrowRuntimeExceptionWhenPITdetectedRobot() {
		expectedEx.expect(UnsupportedOperationException.class);
	    expectedEx.expectMessage("ROBOT Detected: Ignored");
	    RobotService.deploy(0, 0, EAST);
	    RobotService.blockRobot(0, 0);						
	}

	@Test
	public void testMovePositive() {
		robot.setDirection(NORTH);
		RobotService.setRobot(robot);	
		RobotService.move();
		
		assertEquals((robot.getxUnit())+""+robot.getyUnit()+""+robot.getDirection(),
				RobotService.getRobot().getxUnit()+""+RobotService.getRobot().getyUnit()+""+RobotService.getRobot().getDirection());
		
	}
	
	@Test
	public void shouldThrowRuntimeExceptionWhenPITdetectedForMove() {
		expectedEx.expect(UnsupportedOperationException.class);
	    expectedEx.expectMessage("PIT Detected: Ignored");
	    RobotService.deploy(0, 0, EAST);
	    RobotService.move();					
	}
	
	@Test
	public void shouldThrowRuntimeExceptionWhenMoveBeforeDeploy() {
		expectedEx.expect(UnsupportedOperationException.class);
	    expectedEx.expectMessage("Outside zone: Ignored");
		RobotService.setRobot(null);
		RobotService.move();		
		
	}
	
	@Test
	public void shouldThrowRuntimeExceptionWhenMoveCoordinatesInvalid() {
		expectedEx.expect(UnsupportedOperationException.class);
	    expectedEx.expectMessage("Outside zone: Ignored");
	    robot.setDirection(EAST);robot.setxUnit(9);robot.setxUnit(9);
	    RobotService.setRobot(robot);	
		RobotService.move();				
	}
	
	@Test
	public void testLeftEAST() {
		robot.setDirection(EAST);robot.setxUnit(0);robot.setxUnit(0);
		RobotService.setRobot(robot);
		RobotService.left();
		assertEquals(RobotService.getRobot().getDirection(), NORTH);
		
	}
	
	@Test
	public void testLeftNORTH() {
		robot.setDirection(NORTH);robot.setxUnit(0);robot.setxUnit(0);
		RobotService.setRobot(robot);
		RobotService.left();
		assertEquals(RobotService.getRobot().getDirection(), WEST);
		
	}
	@Test
	public void testLeftWEST() {
		robot.setDirection(WEST);robot.setxUnit(0);robot.setxUnit(0);
		RobotService.setRobot(robot);
		RobotService.left();
		assertEquals(RobotService.getRobot().getDirection(), SOUTH);
		
	}
	@Test
	public void testLeftSOUTH() {
		robot.setDirection(SOUTH);robot.setxUnit(0);robot.setxUnit(0);
		RobotService.setRobot(robot);
		RobotService.left();
		assertEquals(RobotService.getRobot().getDirection(), EAST);
		
	}
	
	@Test
	public void shouldThrowRuntimeExceptionWhenLeftBeforeDeploy() {
		expectedEx.expect(UnsupportedOperationException.class);
	    expectedEx.expectMessage("Outside zone: Ignored");
		RobotService.setRobot(null);
		RobotService.move();		
		
	}
	
	@Test
	public void testRightEAST() {
		robot.setDirection(EAST);robot.setxUnit(0);robot.setxUnit(0);
		RobotService.setRobot(robot);
		RobotService.right();
		assertEquals(RobotService.getRobot().getDirection(), SOUTH);
		
	}
	
	@Test
	public void testRightNORTH() {
		robot.setDirection(NORTH);robot.setxUnit(0);robot.setxUnit(0);
		RobotService.setRobot(robot);
		RobotService.right();
		assertEquals(RobotService.getRobot().getDirection(), EAST);
		
	}
	@Test
	public void testRightWEST() {
		robot.setDirection(WEST);robot.setxUnit(0);robot.setxUnit(0);
		RobotService.setRobot(robot);
		RobotService.right();
		assertEquals(RobotService.getRobot().getDirection(), NORTH);
		
	}
	@Test
	public void testRightSOUTH() {
		robot.setDirection(SOUTH);robot.setxUnit(0);robot.setxUnit(0);
		RobotService.setRobot(robot);
		RobotService.right();
		assertEquals(RobotService.getRobot().getDirection(), WEST);
		
	}
	
	@Test
	public void shouldThrowRuntimeExceptionWhenRightBeforeDeploy() {
		expectedEx.expect(UnsupportedOperationException.class);
	    expectedEx.expectMessage("Outside zone: Ignored");
		RobotService.setRobot(null);
		RobotService.move();		
		
	}
	
	@Test
	public void testReportPositive() {
		RobotService.executionTrace.clear();
		robot.setDirection(WEST);robot.setxUnit(0);robot.setxUnit(0);
		RobotService.setRobot(robot);
		RobotService.printRobotPosition();
		assertEquals("0,0,WEST", RobotService.executionTrace.get(0));		
		
	}
	
	@Test
	public void shouldThrowRuntimeExceptionWhenReportBeforeDeploy() {
		expectedEx.expect(UnsupportedOperationException.class);
	    expectedEx.expectMessage("Outside zone: Ignored");
		RobotService.setRobot(null);
		RobotService.move();		
		
	}
	
}
