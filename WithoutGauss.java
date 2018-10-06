package twoEggs;

import java.math.BigDecimal;

public class WithoutGauss {
	private BigDecimal floors;
	
	public WithoutGauss(BigDecimal floors) {
		this.floors = floors;
	}
	
	protected BigDecimal calculate() {
		//Without Gauss: n+(n-1)+(n-2)+(n-3)+(n-4)+...+1 = floors
		
		BigDecimal maxSteps = new BigDecimal("1");
		BigDecimal cnt = new BigDecimal("1");
		BigDecimal sum = new BigDecimal("0");
		
		//As long the sum is below the floor number
		while(sum.compareTo(floors) == -1) {
			cnt = cnt.add( new BigDecimal("1") );
			maxSteps=cnt;
			sum = new BigDecimal("0");
			//As long the maxSteps is above or equal 1
			while(maxSteps.compareTo( new BigDecimal("1") ) >= 0 ) {
				sum = sum.add(maxSteps);
				maxSteps = maxSteps.subtract( new BigDecimal("1") );
			}
		} 
		maxSteps = cnt;
		
		return maxSteps;
		
	}

}
