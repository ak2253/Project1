import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class BalNode {
		Integer value;
		Integer height=1;
		Integer bf=0;
		BalNode left, right, parent;
		public BalNode() {
			value=null;
			left=null;
			right=null;
			parent=null;
		}
		public BalNode(Integer num) {
			value=num;
			left=null;
			right=null;
			parent=null;
		}
	}

class AVLIter {
	BalNode root=new BalNode();
	public void insertIter(Integer num) { //insert num into BST
		if(root.value==null) { //empty BST
			root.value=num;
		}
		else {
			BalNode node = new BalNode(num);
			BalNode temproot = root;
			for(;;) {
				if(temproot.value>num) { //traverse left
					if(temproot.left==null) { //basecase to insert num into BST
						temproot.left=node;
						temproot.left.parent=temproot;
						break;
					}
					temproot=temproot.left;
				}
				else if(temproot.value<num){ //traverse right
					if(temproot.right==null) { //basecase to insert num into BST
						temproot.right=node;
						temproot.right.parent=temproot;
						break;
					}
					temproot=temproot.right;
				}
				else { //temproot.value==num case of two same unique integers
					System.out.println("Must insert an unique integer");
					break;
				}
			}
			while(temproot.parent!=null) { //rebalance parent nodes if needed
				temproot=temproot.parent;
				temproot.bf=newBF(temproot.left,temproot.right);
				temproot.height=newHeight(temproot.left,temproot.right)+1;
				temproot=rebalance(temproot,temproot.parent);
			}
			//rebalance root of BST if needed
			temproot.bf=newBF(temproot.left,temproot.right);
			temproot.height=newHeight(temproot.left,temproot.right)+1;
			temproot=rebalance(temproot,null);
		}
	}
	
	public void deleteIter(Integer num) { //delete num in BST
		BalNode temproot=root;
		for(;;) {
			if(temproot==null) { //empty or num not in BST: do nothing
				temproot=root;
				break;
			}
			if(temproot.value>num) {
				if(temproot.left!=null&&temproot.left.value==num) { //base case for deleting found
					temproot.left=delete(num,temproot.left);
					break;
				}
				temproot=temproot.left;
			}
			else if(temproot.value<num) {
				if(temproot.right!=null&&temproot.right.value==num) { //base case for deleting found
					temproot.right=delete(num,temproot.right);
					break;
				}
				temproot=temproot.right;
			}
			else if(temproot.value==num){ //base case found at parent root
				temproot=delete(num, root);
				break;
			}
		}
		while(temproot.parent!=null) { //rebalance parent nodes if needed
			temproot=temproot.parent;
			temproot.bf=newBF(temproot.left,temproot.right);
			temproot.height=newHeight(temproot.left,temproot.right)+1;
			temproot=rebalance(temproot,temproot.parent);
		}
		//rebalance root of BST if needed
		temproot.bf=newBF(temproot.left,temproot.right);
		temproot.height=newHeight(temproot.left,temproot.right)+1;
		temproot=rebalance(temproot,temproot.parent);
		root=temproot;
	}
	
	public Integer findNextIter(Integer num) { //finding next node to node num
		if(root.value==null) { //empty BST
			System.out.print("empty tree: ");
			return null;
		}
		BalNode temproot=root;
		for(;;) { //traversing to find num
			if(temproot==null) { //number not in BST
				System.out.print("number not in tree: ");
				return null;
			}
			else if(temproot.value==num) //num found
				break;
			else if(temproot.value>num)
				temproot=temproot.left;
			else
				temproot=temproot.right;
		}
		if(temproot.right!=null) //basecase with right child
			return leftMost(temproot.right).value;
		for(;;) { //basecase of no right child so backtracking
			if(temproot.parent==null) {//only num in tree 
				System.out.print("no other num exist except "+num+": ");
				return null;
			}
			else if(temproot.value>temproot.parent.value) {
				temproot=temproot.parent;
				continue;
			}
			return temproot.parent.value;
		}
	}
	
	public Integer findPrevIter(Integer num) { //finding node to prev node num
		if(root.value==null) { //empty BST
			System.out.print("empty tree: ");
			return null;
		}
		BalNode temproot=root;
		for(;;) { //traversing to find num
			if(temproot==null) { //number not in BST
				System.out.print("number not in tree: ");
				return null;
			}
			else if(temproot.value==num) //num found
				break;
			else if(temproot.value>num)
				temproot=temproot.left;
			else
				temproot=temproot.right;
		}
		if(temproot.left!=null) //basecase for left child
			return rightMost(temproot.left).value;
		for(;;) { //base for no left child so backtrack
			if(temproot.parent==null) { //only number in tree
				System.out.print("no other num exist except "+num+": ");
				return null;
			}
			else if(temproot.value<temproot.parent.value) {
				temproot=temproot.parent;
				continue;
			}
			return temproot.parent.value;
		}
	}
	
	public Integer findMinIter() { //finding smallest integer in BST
		if(root.value==null) { //empty tree
			System.out.print("empty tree: ");
			return null;
		}
		BalNode temproot = root;
		for(;;) {
			if(temproot.left==null) //found smallest integer
				break;
			temproot=temproot.left;
		}
		return temproot.value;
	}
	
	public Integer findMaxIter() { //finding largest integer in BST
		if(root.value==null) { //empty tree
			System.out.print("empty tree: ");
			return null;
		}
		BalNode temproot = root;
		for(;;) {
			if(temproot.right==null) //found largest integer in BST
				return temproot.value;
			temproot=temproot.right;
		}
	}
	
	public Integer maxdepth() { //calculate max depth of tree
		if(root==null)
			return 0;
		Queue<BalNode> q=new LinkedList<>();
		Queue<Integer> height=new LinkedList<>();
		q.add(root);
		height.add(0);
		int max=0;
		while(!q.isEmpty()) {
			BalNode cnode=q.poll();
			int cheight=height.poll();
			if(cnode.left==null&&cnode.right==null) {
				if(cheight>max)
					max=cheight;
			}
			if(cnode.left!=null) {
				q.add(cnode.left);
				height.add(cheight+1);
			}
			if(cnode.right!=null) {
				q.add(cnode.right);
				height.add(cheight+1);
			}
		}
		return max;
	}
	
	private Integer newHeight(BalNode node1, BalNode node2) { //calculate the height of tree
		if(node1==null&&node2==null)
			return 0;
		if(node1!=null&&node2==null)
			return node1.height;
		if(node1==null&&node2!=null)
			return node2.height;
		if(node1.height>node2.height)
			return node1.height;
		return node2.height;
	}
	
	private Integer newBF(BalNode node1, BalNode node2) { //calculate balance factor
		if(node1==null&&node2==null)
			return 0;
		if(node1!=null&&node2==null)
			return node1.height;
		if(node1==null&&node2!=null)
			return -node2.height;
		return node1.height-node2.height;
	}
	
	private BalNode rebalance(BalNode root, BalNode rparent) { //check to rebalance or not
		if(root.bf>1) { //left heavy
			if(root.left.bf<0) {//left right rotate
				root.left=leftRotate(root.left,root);
			}
			root=rightRotate(root,rparent);
		}
		else if(root.bf<-1) { //right heavy
			if(root.right.bf>0) {//right left rotate
				root.right=rightRotate(root.right,root);
			}
			root=leftRotate(root,rparent);
		}
		return root;
	}
	
	private BalNode leftRotate(BalNode root,BalNode rparent) { //rotate left
		BalNode temproot=root.right;
		if(temproot==null)
			return root;
		root.right=temproot.left;
		temproot.left=root;
		if(root.right!=null)
			root.right.parent=root;
		root.parent=temproot;
		temproot.parent=rparent;
		if(rparent==null)
			this.root=temproot;
		else if(temproot.value>rparent.value)
			rparent.right=temproot;
		else
			rparent.left=temproot;
		//rebalance the changed nodes if needed
		root.bf=newBF(root.left,root.right);
		root.height=newHeight(root.left,root.right)+1;
		temproot.bf=newBF(temproot.left,temproot.right);
		temproot.height=newHeight(temproot.left,temproot.right)+1;
		return temproot;
	}
	
	private BalNode rightRotate(BalNode root,BalNode rparent) { //rotate right
		BalNode temproot=root.left;
		if(temproot==null)
			return root;
		root.left=temproot.right;
		temproot.right=root;
		if(root.left!=null)
			root.left.parent=root;
		root.parent=temproot;
		temproot.parent=rparent;
		if(rparent==null)
			this.root=temproot;
		else if(root.value>rparent.value)
			rparent.right=temproot;
		else
			rparent.left=temproot;
		//rebalance the changed nodes if needed
		root.bf=newBF(root.left,root.right);
		root.height=newHeight(root.left,root.right)+1;
		temproot.bf=newBF(temproot.left,temproot.right);
		temproot.height=newHeight(temproot.left,temproot.right)+1;
		return temproot;
	}
	
	private BalNode delete(Integer num,BalNode root) { //delete current root
		if(root!=null) {
			BalNode tempp=root.parent;
			BalNode right=root.right;
			BalNode left=root.left;
			/*four total cases for deleting
			 * 1. no child
			 * 2. left child but no right child
			 * 3. no left child but right child
			 * 4. left child and right child
			*/
			if(left==null&&right==null)
				return null;
			else if(left!=null&&right==null) {
				left.parent=tempp;
				return left;
			}
			else if(left==null&&right!=null) {
				right.parent=tempp;
				return right;
			}
			else {
				if(root.right.left==null) {
					root.right.left=left;
					root=right;
					root.parent=tempp;
					return root;
				}
				else {
					BalNode rtemproot=right;
					while(rtemproot.left.left!=null)
						rtemproot=rtemproot.left;
					BalNode ltemproot=rtemproot.left;
					ltemproot.left=root.left;
					ltemproot.right=root.right;
					ltemproot.parent=tempp;
					return ltemproot;
				}
			}
		}
		return null;
	}
	
	private BalNode leftMost(BalNode root) { //getting smallest number under current root
		for(;;) {
			if(root.left==null)
				break;
			root=root.left;
        }
        return root;
    }
	
	private BalNode rightMost(BalNode root) { //getting largest number under current root
		for(;;) {
			if(root.right==null)
				break;
			root=root.right;
		}
		return root;
	}
}

public class AVLtree {
	public static void preOrder(BalNode root) { //debugging purposes
		if(root.left!=null)
			preOrder(root.left);
		System.out.print(root.value+" ");
		if(root.right!=null)
			preOrder(root.right);
	}
}
