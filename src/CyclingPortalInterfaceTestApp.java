import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import cycling.CyclingPortal;
import cycling.CyclingPortalInterface;
import cycling.DuplicatedResultException;
import cycling.IDNotRecognisedException;
import cycling.IllegalNameException;
import cycling.InvalidCheckpointsException;
import cycling.InvalidLengthException;
import cycling.InvalidLocationException;
import cycling.InvalidNameException;
import cycling.InvalidStageStateException;
import cycling.InvalidStageTypeException;
import cycling.Segment;
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

		int[] raceIds = new int[5];
		try {
			int numOfRaces = 5;
			for(int x = 0; x<numOfRaces; x++){
				raceIds[x] =  portal.createRace("Test"+x, "description Test"+x);
			}
		} catch (IllegalNameException e) {
			System.out.println(e.getMessage());
		} catch (InvalidNameException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println(portal.getRaceIds());
		try {
			System.out.println(portal.viewRaceDetails(raceIds[0]));
		} catch (IDNotRecognisedException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			portal.removeRaceById(raceIds[0]);
		} catch (IDNotRecognisedException e1) {
			System.out.println(e1.getMessage());
		}
		
		System.out.println(portal.getRaceIds());
		try {
			System.out.println(portal.viewRaceDetails(raceIds[0]));
		} catch (IDNotRecognisedException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			System.out.println(portal.getNumberOfStages(raceIds[1]));
		} catch (IDNotRecognisedException e) {
			System.out.println(e.getMessage());
		}
		
		int[] stageIds = new int[5];
		try {
			int numOfStages = 5;
			for(int x = 0; x<numOfStages; x++){
				stageIds[x] = portal.addStageToRace(raceIds[2], "test"+x, "test"+x, 10, LocalDateTime.of(2022, 10, 10, 0, 0), StageType.FLAT);
			}
		} catch (IDNotRecognisedException | IllegalNameException | InvalidNameException
		| InvalidLengthException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			System.out.println(portal.getNumberOfStages(raceIds[2]));
		} catch (IDNotRecognisedException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			System.out.println(portal.getRaceStages(raceIds[2]));
		} catch (IDNotRecognisedException e) {
			System.out.println(e.getMessage());
		}

		try {
			System.out.println(portal.getStageLength(stageIds[2]));
		} catch (IDNotRecognisedException e) {
			System.out.println(e.getMessage());
		}

		try {
			portal.removeStageById(stageIds[2]);
		} catch (IDNotRecognisedException e) {
			System.out.println(e.getMessage());
		}


		ArrayList<Integer> segmentIds = new ArrayList<Integer>();
		try {
			segmentIds.add(portal.addCategorizedClimbToStage(stageIds[4], 6.0, SegmentType.C1, 1.0, 0.5));
		} catch (IDNotRecognisedException | InvalidLocationException | InvalidStageStateException
				| InvalidStageTypeException e) {
			System.out.println(e.getMessage());
		}

		try {
			segmentIds.add( portal.addIntermediateSprintToStage(stageIds[4], 2.0));
		} catch (IDNotRecognisedException | InvalidLocationException | InvalidStageStateException
				| InvalidStageTypeException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			segmentIds.add( portal.addIntermediateSprintToStage(stageIds[4], 6.5));
		} catch (IDNotRecognisedException | InvalidLocationException | InvalidStageStateException
				| InvalidStageTypeException e) {
			System.out.println(e.getMessage());

		}

		try {
			portal.concludeStagePreparation(stageIds[4]);
		} catch (IDNotRecognisedException | InvalidStageStateException e) {
			System.out.println(e.getMessage());
		}

		try {
			portal.removeSegment(segmentIds.get(1));
		} catch (IDNotRecognisedException | InvalidStageStateException e) {
			System.out.println(e.getMessage());
		}

		int[] test;
		try {
			test = portal.getStageSegments(stageIds[4]);
			for(int x : test) {
				System.out.print(x + " ");
			}
			System.out.println();
		} catch (IDNotRecognisedException e) {
			System.out.println(e.getMessage());
		}

		try {
			
			portal.createTeam("Test_Name1", "Test Description1");
			portal.createTeam("Test_Name2", "Test Description2");
			portal.createTeam("Test_Name3", "Test Description3");
			portal.createTeam("Test_Name4", "Test Description4");
			portal.createTeam("Test_Name5", "Test Description5");

		} catch (IllegalNameException | InvalidNameException e) {
			System.out.println(e.getMessage());
		}

		System.out.println(portal.getTeams());

		try {
			portal.removeTeam(2);
		} catch (IDNotRecognisedException e) {
			System.out.println(e.getMessage());
		}

		try {
			System.out.println(portal.getTeamRiders(1));
		} catch (IDNotRecognisedException e) {
			System.out.println(e.getMessage());
		}

		try {
			portal.createRider(1, "josh", 2022);
		} catch (IllegalArgumentException | IDNotRecognisedException e) {
			System.out.println(e.getMessage());
		}

		try {
			LocalTime[] arr = {};
			portal.registerRiderResultsInStage(stageIds[4], 1, arr);
		} catch (IDNotRecognisedException | DuplicatedResultException | InvalidCheckpointsException
				| InvalidStageStateException e) {
			System.out.println(e.getMessage());
		}

		try {
			System.out.println(portal.getRiderResultsInStage(stageIds[4], 1));
			System.out.println(portal.getRiderResultsInStage(stageIds[4], 9));
		} catch (IDNotRecognisedException e) {
			System.out.println(e.getMessage());
		}

		try{
			LocalTime t1 = LocalTime.parse("00:00:00");
			LocalTime t2 = LocalTime.parse("00:00:01").minusNanos(100000);
			LocalTime t3 = LocalTime.parse("00:00:02");
			LocalTime t4 = LocalTime.parse("00:00:02").plusNanos(5000000);
			LocalTime[] arr1 = {t1,t1,t1,t1,t4};
			LocalTime[] arr2 = {t1,t1,t1,t1,t2};
			LocalTime[] arr3 = {t1,t1,t1,t1,t3};
			portal.createRider(1, "josh2", 2022);
			portal.createRider(1, "Tom", 2022);
			portal.createRider(1, "Bob", 2022);
			portal.registerRiderResultsInStage(stageIds[4], 2, arr1);
			portal.registerRiderResultsInStage(stageIds[4], 3, arr2);
			portal.registerRiderResultsInStage(stageIds[4], 4, arr3);
		} catch (IllegalArgumentException | IDNotRecognisedException e) {
			System.out.println(e.getMessage());
		} catch (DuplicatedResultException e) {
			System.out.println(e.getMessage());
		} catch (InvalidCheckpointsException e) {
			System.out.println(e.getMessage());
		} catch (InvalidStageStateException e) {
			System.out.println(e.getMessage());
		}

		try {
			System.out.println(portal.getRiderAdjustedElapsedTimeInStage(stageIds[4], 2));
			System.out.println(portal.getRiderAdjustedElapsedTimeInStage(stageIds[4], 3));
			System.out.println(portal.getRiderAdjustedElapsedTimeInStage(stageIds[4], 4));
		} catch (IDNotRecognisedException e) {
			System.out.println(e.getMessage());
		}

		//SKNADOLHFN
		
	}
}
