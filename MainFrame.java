package twoEggs;

import java.io.*;
import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainFrame {
	
	private static final BigDecimal[] floorArray = new BigDecimal[6];
	private static boolean gaus = false;
	private static int pools = 0;
	private static String path = "";
	private static File floors = null;
	private static Result[] res = new Result[6];
	
	public static void main(String[] args) throws Exception {
		
		//Checks if the input is correct
		if(args.length==0) {
			throw new Exception("Zu wenig Argumente");
		}else if( (args.length==1 && !args[0].matches("-pool\\d+") ) || (args.length==2 && !args[1].matches("-pool\\d+") )  ){
			throw new Exception("Die Thread Pool groesse muss angegeben werden");
		}
		
		//Read the path to the floors file
		try {
			
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			
			System.out.printf("Path to the file (Format: C:\\Folder\\filename.txt): \n");
			path = br.readLine();
			
			floors = new File(path);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Read the floors file
		BufferedReader textReader = new BufferedReader(new FileReader(floors) );
		
		for(int i=0; i<floorArray.length; i++) {
			String stringNumber = textReader.readLine();
			if(stringNumber.length()>0) {
				BigDecimal number = new BigDecimal(stringNumber);
				floorArray[i]=number;
			}else {
				i--;
			}
		}
		
		textReader.close();
		
		
		//Checks the arguments behind the application start
		if(args[0].equals("-gauss") ) {
			gaus=true;
		}
		
		if(gaus) {
			char cPools = args[1].charAt(5);
			pools = cPools - 48;
		}else {
			char cPools = args[0].charAt(5);
			pools = cPools - 48;
		}
		
		
		ExecutorService executor = Executors.newFixedThreadPool(pools);
		
		if(gaus) {
			for(int i=0; i<floorArray.length; i++) {
				Future<Result> futureRes = executor.submit(new Handler(new Gauss(floorArray[i] ) ) );
				res[i] = futureRes.get();
			}
			
		}else {
			for(int i=0; i<floorArray.length; i++) {
				Future<Result> futureRes = executor.submit(new Handler(new WithoutGauss(floorArray[i] ) ) );
				res[i] = futureRes.get();
			}
		}
		
		executor.shutdown();
		
		for(int i=0; i<res.length; i++) {
			System.out.println();
			System.out.printf("%s | ", res[i].getResult().toString() );
			System.out.printf("%s | ", res[i].getStartTime() );
			System.out.printf("%s | ", res[i].getEndTime() );
			System.out.printf("%d ms\n", res[i].getTimeDiff() );
			System.out.println();
		}
		
	}

}















