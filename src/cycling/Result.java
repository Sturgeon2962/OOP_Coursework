package cycling;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Result {
    private int stageId;
    private HashMap<Integer, LocalTime[]> riderTimes = new HashMap<Integer, LocalTime[]>();

    public Result(int stageId) {
        setStageId(stageId);
    }
    
    public int getStageId() {
        return stageId;
    }

    public HashMap<Integer, LocalTime[]> getRiderTimes() {
        return riderTimes;
    }
    
    public void setStageId(int stageId) {
        this.stageId = stageId;
    }
}
