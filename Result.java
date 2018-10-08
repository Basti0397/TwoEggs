package twoEggs;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Result {
	
	private Timestamp startTime;
	private Timestamp endTime;
	private BigDecimal result;
	private long timeDiff;
	private long timeStartNano;
	private long timeEndNano;
	
	public long getTimeStartNano() {
        return timeStartNano;
    }

    public void setTimeStartNano(long timeStart) {
        this.timeStartNano = timeStart;
    }

    public long getTimeEndNano() {
        return timeEndNano;
    }

    public void setTimeEndNano(long timeEnd) {
        this.timeEndNano = timeEnd;
        this.setTimeDiff();
    }

    public Result() {
		startTime = null;
		endTime = null; 
		result = null;
		timeDiff = 0;
	}
	
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
		
	}
	
	public void setResult(BigDecimal result) {
		this.result = result;
	}
	
	public void setTimeDiff() {
		timeDiff = timeEndNano - timeStartNano;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public BigDecimal getResult() {
		return result;
	}

	public long getTimeDiff() {
		return timeDiff;
	}
	
	

}
