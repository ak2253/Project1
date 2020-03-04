public class RecvsAVL {
	public static void main(String[] args) { //compare recursion BST to a AVL BST
		BSTR rtree=new BSTR();
		AVLIter btree=new AVLIter();
		Integer[] ar=CnSArray.getRandomArray(10000);
		for(int i=0;i<ar.length;i++) {
			btree.insertIter(ar[i]);
		}
		for(int i=0;i<ar.length;i++) {
			rtree.insertRec(rtree.root,ar[i]);
		}
	}
}
