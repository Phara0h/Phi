package Phi.Geometry;

// ////////////////////////////////////////////////////////////////
// Class:           GLTexturedCylinder                           //
// Author:          Michael Branton                              //
// Purpose:         Represents a vertical cylinder, with base    //
//                  centered at the origin, radius and height 1  //
//                                                               //
//                                                               //
// Date:            Sept 30, 2000                                //
//                                                               //
// ////////////////////////////////////////////////////////////////

/**
 * 
 * @author phara0h
 */
public class GLTexturedCylinder extends GLTexturedParametricSurface
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
    public GLTexturedCylinder(int udiv,int vdiv)
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
        return (float)Math.cos(u*TWO_PI/this.udiv);
    }
    
    /**
     * 
     * @param u
     * @param v
     * @return
     */
    public float y(int u, int v)
    {
        return (float) v/(float)this.vdiv;
    }
    
    /**
     * 
     * @param u
     * @param v
     * @return
     */
    public float z(int u,int v)
    {
        return -(float)Math.sin(u*TWO_PI/this.udiv);
    }
}