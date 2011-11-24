package Phi.Geometry;

// ////////////////////////////////////////////////////////////////
// Class:           GLTexturedSphere                             //
// Author:          Michael Branton                              //
// Purpose:         Represents a unit sphere,                    //
//                  centered at the origin                       //
//                                                               //
//                                                               //
// Date:            Sept 30, 2000                                //
//                                                               //
// ////////////////////////////////////////////////////////////////

/**
 * 
 * @author phara0h
 */
public class GLTexturedSphere extends GLTexturedParametricSurface
{
    private static final double TWO_PI=2*Math.PI;
    
    ////////////////////////////////////////////
    //              methods                   //
    ////////////////////////////////////////////
    
    /**
     * 
     * @param udiv
     * @param vdiv
     */
    public GLTexturedSphere(int udiv,int vdiv)
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
        return (float)Math.sin(v*Math.PI/this.vdiv)*(float)Math.cos(u*TWO_PI/this.udiv);
    }
    
    /**
     * 
     * @param u
     * @param v
     * @return
     */
    public float y(int u, int v)
    {
        return -(float)Math.sin(v*Math.PI/this.vdiv)*(float)Math.sin(u*TWO_PI/this.udiv);
    }
    
    /**
     * 
     * @param u
     * @param v
     * @return
     */
    public float z(int u,int v)
    {
        return (float)Math.cos(v*Math.PI/this.vdiv);
    }
}