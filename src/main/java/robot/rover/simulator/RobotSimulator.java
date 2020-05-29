package robot.rover.simulator;

import static robot.rover.simulator.service.RobotService.blockRobot;
import static robot.rover.simulator.service.RobotService.deploy;
import static robot.rover.simulator.service.RobotService.executionTrace;
import static robot.rover.simulator.service.RobotService.left;
import static robot.rover.simulator.service.RobotService.move;
import static robot.rover.simulator.service.RobotService.printRobotPosition;
import static robot.rover.simulator.service.RobotService.right;
import static robot.rover.simulator.service.RobotService.NORTH;
import static robot.rover.simulator.service.RobotService.SOUTH;
import static robot.rover.simulator.service.RobotService.EAST;
import static robot.rover.simulator.service.RobotService.WEST;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author Purnachandra
 *
 * This class takes input,validate it then delegate the instructions  given in the input to Robot services Class to execute.
 * 
 */

public class RobotSimulator {
	
	public static void main(String[] args) {
		
		RobotSimulator robot = new RobotSimulator();
		//robot.process(input);
		try  {
			InputStream input = new FileInputStream("./directRobot.txt");
			List<String> executionTrace = robot.process(input);
			for (String entry : executionTrace) {
				System.out.println(entry);				
			}
		}catch (IOException ioe) {
			System.err.print("File error :"+ioe.getMessage());
		}
		
	}
	
	 /**
     * Should process the input and return the report lines as result.
     *
     * @param input the input.
     * @return the reported lines.
     */
	
	public List<String> process(InputStream input) {
		
		try  {
			BufferedReader br = new BufferedReader(new InputStreamReader(input));

            String line;
            
            while ((line = br.readLine()) != null) {
              try {
                System.out.println(line);
                if (line.contains("DEPLOY")) {
                	String[] deployArgs = line.split(" ");
                	String[] deployCofficents = null;
                	if (null != deployArgs[1]) {
                		deployCofficents = deployArgs[1].split(",");
	                	if (null != deployCofficents && deployCofficents.length == 3) {
	                		int x = new Integer(deployCofficents[0]);
	                		int y = new Integer(deployCofficents[1]);
	                		String s = deployCofficents[2];
	                		if (null !=s && (s.equals(NORTH) || s.equals(SOUTH) || s.equals(WEST) || s.equals(EAST))) {
	                			deploy(x, y, s);
	                		}else {
	                			System.err.println("Invalid entry :"+ line);
	                		}                		             		
	                		
	                	} else {
	                		System.err.println("Invalid entry :"+ line);
	                	}
                	} else {
                		System.err.println("Invalid entry :"+ line);
                	}
                	
                } else if (line.equals("MOVE")) {
                	move();
                } else if (line.equals("LEFT")) {                	
                	left();
                } else if (line.equals("RIGHT")) {                	
                	right();
                	
                } else if (line.contains("PIT")) {
                	String[] deployArgs = line.split(" ");
                	String[] deployCofficents = null;
                	if (null != deployArgs[1]) {
                		deployCofficents = deployArgs[1].split(",");
	                	if (null != deployCofficents && deployCofficents.length == 2) {
	                		int x = new Integer(deployCofficents[0]);
	                		int y = new Integer(deployCofficents[1]);               		
	                		
	                		blockRobot(x, y);                		
	                	} else {
	                		System.err.println("Invalid entry :"+ line);
	                	}
                	} else {
                		System.err.println("Invalid entry :"+ line);
                	}
                }else if(line.equals("REPORT")) {                	              	
                	printRobotPosition();
                } else {
                	 System.err.println("Invalid entry :"+line);
                }
              } catch(UnsupportedOperationException uoe) {
            	  executionTrace.add(uoe.getMessage());
              }
              catch (RuntimeException e) {
            	  System.err.println(e.getMessage());
              }
            }
        } catch (IOException e) {
			System.err.print(e.getMessage());
		}	
       
		return executionTrace;
    }

}
