package cycling;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Comparator;

/**
 * A class to represent the results of a stage
 * 
 * @author
 * @version 1.0
 */
public class Result implements Serializable {
    private int riderId;
    private LocalTime[] riderTimes;

    /**
     * Constructor to create a new result
     * 
     * @param riderId The ID of the rider
     * @param times The race times of the rider
     */
    public Result(int riderId, LocalTime[] times) {
        setRiderId(riderId);
        setRiderTimes(times);
    }
    
    /**
     * Getter for the race times of the rider
     * 
     * @return Returns a LocalTime array containing the race times of the rider
     */
    public LocalTime[] getRiderTimes() {
        return riderTimes;
    }

    /**
     * Setter for the race times of a rider
     * 
     * @param riderTimes The race times of the rider
     */
    public void setRiderTimes(LocalTime[] riderTimes) {
        this.riderTimes = riderTimes;
    }

    /**
     * Getter for the rider ID
     * 
     * @return An int of the ID of the rider
     */
    public int getRiderId() {
        return riderId;
    }

    /**
     * Setter for the rider ID
     * 
     * @param riderId The ID of the rider
     */
    public void setRiderId(int riderId) {
        this.riderId = riderId;
    }

    // Total time comparator
    /**
     * A comparator, allowing results to be sorted by their overall stage time
     */
    public static Comparator<Result> compareByTime = new Comparator<Result>() {

        @Override
        public int compare(Result result1, Result result2) {
            return Long.compare(Duration.between(result1.getRiderTimes()[0], result1.getRiderTimes()[result1.getRiderTimes().length-1]).toMillis(), Duration.between(result2.getRiderTimes()[0], result2.getRiderTimes()[result2.getRiderTimes().length-1]).toMillis());
        }
        
    };
}
