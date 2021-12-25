
public class test {

	public static void main(String[] args) {
		Integer arrayA[][]= {
				{0,6}, {1,12}, {2,4}, {3,3}, {4,11}, {5,56}, {6,21}};
		Integer arrayB[][]={
			{0,4}, {1,6}, {2,12}, {3,3}, {4,56}, {5,21}, {6,11}};
		
		System.out.println("The Gifts:");
		for(int j=0; j<arrayA.length; ++j) 
			System.out.print("(Size:"+arrayA[j][1]+", Gift:"+arrayA[j][0]+") ");
		System.out.println();
		
		System.out.println("The Boxes:");
		for(int j=0; j<arrayB.length; ++j) 
			System.out.print("(Size:"+arrayB[j][1]+", Box:"+arrayB[j][0]+") ");
		System.out.println();
		System.out.println();
		
		arrayB=giftBoxSort(arrayB, arrayA, 0);
		arrayA=giftBoxSort(arrayA, arrayB, 0);
		
		Integer arrayC[][]=new Integer[arrayA.length][3];
		for(int i=0; i<arrayA.length; ++i)
		{
			arrayC[i][0]=arrayA[i][0];
			arrayC[i][1]=arrayB[i][0];
			
			if(arrayA[i][1]==arrayB[i][1])
				arrayC[i][2]=arrayA[i][1];
		}
	
		
		System.out.println("Matches");
		for(int i=0; i<arrayC.length; ++i)
			System.out.println("(Gift no: "+arrayC[i][0]+", Box no: "+arrayC[i][1]+", Size: "+arrayC[i][2]+")");

	}
	
	public static Integer[][] giftBoxSort(Integer[][] array, Integer[][] pivotArray, int count)
	{
		if(count==pivotArray.length)
			return array;
		else
		{
			array=(Integer[][])sort(array, pivotArray[count][1]);
			count++;
			return giftBoxSort(array, pivotArray, count);
		}
	}
	
	public static Integer[][] sort(Integer[][] array, Integer pivot)
	{
		if(array.length<2)
			return array;
		
		partition(array, 0, array.length-1, pivot);
		return array;
	}
	
	public static void swap(Integer[][] table, int pIndex, int cIndex)
	{
		Integer tempF=table[pIndex][0];
		Integer tempS=table[pIndex][1];
		table[pIndex][0]=table[cIndex][0];
		table[pIndex][1]=table[cIndex][1];
		table[cIndex][0]=tempF;
		table[cIndex][1]=tempS;
	}

	
	public static void partition(Integer[][] table, int start, int end, Integer pivot)
	{
		int up=start;
		int down=end;
		
		do
		{
			while(up<down && table[up][1].compareTo(pivot)<0)
				up++;
			while(table[down][1].compareTo(pivot)>0)
				down--;
			
			if(up<down)
				swap(table, up, down);
		}
		while(up<down);
		
	}

}
