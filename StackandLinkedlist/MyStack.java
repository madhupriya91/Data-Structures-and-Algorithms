
public class MyStack<AnyType>
{

private MyArrayList<AnyType> Alist;  // Creating MyArrayList object for implementing stack
public MyStack(){

 Alist=new MyArrayList<>();
int n= Alist.size();
System.out.println(n);
}

public void push(AnyType d){
 Alist.add(d);         //Using add() of MyArrayList for push()
}

public AnyType pop(){

if(!StackisEmpty())
return Alist.remove(Alist.size()-1);  //Using remove() of MyArrayList for pop()

else{
System.out.println("Stack is already Empty");
throw new ArrayIndexOutOfBoundsException();
}
}


public AnyType peek(){           //To look into the stack
if(!StackisEmpty())
return Alist.get(Alist.size()-1);
else 
 System.out.println("Stack is already Empty");
 throw new ArrayIndexOutOfBoundsException();
}

public boolean StackisEmpty(){     //Checks whether the stack has any content or is it empty.

return (Alist.size()==0);

}

public String toString(){
return "MySTack : "+Alist.toString();     //To print the stack
}


}

class TestStack{

public static boolean balance(MyStack<Character> stack,String pattern) //For checking the balancing of brackets
{
char c;

System.out.println(pattern.length());
for (int i=0;i<pattern.length();i++)
{

c=pattern.charAt(i);
char popped;
   switch(c)
   {
    case '(': stack.push(c);
              System.out.print("\nAfter pushing "+c+" stack is : \n"+stack);
              break;
    case '{': stack.push(c);
              System.out.print("\nAfter pushing "+c+" stack is : \n"+stack);
              break;
    case '[': stack.push(c);
              System.out.print("\nAfter pushing "+c+" stack is : \n"+stack);
              break;
    case ')':  if (stack.StackisEmpty())
                return false;
               popped=stack.pop();
               System.out.println("\nC is "+c+"\nPopped item is :"+popped+"\n After popping "+"stack is : \n"+stack);
               if(popped!='(')
                return false;
                 
               
                break;
    case '}':  if (stack.StackisEmpty())
                return false;
               popped=stack.pop();
                System.out.println("\nC is "+c+"\nPopped item is :"+popped+"\n After popping "+"stack is : \n"+stack);
               if(popped!='{')
                return false;
                break;
    case ']':   if (stack.StackisEmpty())
                return false;
                popped=stack.pop();
                System.out.println("\nC is "+c+"\nPopped item is :"+popped+"\n After popping "+"stack is : \n"+stack);
               if(popped!='[')
                return false;
                break;
      
     default:System.out.println("Not a bracket");
              return false;              
                
   
   
   }

 }
 return true;

}


public static void main(String[] str){

MyStack<Character> Stack_AL=new MyStack<>();     // Creating a Character MyStack object
String pattern="[{()}]";
char arr[]={'a','b','c','d','e'};
for(int i=0;i<5;i++)
{
 Stack_AL.push(arr[i]);        //Creating the stack
}
System.out.println(Stack_AL);  //Printing the stack

for(int i=0;i<5;i++)
{
  Stack_AL.pop();
  System.out.println(Stack_AL);  //Popping elements

}

boolean result=balance(Stack_AL,pattern);  // A balanced bracket set as input

System.out.println("result : "+result);
pattern="{()}]";
result=balance(Stack_AL,pattern);        //An unbalanced bracket set
System.out.println("result : "+result);

 }
}





