package Phi.Geometry;

// ////////////////////////////////////////////////////////////////
// Class:           GLTexturedSquare                             //
// Author:          Michael Branton                              //
// Purpose:         Represents a unit square, with corners at    //
//                  (0,0),(0,1),(1,1),(1,0)                      //
//                                                               //
// Date:            Sept 30, 2000                                //
//                                                               //
// ////////////////////////////////////////////////////////////////

/**
 * 
 * @author phara0h
 */
public class GLTexturedSquare extends GLTexturedParametricSurface
{
	////////////////////////////////////////////
    //              methods                   //
    ////////////////////////////////////////////
	
    /**
     * 
     * @param udiv
     * @param vdiv
     */
    public GLTexturedSquare(int udiv,int vdiv)
    {
        super(udiv,vdiv);
    }
    
    /**
     * 
     * @param u
     * @param v
     * @return
     */
    public float x(int u,int v)
    {
        return u/(float)this.udiv;
    }
    
    /**
     * 
     * @param u
     * @param v
     * @return
     */
    public float y(int u, int v)
    {
        return v/(float)this.vdiv;
    }
    
    /**
     * 
     * @param u
     * @param v
     * @return
     */
    public float z(int u,int v)
    {
        return 0;
    }
}