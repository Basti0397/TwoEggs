package twoEggs;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainFrame {
	
	public static void main(String[] args) throws Exception {
		BigDecimal floors = new BigDecimal("93326215443944152681699238856266700490715968264381621468592963895217599993229915608941463976156518286253697920827223758251185210916864000000000000000000000000");
		boolean gaus = false;
		int pools = 0;
		
		if(args.length==0) {
			throw new Exception("Zu wenig Argumente");
		}else if( (args.length==1 && !args[0].matches("-pool\\d+") ) || (!args[1].matches("-pool\\d+") )  ){
			throw new Exception("Die Thread Pool groesse muss angegeben werden");
		}
		
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
			executor.execute(new Handler(new Gauss(floors) ) );
		}else {
			//executor.execute(new Handler(new WithoutGauss(floors)));
		}
		
		executor.shutdown();
		
		
//		String path = "";
//		boolean isFile = false;
//		File floors = null;
//		
//		try {
//			
//			while( !isFile ) {
//				InputStreamReader isr = new InputStreamReader(System.in);
//				BufferedReader br = new BufferedReader(isr);
//				
//				System.out.printf("Path to the file (Format: C:\\Folder\\filename.txt): \n");
//				path = br.readLine();
//				
//				floors = new File(path);
//				
//				if( floors.isFile() ) {
//					isFile=true;
//					System.out.println("Valid path");
//				}else {
//					System.out.printf("Not a valid file, please try again.\n");
//				}
//			}
//			
//			
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
		
		
		
		
		
		
//		if(gaus) {
//			//Gauss:
//			Gauss g = new Gauss();
//			maxSteps = g.calculate(floors);
//			
//			System.out.printf("Es wird die Summenformel benutzt\n");
//		}else {
//			//Without Gauss:
//			WithoutGauss wG = new WithoutGauss();
//			maxSteps = wG.calculate(floors);
//			
//			System.out.printf("Es wurde nicht die Summenformel benutzt\n");
//		}
//		
		
	}

}
