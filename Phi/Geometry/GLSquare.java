package Phi.Geometry;

// ////////////////////////////////////////////////////////////////
// Class:           GLSquare                                     //
// Author:          Michael Branton                              //
// Purpose:         Represents a unit square, with corners at    //
//                  (0,0),(0,1),(1,1),(1,0)                      //
//                                                               //
// Date:            Sept 30, 2000                                //
//                                                               //
// ////////////////////////////////////////////////////////////////

/**
 * 
 * @author Phara0h
 */
public class GLSquare extends GLParametricSurface
{
        
    /**
     * 
     * @param udiv
     * @param vdiv
     */
    public GLSquare(int udiv,int vdiv)
    {
        super(udiv,vdiv);
    }
    
    /**
     * 
     * @param u
     * @param v
     * @return
     */
    protected float x(int u,int v)
    {
        return u/(float)udiv;
    }
    
    /**
     * 
     * @param u
     * @param v
     * @return
     */
    protected float y(int u, int v)
    {
        return v/(float)vdiv;
    }
    
    /**
     * 
     * @param u
     * @param v
     * @return
     */
    protected float z(int u,int v)
    {
        return 0;
    }
}