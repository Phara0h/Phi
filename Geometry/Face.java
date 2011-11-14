package Phi.Geometry;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author phara0h
 */
public class Face
{
    
    public int[] faceIndex;
    public float[] v = new float[16];
    public float[] vn = new float[12];
    public float[] vt = new float[8];
    private int num = 0;
    private int size = 12;
    private boolean faceInit;
    
    public void init(int numIndexs)
    {
        if(numIndexs > 0)
        size = numIndexs;
        
        faceIndex = new int[size];
        faceInit = true;
    }
    
    public void addFaceIndex(int v)
    {
        if(num < size && faceInit)
        {
            faceIndex[num] = v;
            num++;
        }
    }
}
