import java.util.Scanner;

public class Main {

    private static BST<Integer> bst=null;
    private static BSTComparator<Integer> bstComparator=new BSTComparator<Integer>() {
        @Override
        public int compare(Integer a, Integer b) {
            return a-b;
        }
    };

    static void insertMenu(){
        System.out.println("Enter the item you want to insert (Enter -1 for going back to main menu) : ");
        Scanner scanner=new Scanner(System.in);
        try{
            int item=scanner.nextInt();
            if(item==-1)
                mainMenu();
            else{
                if(bst==null)
                    bst=new BST<>(item);
                else {
                    if(item<0)
                        System.out.println("Please enter non-negative integer");
                    try {
                        bst.insertItem(item, bstComparator);
                        System.out.println("Item inserted successfully...");
                    } catch (BSTException e) {
                        System.out.println(e.getMessage());
                    }
                }
                insertMenu();
            }

        }catch(Exception e){
            System.out.println("Invalid input...");
            insertMenu();
        }
    }
    static void searchMenu(){
        System.out.println("Enter the item you want to search (Enter -1 for going back to main menu) : ");
        Scanner scanner=new Scanner(System.in);
        try{
            int item=scanner.nextInt();
            if(item==-1)
                mainMenu();
            else{
                if(bst==null)
                    System.out.println(item+" has not been found");
                else{
                    boolean res=bst.searchItem(item,bstComparator);
                    if(res) System.out.println(item+" has been found");
                    else System.out.println(item+" has not been found");
                }
                searchMenu();            }

        }catch(Exception e){
            System.out.println("Invalid input...");
            searchMenu();
        }
    }
    static void deleteMenu(){
        System.out.println("Enter the item you want to delete (Enter -1 for going back to main menu) : ");
        Scanner scanner=new Scanner(System.in);
        try{
            int item=scanner.nextInt();
            if(item==-1)
                mainMenu();
            else{
                if(bst!=null)
                    bst.deleteItem(item,false);
                System.out.println("command executed...");
                deleteMenu();            }

        }catch(Exception e){
            System.out.println("Invalid input...");
            deleteMenu();
        }
    }
    static void depthMenu(){
        System.out.println("Enter the item you want to get depth of (Enter -1 for going back to main menu) : ");
        Scanner scanner=new Scanner(System.in);
        try{
            int item=scanner.nextInt();
            if(item==-1)
                mainMenu();
            else{
                if(bst==null) {
                    System.out.println("Item not found...");
                }
                else {
                    int res=bst.getItemDepth(item);
                    if(res==-1)
                        System.out.println("not found...");
                    else
                        System.out.println("depth : "+res);
                }
                depthMenu();            }

        }catch(Exception e){
            System.out.println("Invalid input...");
            depthMenu();
        }
    }

    static void successorMenu(){
        System.out.println("Enter the item you want to get in order successor of (Enter -1 for going back to main menu) : ");
        Scanner scanner=new Scanner(System.in);
        try{
            int item=scanner.nextInt();
            if(item==-1)
                mainMenu();
            else{
                if(bst==null) {
                    System.out.println("successor not found...");
                }
                else {
                    int res=bst.getInOrderSuccessor(item,-1);
                    if(res==-1)
                        System.out.println("successor not found...");
                    else
                        System.out.println("in order successor : "+res);
                }
                successorMenu();            }

        }catch(Exception e){
            System.out.println("Invalid input...");
            depthMenu();
        }
    }

    static void predecessorMenu(){
        System.out.println("Enter the item you want to get in order predecessor of (Enter -1 for going back to main menu) : ");
        Scanner scanner=new Scanner(System.in);
        try{
            int item=scanner.nextInt();
            if(item==-1)
                mainMenu();
            else{
                if(bst==null) {
                    System.out.println("predecessor not found...");
                }
                else {
                    int res=bst.getInOrderPredecessor(item,-1);
                    if(res==-1)
                        System.out.println("predecessor not found...");
                    else
                        System.out.println("in order predecessor : "+res);
                }
                predecessorMenu();            }

        }catch(Exception e){
            System.out.println("Invalid input...");
            depthMenu();
        }
    }

    public static void mainMenu(){
        System.out.println("1. Insert Item");
        System.out.println("2. Search Item");
        System.out.println("3. Get In Order Successor");
        System.out.println("4. Get In Order Predecessor");
        System.out.println("5. Delete Item");
        System.out.println("6. Get Item Depth");
        System.out.println("7. Get Max Item");
        System.out.println("8. Get Min Item");
        System.out.println("9. Get Height");
        System.out.println("10. Print In Order");
        System.out.println("11. Print Pre Order");
        System.out.println("12. Print Post Order");
        System.out.println("13. Get Size");

        System.out.println("Enter a menu : ");
        Scanner scanner=new Scanner(System.in);
        try{
            int menu=scanner.nextInt();
            switch (menu){
                case 1:
                    insertMenu();
                    break;
                case 2:
                    searchMenu();
                    break;
                case 3:
                    successorMenu();
                    break;
                case 4:
                    predecessorMenu();
                    break;
                case 5:
                    deleteMenu();
                    break;
                case 6:
                    depthMenu();
                    break;
//                System.out.println("7. Get Max Item");
//                System.out.println("8. Get Min Item");
//                System.out.println("9. Get Height");
//                System.out.println("10. Print In Order");
//                System.out.println("11. Print Pre Order");
//                System.out.println("12. Print Post Order");
//                System.out.println("13. Get Size");
                case 7:
                    if(bst==null) System.out.println("Tree is empty...");
                    else System.out.println(bst.getMaxItem());
                    break;
                case 8:
                    if(bst==null) System.out.println("Tree is empty...");
                    else System.out.println(bst.getMinItem());
                    break;
                case 9:
                    if(bst==null) System.out.println("Tree is empty...");
                    else System.out.println(bst.getHeight());
                    break;
                case 10:
                    if(bst==null) System.out.println("Tree is empty...");
                    else bst.printInOrder();
                    break;
                case 11:
                    if(bst==null) System.out.println("Tree is empty...");
                    else bst.printPreOrder();
                    break;
                case 12:
                    if(bst==null) System.out.println("Tree is empty...");
                    else bst.printPostOrder();
                    break;
                case 13:
                    if(bst==null) System.out.println("Tree is empty...");
                    else System.out.println(bst.getSize());
                    break;
                default:
                    System.out.println("Invalid input...");
                    mainMenu();
            }
        }catch(Exception e){
            System.out.println("Invalid input...");
            mainMenu();
        }
        System.out.println("Press enter to load menus again...");
        scanner.nextLine();
        scanner.nextLine();
        mainMenu();
    }

    public static void main(String[] args) throws BSTException {
        mainMenu();
    }
}
