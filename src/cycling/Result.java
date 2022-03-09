package cycling;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Result {
    private int riderId;
    private LocalTime[] riderTimes;

    public Result(int riderId, LocalTime[] times) {
        setRiderId(riderId);
        setRiderTimes(times);
    }
    
    public LocalTime[] getRiderTimes() {
        return riderTimes;
    }

    public void setRiderTimes(LocalTime[] riderTimes) {
        this.riderTimes = riderTimes;
    }

    public int getRiderId() {
        return riderId;
    }

    public void setRiderId(int riderId) {
        this.riderId = riderId;
    }

    // Total time comparator
    public static Comparator<Result> compareByTime = new Comparator<Result>() {

        @Override
        public int compare(Result result1, Result result2) {
            return Long.compare(Duration.between(result1.getRiderTimes()[0], result1.getRiderTimes()[result1.getRiderTimes().length-1]).toMillis(), Duration.between(result2.getRiderTimes()[0], result2.getRiderTimes()[result2.getRiderTimes().length-1]).toMillis());
        }
        
    };
}
