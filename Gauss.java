package twoEggs;

import java.math.BigDecimal;

public class Gauss {
	private BigDecimal floors;
	private static final BigDecimal TWO = new BigDecimal(2);;
	
	public Gauss(BigDecimal floors) {
		this.floors = floors;
	}
	
	protected void calculate() {
		/*
		 * Gauss: n(n+1)/2=floors
		 * => n²+n-2floors = 0
		 */
				 
		BigDecimal a = new BigDecimal(1);
		BigDecimal b = new BigDecimal(1);
		BigDecimal c = floors.multiply(new BigDecimal(-2) );		
		BigDecimal maxSteps;
		
		maxSteps = (b.negate().add(sqrt(b.multiply(b).subtract(new BigDecimal(4).multiply(a).multiply(c) ), 100) ) ).divide(new BigDecimal(2).multiply(a), 0,  BigDecimal.ROUND_DOWN);
		
//		if( maxSteps.subtract(maxSteps.toBigInteger() ) >= new BigDecimal(0.5) ) {
//			maxSteps=(int)maxSteps+1;
//		}else {
//			maxSteps=(int)maxSteps;
//		}
		
		maxSteps.toBigInteger();
		
		System.out.printf("Ergebniss (mit Gauss berechnet) ist: %s\n", maxSteps.toString() );
		
	}
	
	public static BigDecimal sqrt(BigDecimal A, final int SCALE) {
	    BigDecimal x0 = new BigDecimal("0");
	    BigDecimal x1 = new BigDecimal(Math.sqrt(A.doubleValue()));
	    while (!x0.equals(x1)) {
	        x0 = x1;
	        x1 = A.divide(x0, SCALE, BigDecimal.ROUND_HALF_UP);
	        x1 = x1.add(x0);
	        x1 = x1.divide(TWO, SCALE, BigDecimal.ROUND_HALF_UP);

	    }
	    return x1;
	}

}
