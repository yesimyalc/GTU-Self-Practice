
public class RecursiveOperations 
{
	public static int stringLength(String str)
	{
		if(str==null || str.equals(""))
			return 0;
		
		return 1+stringLength(str.substring(1));
	}
	
	public static void printString(String str)
	{
		if(str==null || str.equals(""))
			return;
		
		System.out.println(str.charAt(0));
		printString(str.substring(1));
	}
	
	public static void printReversedString(String str)
	{
		if(str==null || str.equals(""))
			return;
		
		printString(str.substring(1));
		System.out.println(str.charAt(0));
	}
	
	public static int factorial(int number)
	{
		if(number==0)
			return 1;
		else if(number<0)
			return -1;
		
		return number*factorial(number-1);
	}
	
	//For positive numbers
	public static double power(double number, double power)
	{
		if(power==0)
			return 1;
		
		return number*power(number, power-1);
	}
	
	//For m>n
	public static double gcd(int m, int n)
	{
		if(m%n==0)
			return n;
		else if(n>m)
			return gcd(n,m);
		
		return gcd(n, m%n);
	}
	
	public static int fibonacci(int n)
	{
		if(n<=2)
			return 1;
		
		return fibonacci(n-1)+fibonacci(n-2);
	}
	
	public static int linearSearch(Object[] items, Object target, int index)
	{
		if(index==items.length)
			return -1;
		else if(items[index].equals(target))
			return index;
		
		return linearSearch(items, target, index+1);
	}
	
	public static int binarySearch(Comparable[] items, Comparable target, int fIndex, int lIndex)
	{
		if(fIndex>lIndex)
			return -1;
		
		int middle=(fIndex+lIndex)/2;
		int compResult=items[middle].compareTo(target);
		
		if(compResult==0)
			return middle;
		else if(compResult<0)
			return binarySearch(items, target, fIndex, middle-1);
		
		return binarySearch(items, target, middle+1, lIndex);
	}
}
