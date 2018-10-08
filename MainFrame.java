package twoEggs;

import java.io.*;
import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainFrame {
	
    private static final int AMOUNT = 7;
	private static BigDecimal[] floorArray = new BigDecimal[AMOUNT];
	private static Result[] res = new Result[AMOUNT];
    private static Future<Result>[] futureArray = new Future[AMOUNT];
	private static boolean gaus = false;
	private static int pools = 0;
	private static String path = "";
	private static File inputFloors = null;
	private static File outputResult = null;
	
	
	
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
			
			System.out.printf("Path to the input file (Format: C:\\Folder\\filename.txt): \n");
			path = br.readLine();
			inputFloors = new File(path);
			
			System.out.printf("Path to the output file (Format: C:\\Folder\\filename.txt): \n");
            path = br.readLine();
            outputResult = new File(path);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Read the floors file
		BufferedReader textReader = new BufferedReader(new FileReader(inputFloors) );
		
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
				futureArray[i] = executor.submit(new Handler(new Gauss(floorArray[i] ) ) );
			}
			
		}else {
			for(int i=0; i<floorArray.length; i++) {
				futureArray[i] = executor.submit(new Handler(new WithoutGauss(floorArray[i] ) ) );
			}
		}
		
		executor.shutdown();
		
		for(int i=0; i<futureArray.length; i++) {
		    res[i] = futureArray[i].get();
		}
		
		PrintWriter pw = new PrintWriter(new FileWriter(outputResult));
		        
		for(int i=0; i<res.length; i++) {
			System.out.printf("\n%s | ", res[i].getResult().toString() );
			System.out.printf("%s | ", res[i].getStartTime().toLocalDateTime() );
			System.out.printf("%s | ", res[i].getEndTime() );
			System.out.printf("%d ns\n\n", res[i].getTimeDiff() );
			pw.printf("\n%s | ", res[i].getResult().toString() );
			pw.printf("%s | ", res[i].getStartTime().toLocalDateTime() );
            pw.printf("%s | ", res[i].getEndTime() );
            pw.printf("%d ns\n\n", res[i].getTimeDiff() );
		}
	}

}















