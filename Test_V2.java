import java.io.File;
import java.io.FileWriter;

public class Test_V2 {
	
	static int length = 100;
	private static PCB_V2[] pcbArr2 = new PCB_V2[length];
	private static File resultsFile2;
	
	public static void main(String[] args)
	{
		runTest(1000);
	}
	
	private static void create(int parentIndex)
	{
		int checkChild = pcbArr2[parentIndex].getFirst();
		for(int i=0; i<pcbArr2.length; i++)
		{
			if(i==parentIndex)
			{
				if(checkChild == -1)	// runs thru this code if the PCB doesn't have any children
				{
					for(int j=0; j<pcbArr2.length; j++)
					{
						if(pcbArr2[length-1] != null)
						{
							length = length*2;
						}
						if(pcbArr2[j]==null)
						{
							pcbArr2[parentIndex].setFirst(j);
							pcbArr2[j] = new PCB_V2(parentIndex, -1, -1, -1);
							System.out.println("Successfully created a child for process " + parentIndex);
							break;
						}
					}
				}
				else if(pcbArr2[checkChild].getOlder() == -1)	// runs thru this code if the PCB only has one child
				{
					for(int j=0; j<pcbArr2.length; j++)
					{
						if(pcbArr2[length-1] != null)
						{
							length = length*2;
						}
						if(pcbArr2[j]==null)
						{
							pcbArr2[checkChild].setOlder(j);	// updates the f_c older child
							pcbArr2[j] = new PCB_V2(parentIndex, -1, checkChild, -1);	// creates a new child
							System.out.println("Successfully created a child for process " + parentIndex);
							break;
						}
					}
				}
				else if(pcbArr2[checkChild].getOlder() != -1)	// if multiple children
				{
					int olderChild = pcbArr2[checkChild].getOlder();
					checkOlderChild(parentIndex, olderChild);
				}
			}
		}
	}
	
	private static void checkOlderChild(int parentIndex, int olderChild)
	{
		int updatedCheck = pcbArr2[olderChild].getOlder();
		if(updatedCheck==-1)
		{
			for(int j=0; j<pcbArr2.length; j++)
			{
				if(pcbArr2[j]==null)
				{
					pcbArr2[olderChild].setOlder(j);	// updates the f_c older child
					pcbArr2[j] = new PCB_V2(parentIndex, -1, olderChild, -1);	// creates a new child
					System.out.println("Successfully created a child for process " + parentIndex);
					break;
				}
			}
		}
		else
		{
			checkOlderChild(parentIndex, updatedCheck);
		}
	}
	
	private static void destroy(int destroyIndex)
	{
		int firstChild = pcbArr2[destroyIndex].getFirst();
		if(firstChild != -1)	// if the destroyed index has a first child, then destroy the first child
		{
			pcbArr2[destroyIndex].setFirst(pcbArr2[firstChild].getOlder());
	
			destroy(firstChild);	
			destroy(destroyIndex);
		}
		else
		{
			pcbArr2[destroyIndex] = null;
			System.out.println("Successfully destroyed process " + destroyIndex);
		}
	}
	
	private static void test1()
	{
		System.out.println("Executing Test #1");
		create(0);
		create(0);
		create(2);
		create(0);
		destroy(0);
	}
	
	private static void runTest(int iterations)
	{
		createResultsFile(1);
		for(int i=0; i<iterations; i++)
		{
			PCB_V2 pcb0 = new PCB_V2(-1, -1, -1, -1);	// creating first PCB
			pcbArr2[0] = pcb0;
			long start1 = System.nanoTime();
			test1();
			long end1 = System.nanoTime();
			long runtime1 = end1-start1;
			writeResultsToFile(runtime1);
		}
	}
	
	private static void createResultsFile(int testNumber)
	{
		try{
            File dir = new File(System.getProperty("user.dir"));
            resultsFile2 = new File(dir.getPath()+"/results_PCB_V1_Test"+testNumber+".csv");
            FileWriter fw = new FileWriter(resultsFile2);
            fw.write("Runtime (nsec)\n");
            fw.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
	}
	
	private static void writeResultsToFile(long runtime)
	{
		try{
            FileWriter fw = new FileWriter(resultsFile2, true);
            fw.write(String.valueOf(runtime)+"\n");
            fw.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
	}
}