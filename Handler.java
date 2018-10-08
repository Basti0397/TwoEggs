package twoEggs;

import java.sql.Timestamp;
import java.util.concurrent.Callable;

public class Handler implements Callable<Result>{
	
	private final Gauss gauss;
	private final WithoutGauss wG;
	private final Result handlerRes;
	
	public Handler(WithoutGauss wG) {
		this.gauss = null;
		this.wG = wG;
		handlerRes = new Result();
	}
	
	public Handler(Gauss gauss) {
		this.gauss = gauss;
		this.wG = null;
		handlerRes = new Result();
	}

	@Override
	public Result call() {
		
	    handlerRes.setStartTime(new Timestamp(System.currentTimeMillis()));
		handlerRes.setTimeStartNano(System.nanoTime());
		
		if(gauss!=null) {
			handlerRes.setResult(gauss.calculate() );
		}else {
			handlerRes.setResult( wG.calculate() );
		}
		
		handlerRes.setEndTime(new Timestamp(System.currentTimeMillis()));
        handlerRes.setTimeEndNano(System.nanoTime());
		
		return handlerRes;
	}
	
	

}
