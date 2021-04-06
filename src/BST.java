public class BST<T> {
    T value;
    BST<T> parent;
    BST<T> left;
    BST<T> right;

    public BST(T value){
        this.value=value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public BST<T> getParent() {
        return parent;
    }

    public void setParent(BST<T> parent) {
        this.parent = parent;
    }

    public BST<T> getLeft() {
        return left;
    }

    public void setLeft(BST<T> left) {
        this.left = left;
    }

    public BST<T> getRight() {
        return right;
    }

    public void setRight(BST<T> right) {
        this.right = right;
    }

    public void insertItem(T value, BSTComparator<T> bstComparator) throws BSTException{
        if(value.equals(this.value))
            throw new BSTException("duplicate value");
        if(bstComparator.compare(value,this.value)<0 && left==null){
            BST<T> leftChild=new BST<>(value);
            leftChild.setParent(this);
            left=leftChild;
            return;
        }else if(bstComparator.compare(value,this.value)>0 && right==null){
            BST<T> rightChild=new BST<>(value);
            rightChild.setParent(this);
            right=rightChild;
            return;
        }
        if (bstComparator.compare(value, this.value) < 0) {
            left.insertItem(value, bstComparator);
            return;
        } else {
            right.insertItem(value, bstComparator);
            return;
        }
    }

    public boolean searchItem(T value,BSTComparator<T> bstComparator){
        if(value.equals(this.value))
            return true;
        if(bstComparator.compare(value,this.value)<0 && left==null)
            return false;
        if(bstComparator.compare(value,this.value)>0 && right==null)
            return false;
        if(bstComparator.compare(value,this.value)<0)
            return left.searchItem(value,bstComparator);
        if(bstComparator.compare(value,this.value)>0)
            return right.searchItem(value,bstComparator);
        return false;
    }

    public T getMaxItem(){
        T res=value;
        BST<T> currNode=right;
        while(currNode!=null){
            res=currNode.value;
            currNode=currNode.right;
        }
        return res;
    }

    public T getMinItem(){
        T res=value;
        BST<T> currNode=left;
        while(currNode!=null){
            res=currNode.value;
            currNode=currNode.left;
        }
        return res;
    }

    public BST getMinNode(){
        if(left==null) return this;
        return left.getMinNode();
    }

    public void deleteItem(T value,boolean isLeft){
        if(value==this.value){
            if(left==null && right==null){
                if(parent!=null){
                    if(isLeft)parent.left=null;
                    else parent.right=null;
                    parent=null;
                }
            }
            else if(left==null){
                this.value=value;
                right.parent=parent;
                if(parent!=null){
                    if(isLeft)parent.left=right;
                    else parent.right=right;
                }
            }
            else if(right==null){
                this.value=value;
                left.parent=parent;
                if(parent!=null){
                    if(isLeft)parent.left=left;
                    else parent.right=left;
                }
            }else{
                BST<T> replaceNode=right.getMinNode();
                this.value=replaceNode.value;
                if(this.value!=right.getValue()){
                    if(replaceNode.right==null)replaceNode.parent.left=null;
                    else replaceNode.parent.left=replaceNode.right;
                }
                else {
                    if(replaceNode.right==null) right=null;
                    else right=replaceNode.right;
                }
                replaceNode.parent=null;
            }
            return;
        }
        if(left!=null)left.deleteItem(value,true);
        if(right!=null)right.deleteItem(value,false);
    }

    public int getSize(){
        int sum=1;
        if(left!=null)sum+=left.getSize();
        if(right!=null)sum+=right.getSize();
        return sum;
    }

    int max(int a, int b){
        if(a>b) return a;
        return b;
    }

    public int getHeight(){
        if(left==null && right==null)return 0;
        if(left==null) return 1+right.getHeight();
        if(right==null) return 1+left.getHeight();
        return max(1+left.getHeight(), 1+right.getHeight());
    }

    public int getItemDepth(T item){
        if(value==item)return 0;
        if(left==null && right==null)return -1;
        if(right!=null){
            int rightVal=right.getItemDepth(item);
            if(rightVal!=-1)
                return 1+rightVal;
        }
        if(left!=null){
            int leftVal=left.getItemDepth(item);
            if(leftVal!=-1)
                return 1+leftVal;
        }
        return -1;
    }

    public void printInOrder(){
        if(left!=null)left.printInOrder();
        System.out.println(value);
        if(right!=null)right.printInOrder();
    }

    public void printPreOrder(){
        System.out.println(value);
        if(left!=null)left.printPreOrder();
        if(right!=null)right.printPreOrder();
    }

    public void printPostOrder(){
        if(left!=null)left.printPostOrder();
        if(right!=null)right.printPostOrder();
        System.out.println(value);
    }

    public T getInOrderSuccessor(T item,T invalid){
        if(item==value){
            if(right!=null)return right.getMinItem();
            BST tmpBst=this;
            while (tmpBst.parent!=null && !tmpBst.equals(tmpBst.parent.left))
                tmpBst=tmpBst.parent;
            if(tmpBst.parent!=null)return (T) tmpBst.parent.value;
            return invalid;

        }
        if(left!=null){
            T leftVal=left.getInOrderSuccessor(item,invalid);
            if(!leftVal.equals(invalid))return leftVal;
        }
        if(right!=null){
            T rightVal=right.getInOrderSuccessor(item,invalid);
            if(!rightVal.equals(invalid))return rightVal;
        }
        return invalid;
    }

    public T getInOrderPredecessor(T item, T invalid){
        if(item==value){
            if(left!=null)return left.getMaxItem();
            BST tmpBst=this;
            while (tmpBst.parent!=null && !tmpBst.equals(tmpBst.parent.right))
                tmpBst=tmpBst.parent;
            if(tmpBst.parent!=null)return (T) tmpBst.parent.value;
            return invalid;
        }
        if(left!=null){
            T leftVal=left.getInOrderPredecessor(item,invalid);
            if(!leftVal.equals(invalid))return leftVal;
        }
        if(right!=null){
            T rightVal=right.getInOrderPredecessor(item,invalid);
            if(!rightVal.equals(invalid))return rightVal;
        }
        return invalid;
    }
}
