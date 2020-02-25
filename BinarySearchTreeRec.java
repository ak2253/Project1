class NodeRec {
	Integer value;
	NodeRec right, left, parent;
	public NodeRec() {
		value=null;
		right=null;
		left=null;
		parent=null;
	}
	public NodeRec(Integer num) {
		value=num;
		right=null;
		left=null;
		parent=null;
	}
}

class BSTR {
	NodeRec root=new NodeRec();
	public void insertRec(NodeRec root, Integer num) { //insert num into BST
		if(root.value==null) { //parent root
			root.value=num;
		}
		else if(root.value>num) { //go to left side of BST
			if(root.left==null) {
				//insert into tree
				NodeRec node=new NodeRec(num);
				root.left=node;
				root.left.parent=root;
			}
			else
				insertRec(root.left,num);
		}
		else if(root.value<num) { //go to right side of BST
			if(root.right==null) {
				//insert into BST
				NodeRec node=new NodeRec(num);
				root.right=node;
				root.right.parent=root;
			}
			else
				insertRec(root.right,num);
		}
		else //root.value==num two same unique numbers
			System.out.println("Must insert an unique integer");
	}
	
	public void deleteRec(Integer num) { //find and delete num in BST
		root=deleteRec(root,num);
	}
	public Integer findNextRec(NodeRec root, Integer num) { //finding node next to node num
		if(root==null||root.value==null) { //empty or number not in BST
			System.out.print("number not in tree: ");
			return null;
		}
		else if(root.value==num) { //base case for finding next num
			if(root.right!=null) //right child case
				return leftMost(root.right).value;
			//no right child case
			return nextHelper(root);
		}
		else { //finding num in BST
			if(root.value>num)
				return findNextRec(root.left, num);
			else
				return findNextRec(root.right, num);
		}
	}
	
	public Integer findPrevRec(NodeRec root, Integer num) { //finding node prev to node num
		if(root==null||root.value==null) { //empty or number not in BST
			System.out.print("number not in tree: ");
			return null;
		}
		else if(root.value==num) { //base case for finding prev num
			if(root.left!=null) //left child case
				return rightMost(root.left).value;
			//no left child case
			return prevHelper(root);
		}
		else { //finding num in BST
			if(root.value>num)
				return findPrevRec(root.left, num);
			else
				return findPrevRec(root.right, num);
		}
	}
	
	public Integer findMinRec(NodeRec root) { //finding smallest integer in BST
		if(root.value==null) //empty BST
			System.out.print("empty tree: ");
		if(root.left==null) //base case of smallest integer
			return root.value;
		return findMinRec(root.left); //traverse left
	}
	
	public Integer findMaxRec(NodeRec root) { //finding largest integer in BST
		if(root.value==null) //empty BST
			System.out.print("empty tree: ");
		if (root.right == null) //base case of largest integer
			return root.value;
		return findMaxRec(root.right); //traverse right
	}
	
	private NodeRec deleteRec(NodeRec root, Integer num) {
		if(root!=null) { //check if empty tree
			if(root.value==num) { //base case for deleting
				if(root.left==null) //no left child
					return root.right;
				else if(root.right==null) //no right child
					return root.left;
				//shift rest of BST accordingly
				root.value=leftMost(root.right).value;
				root.right=deleteRec(root.right,root.value);
			}
			else { //finding num in BST
				if(root.value>num)
					root.left=deleteRec(root.left,num);
				else
					root.right=deleteRec(root.right,num);
			}
		}
		return root;
	}
	
	private Integer nextHelper(NodeRec root) { //finding next num through backtracking
		if(root.parent==null)//parent root
			return null;
		else if(root.value>root.parent.value)
			return nextHelper(root.parent);
		return root.parent.value;
	}
	
	private NodeRec leftMost(NodeRec root) { //getting smallest integer under node
		if(root.left==null)
			return root;
		return leftMost(root.left);
	}
	
	private Integer prevHelper(NodeRec root) { //finding next num through backtracking
		if(root.parent==null) //parent root
			return null;
		else if(root.value<root.parent.value)
			return prevHelper(root.parent);
		return root.parent.value;
	}
	
	private NodeRec rightMost(NodeRec root) { //getting largest integer under node
		if(root.right==null)
			return root;
		return rightMost(root.right);
	}
}

public class BinarySearchTreeRec {
	public static void main(String[] args) {
		//test cases
		BSTR tree = new BSTR();
		System.out.println(tree.findMaxRec(tree.root));
		System.out.println(tree.findMinRec(tree.root));
		System.out.println(tree.findNextRec(tree.root,10));
		System.out.println(tree.findPrevRec(tree.root,10));
		tree.insertRec(tree.root,10);
		tree.insertRec(tree.root,5);
		tree.insertRec(tree.root,6);
		tree.insertRec(tree.root,7);
		tree.insertRec(tree.root,20);
		tree.insertRec(tree.root,12);
		tree.insertRec(tree.root,11);
		tree.insertRec(tree.root,16);
		tree.insertRec(tree.root,7);
		tree.insertRec(tree.root,19);
		tree.insertRec(tree.root,18);
		tree.insertRec(tree.root,17);
		tree.insertRec(tree.root,17);
		System.out.println(tree.findNextRec(tree.root,10));
		System.out.println(tree.findPrevRec(tree.root,10));
		tree.deleteRec(7);
		tree.deleteRec(7);
		System.out.println(tree.findPrevRec(tree.root,10));
		System.out.println(tree.findMaxRec(tree.root));
		System.out.println(tree.findMinRec(tree.root));
		tree.deleteRec(6);
		tree.deleteRec(5);
		tree.deleteRec(20);
		System.out.println(tree.findPrevRec(tree.root,6));
		System.out.println(tree.findMaxRec(tree.root));
		System.out.println(tree.findMinRec(tree.root));
	}
}
