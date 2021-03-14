/* CS 4310
*  Group Assignment #1
*  
*  Group Members:
*  Teresita Leonor S. Esver 
*  Patriz Elaine M. Daroy
*  Alexa Tang
*/

public class PCB_V2
{
    private int parent, first_child, younger_sibling,older_sibling;
    PCB_V2(int p, int f_c, int y_s, int o_s)
    {
        parent = p;
        first_child = f_c;
        younger_sibling = y_s;
        older_sibling = o_s;
    }
    
    public int getParent()
    {
    	return parent;
    }
    
    public int getFirst()
    {
    	return first_child;
    }
    
    public int getYounger()
    {
    	return younger_sibling;
    }
    
    public int getOlder()
    {
    	return older_sibling;
    }
    
    public void setFirst(int first)
    {
    	first_child = first;
    }
    
    public void setOlder(int old)
    {
    	older_sibling = old;
    }
}