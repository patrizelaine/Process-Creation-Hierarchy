import java.io.File;
import java.io.FileWriter;

public class TestAppV1{
    //pcb of size n
    private static PCB_V1[] pcbArr = new PCB_V1[3]; //create array of size 10 -- make user input later
    private static File resultsFile;

    public static void main(String[] args){
        runTest1(1000);
        //runTest2(1000);
        //runTest3(1000);
        //runTest4(1000);
    }

    //create a new child process, param: parent who's creating child
    private static void create(int processIndex){
        PCB_V1 p = pcbArr[processIndex];
        PCB_V1 q = new PCB_V1(java.util.Arrays.asList(pcbArr).indexOf(p)); //create child & record parent's index

        //double array size if the array is full
        if((pcbArr[pcbArr.length-1])!=null){
            int originalSize = pcbArr.length;
            PCB_V1[] resized = new PCB_V1[originalSize*2];
            System.arraycopy(pcbArr,0,resized, 0, originalSize);
            pcbArr = resized;
            System.out.println(pcbArr.length);
        }
        else {
            for(int i=0; i<pcbArr.length;i++){
                if(pcbArr[i]==null){
                    pcbArr[i]=q;
                    p.addChild(i); //add pcb array index of child to p's linked list
                    break;
                }
            }
        }
        //System.out.println("Create("+q.getParentIndex()+")\t/* creates child #"+p.getYoungestChildIndex()+" of PCB["+processIndex+"] at PCB["+java.util.Arrays.asList(pcbArr).indexOf(q)+"] */");
    }

    //destroy function
    private static void destroy(int processIndex) {
        PCB_V1 p = pcbArr[processIndex];
        //System.out.println("Destroy called on PCB["+processIndex+"]");
        if(!p.getChildren().isEmpty()) {
            for (Integer q : p.getChildren()) {
                destroy(q);
                pcbArr[q] = null;
                //System.out.println("/* Child #" + q + " of PCB[" + processIndex + "] destroyed */");
            }
            p.removeFromLinkedList(); //remove child from linked list
        }
    }

    private static void test1(){
        create(0);
        create(0);
        create(2);
        create(0);
        destroy(0);
    }

    private static void test2(){
        create(0); //create 1st child of pcbArr[0] at pcbArr[1]
        create(0); //create 2nd child of pcbArr[0] at pcbArr[2]
        create(2); //create 1st child of pcbArr[2] at pcbArr[3]
        create(2); //creates 2nd child of pcbArr[2] at pcbArr[4]
        create(2);
        create(3);
        destroy(1);
    }

    private static void test3(){
        create(0);
        create(1);
        create(2);
        create(3);
        destroy(2);
    }

    private static void test4(){
        create(0);
        create(1);
        create(1);
        create(1);
        destroy(0);
    }
    private static void runTest1(int iterations){
        createResultsFile(1);
        //execute test
        for(int i=0; i<iterations; i++){
            PCB_V1 pcb0 = new PCB_V1(-1); //-1 because no parent
            pcbArr[0] = pcb0; //only pcb we have running is pcb0
            //System.out.println("ITERATION "+(i+1)+"/"+iterations);
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
            PCB_V1 pcb0 = new PCB_V1(-1); //-1 because no parent
            pcbArr[0] = pcb0; //only pcb we have running is pcb0
            //System.out.println("ITERATION "+(i+1)+"/"+iterations);
            long start = System.nanoTime();
            test2();
            long end = System.nanoTime();
            long runtime = end-start;
            //System.out.println("Runtime: "+runtime);
            writeResultsToFile(runtime);
        }
        System.out.println(iterations+" iterations completed");
    }

    private static void runTest3(int iterations){
        createResultsFile(3);
        //execute test
        for(int i=0; i<iterations; i++){
            PCB_V1 pcb0 = new PCB_V1(-1); //-1 because no parent
            pcbArr[0] = pcb0; //only pcb we have running is pcb0
            //System.out.println("ITERATION "+(i+1)+"/"+iterations);
            long start = System.nanoTime();
            test3();
            long end = System.nanoTime();
            long runtime = end-start;
            //System.out.println("Runtime: "+runtime);
            writeResultsToFile(runtime);
        }
        System.out.println(iterations+" iterations completed");
    }

    private static void runTest4(int iterations){
        createResultsFile(4);
        //execute test
        for(int i=0; i<iterations; i++){
            PCB_V1 pcb0 = new PCB_V1(-1); //-1 because no parent
            pcbArr[0] = pcb0; //only pcb we have running is pcb0
            //System.out.println("ITERATION "+(i+1)+"/"+iterations);
            long start = System.nanoTime();
            test4();
            long end = System.nanoTime();
            long runtime = end-start;
            //System.out.println("Runtime: "+runtime);
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

    /*private static void test2(){
        System.out.println("***TEST 2***");
        create(0);
        create(3);
        create(7);
        destroy(0);
        create(2);
        destroy(2);
    }*/
}
