package robot.rover.simulator;

import java.io.InputStream;
import java.util.List;

import static robot.rover.simulator.service.RobotService.*;

public class RobotSimulator {
	
	public static void main(String[] args) {
		
		RobotSimulator robot = new RobotSimulator();
		//robot.process(input);
		
	}
	
	public List<String> process(InputStream input) {
		deploy(0, 0,NORTH);
        throw new UnsupportedOperationException();
    }

}
