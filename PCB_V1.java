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
    private LinkedList<Integer> children;

    public PCB_V1(int p_i)
    {
        index_parent = p_i;
        children = new LinkedList<>();
    }

    //add child to linked list of children
    public void addChild(int childIndex)
    {
        children.add(childIndex);
    }

    //get children
    public LinkedList<Integer> getChildren()
    {
        return children;
    }

    //get parent index
    public int getParentIndex(){
        return this.index_parent;
    }

    //remove child from linked list
    public void removeChild(Integer childIndex) {
        children.remove(childIndex);
    }
}