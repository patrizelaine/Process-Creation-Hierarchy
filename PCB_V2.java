/* CS 4310
*  Group Assignment #1
*  
*  Group Members:
*  Teresita Leonor S. Esver 
*  Patriz Elaine M. Daroy
*  Alexa Tang
*/

package Process-Creation_Hierarchy;

public class PCB_V2
{
    private int parent, first_child, younger_sibling,oldest_sibling;
    PCB_V2(int p, int f_c, int y_s, int o_s)
    {
        p = parent;
        f_c = first_child;
        y_s = younger_sibling;
        o_s = oldest_sibling;
    }
}
