import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import robot.rover.simulator.block.PITBlock;
import robot.rover.simulator.robotpojo.Robot;
import robot.rover.simulator.service.RobotService;

import static robot.rover.simulator.service.RobotService.*;

import java.util.ArrayList;
import java.util.List;


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

}
