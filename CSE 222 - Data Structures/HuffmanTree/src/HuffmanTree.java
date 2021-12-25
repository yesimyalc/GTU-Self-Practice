import java.io.PrintStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class HuffmanTree implements Serializable
{
	protected BinaryTree<HuffData> huffTree;
	
	public static class HuffData implements Serializable
	{
		private double weight;
		private Character symbol;
		
		public HuffData(double weight, Character symbol)
		{
			this.weight=weight;
			this.symbol=symbol;
		}
		
		public HuffData(double weight)
		{
			this.weight=weight;
			this.symbol=null;
		}
		
		public Character getChar() {return symbol;}
		public double getWeight() {return weight;}
	}
	
	private static class CompareHuffmanTrees implements Comparator<BinaryTree<HuffData>>
	{
		public int compare(BinaryTree<HuffmanTree.HuffData> o1, BinaryTree<HuffmanTree.HuffData> o2) 
		{
			double wLeft=o1.getRoot().getWeight();
			double wRight=o2.getRoot().getWeight();
			return Double.compare(wLeft, wRight);
		}
	}
	
	public void buldTree(HuffData[] symbols)
	{
		Queue<BinaryTree<HuffData>> theQueue=new PriorityQueue<BinaryTree<HuffData>>(symbols.length, new CompareHuffmanTrees());
		for(HuffData nextSymbol: symbols)
		{
			BinaryTree<HuffData> bt=new BinaryTree<HuffData>(nextSymbol, null, null);
			theQueue.offer(bt);
		}
		
		while(theQueue.size()>1)
		{
			BinaryTree<HuffData> first=theQueue.poll();
			BinaryTree<HuffData> second=theQueue.poll();
			
			double wl=first.getRoot().getWeight();
			double wr=second.getRoot().getWeight();
			HuffData sum=new HuffData(wl+wr);
			BinaryTree<HuffData> newTree=new BinaryTree<HuffData>(sum, first, second);
			theQueue.offer(newTree);
		}
		
		huffTree=theQueue.poll();
	}
	
	private void printCode(PrintStream out, String code, BinaryTree<HuffData> bt)
	{
		if(bt.getRoot().getChar()!=null && bt.getRoot().getChar().equals(' '))
			out.println("Space: "+code);
		else if(bt.getRoot().getChar()!=null)
			out.println(bt.getRoot().getChar()+": "+code);
		else
		{
			printCode(out, code+"0", bt.getLeftSubtree());
			printCode(out, code+"1", bt.getRightSubtree());
		}
	}
	
	public void printCode(PrintStream out)
	{
		printCode(out, "", huffTree);
	}
	
}
