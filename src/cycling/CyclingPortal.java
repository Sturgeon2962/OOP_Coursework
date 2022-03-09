package cycling;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * CyclingPortal is love, CyclingPortal is life.
 * 
 * @author Humans
 * @version 1.0
 *
 */
public class CyclingPortal implements CyclingPortalInterface {
	// Move lists to be stored here
	private int nextSegmentId = 1;
	private int nextStageId = 1;
	private int nextRiderId = 1;
	private int nextTeamId = 1;
	private int nextRaceId = 1;

	private ArrayList<Race> races = new ArrayList<Race>();
	private ArrayList<Team> teams = new ArrayList<Team>();
	private HashMap<Integer, ArrayList<Result>> stageResults = new HashMap<Integer, ArrayList<Result>>();

	private void isInvalidName(String name) throws InvalidNameException{

		if(name.equals(null) || name.equals("")){
			throw new InvalidNameException("name is empty or null");
		}
		if(name.contains(" ")){
			throw new InvalidNameException("Name is more than 1 word");
		}
		//check if string is too long - what is system limit of characters?	
	}

	private Race getRaceById(int raceId) throws IDNotRecognisedException{
		for (Race race : races) {
			if (race.getRaceID() == raceId) {
				return race;
			}
		}
		throw new IDNotRecognisedException("The Id does not exist");
	}

	private int getRaceIdByStageId(int stageId) throws IDNotRecognisedException {
		for (Race race : races) {
			for (Stage stage : race.getStages()) {
				if (stageId == stage.getStageId()) {
					return race.getRaceID();
				}
			}
		}
		throw new IDNotRecognisedException("The Id does not exist");
	}

	private int getStageIdBySegmentId(int segmentId) throws IDNotRecognisedException {
		for (Race race : races) {
			for (Stage stage : race.getStages()) {
				for (Segment segment : stage.getSegments()) {
					if (segmentId == segment.getSegmentId()) {
						return stage.getStageId();
					}
				}
			}
		}
		throw new IDNotRecognisedException("The Id does not exist");
	}

	private Stage getStageById(int stageId) throws IDNotRecognisedException {
		for (Race race : races) {
			for (Stage stage : race.getStages()) {
				if (stageId == stage.getStageId()) {
					return stage;
				}
			}
		}
		throw new IDNotRecognisedException("The Id does not exist");
	}

	private void checkValidLocation(double stageLength, double location) throws InvalidLocationException {
		if(location < 0) {
			throw new InvalidLocationException("location can't be negitive");
		} else if(location > stageLength) {
			throw new InvalidLocationException("location can't be after the end of the stage");
		}
	}

	private void checkValidStageState(boolean targetState, int stageId, String msg) throws IDNotRecognisedException, InvalidStageStateException {
		if(targetState != getStageById(stageId).isFullyCreated()) {
			throw new InvalidStageStateException(msg);
		}
	}

	private void checkValidCreateSegment(int stageId, double location) throws InvalidLocationException, InvalidStageStateException, IDNotRecognisedException {
		checkValidLocation(getStageLength(stageId), location);
		checkValidStageState(false, stageId, "stage already finished");
	}

	private Team getTeamById(int teamId) throws IDNotRecognisedException{
		for(Team team:teams){
			if (team.getId() == teamId){
				return team;
			}
		}
		throw new IDNotRecognisedException("team does not exist");
	}

	private Team getTeamByRiderId(int riderId) throws IDNotRecognisedException {
		for(Team team:teams){
			for(Rider rider : team.getTeamMembers()) {
				if (rider.getRiderId() == riderId) {
					return team;
				}
			}
		}
		throw new IDNotRecognisedException("rider does not exist");
	}

	private Rider getRiderById(int riderId) throws IDNotRecognisedException {
		for(Team team:teams){
			for(Rider rider : team.getTeamMembers()) {
				if (rider.getRiderId() == riderId) {
					return rider;
				}
			}
		}
		throw new IDNotRecognisedException("rider does not exist");
	}

	private boolean isClimb(SegmentType type) {
		if(type == SegmentType.SPRINT) {
			return false;
		}
		return true;
	}

	@Override
	public int[] getRaceIds() {
		int[] raceIds = new int[races.size()];
		for(int i = 0; i < races.size(); i++){
			raceIds[i] = races.get(i).getRaceID();
		}

		return raceIds;
	}

	@Override
	public int createRace(String name, String description) throws IllegalNameException, InvalidNameException {
		for(Race race:races){
			if(race.getName().equals(name)){
				throw new IllegalNameException("Name in use");
			}
		}
		isInvalidName(name);
		Race newRace = new Race(name, description, nextRaceId++);
		races.add(newRace);
		return newRace.getRaceID();
	}

	@Override
	public String viewRaceDetails(int raceId) throws IDNotRecognisedException {
		for(Race race : races){
			if(race.getRaceID() == raceId){
				return race.getDescription();
			}
		}
		throw new IDNotRecognisedException("The Id does not exist");
	}

	@Override
	public void removeRaceById(int raceId) throws IDNotRecognisedException {
		Race race = getRaceById(raceId);
		races.remove(race);
	}

	@Override
	public int getNumberOfStages(int raceId) throws IDNotRecognisedException {
		Race race = getRaceById(raceId);
		return race.getStages().size();

	}

	@Override
	public int addStageToRace(int raceId, String stageName, String description, double length, LocalDateTime startTime,
			StageType type)
			throws IDNotRecognisedException, IllegalNameException, InvalidNameException, InvalidLengthException {
		isInvalidName(stageName);
		Race race = getRaceById(raceId);
		for(Stage stage:race.getStages()){
			if(stage.getName().equals(stageName)){
				throw new IllegalNameException("Name in use");
			}
		}
		if(length < 5 ){
			throw new InvalidLengthException("Stage too short");
		}
		Stage newStage = new Stage(stageName, description, length, startTime, type, nextStageId++);
		race.addStage(newStage); 
		
		return newStage.getStageId();
	}

	@Override
	public int[] getRaceStages(int raceId) throws IDNotRecognisedException {
		int[] stageIds = new int[getNumberOfStages(raceId)];
		Race race = getRaceById(raceId);
		for(int i = 0; i < getNumberOfStages(raceId); i++){
			stageIds[i] = race.getStages().get(i).getStageId();
		}
		return stageIds;
	}

	@Override
	public double getStageLength(int stageId) throws IDNotRecognisedException {
		return getStageById(stageId).getLength();
	}

	@Override
	public void removeStageById(int stageId) throws IDNotRecognisedException {
		Stage stage = getStageById(stageId);
		int raceId = getRaceIdByStageId(stageId);
		getRaceById(raceId).removeStage(stage);
	}

	@Override
	public int addCategorizedClimbToStage(int stageId, Double location, SegmentType type, Double averageGradient,
			Double length) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException,
			InvalidStageTypeException {
		Stage stage = getStageById(stageId);
		checkValidCreateSegment(stageId, location);
		if(!isClimb(type)) {
			throw new InvalidStageTypeException("Cannot create climb with type SPRINT");
		}
		Climb newClimb = new Climb(location, type, averageGradient, length, nextSegmentId++);
		stage.addSegment(newClimb);
		return newClimb.getSegmentId();
	}

	@Override
	public int addIntermediateSprintToStage(int stageId, double location) throws IDNotRecognisedException,
			InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {
		Stage stage = getStageById(stageId);
		checkValidCreateSegment(stageId, location);
		IntermediateSprint newSprint = new IntermediateSprint(location, nextSegmentId++);
		stage.addSegment(newSprint);
		return newSprint.getSegmentId();
	}

	@Override
	public void removeSegment(int segmentId) throws IDNotRecognisedException, InvalidStageStateException {
		int stageId = getStageIdBySegmentId(segmentId);
		checkValidStageState(false, stageId, "stage is already finished");
		Stage stage = getStageById(stageId);
		stage.removeSegmentById(segmentId);
	}

	@Override
	public void concludeStagePreparation(int stageId) throws IDNotRecognisedException, InvalidStageStateException {
		Stage stage = getStageById(stageId);
		checkValidStageState(false, stageId, "msg");
		stage.setFullyCreated(true);
	}

	@Override
	public int[] getStageSegments(int stageId) throws IDNotRecognisedException {
		ArrayList<Segment> segments = getStageById(stageId).getSegments();
		int numSegments = segments.size();
		int[] segmentIds = new int[numSegments];
		for(int i = 0; i < numSegments; i++) {
			segmentIds[i] = segments.get(i).getSegmentId();
		}
		return segmentIds;
	}

	@Override
	public int createTeam(String name, String description) throws IllegalNameException, InvalidNameException {
		for(Team team : teams) {
			if(team.getTeamName().equals(name)) {
				throw new IllegalNameException("name in use");
			}
		}
		isInvalidName(name);

		Team newTeam = new Team(name, description, nextTeamId++);
		teams.add(newTeam);
		return newTeam.getId();
	}

	@Override
	public void removeTeam(int teamId) throws IDNotRecognisedException {
		teams.remove(getTeamById(teamId));	
	}

	@Override
	public int[] getTeams() {
		int[] teamIds = new int[teams.size()];
		for(int i = 0; i < teams.size(); i++){
			teamIds[i] = teams.get(i).getId();
		}
		return teamIds;
	}

	@Override
	public int[] getTeamRiders(int teamId) throws IDNotRecognisedException {
		Team team = getTeamById(teamId);
		int[] riderIds = new int[team.getTeamMembers().size()];
		for(int i = 0; i < team.getTeamMembers().size(); i++){
			riderIds[i] = team.getTeamMembers().get(i).getRiderId();
		}
		return riderIds;
	}

	@Override
	public int createRider(int teamID, String name, int yearOfBirth)
			throws IDNotRecognisedException, IllegalArgumentException {
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		if(yearOfBirth > year){
			throw new IllegalArgumentException("That person is not yet born");
		} else if(yearOfBirth < 1900){
			throw new IllegalArgumentException("That person is probably dead now");
		}
		
		Team team = getTeamById(teamID);
		Rider rider = team.createRider(name, yearOfBirth, nextRiderId++);
		return rider.getRiderId();
	}

	@Override
	public void removeRider(int riderId) throws IDNotRecognisedException {
		Team ridersTeam = getTeamByRiderId(riderId);
		ridersTeam.removeRider(riderId);
	}

	@Override
	public void registerRiderResultsInStage(int stageId, int riderId, LocalTime... checkpoints)
			throws IDNotRecognisedException, DuplicatedResultException, InvalidCheckpointsException,
			InvalidStageStateException {
		Stage stage = getStageById(stageId);
		if (stage.getSegments().size()+2 != checkpoints.length) {
			throw new InvalidCheckpointsException("invalid checkpoints");
		}
		if (!stage.isFullyCreated()) {
			throw new InvalidStageStateException("can't add reults to unfinished race");
		}
		if (stageResults.containsKey(stageId)) {
			ArrayList<Result> stageResult = stageResults.get(stageId);
			for (Result result : stageResult) {
				if (riderId == result.getRiderId()) {
					throw new DuplicatedResultException("rider result already exists for this stage");
				}
			}
			stageResult.add(new Result(riderId, checkpoints));
		} else {
			ArrayList<Result> newStageResults = new ArrayList<Result>();
			newStageResults.add(new Result(riderId, checkpoints));
			stageResults.put(stageId, newStageResults);
		}
	}

	@Override
	public LocalTime[] getRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		if (stageResults.containsKey(stageId)) {
			ArrayList<Result> stageResult = stageResults.get(stageId);
			for (Result result : stageResult) {
				if (result.getRiderId() == riderId) {
					return result.getRiderTimes();
				}
			}
			throw new IDNotRecognisedException("no result in stage for rider");
		} else {
			throw new IDNotRecognisedException("stageID has no results");
		}
	}

	@Override
	public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId) throws IDNotRecognisedException {
		return null;
	}

	@Override
	public void deleteRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public int[] getRidersRankInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalTime[] getRankedAdjustedElapsedTimesInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersPointsInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersMountainPointsInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eraseCyclingPortal() {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveCyclingPortal(String filename) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadCyclingPortal(String filename) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeRaceByName(String name) throws NameNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public LocalTime[] getGeneralClassificationTimesInRace(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersPointsInRace(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersMountainPointsInRace(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersGeneralClassificationRank(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersPointClassificationRank(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersMountainPointClassificationRank(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

}
