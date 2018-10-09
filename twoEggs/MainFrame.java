package twoEggs;

import java.io.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
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
	private static long timeDiffProgramm;
	private static Timestamp startProgramm;
	private static Timestamp endProgramm;
	
	
	
	public static void main(String[] args) throws Exception {
		
		startProgramm = new Timestamp(System.currentTimeMillis() );
		
		//Checks if the input is correct
		if(args.length==0) {
			throw new Exception("Zu wenig Argumente");
		}else if( (args.length==1 && !args[0].matches("-pool\\d+") ) || (args.length==2 && !args[1].matches("-pool\\d+") )  ){
			throw new Exception("Die Thread Pool groesse muss angegeben werden");
		}
		
		//Checks the arguments behind the application start
		if(args[0].equals("-gauss") ) {
			gaus=true;
		}
		
		if(gaus) {
			char cPools = args[1].charAt(5);
			pools = cPools - 48;
			
			if(args[1].length()>6) {
				pools *= 10;
				pools += (args[1].charAt(6)-48);
			}
		}else {
			char cPools = args[0].charAt(5);
			pools = cPools - 48;
			
			if(args[0].length()>6) {
				pools *= 10;
				pools += (args[0].charAt(6)-48);
			}
		}
		
		//Read the path to the floors file
		try {
			
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			
			System.out.printf("Path to the input file (Format: C:\\Folder\\filename.txt): \n");
			path = br.readLine();
			inputFloors = new File(path);
			
			int cnt=0;
			String filename = "";
			while( path.charAt(cnt)!='.' ) {
				cnt++;
			}
			cnt--;
			while( path.charAt(cnt)!='\\') {
				filename = path.charAt(cnt) + filename;
				cnt--;
			}
			String gausString = "";
			if(gaus) {
				gausString = "Gauss";
			}else {
				gausString = "without_Gauss";
			}
			
			String poolSize = String.format("%d", pools);
			
			//Set the output variable
            outputResult = new File("C:\\Users\\Basti-PC\\Documents\\eclipse_workspace\\TwoEggs\\src\\data\\Results\\" + filename + "_" + gausString + "_" + poolSize + "Thread(s)" + ".txt");
			
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
			System.out.printf("%s | ", res[i].getStartTime() );
			System.out.printf("%s | ", res[i].getEndTime() );
			System.out.printf("%d ns\n\n", res[i].getTimeDiff() );
			pw.printf("%s | ", res[i].getResult().toString() );
			pw.printf("%s | ", res[i].getStartTime() );
            pw.printf("%s | ", res[i].getEndTime() );
            pw.printf("%d ns%n%n", res[i].getTimeDiff() );
		}
		
		long timeSum = 0;
		for(int i=0; i<res.length; i++) {
			timeSum += res[i].getTimeDiff();
		}
		
		endProgramm = new Timestamp(System.currentTimeMillis() );
		timeDiffProgramm = endProgramm.getTime() - startProgramm.getTime();
		
		System.out.printf("7 tasks finished\n");
		System.out.printf("Program time: %d ms\n",  timeDiffProgramm);
		System.out.printf("All tasks: %d ns, %d ms\n", timeSum, timeSum/1000000);
		
		pw.printf("7 tasks finished%n");
		pw.printf("Program time: %d ms%n",  timeDiffProgramm);
		pw.printf("All tasks: %d ns, %d ms\n", timeSum, timeSum/1000000);
				
		pw.close();
	}

}















