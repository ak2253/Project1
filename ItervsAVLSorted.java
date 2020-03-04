public class ItervsAVLSorted {
	public static void main(String[] args) { ////compare a Iterative BST to a AVL BST with a sorted array
		AVLIter btree=new AVLIter();
		BSTIter itree=new BSTIter();
		Integer[] ar=CnSArray.getSortedArray(10000);
		long start=System.currentTimeMillis();
		for(int i=0;i<ar.length;i++) {
			btree.insertIter(ar[i]);
		}
		System.out.println("Balance tree max depth: "+btree.maxdepth());
		for(int i=0;i<ar.length;i++) {
			btree.deleteIter(ar[i]);
		}
		long end=System.currentTimeMillis();
		long AVLtime=end-start;
		start=System.currentTimeMillis();
		for(int i=0;i<ar.length;i++) {
			itree.insertIter(ar[i]);
		}
		System.out.println("Normal tree max depth: "+itree.maxdepth());
		for(int i=0;i<ar.length;i++) {
			itree.deleteIter(ar[i]);
		}
		end=System.currentTimeMillis();
		long itertime=end-start;
		System.out.println("Time took for Balance tree: "+AVLtime+" ms");
		System.out.println("Time took for Normal tree: "+itertime+" ms");
		System.out.println("\ntime difference: "+(itertime-AVLtime)+" ms");
	}
}
