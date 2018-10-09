package functions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Math;
import java.math.BigDecimal;

public class MainFrame {
	
	public static void main(String... unused) throws IOException {
		
		int[] x = {1, 10, 25, 50, 100, 500, 1000};
		int cnt=0;
		File[] floors = new File[6];
		PrintWriter fileWriter;
		
		floors[0] = new File("C:\\Users\\Basti-PC\\Documents\\eclipse_workspace\\TwoEggs\\data\\Input\\x.txt");
		floors[1] = new File("C:\\Users\\Basti-PC\\Documents\\eclipse_workspace\\TwoEggs\\data\\Input\\lg(x).txt");
		floors[2] = new File("C:\\Users\\Basti-PC\\Documents\\eclipse_workspace\\TwoEggs\\data\\Input\\x_quadrat.txt");
		floors[3] = new File("C:\\Users\\Basti-PC\\Documents\\eclipse_workspace\\TwoEggs\\data\\Input\\xlog10(x).txt");
		floors[4] = new File("C:\\Users\\Basti-PC\\Documents\\eclipse_workspace\\TwoEggs\\data\\Input\\x_kubik.txt");
		floors[5] = new File("C:\\Users\\Basti-PC\\Documents\\eclipse_workspace\\TwoEggs\\data\\Input\\x!.txt");
		
		//x
		fileWriter = new PrintWriter( new FileWriter(floors[0]) );
		
		for(int number: x) {
			fileWriter.printf("%d%n", number);
		}
		fileWriter.close();
		
		//lg(x)
		fileWriter = new PrintWriter( new FileWriter(floors[1]) );
		
		while(cnt<=x.length-1) {
			double res = Math.log10(x[cnt] );
			long resRounded = 0;
			
			if( (res - (long)res) != 0 ) {
				resRounded = (long)res + 1; 
			}else {
				resRounded = (long)res;
			}
			
			fileWriter.printf("%d%n", resRounded );
			cnt++;
		}
		cnt=0;
		fileWriter.close();
		
		//x²
		fileWriter = new PrintWriter( new FileWriter(floors[2]) );
		
		while(cnt<=x.length-1) {
			fileWriter.printf("%d%n", x[cnt]*x[cnt] );
			cnt++;
		}
		cnt=0;
		fileWriter.close();
		
		//xlog10[x]
		fileWriter = new PrintWriter( new FileWriter(floors[3]) );
		
		while(cnt<=x.length-1) {
			double res = x[cnt] * Math.log10(x[cnt] );
			long resRounded = 0;
			
			if( (res - (long)res) != 0 ) {
				resRounded = (long)res + 1; 
			}else {
				resRounded = (long)res;
			}
			
			fileWriter.printf("%d%n", resRounded );
			cnt++;
		}
		cnt=0;
		fileWriter.close();
		
		//x³
		fileWriter = new PrintWriter( new FileWriter(floors[4]) );
		
		while(cnt<=x.length-1) {
			fileWriter.printf("%d%n", x[cnt]*x[cnt]*x[cnt] );
			cnt++;
		}
		cnt=0;
		fileWriter.close();
		
		//x!
		fileWriter = new PrintWriter( new FileWriter(floors[5]) );
		
		while(cnt<=x.length-1) {
			fileWriter.printf("%s%n%n", fak(x[cnt]).toString() );
			cnt++;
		}
		cnt=0;
		fileWriter.close();
	}
	
	public static BigDecimal fak(int x) {
		
		BigDecimal res = new BigDecimal(1);
		
		while(x>1) {
			res = res.multiply(new BigDecimal(x) );
			x--;
		}
		
		return res;
	}

}
