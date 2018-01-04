import java.util.Random;


public class StringHashFunctions implements HashFunctions<String> {

	Random random = new Random();
	
	int constVal1 = random.nextInt();
	
	int constVal2 = random.nextInt();
	
	@Override
	public int HashFunction1(String value) {
		int hashVal =0;
	
		for(int i=0;i<value.length();i++)
			hashVal= value.hashCode() * constVal1 + hashVal - constVal2*constVal2 ; 
		if (hashVal < 0)
		{
			return -1*hashVal;
		}
		return hashVal;

	}

	public int HashFunction2(String value) {
		int i=0,hashValue=0;
		
       while(i<value.length()){
    	   hashValue= (int)value.charAt(i)+value.hashCode() * constVal2 - constVal1*constVal1 ;
    	   i++;
       }
       
   	if (hashValue < 0)
	{
		return -1*hashValue;
	}
       return hashValue;
	}


public void ChangeHashFunction(){
	System.out.println(" Rehash");
	
constVal1 = random.nextInt();
	
 constVal2 = random.nextInt();
 }
}