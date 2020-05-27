package robot.rover.simulator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import robot.rover.simulator.service.RobotService;

import static robot.rover.simulator.service.RobotService.*;

public class RobotSimulator {
	
	public static void main(String[] args) {
		
		RobotSimulator robot = new RobotSimulator();
		//robot.process(input);
		try (InputStream input = new FileInputStream("./directRobot.txt")) {
			robot.process(input);
		}catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	}
	
	public List<String> process(InputStream input) {
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(input));) {

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
                		deploy(x, y, s);
                	}
                	
                } else if (line.equals("MOVE")) {
                	RobotService.move();
                } else if (line.contains("PIT")) {
                	String[] deployArgs = line.split(" ");
                	String[] deployCofficents = deployArgs[1].split(" ");
                	if (deployCofficents.length == 2) {
                		int x = new Integer(deployCofficents[0]);
                		int y = new Integer(deployCofficents[1]);                		
                		blockRobot(x, y);
                	}
                }else if(line.equals("REPORT")) {
                	printRobotPosition();
                } else {
                	throw new UnsupportedOperationException();
                }
                
            }
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       // throw new UnsupportedOperationException();
		return null;
    }

}
