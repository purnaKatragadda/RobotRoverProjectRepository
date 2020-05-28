package robot.rover.simulator.service;

import java.util.ArrayList;
import java.util.List;

import robot.rover.simulator.block.PITBlock;
import robot.rover.simulator.robotpojo.Robot;

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
	
	public static void printRobotPosition()throws UnsupportedOperationException {
		if (null == robot) {
			throw new UnsupportedOperationException("Outside zone: Ignored");
		}
		executionTrace.add(robot.getxUnit()+","+robot.getyUnit()+","+robot.getDirection());
		
		//System.out.println(executionTrace);
	}


}
