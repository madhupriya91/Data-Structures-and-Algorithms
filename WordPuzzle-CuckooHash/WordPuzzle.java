
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordPuzzle {

	static char getRandomCharacter() {
		int rl = ((int) (Math.random() * 50 + 1) % 26) + 'a';
		return (char) rl;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int count = 0;
		int maxLength = 0;
		String longestString = null;
		char puzzl[][]; // declaration
		MyLinkedList<String> dictionaryLL = new MyLinkedList<>();
		AvlTree<String> dictionayTree = new AvlTree<>();
		CuckooHash<String> dictionaryHash = new CuckooHash<>(1, new StringHashFunctions());
// String names[]={"madhu","priya","anu","radha","pooja","pooh","piggy"};

//dictionaryHash.search("radha");

//dictionaryHash.printTable();

		try (BufferedReader br = new BufferedReader(
				new FileReader("C:\\Madhu\\Eclipse\\WordPuzzle\\src\\dictionary.txt"))) {
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				// System.out.println(sCurrentLine);
				dictionaryLL.add(sCurrentLine);
				dictionayTree.insert(sCurrentLine);
			dictionaryHash.insert(sCurrentLine);
				
				System.out.println(count);
				count++;
				if (sCurrentLine.length() > maxLength) {
					maxLength = sCurrentLine.length();
					longestString = sCurrentLine;
				}

			}
			dictionaryHash.printTable();
			System.out.println(count);
			br.close();
		} catch (IOException e) {
			System.out.println("Error reading");

		}
		
		
	

	
		System.out.println("\n Maxlength : " + maxLength + "longest word  :" + longestString);
		System.out.println("Enter the number of rows");
		int rows = input.nextInt();
		System.out.println("Enter the number of columns");
		int columns = input.nextInt();
		input.close();

		puzzl = new char[rows][columns];
		int i, j;

		for (i = 0; i < rows; i++) {
			for (j = 0; j < columns; j++) {
				puzzl[i][j] = getRandomCharacter();
			}
		}

		for (i = 0; i < rows; i++) {
			for (j = 0; j < columns; j++)

				System.out.print(puzzl[i][j] + " ");
			System.out.println();
		}
		
		
		
		
		
		search(maxLength, puzzl, null, null, dictionaryHash,
				rows, columns);
		search(maxLength, puzzl, null, dictionayTree, null,
				rows, columns);
		search(maxLength, puzzl, dictionaryLL, null, null,
				rows, columns);
	}

	private static void search(int maxLength, char[][] puzzl,
			MyLinkedList<String> dictionaryLL, AvlTree<String> dictionayTree,
			CuckooHash<String> dictionaryHash, int rows, int columns) {
		int i;
		int j;
		int s;
		StringBuilder word = new StringBuilder();
		for (i = 0; i < rows; i++) {
			System.out.println();
			for (j = 0; j < columns; j++) {
				word.setLength(0);
				// System.out.println(" \nAt : "+puzzl[i][j]+ " ");
				//System.out.println("At   : " + puzzl[i][j] + " to right ");
				for (int k = j; k < columns; k++)
				// for(int k=j; word.length()<maxLength;k++)
				{// System.out.print(puzzl[i][k]);
					word.append(puzzl[i][k]);
					if (word.length() > maxLength)
						break;

					if (dictionayTree!=null && dictionayTree.contains(word.toString())) {
						System.out.println("MATCHED ! in Tree : " + word + "\n");
					}
					
					if (dictionaryHash!=null&& dictionaryHash.contains(word.toString())) {
						System.out.println("MATCHED ! in Cuckoo hash : " + word + "\n");
					}
					
					if (dictionaryLL!=null){
					for (String allWords : dictionaryLL) {
						if (allWords.compareTo(word.toString()) == 0) {
							System.out.println("MATCHED ! in LL  : " + word + "\n");
						}
					}
					}
				
					//System.out.println(word);

				}
				System.out.println();
				word.setLength(0);

				System.out.println("\nAt  " + puzzl[i][j] );
				for (int k = j; k >= 0; k--) {
					word.append(puzzl[i][k]);
					if (word.length() > maxLength)
						break;

					if (dictionayTree!=null && dictionayTree.contains(word.toString())) {
						System.out.println("MATCHED ! in Tree : " + word + "\n");
					}

					
					
					if (dictionaryHash!=null&& dictionaryHash.contains(word.toString())) {
						System.out.println("MATCHED ! in Cuckoo hash : " + word + "\n");
					}
					
					
					if (dictionaryLL!=null){
					for (String allWords : dictionaryLL) {
						if (allWords.compareTo(word.toString()) == 0) {
							System.out.println("MATCHED ! in LL  : " + word + "\n");
						}
					}
					}
				
					//System.out.println(word);
				}
				System.out.println();

				word.setLength(0);
				System.out.println("\nAt " + puzzl[i][j] );
				for (int k = i; k >= 0; k--) {
					word.append(puzzl[k][j]);
					if (word.length() > maxLength)
						break;

					if (dictionayTree!=null && dictionayTree.contains(word.toString())) {
						System.out.println("MATCHED ! in Tree : " + word + "\n");
					}

					if (dictionaryHash!=null&& dictionaryHash.contains(word.toString())) {
						System.out.println("MATCHED ! in Cuckoo hash : " + word + "\n");
					}
					
					if (dictionaryLL!=null){
				for (String allWords : dictionaryLL) {
						if (allWords.compareTo(word.toString()) == 0) {
							System.out.println("MATCHED ! in LL  : " + word + "\n");
						}
					}
					}
			
					//System.out.println(word);
				}
				System.out.println();
				word.setLength(0);
				//System.out.println("\nAt : " + puzzl[i][j] + " towards bottom :");
				for (int k = i; k < rows; k++) {
					word.append(puzzl[k][j]);
					if (word.length() > maxLength)
						break;

					if (dictionayTree!=null && dictionayTree.contains(word.toString())) {
						System.out.println("MATCHED ! in Tree : " + word + "\n");
					}
					
					if (dictionaryHash!=null&& dictionaryHash.contains(word.toString())) {
						System.out.println("MATCHED ! in Cuckoo hash : " + word + "\n");
					}

					if (dictionaryLL!=null){
					for (String allWords : dictionaryLL) {
						if (allWords.compareTo(word.toString()) == 0) {
							System.out.println("MATCHED ! in LL  : " + word + "\n");
						}
					}
					}
				
					//System.out.println(word);
				}
				System.out.println();
				int x, y;
				word.setLength(0);
				//System.out.println("\nAt  : " + puzzl[i][j] + " towards right down");
				for (x = i, y = j; x < rows; x++, y++) {
					if (y == columns)
						break;
					// System.out.println("x = "+x+" y = "+y);
					word.append(puzzl[x][y]);
					if (word.length() > maxLength)
						break;

					if (dictionayTree!=null && dictionayTree.contains(word.toString())) {
						System.out.println("MATCHED ! in Tree : " + word + "\n");
					}

					if (dictionaryHash!=null&& dictionaryHash.contains(word.toString())) {
						System.out.println("MATCHED ! in Cuckoo hash : " + word + "\n");
					}
					
					if (dictionaryLL!=null){
					for (String allWords : dictionaryLL) {
						if (allWords.compareTo(word.toString()) == 0) {
							System.out.println("MATCHED ! in LL  : " + word + "\n");
						}
					}
					}
					
					//System.out.println(word);
				}
				System.out.println();
				word.setLength(0);
				//System.out.println("\nAt : " + puzzl[i][j] + " towards right up");
				for (x = i, y = j; x >= 0; x--, y++) {
					if (y == columns)
						break;
					// System.out.println("x = "+x+" y = "+y);
					word.append(puzzl[x][y]);
					if (word.length() > maxLength)
						break;

					if (dictionayTree!=null && dictionayTree.contains(word.toString())) {
						System.out.println("MATCHED ! in Tree : " + word + "\n");
					}
					
					
					if (dictionaryHash!=null&& dictionaryHash.contains(word.toString())) {
						System.out.println("MATCHED ! in Cuckoo hash : " + word + "\n");
					}
					if (dictionaryLL!=null){
					for (String allWords : dictionaryLL) {
						if (allWords.compareTo(word.toString()) == 0) {
							System.out.println("MATCHED ! in LL  : " + word + "\n");
						}
					}
					}
				
					//System.out.println(word);
				}
				System.out.println();
				word.setLength(0);
				//System.out.println("\nAt  : " + puzzl[i][j] + " towards left down");
				for (x = i, y = j; x < rows; x++, y--) {
					if (y < 0)
						break;
					// System.out.println("x = "+x+" y = "+y);
					word.append(puzzl[x][y]);
					if (word.length() > maxLength)
						break;

					if (dictionayTree!=null && dictionayTree.contains(word.toString())) {
						System.out.println("MATCHED ! in Tree : " + word + "\n");
					}

					if (dictionaryHash!=null&& dictionaryHash.contains(word.toString())) {
						System.out.println("MATCHED ! in Cuckoo hash : " + word + "\n");
					}
					
					if (dictionaryLL!=null){
					for (String allWords : dictionaryLL) {
						if (allWords.compareTo(word.toString()) == 0) {
							System.out.println("MATCHED ! in LL  : " + word + "\n");
						}
					}

					}	
			
					//System.out.println(word);
				}
				System.out.println();
				word.setLength(0);
				//System.out.println("\nAt  : " + puzzl[i][j] + " towards left up");
				for (x = i, y = j; x >= 0; x--, y--) {
					if (y < 0)
						break;
					// System.out.println("x = "+x+" y = "+y);
					word.append(puzzl[x][y]);
					if (word.length() > maxLength)
						break;

					if (dictionayTree!=null && dictionayTree.contains(word.toString())) {
						System.out.println("MATCHED ! in Tree : " + word + "\n");
					}

					if (dictionaryHash!=null&& dictionaryHash.contains(word.toString())) {
						System.out.println("MATCHED ! in Cuckoo hash : " + word + "\n");
					}
					
					if (dictionaryLL!=null){
					for (String allWords : dictionaryLL) {
						if (allWords.compareTo(word.toString()) == 0) {
							System.out.println("MATCHED ! in LL  : " + word + "\n");
						}
					}
					}
				
					//System.out.println(word);
				}
				System.out.println();
			}

		}
	}

}
