/* CS 4310
*  Group Assignment #1
*  
*  Group Members:
*  Teresita Leonor S. Esver 
*  Patriz Elaine M. Daroy
*  Alexa Tang
*/

package Process-Creation_Hierarchy;
import java.util.LinkedList;

public class PCB
{
    private int index_parent;

    //any processes created by the parent process are
    //held in this linked list of child processes
    LinkedList<PCB> children = new LinkedList<>();

    PCB(int p_i)
    {
        p_i = index_parent;
    }

    public void addChild(PCB child)
    {
        children.add(child);
    }
    
    public LinkedList<PCB> getChildren()
    {
        return children;
    }
}
