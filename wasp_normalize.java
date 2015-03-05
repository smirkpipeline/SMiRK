import java.util.Vector;
import java.io.*;
import java.util.StringTokenizer;


public class wasp_normalize {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/* Program to normalize WASP sequenced small RNA Libraries. Requires two inputs
		 * INPUT 1) non normalize miRNA READS
		 * INPUT 2) smaple INFO
		 * 
		 *  Output 1) CSV of normalized reads
		 * 
		 */
		
		try{
			
			Vector<Sample> samps = new Vector<Sample>();
			String mirnaReads = new String(args[0]);
			String sampleInfo = new String(args[1]);
			
			String readNormalFile = new String(args[0]+"readOut.csv");
			String directNormalFile = new String(args[0]+"directNormal.csv");
			
			BufferedReader miBR = new BufferedReader(new FileReader(mirnaReads));
			BufferedReader siBR = new BufferedReader(new FileReader(sampleInfo));
			
			
			String miLine = new String();
			String siLine = new String();
			
			
			// Get all the sample info and non normalized reads into the sample
			miLine = siBR.readLine();
			while((siLine = siBR.readLine())!= null){
				//System.out.println(siLine);
				StringTokenizer st = new StringTokenizer(siLine,",");
				String sName = st.nextToken();
				int totalCount = Integer.parseInt(st.nextToken());
				Sample s= new Sample();
				s.name = sName;
				s.totalReads = totalCount;
				samps.add(s);
			}
		
			
			miLine =  miBR.readLine();
			while((miLine = miBR.readLine())!= null){
				StringTokenizer st = new StringTokenizer(miLine,",");
				String miName = st.nextToken();
				int sampNum =0;
				while(st.hasMoreTokens()){
					miRNA m = new miRNA();
					m.name = miName;
					m.count = Integer.parseInt(st.nextToken());
					
					//System.out.println(m);
					samps.elementAt(sampNum).miRs.add(m);
					sampNum++;
				}
			}
			
		
			
			//Now that we have all the reads it is time to generate calculated values
			double ratioMean = 0.0;
			
			for(int i=0; i<samps.size(); i++){
				samps.elementAt(i).genCalcValues();
				
				ratioMean += samps.elementAt(i).ratio;
				
				System.out.println(samps.elementAt(i));
			}
			
			ratioMean = ratioMean / samps.size();
			
		
			for(int i=0; i<samps.size(); i++){
				samps.elementAt(i).normalize(ratioMean);
			}
			
			
			
			//Everything should be normalized so lets make several CSVs!
			BufferedWriter readNormBW = new BufferedWriter(new FileWriter(readNormalFile));
			BufferedWriter directNormBW = new BufferedWriter(new FileWriter(directNormalFile));
			
			//Have to print it in an inverted way....
			readNormBW.write("miRNA");
			directNormBW.write("miRNA");
			for(Sample s: samps){
				readNormBW.write("," + s.name);
				directNormBW.write("," + s.name);
			}
			readNormBW.write("\n");
			directNormBW.write("\n");
			
			for(int i =0; i<samps.firstElement().miRs.size(); i++){
				readNormBW.write(samps.firstElement().miRs.elementAt(i).name);
				directNormBW.write(samps.firstElement().miRs.elementAt(i).name);
				for(Sample s: samps){
					readNormBW.write("," + s.readNorm.elementAt(i).count);
					directNormBW.write("," + s.directNorm.elementAt(i).count);
				}
				readNormBW.write("\n");
				directNormBW.write("\n");
			}
			
			readNormBW.flush();
			readNormBW.close();
				
			directNormBW.flush();
			directNormBW.close();
			/*********/
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		

	}

}
