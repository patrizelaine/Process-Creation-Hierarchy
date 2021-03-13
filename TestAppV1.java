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
    private static void create(PCB_V1 p){
        PCB_V1 q = new PCB_V1(java.util.Arrays.asList(pcbArr).indexOf(p)); ////create child & record parent's index
        p.addChild(java.util.Arrays.asList(pcbArr).indexOf(p)); //add parent index
        for(int i=0; i<pcbArr.length;i++){
            if(pcbArr[i]==null){
                pcbArr[i]=q;
                break;
            }
        }
        System.out.println("Create("+q.getParentIndex()+")\t/* creates child #"+p.getYoungestChildIndex()+" of PCB["+java.util.Arrays.asList(pcbArr).indexOf(p)+"] at PCB["+java.util.Arrays.asList(pcbArr).indexOf(q)+"] */");
    }

    //implement destroy(PCB_V1 p)

    private static void test1(){
        System.out.println("Executing test #1");
        create(pcbArr[0]);
        create(pcbArr[0]);
        create(pcbArr[2]);
        create(pcbArr[0]);
    }

    private static void test2(){
        System.out.println("Executing test #2");
        create(pcbArr[0]);
        create(pcbArr[0]);
        create(pcbArr[0]);
        create(pcbArr[2]);
        create(pcbArr[2]);
    }
}
