import java.time.LocalDateTime;

import cycling.CyclingPortal;
import cycling.CyclingPortalInterface;
import cycling.IDNotRecognisedException;
import cycling.IllegalNameException;
import cycling.InvalidLengthException;
import cycling.InvalidNameException;
import cycling.StageType;

/**
 * A short program to illustrate an app testing some minimal functionality of a
 * concrete implementation of the CyclingPortalInterface interface -- note you
 * will want to increase these checks, and run it on your CyclingPortal class
 * (not the BadCyclingPortal class).
 *
 * 
 * @author Diogo Pacheco
 * @version 1.0
 */
public class CyclingPortalInterfaceTestApp {

	/**
	 * Test method.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		System.out.println("The system compiled and started the execution...");

		CyclingPortalInterface portal = new CyclingPortal();

		try {
			int numOfStages = 5;
			for(int x = 0; x<numOfStages; x++){
				portal.createRace("Test"+x, "description Test"+x);
			}
			
		} catch (IllegalNameException e) {
			System.out.println(e.getMessage());
		} catch (InvalidNameException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println(portal.getRaceIds());
		try {
			System.out.println(portal.viewRaceDetails(1));
		} catch (IDNotRecognisedException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			portal.removeRaceById(1);
		} catch (IDNotRecognisedException e1) {
			System.out.println(e1.getMessage());
		}
		
		System.out.println(portal.getRaceIds());
		try {
			System.out.println(portal.viewRaceDetails(1));
		} catch (IDNotRecognisedException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			System.out.println(portal.getNumberOfStages(2));
		} catch (IDNotRecognisedException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			portal.addStageToRace(3, "test1", "test1", 6, LocalDateTime.of(2022, 10, 10, 0, 0), StageType.FLAT);
		} catch (IDNotRecognisedException | IllegalNameException | InvalidNameException
		| InvalidLengthException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			System.out.println(portal.getNumberOfStages(3));
		} catch (IDNotRecognisedException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			System.out.println(portal.getRaceStages(3));
		} catch (IDNotRecognisedException e) {
			System.out.println(e.getMessage());
		}
	}
	
}
