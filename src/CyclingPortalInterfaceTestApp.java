import java.lang.reflect.Array;
import java.time.LocalDateTime;

import cycling.CyclingPortal;
import cycling.CyclingPortalInterface;
import cycling.IDNotRecognisedException;
import cycling.IllegalNameException;
import cycling.InvalidLengthException;
import cycling.InvalidLocationException;
import cycling.InvalidNameException;
import cycling.InvalidStageStateException;
import cycling.InvalidStageTypeException;
import cycling.SegmentType;
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
			int numOfRaces = 5;
			for(int x = 0; x<numOfRaces; x++){
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
			int numOfStages = 5;
			for(int x = 0; x<numOfStages; x++){
				portal.addStageToRace(3, "test"+x, "test"+x, 10, LocalDateTime.of(2022, 10, 10, 0, 0), StageType.FLAT);
			}
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

		try {
			System.out.println(portal.getStageLength(1));
		} catch (IDNotRecognisedException e) {
			System.out.println(e.getMessage());
		}

		try {
			portal.removeStageById(3);
		} catch (IDNotRecognisedException e) {
			System.out.println(e.getMessage());
		}

	
		try {
			portal.addCategorizedClimbToStage(5, 6.0, SegmentType.C1, 1.0, 0.5);
		} catch (IDNotRecognisedException | InvalidLocationException | InvalidStageStateException
				| InvalidStageTypeException e) {
			System.out.println(e.getMessage());
		}

		try {
			portal.addIntermediateSprintToStage(5, 2.0);
		} catch (IDNotRecognisedException | InvalidLocationException | InvalidStageStateException
				| InvalidStageTypeException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			portal.addIntermediateSprintToStage(5, 6.5);
		} catch (IDNotRecognisedException | InvalidLocationException | InvalidStageStateException
				| InvalidStageTypeException e) {
			System.out.println(e.getMessage());

		}

		try {
			portal.concludeStagePreparation(5);
		} catch (IDNotRecognisedException | InvalidStageStateException e) {
			System.out.println(e.getMessage());
		}

		try {
			portal.removeSegment(1);
		} catch (IDNotRecognisedException | InvalidStageStateException e) {
			System.out.println(e.getMessage());
		}

		int[] test;
		try {
			test = portal.getStageSegments(5);
			for(int x : test) {
				System.out.print(x + " ");
			}
			System.out.println();
		} catch (IDNotRecognisedException e) {
			System.out.println(e.getMessage());
		}
	}
}
