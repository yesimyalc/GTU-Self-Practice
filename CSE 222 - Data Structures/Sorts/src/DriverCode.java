
public class DriverCode
{

	public static void main(String[] args) 
	{
		Integer[] a1= {3,5,1,8,34,67,23,45,90,11,2};
		Integer[] a2= {3,5,1,8,34,67,23,45,90,11,2};
		Integer[] a3= {3,5,1,8,34,67,23,45,90,11,2};
		Integer[] a4= {3,5,1,8,34,67,23,45,90,11,2};
		Integer[] a5= {3,5,1,8,34,67,23,45,90,11,2};
		Integer[] a6= {3,5,1,8,34,67,23,45,90,11,2};
		Integer[] a7= {3,5,1,8,34,67,23,45,90,11,2};
		SelectionSort<Integer> ss=new SelectionSort<Integer>();
		BubbleSort<Integer> bs=new BubbleSort<Integer>();
		InsertionSort<Integer> is=new InsertionSort<Integer>();
		ShellSort<Integer> ss2=new ShellSort<Integer>();
		MergeSort<Integer> ms=new MergeSort<Integer>();
		HeapSort<Integer> hs=new HeapSort<Integer>();
		QuickSort<Integer> qs=new QuickSort<Integer>();
		ss.sort(a1);
		System.out.println("***Selection Sort Test***");
		for(int i=0; i<a1.length; i++)
			System.out.println(a1[i]);
		System.out.println("*************************");
		bs.sort(a2);
		System.out.println("***Bubble Sort Test***");
		for(int i=0; i<a2.length; i++)
			System.out.println(a2[i]);
		System.out.println("*************************");
		is.sort(a3);
		System.out.println("***Insertion Sort Test***");
		for(int i=0; i<a3.length; i++)
			System.out.println(a3[i]);
		System.out.println("*************************");
		ss2.sort(a4);
		System.out.println("***Shell Sort Test***");
		for(int i=0; i<a4.length; i++)
			System.out.println(a4[i]);
		System.out.println("*************************");
		ms.sort(a5);
		System.out.println("***Merge Sort Test***");
		for(int i=0; i<a5.length; i++)
			System.out.println(a5[i]);
		System.out.println("*************************");
		hs.sort(a6);
		System.out.println("***Heap Sort Test***");
		for(int i=0; i<a6.length; i++)
			System.out.println(a6[i]);
		System.out.println("*************************");
		qs.sort(a7);
		System.out.println("***Quick Sort Test***");
		for(int i=0; i<a7.length; i++)
			System.out.println(a7[i]);
		System.out.println("*************************");
		
	}

}
