import java.util.LinkedList;

public class TestAppV1{
    //pcb of size n
    private static PCB_V1[] pcbArr = new PCB_V1[10]; //create array of size 10 -- make user input later
    public static void main(String[] args){
        //start with PCB 0
        PCB_V1 pcb0 = new PCB_V1(-1); //-1 because no parent
        pcbArr[0] = pcb0; //only pcb we have running is pcb0
        //execute test
        test1();
        test2();
    }
    //create a new child process, param: parent who's creating child
    private static void create(int processIndex){
        PCB_V1 p = pcbArr[processIndex];
        PCB_V1 q = new PCB_V1(java.util.Arrays.asList(pcbArr).indexOf(p)); ////create child & record parent's index
        for(int i=0; i<pcbArr.length;i++){
            if(pcbArr[i]==null){
                pcbArr[i]=q;
                p.addChild(i); //add pcb array index of child to p's linked list
                break;
            }
        }
        System.out.println("Create("+q.getParentIndex()+")\t/* creates child #"+p.getYoungestChildIndex()+" of PCB["+java.util.Arrays.asList(pcbArr).indexOf(p)+"] at PCB["+java.util.Arrays.asList(pcbArr).indexOf(q)+"] */");
    }

    //implement destroy(PCB_V1 p)
    private static void destroy(int processIndex) {
        PCB_V1 p = pcbArr[processIndex];
        int pIndex = java.util.Arrays.asList(pcbArr).indexOf(p);
        System.out.println("Destroy called on PCB["+pIndex+"]");
        if(!p.getChildren().isEmpty()) {
            for (Integer q : p.getChildren()) {
                destroy(q);
                pcbArr[q] = null;
                System.out.println("/* Child #" + q + " of PCB[" + pIndex + "] destroyed */");
            }
            p.removeFromLinkedList(); //removes child that just destroyed all its children
        }
        else {
            //PCB_V1 parent = pcbArr[p.getParentIndex()];
            //delete caller process p from parent info
            pcbArr[pIndex] = null;
            //parent.getChildren().remove(processIndex); //remove process index from parent
            System.out.println("/* PCB["+processIndex+"] and its children destroyed */");
        }
    }

    private static void test1(){
        System.out.println("Executing test #1");
        create(0);
        create(0);
        create(2);
        create(0);
        destroy(0);
    }

    private static void test2(){
        System.out.println("Executing test #2");
        create(0);
        create(0);
        create(0);
        create(2);
        create(2);
        destroy(2);
    }
}
