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
    private int index_parent; //parent index in pcbArray
    private LinkedList<Integer> children; //child is index in pcbArray

    //parent index passed in constructor
    public PCB_V1(int p_i)
    {
        index_parent = p_i;
        children = new LinkedList<>();
    }
    //add child to children
    public void addChild(int childIndex)
    {
        children.add(childIndex);
    }
    //get children of pcb
    public LinkedList<Integer> getChildren()
    {
        return children;
    }
    //get parent index
    public int getParentIndex(){
        return this.index_parent;
    }
    //remove specific child
    public void removeChild(Integer childIndex){
        children.remove(childIndex);
    }
}
