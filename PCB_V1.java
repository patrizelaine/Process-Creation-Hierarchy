/* CS 4310
 *  Group Assignment #1
 *
 *  Group Members:
 *  Teresita Leonor S. Esver
 *  Patriz Elaine M. Daroy
 *  Alexa Tang
 */

import java.util.LinkedList;

public class PCB_V1
{
    private int index_parent;
    //any processes created by the parent process are
    //held in this linked list of child processes
    LinkedList<PCB_V1> children;
    //constructor
    public PCB_V1(int p_i)
    {
        index_parent = p_i;
        children = new LinkedList<>();
    }

    //add child to linked list of chldren
    public void addChild(int parentIndex)
    {
        children.add(new PCB_V1(parentIndex));
    }
    //get children of pcb
    public LinkedList<PCB_V1> getChildren()
    {
        return children;
    }

    //get youngest child
    public int getYoungestChildIndex(){
        return children.size();
    }

    public int getParentIndex(){
        return this.index_parent;
    }
}
