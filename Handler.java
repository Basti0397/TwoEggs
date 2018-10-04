package twoEggs;

import java.sql.Timestamp;

public class Handler implements Runnable{
	
	private final Gauss gauss;
	private final WithoutGauss wG;
	
	public Handler(WithoutGauss wG) {
		this.gauss = null;
		this.wG = wG;
	}
	
	public Handler(Gauss gauss) {
		this.gauss = gauss;
		this.wG = null;
	}

	@Override
	public void run() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println(timestamp);
		if(gauss!=null) {
			gauss.calculate();
		}else {
			wG.calculate();
		}
		timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println(timestamp);
	}
	
	

}
