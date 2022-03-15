package cycling;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
		//Remove to Results
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
			Collections.sort(stageResult, Result.compareByTime);
		} else {
			ArrayList<Result> newStageResults = new ArrayList<Result>();
			newStageResults.add(new Result(riderId, checkpoints));
			stageResults.put(stageId, newStageResults);
		}
	}

	@Override
	public LocalTime[] getRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		getRiderById(riderId);
		getStageById(stageId);
		if (stageResults.containsKey(stageId)) {
			ArrayList<Result> stageResult = stageResults.get(stageId);
			for (Result result : stageResult) {
				if (result.getRiderId() == riderId) {
					return result.getRiderTimes();
				}
			}
		}
		LocalTime[] blank = {};
		return blank;
	}

	@Override
	public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId) throws IDNotRecognisedException {
		getRiderById(riderId);
		Stage stage = getStageById(stageId);
		Boolean adjust = true;
		if (stage.getCategory() == StageType.TT) {
			adjust = false;
		}
		int index = -1;
		if (stageResults.containsKey(stageId)) {
			ArrayList<Result> stageResult = stageResults.get(stageId);
			for (Result result : stageResult) {
				if (result.getRiderId() == riderId) {
					if (!adjust) {
						LocalTime timeToReturn = LocalTime.parse("00:00:00");
						Duration time = Duration.between(result.getRiderTimes()[0], result.getRiderTimes()[result.getRiderTimes().length-1]);
						timeToReturn = (LocalTime) time.addTo(timeToReturn);
						return timeToReturn;
					}
					index = stageResult.indexOf(result);
					break;
				}
			}
			if (index < 0) {
				return null;
			} else {
				ArrayList<LocalTime> riderTimes = new ArrayList<LocalTime>();
				for (Result result : stageResult) {
					LocalTime timeToReturn = LocalTime.parse("00:00:00");
					Duration time = Duration.between(result.getRiderTimes()[0], result.getRiderTimes()[result.getRiderTimes().length-1]);
					timeToReturn = (LocalTime) time.addTo(timeToReturn);
					riderTimes.add(timeToReturn);
				}
				while ((index > 0)&&(Duration.between(riderTimes.get(index-1), riderTimes.get(index)).toMillis()<1000)) {
					index--;
				}
				return riderTimes.get(index);
			}
		}
		return null;
	}

	@Override
	public void deleteRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		getRiderById(riderId);
		getStageById(stageId);
		if (stageResults.containsKey(stageId)) {
			ArrayList<Result> stageResult = stageResults.get(stageId);
			Result toRemove = null;
			for (Result result : stageResult) {
				if (result.getRiderId() == riderId) {
					toRemove = result;
					break;
				}
			}
			stageResult.remove(toRemove);
		}
	}

	@Override
	public int[] getRidersRankInStage(int stageId) throws IDNotRecognisedException {
		getStageById(stageId);
		if (stageResults.containsKey(stageId)) {
			ArrayList<Result> stageResult = stageResults.get(stageId);
			int[] riderRanks = new int[stageResult.size()];
			for (int i = 0; i < stageResult.size(); i++) {
				riderRanks[i] = stageResult.get(i).getRiderId();
			}
			return riderRanks;
		}
		return new int[0];
	}

	@Override
	public LocalTime[] getRankedAdjustedElapsedTimesInStage(int stageId) throws IDNotRecognisedException {
		getStageById(stageId);
		if (stageResults.containsKey(stageId)) {
			ArrayList<Result> stageResult = stageResults.get(stageId);
			LocalTime[] riderTimes = new LocalTime[stageResult.size()];
			if (riderTimes.length <= 1) {
				for (int i = 0; i < riderTimes.length; i++) {
					LocalTime timeToReturn = LocalTime.parse("00:00:00");
					riderTimes = stageResult.get(i).getRiderTimes();
					Duration totalTime = Duration.between(riderTimes[0], riderTimes[riderTimes.length-1]);
					riderTimes[i] = (LocalTime) totalTime.addTo(timeToReturn);
				}
				return riderTimes;
			} else {
				ArrayList<LocalTime> originalRiderTimes = new ArrayList<LocalTime>();
				for (Result result : stageResult) {
					LocalTime timeToReturn = LocalTime.parse("00:00:00");
					Duration time = Duration.between(result.getRiderTimes()[0], result.getRiderTimes()[result.getRiderTimes().length-1]);
					timeToReturn = (LocalTime) time.addTo(timeToReturn);
					originalRiderTimes.add(timeToReturn);
				}
				for (int i = riderTimes.length-1; i > 0; i--) {
					int index = i;
					while ((index > 0)&&(Duration.between(originalRiderTimes.get(index-1), originalRiderTimes.get(index)).toMillis()<1000)) {
						index--;
					}
					riderTimes[i] = originalRiderTimes.get(index);
				}
				riderTimes[0] = originalRiderTimes.get(0);
				return riderTimes;
			}
		}
		LocalTime[] blank = {};
		return blank;
	}

	@Override
	public int[] getRidersPointsInStage(int stageId) throws IDNotRecognisedException {
		Stage stage = getStageById(stageId);
		if (stageResults.containsKey(stageId)) {
			ArrayList<Result> stageResult = stageResults.get(stageId);
			int[] riderPoints = new int[stageResult.size()];
			int[] stagePoints;
			switch (stage.getCategory()) {
				case FLAT:
					stagePoints = Stage.getSprintFlat();
					break;
				case MEDIUM_MOUNTAIN:
					stagePoints = Stage.getSprintHilly();
					break;
				case HIGH_MOUNTAIN:
					stagePoints = Stage.getSprintHigh();
					break;
				case TT:
					stagePoints = Stage.getSprintTT();
					break;
				default:
					stagePoints = new int[0];
			}
			for (int i = 0; i < riderPoints.length; i++) {
				if (i >= stagePoints.length) {
					riderPoints[i] = 0;
				} else {
					riderPoints[i] = stagePoints[i];
				}
			}
			// check for intermediate sprint
			for (int i = 0; i < stage.getSegments().size(); i++) {
				if (stage.getSegments().get(i).getType() == SegmentType.SPRINT) {
					HashMap<Integer, LocalTime> sprintTimes = new HashMap<Integer, LocalTime>();
					for (int j = 0; j < stageResult.size(); j++) {
						sprintTimes.put(stageResult.get(j).getRiderId(), stageResult.get(j).getRiderTimes()[i+1]);
					}
					// Order sprint times
					ArrayList<Integer> orderedRiders = new ArrayList<Integer>();
					sprintTimes.entrySet().stream().sorted(
						(t1, t2) -> t1.getValue().compareTo(t2.getValue())
					).forEach(t -> orderedRiders.add(t.getKey()));
					// Add intermediate sprint points to riders
					for (int k = 0; k < orderedRiders.size(); k++) {
						for (Result result : stageResult) {
							if (result.getRiderId() == orderedRiders.get(k)) {
								try {
									riderPoints[stageResult.indexOf(result)] += Stage.getSprintIntermediate()[k];
								} catch (Exception e) {
									break;
								}
							}
						}
					}
				}
			}
			return riderPoints;
		}
		int[] emptyArray = new int[0];
		return emptyArray;
	}

	@Override
	public int[] getRidersMountainPointsInStage(int stageId) throws IDNotRecognisedException {
		Stage stage = getStageById(stageId);
		if (stageResults.containsKey(stageId)) {
			ArrayList<Result> stageResult = stageResults.get(stageId);
			int[] riderPoints = new int[stageResult.size()];
			// Set default points to 0
			Arrays.fill(riderPoints, 0);
			for (int i = 0; i < stage.getSegments().size(); i++) {
				if (stage.getSegments().get(i).getType() != SegmentType.SPRINT) {
					HashMap<Integer, LocalTime> climbTimes = new HashMap<Integer, LocalTime>();
					for (int j = 0; j < stageResult.size(); j++) {
						climbTimes.put(stageResult.get(j).getRiderId(), stageResult.get(j).getRiderTimes()[i+1]);
					}
					// Order climb times
					ArrayList<Integer> orderedRiders = new ArrayList<Integer>();
					climbTimes.entrySet().stream().sorted(
						(t1, t2) -> t1.getValue().compareTo(t2.getValue())
					).forEach(t -> orderedRiders.add(t.getKey()));
					// Add points
					for (int k = 0; k < orderedRiders.size(); k++) {
						for (Result result : stageResult) {
							if (result.getRiderId() == orderedRiders.get(k)) {
								switch (stage.getSegments().get(i).getType()) {
									case C1:
										try {
											riderPoints[stageResult.indexOf(result)] += Stage.getMountainC1()[k];
										} catch (Exception e) {
											break;
										}
										break;
									case C2:
										try {
											riderPoints[stageResult.indexOf(result)] += Stage.getMountainC2()[k];
										} catch (Exception e) {
											break;
										}
										break;
									case C3:
										try {
											riderPoints[stageResult.indexOf(result)] += Stage.getMountainC3()[k];
										} catch (Exception e) {
											break;
										}
										break;
									case C4:
										try {
											riderPoints[stageResult.indexOf(result)] += Stage.getMountainC4()[k];
										} catch (Exception e) {
											break;
										}
										break;
									case HC:
										try {
											riderPoints[stageResult.indexOf(result)] += Stage.getMountainHC()[k];
										} catch (Exception e) {
											break;
										}
										break;
									default:
										break;
								}
							}
						}
					}
				}
			}
			return riderPoints;
		}
		int[] emptyArray = new int[0];
		return emptyArray;
	}

	@Override
	public void eraseCyclingPortal() {
		nextSegmentId = 1;
		nextStageId = 1;
		nextRiderId = 1;
		nextTeamId = 1;
		nextRaceId = 1;

		races.clear();
		teams.clear();
		stageResults.clear();
	}

	@Override
	public void saveCyclingPortal(String filename) throws IOException {
		ObjectOutputStream ob = new ObjectOutputStream(new FileOutputStream(filename));
		ob.writeObject(nextSegmentId);
		ob.writeObject(nextStageId);
		ob.writeObject(nextRiderId);
		ob.writeObject(nextTeamId);
		ob.writeObject(nextRaceId);
		ob.writeObject(races);
		ob.writeObject(teams);
		ob.writeObject(stageResults);
		ob.flush();
		ob.close();
	}

	@Override
	public void loadCyclingPortal(String filename) throws IOException, ClassNotFoundException {
		ObjectInputStream ob = new ObjectInputStream(new FileInputStream(filename));
		Object newObject;
		newObject = ob.readObject();
		if (newObject instanceof Integer) {
			nextSegmentId = (Integer) newObject;
		}
		newObject = ob.readObject();
		if (newObject instanceof Integer) {
			nextStageId = (Integer) newObject;
		}
		newObject = ob.readObject();
		if (newObject instanceof Integer) {
			nextRiderId = (Integer) newObject;
		}
		newObject = ob.readObject();
		if (newObject instanceof Integer) {
			nextTeamId = (Integer) newObject;
		}
		newObject = ob.readObject();
		if (newObject instanceof Integer) {
			nextRaceId = (Integer) newObject;
		}
		newObject = ob.readObject();
		if (newObject instanceof ArrayList) {
			races = (ArrayList<Race>) newObject;
		}
		newObject = ob.readObject();
		if (newObject instanceof ArrayList) {
			teams = (ArrayList<Team>) newObject;
		}
		newObject = ob.readObject();
		if (newObject instanceof HashMap) {
			stageResults = (HashMap<Integer, ArrayList<Result>>) newObject;
		}
		ob.close();
	}

	@Override
	public void removeRaceByName(String name) throws NameNotRecognisedException {
		int[] stageids;
		for (Race race: races){
			if (race.getName().equals(name)){
				stageids = new int[race.getStages().size()]; 
				for (int i=0;i<stageids.length;i++){
					stageids[i] = race.getStages().get(i).getStageId();
				}
				races.remove(race);		
				for(int stage:stageids){
					stageResults.remove(stage);
				}
				return;
			}
		}
		throw new NameNotRecognisedException("Race does not eixst so cannot be removes");
	}

	@Override
	public LocalTime[] getGeneralClassificationTimesInRace(int raceId) throws IDNotRecognisedException {
		Race race = getRaceById(raceId);
		HashMap<Integer,LocalTime> totalTime = new HashMap<Integer, LocalTime>();

		for (Stage stage : race.getStages()){
			int[] riders = getRidersRankInStage(stage.getStageId());
			LocalTime[] ridertimes = getRankedAdjustedElapsedTimesInStage(stage.getStageId());
			for(int i = 0; i< riders.length; i++){
				if(totalTime.containsKey(riders[i])){
					Duration x = Duration.between(LocalTime.parse("00:00:00"), ridertimes[i]);
					totalTime.put(riders[i], (LocalTime) x.addTo(totalTime.get(riders[i])));
				}else{
					totalTime.put(riders[i], ridertimes[i]);
				}

			}
		}
		ArrayList<LocalTime> timeToReturn = new ArrayList<LocalTime>();
		for(LocalTime i : totalTime.values()){
			timeToReturn.add(i);
		}
		
		Collections.sort(timeToReturn);
		LocalTime[] times = new LocalTime[timeToReturn.size()];
		for(int i = 0; i < timeToReturn.size(); i++){
			times[i] = timeToReturn.get(i);
		} 
		return times;
	}

	@Override
	public int[] getRidersPointsInRace(int raceId) throws IDNotRecognisedException {
		Race race = getRaceById(raceId);
		HashMap<Integer, Integer> overallRiderPoints = new HashMap<Integer, Integer>();
		for (Stage stage : race.getStages()) {
			int[] riderRank = getRidersRankInStage(stage.getStageId());
			int[] riderPoints = getRidersPointsInStage(stage.getStageId());
			for (int i = 0; i < riderRank.length; i++) {
				if (overallRiderPoints.containsKey(riderRank[i])) {
					overallRiderPoints.put(riderRank[i], overallRiderPoints.get(riderRank[i]) + riderPoints[i]);
				} else {
					overallRiderPoints.put(riderRank[i], riderPoints[i]);
				}
			}
		}
		int[] overallRiderRank = getRidersGeneralClassificationRank(raceId);
		int[] riderPoints = new int[overallRiderRank.length];
		for (int i = 0; i < overallRiderRank.length; i++) {
			riderPoints[i] = overallRiderPoints.get(overallRiderRank[i]);
		}
		return riderPoints;
	}

	@Override
	public int[] getRidersMountainPointsInRace(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersGeneralClassificationRank(int raceId) throws IDNotRecognisedException {
		Race race = getRaceById(raceId);
		HashMap<Integer,LocalTime> totalTime = new HashMap<Integer, LocalTime>();

		for (Stage stage : race.getStages()){
			int[] riders = getRidersRankInStage(stage.getStageId());
			LocalTime[] ridertimes = getRankedAdjustedElapsedTimesInStage(stage.getStageId());
			for(int i = 0; i< riders.length; i++){
				if(totalTime.containsKey(riders[i])){
					Duration x = Duration.between(LocalTime.parse("00:00:00"), ridertimes[i]);
					totalTime.put(riders[i], (LocalTime) x.addTo(totalTime.get(riders[i])));
				}else{
					totalTime.put(riders[i], ridertimes[i]);
				}

			}
		}

		ArrayList<Integer> riderOverallRank = new ArrayList<Integer>();
		totalTime.entrySet().stream().sorted((t1, t2) -> t1.getValue().compareTo(t2.getValue())).forEach(t -> riderOverallRank.add(t.getKey()));
		int[] riderRanks = new int[riderOverallRank.size()];
		for (int i = 0; i < riderOverallRank.size(); i++) {
			riderRanks[i] = riderOverallRank.get(i);
		}
		return riderRanks;
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

