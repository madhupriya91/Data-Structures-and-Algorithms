
import java.util.Scanner;

class Player{
	
	char value;
	boolean win;
	public Player(char choice,boolean res){
		value=choice;
		win=res;
		
	}
	boolean getWin(){
		
		return this.win;
	}
	
char getVal(){
		
		return this.value;
	}
}
class GridCell{
	int row,col;
	char value;
	public GridCell(int row,int col,char value){
		this.row=row;
		this.col=col;
		this.value=value;
				
	}
	char getValue(){
		
		return this.value;
	}
	
}
class TicTacGrid{
	GridCell[][] cells=new GridCell[3][3];
	
	TicTacGrid(char[][] values){
		for(int i=0;i<3;i++){
			
			for(int j=0;j<3;j++){
				cells[i][j]=new GridCell(i,j,values[i][j]);
				
				
			}
			
		}
		
		
		
	}
	
	void printGrid(){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				System.out.print(cells[i][j].getValue());
				
				
				
			}
			System.out.println();
			
			
		}
		
		
	}
	Player wonWithRow(){
		int winFlag=1;
		
		char value=cells[0][0].getValue();
		Player winner=new Player(value,false);
		for(int i=0;i<3;i++){
			value=this.cells[i][0].getValue();
			for(int j=1;j<3;j++){
				if(this.cells[i][j].getValue()==value)
					continue;
				else
					winFlag=0;
				
				
			}
			if(winFlag==1){
				winner=new Player(value,true);
				return winner;
			}
		}
		return new Player(value,false);
		
	}
	
	Player wonWithCol(){
		int winFlag=1;
		char value=cells[0][0].getValue();
		Player winner=new Player(value,false);

		for(int j=0;j<3;j++){
			value=this.cells[0][j].getValue();
			for(int i=1;i<3;i++){
				if(this.cells[i][j].getValue()==value)
					continue;
				else
					winFlag=0;
				
				
			}
			if(winFlag==1){
				winner=new Player(value,true);
			return winner;
			}
		}
		return new Player(value,false);
		
		
	}
	
	Player wonWithDiag(){
		int winFlag=1;
		int i=0,j=0;
		char value=cells[i][j].getValue();
		i++;j++;
		while(i<3 && j<3){
		 if(cells[i][j].getValue()==value){
			 i++; j++;
			 continue;
			 
			 
		 }
		 else{
			 
			 winFlag=0;
		 }
			
			i++;
			j++;
		}
		
		if(winFlag==0){
			winFlag=1;
			 i=0;j=2;
			value=cells[i][j].getValue();
			i++;j--;
			while(i<3 && j>=0){
			 if(cells[i][j].getValue()==value){
				 i++; j--;
				 continue;
				 
				 
			 }
			 else{
				 
				 winFlag=0;
			 }
			
			
			 i++; j--;
			
			
		}
		}
		if(winFlag==1)return new Player(value,true);
		else
		return new Player(value,false);
			
	}
	
	
	
	
	
	
	
	
	
	void hasWon(){
		Player winner;
		if(wonWithRow().getWin() || wonWithCol().getWin() || wonWithDiag().getWin())
			{
			if(wonWithRow().getWin())
				winner=wonWithRow();
			else if (wonWithCol().getWin())
				winner=wonWithCol();
			else
				winner=wonWithDiag();
			
			System.out.println("Player "+winner.getVal()+" has won the game !!");
			}
		
		else
			System.out.println("TIE");
		
		
		
	}
	

}

public class TicTacWin {

	
	
	
	public static void main(String args[]){
		char[][] entries=new char[3][3];
		Scanner scan=new Scanner(System.in);
				for(int i=0;i<3;i++){
			System.out.println("Enter values for row "+(i+1));
			for(int j=0;j<3;j++){
				entries[i][j]=scan.next().charAt(0);
				
			}
			
			
		}
		
		TicTacGrid gameGrid=new TicTacGrid(entries);
		gameGrid.printGrid();
		gameGrid.hasWon();
		//Assuming game is over
		//if(gameGrid.hasWon()){
			
			//System.out.println("Winner is : "+gameGrid.whoWon());
			
			
	//	}
		
		
	}
}
