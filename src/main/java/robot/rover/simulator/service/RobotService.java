package robot.rover.simulator.service;

import java.util.ArrayList;
import java.util.List;

import robot.rover.simulator.block.PITBlock;
import robot.rover.simulator.robotpojo.Robot;

public final class RobotService {
	private static Robot robot;
	public final static byte MAX_X_UNIT = 10;
	public final static byte MAX_Y_UNIT = 10;
	public final static String NORTH = "NORTH";
	public final static String EAST = "EAST";
	public final static String SOUTH = "SOUTH";
	public final static String WEST = "WEST";
	public static List<PITBlock> pitblocks = new ArrayList<PITBlock>();
		
	public static Robot getRobot() {
		if (null == robot) {
			robot = new Robot();
		}
		return robot;
	}
	
	public static void deploy(int x,int y,String direction) {
		
		getRobot().setDirection(direction);
		getRobot().setxUnit(x);
		getRobot().setyUnit(y);
		
	}
	

public static void move() {
		
		if (robot.getDirection().equals(NORTH)) {
			robot.setyUnit(robot.getyUnit() + 1);
		} else if(robot.getDirection().equals(EAST)){
			robot.setxUnit(robot.getxUnit() + 1);
		} else if (robot.getDirection().equals(WEST)) {
			robot.setxUnit(robot.getxUnit() - 1);
		} else {
			robot.setyUnit(robot.getyUnit() - 1);
		}
		
	}

	public static void left() {
		
		if (robot.getDirection().equals(NORTH)) {
			robot.setDirection(WEST);
		} else if(robot.getDirection().equals(EAST)){
			robot.setDirection(SOUTH);
		} else if (robot.getDirection().equals(WEST)) {
			robot.setDirection(SOUTH);
		} else {
			robot.setDirection(EAST);
		}
		
	}
	public static void right() {
		
		if (robot.getDirection().equals(NORTH)) {
			robot.setDirection(EAST);
		} else if(robot.getDirection().equals(EAST)){
			robot.setDirection(NORTH);
		} else if (robot.getDirection().equals(WEST)) {
			robot.setDirection(NORTH);
		} else {
			robot.setDirection(WEST);
		}
		
	}


}
