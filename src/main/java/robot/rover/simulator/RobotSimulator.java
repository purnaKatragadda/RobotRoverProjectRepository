package robot.rover.simulator;

import static robot.rover.simulator.service.RobotService.blockRobot;
import static robot.rover.simulator.service.RobotService.deploy;
import static robot.rover.simulator.service.RobotService.executionTrace;
import static robot.rover.simulator.service.RobotService.left;
import static robot.rover.simulator.service.RobotService.move;
import static robot.rover.simulator.service.RobotService.printRobotPosition;
import static robot.rover.simulator.service.RobotService.right;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

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
	
	public List<String> process(InputStream input) {
		
		try  {
			BufferedReader br = new BufferedReader(new InputStreamReader(input));

            String line;
            
            while ((line = br.readLine()) != null) {
              
                System.out.println(line);
                if (line.contains("DEPLOY")) {
                	String[] deployArgs = line.split(" ");
                	String[] deployCofficents = deployArgs[1].split(",");
                	if (deployCofficents.length == 3) {
                		int x = new Integer(deployCofficents[0]);
                		int y = new Integer(deployCofficents[1]);
                		String s = deployCofficents[2];
                		
                		try {
                			deploy(x, y, s);
                        	}catch (UnsupportedOperationException uoe) {
        						executionTrace.add(uoe.getMessage());
        					}
                		
                	}
                	
                } else if (line.equals("MOVE")) {
                	try {
                	move();
                	}catch (UnsupportedOperationException uoe) {
						executionTrace.add(uoe.getMessage());
					}
                } else if (line.equals("LEFT")) {                	
                	try {
                		left();
                    	}catch (UnsupportedOperationException uoe) {
    						executionTrace.add(uoe.getMessage());
    					}
                } else if (line.equals("RIGHT")) {                	
                	try {
                		right();
                    	}catch (UnsupportedOperationException uoe) {
    						executionTrace.add(uoe.getMessage());
    					}
                	
                } else if (line.contains("PIT")) {
                	String[] deployArgs = line.split(" ");
                	String[] deployCofficents = deployArgs[1].split(",");
                	if (deployCofficents.length == 2) {
                		int x = new Integer(deployCofficents[0]);
                		int y = new Integer(deployCofficents[1]);               		
                		
                		try {
                			blockRobot(x, y);
                        	}catch (UnsupportedOperationException uoe) {
        						executionTrace.add(uoe.getMessage());
        					}
                		
                	}
                }else if(line.equals("REPORT")) {                	              	
                	try {
                		printRobotPosition();
                    	}catch (UnsupportedOperationException uoe) {
    						executionTrace.add(uoe.getMessage());
    					}
                } else {
                	System.err.println("Invalid entry :"+line);
                }
             
            }
        } catch (IOException e) {
			System.err.print(e.getMessage());
		}
		
       
		return executionTrace;
    }

}
