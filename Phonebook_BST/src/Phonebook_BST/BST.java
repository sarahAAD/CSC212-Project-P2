package Phonebook_BST;
enum Order{
    preOrder,inOrder, postOrder
}
enum Relative{
        Root,Parent,LeftChild,RightChild
}   

class BSTNode{
    public String key; 
    public Contact data;
    public BSTNode left,right;
    
    public BSTNode(Contact val){
        data = val;
        left=right=null;
    }
    
    public BSTNode(String k, BSTNode l, BSTNode r){
        key = k;
        left=l;
        right=r;
    }
}
public class BST {

    
   private BSTNode root , current;
   
   public BST(){
       root=current=null;
   }
   

   
   public boolean empty(){
       return root==null;
   }
   
   public void inOrder(Order ord){
               inOrder(root);

   }
   

   
   public void inOrder(BSTNode p){ // this method is for printing the contacts LNR 
       if(p!=null)
       {
          inOrder(p.left);
          System.out.println(p.data.toString());
          inOrder(p.right);  
       }
   }
   
   
      public void insertContact(Contact val){
         root=insertContact(root,val);
      }
   
   private BSTNode insertContact(BSTNode root,Contact val){
       if(root==null){ //base case 
           BSTNode newNode = new BSTNode(val);
           current = newNode;
           return newNode;
       }
       
       if (val.compareTo(root.data)<0)
           root.left=insertContact(root.left,val);
       else root.right=insertContact(root.right,val);

   
   return root;        
}
   
   
   private boolean findkey(BSTNode  root , Contact data){
       if (root == null)
           return false;
    else if (data.getName().equalsIgnoreCase(root.data.getName()))
           return true;
    else if (((Contact)data).getName().compareToIgnoreCase(((Contact)root.data).getName())<0 )
           return findkey(root.left,data);
    else return findkey(root.right,data);
   }
   
   public boolean findkey(Contact data){
       return findkey(root,data);
   }
   


private boolean isExist(BSTNode root , String name,String phoneNum){
if (root ==null){
    return false;
}

if (root.data.getPhoneNumber().equalsIgnoreCase(phoneNum)||root.data.getName().equalsIgnoreCase(name))
    return true;
    
    boolean right = isExist(root.right,name,phoneNum);
    boolean left = isExist(root.left,name,phoneNum);
    return right||left;
}

public boolean isExist(Contact data){
    return isExist(root, data.getName(),data.getPhoneNumber());
}

private boolean Search(BSTNode p,String n , String data){
    boolean found= false;
    switch(n){
        case "PhoneNumber":
          if(p==null)
              return false;
       
          if (p.data.getPhoneNumber().equals(data)){
          System.out.println(p.data.toString());
          return true;}
          Search(p.left,n,data);
          Search(p.right,n,data);
          break;
          
        case "FirstName":
            if(p ==null)
                return false;
            Search(p.left,n,data);
           int index= p.data.getName().indexOf(" ");
           if(index != -1){
            String firstName = p.data.getName().substring(0,index );
            if (firstName.equalsIgnoreCase(data))
            System.out.println(p.data.toString());
           found = true;}
            Search(p.right,n,data);
            break;
            
        case "Email":
          if(p==null)
              return false;
       
          Search(p.left,n,data);
          if (p.data.getEmail().equals(data)){
          System.out.println(p.data.toString());
          found = true;}
          Search(p.right,n,data);
          break;  
          
        case "Birthday":
          if(p==null)
              return false;
       
          Search(p.left,n,data);
          if (p.data.getBirthday().equals(data)){
          System.out.println(p.data.toString());
          found = true;}
          Search(p.right,n,data);
          break;  
          
        case "Name":
          if(p==null)
              return false;
       
          if (p.data.getName().equalsIgnoreCase(data)){
          System.out.println(p.data.toString());
          return true;}
          Search(p.left,n,data);
          Search(p.right,n,data);
          break;
          
        case "Address":
            if(p==null)
              return false ;
       
          Search(p.left,n,data);
          if (p.data.getAddress().equals(data)){
          System.out.println(p.data.toString());
          found = true;}
          Search(p.right,n,data);
          break;  
       } 
    return found;
    }

public boolean Search(String n, String data){
    return Search(root,n,data);
}
}