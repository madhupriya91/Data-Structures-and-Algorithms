public class CuckooHash<AnyType> {

	private Object[] table1, table2;
	private HashFunctions hf;
	private int size;
	private int entry_count = 0;
	private int count_t1 = 0;
	private int count_t2 = 0;

	private int maxAttempts = 222;
	
	private int tempAttempts=0;

	CuckooHash(int tableSize, HashFunctions<AnyType> hf) {
		table1 = new Object[tableSize];
		table2 = new Object[tableSize];
		this.hf = hf;
		size = tableSize;

	}

	private void cuckoo(int tflag, AnyType value) {

		tempAttempts++;
		if(tempAttempts>maxAttempts)
		{
			rehash(false);
			insert(value);
			
			
		}
		else if (1 == tflag) {

			int idx2 = hf.HashFunction2(value) % size;
			if (table2[idx2] == null)
				table2[idx2] = value;
			else {
AnyType tempData= (AnyType) table2[idx2];
table2[idx2]= value;
				cuckoo(2, tempData);
			}
			
			count_t2++;
		}
		else {
			int idx1 = hf.HashFunction1(value) % size;
			if (table1[idx1] == null)
				table1[idx1] = value;
			else {

				AnyType tempData= (AnyType) table1[idx1];
				table1[idx1]= value;
				cuckoo(1, tempData);
			}
			
			count_t1++;
		}

	}

	public void printTable() {
		int c=0;
		System.out.println("Table1");
		for (Object t : table1) {
			System.out.println((AnyType) t);
			if(t!=null)
			{
				c++;
			}
		}
		System.out.println("Table2");
		for (Object t : table2) {
			System.out.println((AnyType) t);
			if(t!=null)
			{
				c++;
			}
		}
		System.out.println(" Total " + c);

	}

	public void insert(AnyType data) {

		
		tempAttempts=0;	
		// printTable();
		if (contains(data))
			return;

		if (entry_count > 0.8 * size) {

			rehash(true);

		}
		int idx1 = hf.HashFunction1(data) % size;
		if (table1[idx1] == null)
			table1[idx1] = data;
		else {
			AnyType tempData= (AnyType) table1[idx1];
			table1[idx1] = data;
			
			cuckoo(1, tempData);
		}
		
		count_t1++;
		entry_count++;
	
		
	}

	private void rehash(boolean isResizeRequired) {
		hf.ChangeHashFunction();
		Object[] temp_table1, temp_table2;
		temp_table1 = table1;
		temp_table2 = table2;

		if (isResizeRequired) {
			size *= 2;
		}
		table1 = new Object[size];
		table2 = new Object[size];
		entry_count=0;
		count_t1=0;
		count_t2=0;
		for (int i = 0; i < temp_table1.length ; i++) {
if(temp_table1[i]!=null)
			insert((AnyType) temp_table1[i]);

		}
		for (int i = 0; i < temp_table2.length; i++) {
			if(temp_table2[i]!=null)
			insert((AnyType) temp_table2[i]);

		}
	}

	boolean contains(AnyType data) {
		if (search(data) > 0) {
			return true;
		}

		return false;
	}

	int search(AnyType data) {
		int pos = -1;

		int h1 = hf.HashFunction1(data) % size;
		int h2 = hf.HashFunction2(data) % size;
		if (table1[h1] != null && table1[h1].equals(data)) {
			//System.out.println("Exists at index  " + h1 + "of table1");
			pos = h1;
		}
		if (table2[h2] != null && table2[h2].equals(data)) {
			//System.out.println("Exists at index  " + h2 + "of table2");
			pos = h2;
		}
		return pos;
	}

	void delete(AnyType data) {
		if (contains(data)) {
			if (table1[search(data)] != null
					&& table1[search(data)].equals(data)) {
				table1[search(data)] = null;
			}
			if (table2[search(data)] != null
					&& table2[search(data)].equals(data)) {
				table2[search(data)] = null;
			}
		} else
			System.out.println("Data not found \n");

	}

	public static void main(String args[]) {

	}
}
