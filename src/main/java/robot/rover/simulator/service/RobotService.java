package robot.rover.simulator.service;

import java.util.ArrayList;
import java.util.List;

import robot.rover.simulator.block.PITBlock;
import robot.rover.simulator.robotpojo.Robot;

/**
 * @author Purnachandra
 *
 * This class provides the services to deploy the Robot, executes the actions(move,left,right,report) as per the conditions.
 * 
 */
public final class RobotService {
	private static Robot robot;
	public final static byte MAX_X_UNIT = 9;
	public final static byte MAX_Y_UNIT = 9;
	public final static byte ZERO =0;
	public final static String NORTH = "NORTH";
	public final static String EAST = "EAST";
	public final static String SOUTH = "SOUTH";
	public final static String WEST = "WEST";
	public static List<PITBlock> pitblocks = new ArrayList<PITBlock>();
	public static List<String> executionTrace = new ArrayList<String>();
		
	public static Robot getRobot() {
		if (null == robot) {
			robot = new Robot();
		}
		return robot;
	}
	
	public static void setRobot(Robot r) {
		robot = r;
	}
	
	
	 /**
     * This method deploys the Robot in 10*10 surface by following the given conditions.
     *
     * @param int x - represents the Robot x coordinate
     * @param int y - represents the Robot y coordinate
     * @param String direction - represents the direction of the Robot.
     * @return void
     */
	public static void deploy(int x,int y,String direction)throws UnsupportedOperationException {
		
		if((ZERO>x || x > MAX_X_UNIT) 
				|| (ZERO>y || y > MAX_Y_UNIT)) {
			throw new UnsupportedOperationException("Outside zone: Ignored");			
		}
		
		if (null != robot && pitblocks.size()>0) {
			
			for (PITBlock pitBlock :pitblocks) {
				if (x == pitBlock.getxUnit() && y == pitBlock.getyUnit()) {
					throw new UnsupportedOperationException("PIT Detected: Ignored");
				}
			}
			
		}
		
		getRobot().setDirection(direction);
		getRobot().setxUnit(x);
		getRobot().setyUnit(y);
		
	}
	
	 /**
     * This method move the Robot one step forward/backward  in 10*10 surface by following the given conditions.
     *
     * @param 
     * @return void
     */

	public static void move()throws UnsupportedOperationException {
	

		if (null == robot) {
			throw new UnsupportedOperationException("Outside zone: Ignored");
		}		
		
		int tmpX=robot.getxUnit(),tmpY=robot.getyUnit();
		if (robot.getDirection().equals(NORTH)) {
			tmpY = robot.getyUnit() + 1;
		} else if(robot.getDirection().equals(EAST)){
			tmpX = robot.getxUnit() + 1;
		} else if (robot.getDirection().equals(WEST)) {
			tmpX = robot.getxUnit() - 1;
		} else if (robot.getDirection().equals(SOUTH)){
			tmpY = robot.getyUnit() - 1;
		}
		
		if ((ZERO>tmpX || tmpX > MAX_X_UNIT) 
				|| (ZERO>tmpY || tmpY > MAX_Y_UNIT)) {
			throw new UnsupportedOperationException("Outside zone: Ignored");
		}
		
		if (pitblocks.size()>0) {
			for (PITBlock pitBlock :pitblocks) {
				if (tmpX == pitBlock.getxUnit() 
						&& tmpY == pitBlock.getyUnit()) {
					throw new UnsupportedOperationException("PIT Detected: Ignored");
				}
			}			
																								
		}
		
		
		
		robot.setxUnit(tmpX);
		robot.setyUnit(tmpY);
		
	}
	
	/**
     * This method changes the direction of  the Robot to towards left side by following the given conditions.
     *
     * @param 
     * @return void
     */

	public static void left()throws UnsupportedOperationException {
		if (null == robot) {
			throw new UnsupportedOperationException("Outside zone: Ignored");
		}
		
		if (robot.getDirection().equals(NORTH)) {
			robot.setDirection(WEST);
		} else if(robot.getDirection().equals(EAST)){
			robot.setDirection(NORTH);
		} else if (robot.getDirection().equals(WEST)) {
			robot.setDirection(SOUTH);
		} else {
			robot.setDirection(EAST);
		}
		
	}
	
	/**
     * This method changes the direction of  the Robot to towards right side by following the given conditions.
     *
     * @param 
     * @return void
     */
	public static void right()throws UnsupportedOperationException {
		
		if (null == robot) {
			throw new UnsupportedOperationException("Outside zone: Ignored");
		}
		
		if (robot.getDirection().equals(NORTH)) {
			robot.setDirection(EAST);
		} else if(robot.getDirection().equals(EAST)){
			robot.setDirection(SOUTH);
		} else if (robot.getDirection().equals(WEST)) {
			robot.setDirection(NORTH);
		} else {
			robot.setDirection(WEST);
		}
		
	}
	
	 /**
     * This method deploys the PITs in 10*10 surface by following the given conditions.
     *
     * @param int x - represents the Robot x coordinate
     * @param int y - represents the Robot y coordinate    
     * @return void
     */
	
	public static void blockRobot(int x,int y)throws UnsupportedOperationException {
		
		if (null == robot || (ZERO>x || x > MAX_X_UNIT) 
				|| (ZERO>y || y > MAX_Y_UNIT)) {
			throw new UnsupportedOperationException("Outside zone: Ignored");
		}		
				
		
		if (robot.getxUnit() == x && robot.getyUnit() == y) {
			throw new UnsupportedOperationException("ROBOT Detected: Ignored");
		}
		
		PITBlock pitBLock = new PITBlock();
		pitBLock.setxUnit(x);
		pitBLock.setyUnit(y);
		pitblocks.add(pitBLock);
	
	}
	
	 /**
     * This method maintains always the Robot's current position by following the given conditions.
     *
     * @param 
     * @return void
     */
	
	public static void printRobotPosition()throws UnsupportedOperationException {
		if (null == robot) {
			throw new UnsupportedOperationException("Outside zone: Ignored");
		}
		executionTrace.add(robot.getxUnit()+","+robot.getyUnit()+","+robot.getDirection());
		
		//System.out.println(executionTrace);
	}


}
