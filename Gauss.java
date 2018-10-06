package twoEggs;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Gauss {
	private BigDecimal floors;
	private static final BigDecimal TWO = new BigDecimal(2);;
	
	public Gauss(BigDecimal floors) {
		this.floors = floors;
	}
	
	protected BigDecimal calculate() {
		/*
		 * Gauss: n(n+1)/2=floors
		 * => n²+n-2floors = 0
		 */
				 
		BigDecimal a = new BigDecimal(1);
		BigDecimal b = new BigDecimal(1);
		BigDecimal c = floors.multiply(new BigDecimal(-2) );		
		BigDecimal maxSteps;
		BigDecimal numerator; 
		BigDecimal denominator;
		
		numerator = b.negate().add(sqrt(b.multiply(b).subtract(new BigDecimal(4).multiply(a).multiply(c) ), 10) );
		denominator = new BigDecimal(2).multiply(a);
		
		maxSteps = numerator.divide(denominator, 0, BigDecimal.ROUND_HALF_UP);
		
		
		maxSteps.toBigInteger();
		
		return maxSteps;
	}
	
	private static BigDecimal sqrt(BigDecimal x, int scale)
    {
        // Check that x >= 0.
        if (x.signum() < 0) {
            throw new IllegalArgumentException("x < 0");
        }
 
        // n = x*(10^(2*scale))
        BigInteger n = x.movePointRight(scale << 1).toBigInteger();
 
        // The first approximation is the upper half of n.
        int bits = (n.bitLength() + 1) >> 1;
        BigInteger ix = n.shiftRight(bits);
        BigInteger ixPrev;
 
        // Loop until the approximations converge
        // (two successive approximations are equal after rounding).
        do {
            ixPrev = ix;
 
            // x = (x + n/x)/2
            ix = ix.add(n.divide(ix)).shiftRight(1);
 
            Thread.yield();
        } while (ix.compareTo(ixPrev) != 0);
 
        return new BigDecimal(ix, scale);
    }

}
