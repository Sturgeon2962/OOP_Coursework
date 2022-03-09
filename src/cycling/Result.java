package cycling;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Result {
    private int riderId;
    private LocalTime[] riderTimes;
    
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
}
