
public class miRNA {
	public String name = new String();
	public int count  = 0;
	
	
	public miRNA(){
		name = new String();
		count = 0;
	}
	
	public String toString(){
		return (name + "\t" + count);
	}
}
