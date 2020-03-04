class NodeIter{
	Integer value;
	NodeIter right, left, parent;
	public NodeIter() {
		value=null;
		right=null;
		left=null;
		parent=null;
	}
	public NodeIter(Integer num) {
		value=num;
		right=null;
		left=null;
		parent=null;
	}
}

class BSTIter {
	NodeIter root=new NodeIter();
	public void insertIter(Integer num) { //insert num into BST
		if(root.value==null) { //empty BST
			root.value=num;
		}
		else {
			NodeIter node = new NodeIter(num);
			NodeIter temproot = root;
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
		}
	}
	
	public void deleteIter(Integer num) { //delete num in BST
		NodeIter temproot=root;
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
		root=temproot;
	}
	
	public Integer findNextIter(Integer num) { //finding next node to node num
		if(root.value==null) { //empty BST
			System.out.print("empty tree: ");
			return null;
		}
		NodeIter temproot=root;
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
		NodeIter temproot=root;
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
		NodeIter temproot = root;
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
		NodeIter temproot = root;
		for(;;) {
			if(temproot.right==null) //found largest integer in BST
				return temproot.value;
			temproot=temproot.right;
		}
	}
	
	public Integer maxdepth() { //find max depth of BST
		if(root==null)
			return 0;
		Queue<NodeIter> q=new LinkedList<>();
		Queue<Integer> height=new LinkedList<>();
		q.add(root);
		height.add(0);
		int max=0;
		while(!q.isEmpty()) {
			NodeIter cnode=q.poll();
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
	
	private NodeIter delete(Integer num,NodeIter root) { //delete current root
		if(root!=null) {
			NodeIter tempp=root.parent;
			NodeIter right=root.right;
			NodeIter left=root.left;
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
					NodeIter rtemproot=right;
					while(rtemproot.left.left!=null)
						rtemproot=rtemproot.left;
					NodeIter ltemproot=rtemproot.left;
					ltemproot.left=root.left;
					ltemproot.right=root.right;
					ltemproot.parent=tempp;
					return ltemproot;
				}
			}
		}
		return null;
	}
	
	private NodeIter leftMost(NodeIter root) { //getting smallest number under current root
		for(;;) {
			if(root.left==null)
				break;
			root=root.left;
        }
        return root;
    }
	
	private NodeIter rightMost(NodeIter root) { //getting largest number under current root
		for(;;) {
			if(root.right==null)
				break;
			root=root.right;
		}
		return root;
	}
}

public class BinarySearchTreeIter {
	public static void main(String[] args) {
		//test cases
        BSTIter tree = new BSTIter();
		System.out.println(tree.findNextIter(17));
		System.out.println(tree.findPrevIter(19));
		System.out.println(tree.findMaxIter());
		System.out.println(tree.findMinIter());
		tree.insertIter(10);
		tree.insertIter(5);
		tree.insertIter(5);
		tree.insertIter(6);
		tree.insertIter(7);
		tree.insertIter(20);
		tree.insertIter(12);
		tree.insertIter(11);
		tree.insertIter(16);
		tree.insertIter(19);
		tree.insertIter(18);
		tree.insertIter(17);
		System.out.println(tree.findNextIter(1));
		System.out.println(tree.findPrevIter(19));
		System.out.println(tree.findMaxIter());
		tree.deleteIter(20);
		tree.deleteIter(20);
		System.out.println(tree.findMaxIter());
		System.out.println(tree.findMinIter());
		tree.deleteIter(5);
		tree.deleteIter(5);
		System.out.println(tree.findPrevIter(5));
		System.out.println(tree.findMinIter());
	}
}
