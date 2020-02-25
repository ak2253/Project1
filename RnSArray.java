import java.util.Random;

public class RnSArray {
	private static void swap(int i, int j, Integer[] ar) {
		Integer temp=ar[i];
		ar[i]=ar[j];
		ar[j]=temp;
	}
	
	public static Integer[] getRandomArray(int n) { //get random array
		Random rand=new Random();
		Integer[] ar=getSortedArray(n); //create array
		for(int i=0;i<n;i++) { // randomize array
			int index=rand.nextInt(n);
			swap(i,index,ar);
		}
		return ar;
	}
	
	public static Integer[] getSortedArray(int n) { //get sorted array
		Integer[] ar=new Integer[n];
		int temp=n;
		for(int i=0;i<n;i++) {
			ar[i]=temp;
			temp-=1;
		}
		return ar;
	}
	
	public static void main(String[] args) {
		Random rand=new Random();
		Integer[] rar=getRandomArray(10);
		for(int i=0;i<rar.length;i++)
			System.out.print(rar[i]+" ");
		Integer[] sar=getSortedArray(10);
		System.out.println();
		for(int i=0;i<sar.length;i++)
			System.out.print(sar[i]+" ");
	}
}
