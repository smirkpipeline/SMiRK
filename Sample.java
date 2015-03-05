import java.util.Vector;

public class Sample {

	public String name = new String();
	public Vector<miRNA> miRs = new Vector<miRNA>();
	public Vector<miRNA> readNorm = new Vector<miRNA>();
	public Vector<miRNA> directNorm = new Vector<miRNA>();
	
	
	int totalReads = 0;
	int totalMi = 0;
	double ratio = 0.0;
	
	public Sample(){
		name = new String();
		miRs = new Vector<miRNA>();
		totalReads =0;
		totalMi = 0;
		ratio = 0.0;
	}
	
	
	int miValue(String mi){
		for(miRNA m: miRs){
			if(m.name.equalsIgnoreCase(mi)){
				return m.count;
			}
		}
		return -1;
	}
	
	void genCalcValues(){
		for(miRNA m : miRs ){
			totalMi+= m.count;
		}
		
		ratio = (double)totalMi/(double)totalReads;
	}
	
	void normalize(double ratioMean){
		double directNormMult = (double)(1000000.0/(double)totalMi);
		for( miRNA m: miRs){
			miRNA newM = new miRNA();
			newM.name = m.name;
			newM.count = (int)((double)m.count * directNormMult);
			directNorm.addElement(newM);
		}
		
		double readMult = (ratioMean/ratio) * (double)(1000000.0/(double)totalReads);
		//System.out.println(readMult);
		for( miRNA m: miRs){
			miRNA newM = new miRNA();
			newM.name = m.name;
			newM.count = (int)((double)m.count * readMult);
			readNorm.addElement(newM);
		}		
	}
	
	public String toString(){
		return (name + "\t" + totalReads + "\t" + totalMi);
	}
}
