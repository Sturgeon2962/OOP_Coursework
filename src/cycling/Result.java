package cycling;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Result {
    private int stageId;
    private int riderId;
    private LocalTime[] riderTimes;

    public Result(int stageId) {
        setStageId(stageId);
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

    public int getStageId() {
        return stageId;
    }
    
    public void setStageId(int stageId) {
        this.stageId = stageId;
    }
}
