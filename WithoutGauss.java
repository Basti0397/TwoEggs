package twoEggs;

public class WithoutGauss {
	private int floors;
	
	public WithoutGauss(int floors) {
		this.floors = floors;
	}
	
	protected void calculate() {
		//Without Gauss: n+(n-1)+(n-2)+(n-3)+(n-4)+...+1 = floors
		
		int maxSteps=1;
		int cnt=1;
		int sum=0;
		
		while(sum<floors) {
			cnt++;
			maxSteps=cnt;
			sum=0;
			while(maxSteps>=1) {
				sum += maxSteps;
				maxSteps--;
			}
		}
		maxSteps = cnt;
		
		System.out.printf("Ergebniss (ohne Gauss berechnet) ist: %d\n", maxSteps);
		
	}

}
