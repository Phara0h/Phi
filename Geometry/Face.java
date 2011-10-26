/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Phi.Geometry;

/**
 *
 * @author phara0h
 */
public class Face
{
    public int[] face = new int[12];
    private int num = 0;
    
    public void addVertex(int v)
    {
        if(num < 12)
        {
            face[num] = v;
            num++;
        }
    }
}
