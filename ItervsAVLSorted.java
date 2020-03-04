public class ItervsAVLSorted {
	public static void main(String[] args) { ////compare a Iterative BST to a AVL BST with a sorted array
		AVLIter btree=new AVLIter();
		BSTIter itree=new BSTIter();
		Integer[] ar=CnSArray.getSortedArray(10000);
		for(int i=0;i<ar.length;i++) {
			btree.insertIter(ar[i]);
		}
		for(int i=0;i<ar.length;i++) {
			itree.insertIter(ar[i]);
		}
		System.out.println("Balance tree max depth: "+btree.maxdepth());
		System.out.println("Normal tree max depth: "+itree.maxdepth());
	}
}
