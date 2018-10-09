package twoEggs;

import java.math.BigDecimal;

public class WithoutGauss {
	private BigDecimal floors;
	private BigDecimal one = new BigDecimal(1);
	private BigDecimal nMaxBD = new BigDecimal(0);
	
	public WithoutGauss(BigDecimal floors) {
		this.floors = floors;
	}
	
	protected BigDecimal calculate() {
	    
	    BigDecimal zaehler = floors.subtract(one);
        BigDecimal sum = new BigDecimal(0);
        sum=sum.setScale(2);
        BigDecimal ip1;
        
        for (BigDecimal i=new BigDecimal(1);i.compareTo(floors)<=0;i=i.add(one)) {
            zaehler = zaehler.add(i);
            ip1=i.add(one);
            sum=zaehler.divide(ip1,BigDecimal.ROUND_UP);
            if (i.compareTo(sum)>=0) {
                nMaxBD=i;
                break;
            }    
        }
        
        return nMaxBD;
	    
	    
//		//Without Gauss: n+(n-1)+(n-2)+(n-3)+(n-4)+...+1 = floors
//		
//		BigDecimal maxSteps = new BigDecimal("1");
//		BigDecimal cnt = new BigDecimal("1");
//		BigDecimal sum = new BigDecimal("0");
//		
//		//As long the sum is below the floor number
//		while(sum.compareTo(floors) == -1) {
//			cnt = cnt.add( new BigDecimal("1") );
//			maxSteps=cnt;
//			sum = new BigDecimal("0");
//			//As long the maxSteps is above or equal 1
//			while(maxSteps.compareTo( new BigDecimal("1") ) >= 0 ) {
//				sum = sum.add(maxSteps);
//				maxSteps = maxSteps.subtract( new BigDecimal("1") );
//			}
//		} 
//		maxSteps = cnt;
//		
//		return maxSteps;
		
	}
	
	

}
