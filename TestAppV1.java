import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class TestAppV1{
    private static PCB_V1[] pcbArr = new PCB_V1[100]; //pcb of size n
    private static File resultsFile; //output file for runtime results

    public static void main(String[] args){
        runTest1(1000);
        runTest2(1000);
        runTest3(1000);
        runTest4(1000);
    }
    //create a new child process, which belongs to parent at pcbArr[processIndex]
    private static void create(int processIndex) {
        PCB_V1 p = pcbArr[processIndex];
        PCB_V1 q = new PCB_V1(processIndex); //child q

        //double array size if pcbArr full
        if ((pcbArr[pcbArr.length - 1]) != null) {
            int originalSize = pcbArr.length;
            PCB_V1[] resized = new PCB_V1[originalSize * 2];
            System.arraycopy(pcbArr, 0, resized, 0, originalSize);
            pcbArr = resized;
            System.out.println("Resized PCB Array to " + pcbArr.length);

         //otherwise allocate free pcb
        } else {
            for (int i = 0; i < pcbArr.length; i++) {
                if (pcbArr[i] == null) {
                    pcbArr[i] = q;
                    p.addChild(i); //add child to parent pcb's children
                    break;
                }
            }
            //}
            //System.out.println("Create(" + q.getParentIndex() + ")\t/* creates child #" + p.getYoungestChildIndex() + " of PCB[" + processIndex + "] at PCB[" + java.util.Arrays.asList(pcbArr).indexOf(q) + "] */");
        }
    }

    //destroy a process at pcbArr[processIndex] and all its descendents
    private static void destroy(int processIndex) {
        PCB_V1 p = pcbArr[processIndex]; //pcb, p, at pcbArr[processIndex]
        int parentIndex = p.getParentIndex(); //parent index

        //if p has no children
        if(p.getChildren().isEmpty()){
            //if p is the first pcb in pcbArr
            if(p.getParentIndex()==-1){
                pcbArr[0]=null;
                //System.out.println("Destroyed: "+processIndex);
            }
            //otherwise use parent index to destroy child at processIndex
            else{
                PCB_V1 parentPCB = pcbArr[parentIndex];
                pcbArr[processIndex]=null;
                parentPCB.removeChild(processIndex);
                //System.out.println("Destroyed: "+processIndex);
            }
            }
         //if p has children
        else if(!p.getChildren().isEmpty()) {
            LinkedList<Integer> childrenCopy = (LinkedList<Integer>) p.getChildren().clone();
            ListIterator<Integer> iterator = childrenCopy.listIterator();
            //for each child
            while(iterator.hasNext()){
                int q = iterator.next();
                destroy(q); //recursively destroy all descendents
                pcbArr[q]=null; //free q's pcb in pcbArr
                p.removeChild(q); //deallocate q from p's children
            }
            destroy(processIndex); //call destroy on p
        }
    }

    private static void test1(){
        create(0); //creates 1st child of pcbArr[0] at pcb[1]
        create(0); //creates 2nd child of pcbArr[0] at pcb[2]
        create(2); //creates 1st child of pcbArr[2] at pcb[3]
        create(0); //creates 3rd child of pcbArr[0] at pcb[4]
        destroy(0); //destroys process 0 and processes 1-4
    }

    private static void test2(){
        create(0); //create 1st child of pcbArr[0] at pcbArr[1]
        create(0); //create 2nd child of pcbArr[0] at pcbArr[2]
        create(2); //create 1st child of pcbArr[2] at pcbArr[3]
        create(2); //creates 2nd child of pcbArr[2] at pcbArr[4]
        create(2); //creates 3rd child of pcbArr[2] at pcbArr[5]
        create(3); //creates 1st child of pcbArr[3] at pcbArr[6]
    }

    private static void test3(){
        create(0); //creates 1st child of pcbArr[0] at pcbArr[1]
        create(1); //creates 1st child of pcbArr[1] at pcbArr[2]
        create(2); //creates 1st child of pcbArr[2] at pcbArr[3]
        create(3); //creates 1st child of pcbArr[3] at pcbArr[4]
        destroy(2); //destroys process 2 and processes 3,4
    }

    private static void test4(){
        create(0); //creates 1st child of pcbArr[0] at pcbArr[1]
        create(1); //creates 1st child of pcbArr[1] at pcbArr[2]
        create(1); //creates 2nd child of pcbArr[1] at pcbArr[3]
        create(1); //creates 3rd child of pcbArr[1] at pcbArr[4]
        destroy(0); //destroys process 0 and processes
    }
    private static void runTest1(int iterations){
        createResultsFile(1);
        //execute test
        for(int i=0; i<iterations; i++){
            Arrays.fill(pcbArr,null);
            PCB_V1 pcb0 = new PCB_V1(-1); //-1 because no parent
            pcbArr[0] = pcb0; //only pcb we have running is pcb0
            long start = System.nanoTime();
            test1();
            long end = System.nanoTime();
            long runtime = end-start;
            //System.out.println("Runtime: "+runtime);
            writeResultsToFile(runtime);
        }
        System.out.println(iterations+" iterations completed");
    }

    private static void runTest2(int iterations){
        createResultsFile(2);
        //execute test
        for(int i=0; i<iterations; i++){
            Arrays.fill(pcbArr,null);
            PCB_V1 pcb0 = new PCB_V1(-1); //-1 because no parent
            pcbArr[0] = pcb0; //only pcb we have running is pcb0
            long start = System.nanoTime();
            test2();
            long end = System.nanoTime();
            long runtime = end-start;
            writeResultsToFile(runtime);
        }
        System.out.println(iterations+" iterations completed");
    }

    private static void runTest3(int iterations){
        createResultsFile(3);
        //execute test
        for(int i=0; i<iterations; i++){
            Arrays.fill(pcbArr,null);
            PCB_V1 pcb0 = new PCB_V1(-1); //-1 because no parent
            pcbArr[0] = pcb0; //only pcb we have running is pcb0
            long start = System.nanoTime();
            test3();
            long end = System.nanoTime();
            long runtime = end-start;
            writeResultsToFile(runtime);
        }
        System.out.println(iterations+" iterations completed");
    }

    private static void runTest4(int iterations){
        createResultsFile(4);
        //execute test
        for(int i=0; i<iterations; i++){
            Arrays.fill(pcbArr,null);
            PCB_V1 pcb0 = new PCB_V1(-1); //-1 because no parent
            pcbArr[0] = pcb0; //only pcb we have running is pcb0
            long start = System.nanoTime();
            test4();
            long end = System.nanoTime();
            long runtime = end-start;
            writeResultsToFile(runtime);
        }
        System.out.println(iterations+" iterations completed");
    }
    private static void createResultsFile(int testNumber){
        try{
            File dir = new File(System.getProperty("user.dir"));
            resultsFile = new File(dir.getPath()+"/results_PCB_V1_Test"+testNumber+".csv");
            FileWriter fw = new FileWriter(resultsFile);
            fw.write("Runtime (nsec)\n");
            fw.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private static void writeResultsToFile(long runtime){
        try{
            FileWriter fw = new FileWriter(resultsFile, true);
            fw.write(String.valueOf(runtime)+"\n");
            fw.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
