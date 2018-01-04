// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws Exception as appropriate

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( ) throws Exception
    {
        if( isEmpty( ) )
            throw new Exception( );
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( ) throws Exception
    {
        if( isEmpty( ) )
            throw new Exception( );
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
    
    
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    /* Assignment question 1(a) : To count the number of nodes
     Public method which calls the private method */
     
     public int nodeCount()
     {
     
      return nodeCount(root);
     
     }

  /* Assignement question 1(b) : To find out whether the tree is full or not */

   public boolean isFull()
   {
   
    return isFull(root);
   
   }

  public boolean compareStructures( BinarySearchTree tree2)
   {
     
    
    return compareStructures(root,tree2.root); 
   }
   
   public boolean equals( BinarySearchTree tree2)
   {
   
   return equals(root,tree2.root);
   }

  /* Assignment question : COPYING trees */
  
    public BinarySearchTree<AnyType> copy()
    {
    
     BinarySearchTree<AnyType> t = new BinarySearchTree<>( );
    
     t.root = copy(root,t.root);
     return t;
    }
    
   /* Assignment question to print the mirror */
   
    public BinarySearchTree<AnyType> mirror()
    {
    
     BinarySearchTree<AnyType> t = new BinarySearchTree<>( );
          t.root=mirror(root,t.root);
    return t;
    }

  /* Checking whether the trees are mirrors */
  
  public boolean isMirror(BinarySearchTree<AnyType> t)
  {
  
  
  return isMirror(root,t.root);
  
  }
   
   /* To rotate right */
   
   public void rotateRight(AnyType value)
   {
   if(isEmpty())
   System.out.println("Empty tree ");

   else
          root=rotateRight(value,root);
   

   
   }
   
   /* Rotate left */
   
    public void rotateLeft(AnyType value)
   {
    if(isEmpty())
   System.out.println("Empty tree ");

   else

           root=rotateLeft(value,root);
   
   
   }

  public void printLevels()
  {
  
    int h=height(root);
        for(int i=1;i<=h+1;i++)
    {
    System.out.println("Level :"+i);
     printLevels(root,i);
    
    }
  }
    
    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }
    
    /* Private methode to count the number of nodes*/
    
    private int nodeCount( BinaryNode<AnyType> treeNode )
    {
    
    if(null!=treeNode)
     return 1+nodeCount(treeNode.left)+nodeCount(treeNode.right);
     else
     return 0;
    
    }
    
    /* Internal method to find whether the tree is full */
    private boolean isFull( BinaryNode<AnyType> treeNode )
    {
    
      if (null==treeNode)  
            return false;
      
       if((isFull(treeNode.left)==false)&&(isFull(treeNode.right)==false)) return true;
       
       if ((isFull(treeNode.left)==true)&&(isFull(treeNode.right)==true)) return true;
       return false;
    }
    
    
    private boolean compareStructures(BinaryNode<AnyType> treeNode1, BinaryNode<AnyType> treeNode2)
    
    {
          if((treeNode1==null)&&(treeNode2==null)) return true;
           if(((treeNode1==null)&&(treeNode2!=null))||((treeNode1!=null)&&(treeNode2==null))) {System.out.println("Structures are not same");
             
    
             return false;
       }
     if((compareStructures(treeNode1.left,treeNode2.left)==true)&&((compareStructures(treeNode1.right,treeNode2.right)==true)))
      return true;
          return false;
    }
    
    /* Internal method to find whether trees are identical */
    
    private boolean equals( BinaryNode<AnyType> treeNode1, BinaryNode<AnyType> treeNode2)
    {
     if ((null==treeNode1)&&(null==treeNode2)) return true;
     if(treeNode1.element==treeNode2.element)
       {
       if((equals(treeNode1.left,treeNode2.left))&&(equals(treeNode1.right,treeNode2.right)))
       return true;       
       }
    
    return false;
    }
    
    /* Internal method for copying tree */
    
    private BinaryNode<AnyType> copy(BinaryNode<AnyType> node1,BinaryNode<AnyType> node2)
    {
 
          if(null!=node1)
          {
                  
         node2=new BinaryNode(node1.element,null,null);
               }
       else return node2;
      node2.left=copy(node1.left,null);
      node2.right=copy(node1.right,null);
      return node2;
    
    }


/* Internal method for mirroring a  tree */
    
    private BinaryNode<AnyType> mirror(BinaryNode<AnyType> node1,BinaryNode<AnyType> node2)
    {
         if(null!=node1)
          {
                  
         node2=new BinaryNode(node1.element,null,null);
          }
       else return node2;
      node2.left=mirror(node1.right,null);
      node2.right=mirror(node1.left,null);
      return node2;

    }


    /* Internal method to check whether trees are mirrors */
    
    private boolean isMirror(BinaryNode<AnyType> treeNode1,BinaryNode<AnyType> treeNode2)
    {
     if(treeNode1.element.compareTo(treeNode2.element)==0)
       {
         if((treeNode1.left.element.compareTo(treeNode2.right.element)==0)&&(treeNode1.right.element.compareTo(treeNode2.left.element)==0))
         return true;
       
       }
    
    return false;
    
    }
    
    private BinaryNode<AnyType> rotateRight(AnyType val,BinaryNode<AnyType> treeNode)
    {
   
   

        if(treeNode!=null){
                         if(val.compareTo(treeNode.element)==0)
                          {
                            
                              BinaryNode<AnyType> temp= new BinaryNode(null,null,null);
                              temp=treeNode.left;
                              treeNode.left=treeNode.left.right;
                              temp.right=treeNode;
                              treeNode=temp;
      
                           }
                        else if((treeNode.element.compareTo(val))>0)
                           {
                              treeNode.left=rotateRight(val,treeNode.left);

                           }
                          else if((treeNode.element.compareTo(val))<0)
                          {
                              treeNode.right=rotateRight(val,treeNode.right);
                          } 
                       }
    return treeNode;

    }
    
    
    private BinaryNode<AnyType> rotateLeft(AnyType val,BinaryNode<AnyType> treeNode)
    {
    if(treeNode!=null){
    if(val.compareTo(treeNode.element)==0)
      {
      BinaryNode<AnyType> temp=new BinaryNode(null,null,null);
      temp =treeNode.right;
      treeNode.right=treeNode.right.left;
      temp.left=treeNode;
      treeNode=temp;      
              }
          else if((treeNode.element.compareTo(val))<0)
      {
           treeNode.right=rotateLeft(val,treeNode.right);
      }
     else if((treeNode.element.compareTo(val))>0)
     {
      treeNode.left=rotateLeft(val,treeNode.left);
     } 
            }
          return treeNode;
    }
    
    
    private void printLevels(BinaryNode<AnyType> node,int level)
     {
       if(null==node) {
              return;
       
       }if(level==1)
       System.out.println(" "+node.element);
       if(level>1)
        {
         printLevels(node.left,level-1);
         printLevels(node.right,level-1);
        
        }
     
     
     
     } 
    
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }


      /** The tree root. */
    private BinaryNode<AnyType> root;

        // Test program
    public static void main( String [ ] args )throws Exception
    {
    
        BinarySearchTree<Integer> t = new BinarySearchTree<>( );
                        
              
        /* To count the number of nodes */
       
        int arr[]={10,5,20,15,25};
        BinarySearchTree<Integer> t1 = new BinarySearchTree<>( );
        BinarySearchTree<Integer> t2 = new BinarySearchTree<>( );
        BinarySearchTree<Integer> t3 = new BinarySearchTree<>( );
        for (int j=0;j<5;j++)
          t1.insert( arr[j] );
          System.out.println(" Tree 1 :  ");
            t1.printTree( );
            int arr2[]={30,20,40,35,50};
            for (int j=0;j<5;j++)
          t2.insert( arr2[j] );
     
          System.out.println(" Tree 2 :  ");
          t2.printTree( );
          
          int arr3[]={30,20,40,35,50};

            for (int j=0;j<5;j++)
          t3.insert( arr3[j] );
          System.out.println(" Tree 3 :  ");
          t3.printTree( );
         
         /* To count the number of nodes */
         System.out.println("No of nodes in Tree 1 : "+t1.nodeCount());

        /* To check whether the tree is full */
        System.out.println("The tree 1 is full " +t1.isFull());
        
       /* To compare the structures of the tress */
        System.out.println("Are the structures of tree 1 and tree 2 same ? "   +t1.compareStructures(t2));
        
       /* To check whether the tress are identical*/
        System.out.println("Are tree 3 and tree 2 equal trees ?  "+t2.equals(t3));
        System.out.println("Are tree 3 and tree 1 equal trees ?  "+t1.equals(t3));

       
       /* Copying tree t2 */ 
       System.out.println(" Printing tree 2  : ");
        t2.printTree( );
        BinarySearchTree<Integer> t4 = t2.copy();
        System.out.println("Copied tree 4 from tree 2 :  ");
        t4.printTree();
        System.out.println("Removing element 20 from the copy of the tree (tree 4)");
        t4.remove(20);            //Making sure that any change in the copy of the tree doesnt affect the original tree
        System.out.println("Original tree (tree 2 ) after removing 20 from the copy of the tree:  ");

        t2.printTree( );
        System.out.println("Removed 20 from copy of the tree (tree4 ) :  ");

        t4.printTree( );


        /* Mirror of tree t2 */
        System.out.println("Printing tree2   ");
        t2.printTree();
        System.out.println("Tree 2 mirror :  ");
        
        t2.mirror().printTree();
        
        /* To check whether the tress are mirror of each other */
        System.out.println("Making tree 4 the mirror of tree 2");
        t4=t2.mirror();
        
        System.out.println("\n Is tree 4 the mirror of tree 2 ? "+t2.isMirror(t4));
        System.out.println("\n Is tree 4 the mirror of tree 1 ? "+t1.isMirror(t4));
        
        /* Single Rotation to the right */ 
        
       int arr5[]={10,5,20,4,6,15,25};
       BinarySearchTree<Integer> t5 = new BinarySearchTree<>( );
              for (int j=0;j<7;j++)
          t5.insert( arr5[j] );
          System.out.println("Before rotating right on  tree 5 on node with value 10 :  \n\n");
          t5.printLevels();
        t5.rotateRight(10);
        System.out.println("After rotating right on  tree 5 on node with value 10: \n\n ");
                 t5.printLevels();
        
        /* Single Rotation to the left */ 

        BinarySearchTree<Integer> t6 = new BinarySearchTree<>( );
              for (int j=0;j<7;j++)
          t6.insert( arr5[j] );
         
          System.out.println("Before rotating tree 6 to the left on node with value 20: \n\n ");
         

          t6.printLevels();
        t6.rotateLeft(20);
        System.out.println("After rotating tree 6 to the left on node with value 20: \n\n ");
            
          t6.printLevels();
        
        /*Level Order printing */
        
        System.out.println("Tree 1 : ");
        t1.printTree();
        System.out.println("Level order of tree 1  ");
        t1.printLevels();
        
                }
}