package Phi.Geometry;

// ////////////////////////////////////////////////////////////////
// Class:           GLCylinder                                   //
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
 * @author Phara0h
 */
public class GLCylinder extends GLParametricSurface
{
    private static final double TWO_PI=2*Math.PI;
    
        
    /**
     * 
     * @param udiv
     * @param vdiv
     */
    public GLCylinder(int udiv,int vdiv)
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
        return (float)Math.cos(v*TWO_PI/this.vdiv);
    }
    
    /**
     * 
     * @param u
     * @param v
     * @return
     */
    protected float y(int u, int v)
    {
        return (float) u/(float)this.udiv;
    }
    
    /**
     * 
     * @param u
     * @param v
     * @return
     */
    protected float z(int u,int v)
    {
        return (float)Math.sin(v*TWO_PI/this.vdiv);
    }
}