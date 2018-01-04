/**
 * LinkedList class implements a doubly-linked list.
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType>
{
    /**
     * Construct an empty LinkedList.
     */
    public MyLinkedList( )
    {
        doClear( );
    }
    
    private void clear( )
    {
        doClear( );
    }
    
    /**
     * Change the size of this collection to zero.
     */
    public void doClear( )
    {
        beginMarker = new Node<>( null, null, null );
        endMarker = new Node<>( null, beginMarker, null );
        beginMarker.next = endMarker;
        
        theSize = 0;
        modCount++;
    }
    
    /**
     * Returns the number of items in this collection.
     * @return the number of items in this collection.
     */
    public int size( )
    {
        return theSize;
    }
    
    public boolean isEmpty( )
    {
        return size( ) == 0;
    }
    
    /**
     * Adds an item to this collection, at the end.
     * @param x any object.
     * @return true.
     */
    public boolean add( AnyType x )
    {
        add( size( ), x );   
        return true;         
    }
    
    /**
     * Adds an item to this collection, at specified position.
     * Items at or after that position are slid one position higher.
     * @param x any object.
     * @param idx position to add at.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    public void add( int idx, AnyType x )
    {
        addBefore( getNode( idx, 0, size( ) ), x );
    }
    
    /**
     * Adds an item to this collection, at specified position p.
     * Items at or after that position are slid one position higher.
     * @param p Node to add before.
     * @param x any object.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */    
    private void addBefore( Node<AnyType> p, AnyType x )
    {
        Node<AnyType> newNode = new Node<>( x, p.prev, p );
        newNode.prev.next = newNode;
        p.prev = newNode;         
        theSize++;
        modCount++;
    }   
    
    
    /**
     * Returns the item at position idx.
     * @param idx the index to search in.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType get( int idx )
    {
        return getNode( idx ).data;
    }
        
    /**
     * Changes the item at position idx.
     * @param idx the index to change.
     * @param newVal the new value.
     * @return the old value.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType set( int idx, AnyType newVal )
    {
        Node<AnyType> p = getNode( idx );
        AnyType oldVal = p.data;
        
        p.data = newVal;   
        return oldVal;
    }
    
    /**
     * Gets the Node at position idx, which must range from 0 to size( ) - 1.
     * @param idx index to search at.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size( ) - 1, inclusive.
     */
    private Node<AnyType> getNode( int idx )
    {
        return getNode( idx, 0, size( ) - 1 );
    }

    /**
     * Gets the Node at position idx, which must range from lower to upper.
     * @param idx index to search at.
     * @param lower lowest valid index.
     * @param upper highest valid index.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between lower and upper, inclusive.
     */    
    private Node<AnyType> getNode( int idx, int lower, int upper )
    {
        Node<AnyType> p;
        
        if( idx < lower || idx > upper )
            throw new IndexOutOfBoundsException( "getNode index: " + idx + "; size: " + size( ) );
            
        if( idx < size( ) / 2 )
        {
            p = beginMarker.next;
            for( int i = 0; i < idx; i++ )
                p = p.next;            
        }
        else
        {
            p = endMarker;
            for( int i = size( ); i > idx; i-- )
                p = p.prev;
        } 
        
        return p;
    }
    
    /**
     * Removes an item from this collection.
     * @param idx the index of the object.
     * @return the item was removed from the collection.
     */
    public AnyType remove( int idx )
    {
        return remove( getNode( idx ) );
    }
    
    //Swaps two nodes by changing the links
    
    public void swap(int idx1,int idx2){
     if(!(idx1< size())&&(idx2<size()))  //Checking whether the indices are within the size
           return;
    swap(getNode(idx1),getNode(idx2));
    
    }
    //Shifts the elements based on the value of num
    public void shift (int num){
    
    if((Math.abs(num)>size()))
     num = num%(size());        //if num is greater than the size, we can use the remainder value after dividing it by the size of the list
     int f= (num>0)?1:-1;       //Setting flag to decide whether shifing has to be forward or backward.
     System.out.println("Flag : "+f);
         shiftP (Math.abs(num),f) ;  
    
  }  
  
  //Erases "count" number of elements from index "idx"
  public void erase(int idx, int count){
  if (idx>=size())            //Checking the limits
  throw new IndexOutOfBoundsException();
  if (count+idx > size())
  throw new IndexOutOfBoundsException();
  
   erase(getNode(idx),count);
  
   
  }
  //Inserts a new list from index idx
   public void insert(MyLinkedList list,int idx){
    
    if(idx>size())
    
    throw new IndexOutOfBoundsException();
    
    insert(list, getNode(idx));
    
    }
    

        /**
     * Removes the object contained in Node p.
     * @param p the Node containing the object.
     * @return the item was removed from the collection.
     */
    private AnyType remove( Node<AnyType> p )
    {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;
        
        return p.data;
    }
       private void swap( Node<AnyType> Node1, Node<AnyType> Node2 )
    {
        
         Node<AnyType> temp;
            
          
            Node1.prev.next=Node2;  // Changing the links of the neighbouring nodes 
            Node1.next.prev=Node2;
            Node2.next.prev=Node1;
            Node2.prev.next=Node1;
            temp=Node1.next;           // Changing the links of the nodes to be swapped.
            Node1.next=Node2.next;
            Node2.next=temp;
            temp=Node1.prev;
             Node1.prev=Node2.prev;
            Node2.prev=temp;
                           
    
    }
   
   private void shiftP( int value,int flag){
   Node<AnyType> p;

  AnyType temp;
  if(flag>0){                   //Deciding whether to shift forward or backward
    for (int j=0;j<value;j++){
        
 p=beginMarker.next;      //Setting pointer at the beginning for forward shift
 temp=p.data;
 

  for (int i=0;i<size()-1;i++)
  {
   p.data=p.next.data;       // Shifting the values forward
  p=p.next;
    
  } 
  
  
   p.data=temp;  

      
   
   }
   }
   else{
   for (int j=0;j<value;j++){ // For shifting backwards
 
   p=endMarker.prev;           // Setting the pointer at the end of the list
 temp=p.data;
 

  for (int i=0;i<size()-1;i++)
  {
   p.data=p.prev.data;
  p=p.prev;
    
  } 

    p.data=temp; 
    }
   
   
   
   
   }
   }
   
   private void erase(Node<AnyType> p, int n){
   
    AnyType old;
   Node<AnyType> temp=p;
   for (int i=0;i<n;i++){
   old = remove( temp );     //Erasing the n number of nodes starting from the node p
   temp=temp.next;
   
   }
   
   
   }
    
    private void insert(MyLinkedList L,Node<AnyType> p){
    
    Node<AnyType> newnode;
    AnyType x;
    
    for (int i=0;i<L.size();i++){
    newnode=L.getNode(i);           //Fetching the node from the list to be inserted
    x=newnode.data;                 // Getting the data in that node
    addBefore(p,x);                 // Inserting in the existing list
    
    
    }
    
    
    }  
   
   
    
    /**
     * Returns a String representation of this collection.
     */
    public String toString( )
    {
        StringBuilder sb = new StringBuilder( "[ " );

        for( AnyType x : this )
            sb.append( x + " " );
        sb.append( "]" );

        return new String( sb );
    }

    /**
     * Obtains an Iterator object used to traverse the collection.
     * @return an iterator positioned prior to the first element.
     */
    public java.util.Iterator<AnyType> iterator( )
    {
        return new LinkedListIterator( );
    }

    /**
     * This is the implementation of the LinkedListIterator.
     * It maintains a notion of a current position and of
     * course the implicit reference to the MyLinkedList.
     */
    private class LinkedListIterator implements java.util.Iterator<AnyType>
    {
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;
        
        public boolean hasNext( )
        {
            return current != endMarker;
        }
        
        public AnyType next( )
        {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !hasNext( ) )
                throw new java.util.NoSuchElementException( ); 
                   
            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }
        
        public void remove( )
        {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !okToRemove )
                throw new IllegalStateException( );
                
            MyLinkedList.this.remove( current.prev );
            expectedModCount++;
            okToRemove = false;       
        }
    }
    
    /**
     * This is the doubly-linked list node.
     */
    private static class Node<AnyType>
    {
        public Node( AnyType d, Node<AnyType> p, Node<AnyType> n )
        {
            data = d; prev = p; next = n;
        }
        
        public AnyType data;
        public Node<AnyType>   prev;
        public Node<AnyType>   next;
    }
    
    private int theSize;
    private int modCount = 0;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;
}

class TestLinkedList
{
    public static void main( String [ ] args )
    {
        MyLinkedList<Integer> lst = new MyLinkedList<>( );

        for( int i = 0; i < 10; i++ )           //Adding elements to the linked list
                lst.add( i );
        for( int i = 20; i < 30; i++ )         //Adding elements in the beginning of the linked list
                lst.add( 0, i );

        lst.remove( 0 );                  //removing the first element
        lst.remove( lst.size( ) - 1 );  // removing the last element

        System.out.println( lst );

        java.util.Iterator<Integer> itr = lst.iterator( );
        while( itr.hasNext( ) )                   // removig all items
        {
                itr.next( );
                itr.remove( );
                System.out.println( lst );
        }
    
    for( int i = 0; i < 10; i++ )
                lst.add( i );
       System.out.println( lst );
 System.out.println("Swapping the 3rd and 8th elements"); 
        lst.swap(2,7);               //Swapping the elements at index2 and 7
        System.out.println( lst );
        lst.shift(-3);   
         System.out.println( "Shifted backwards by 3\n"+lst );
         lst.shift(2);  
         System.out.println( "Shifted forward by 2\n"+lst );

         
         lst.erase(2,4);
         System.out.println( "After erasing 4 lements from index 2\n"+lst );

             


char arr[]={'a','b','c','d','e'};
MyLinkedList<Character> lst1 = new MyLinkedList<>( );  // New linked list
          for( int i = 0; i < 5; i++ )
                lst1.add( arr[i] );
                System.out.println("New list to be inserted : \n" +lst1 );
         lst.insert(lst1,2);       //Inserting the new list into the old list at the third position 
System.out.println("After inserting at index 2 \n"+ lst );
//lst.erase(6,6);               // Throws exception as no of elements starting from imdex 4 exceeds the size  
//lst.erase(11,1);           // Throws exception as index is greater than the size




                
    }
}