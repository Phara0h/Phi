package Phi.Geometry;

// ////////////////////////////////////////////////////////////////
// Class:           GLPotatoChip                                 //
// Author:          Michael Branton                              //
// Purpose:         Represents a potato chip                     //
//                  centered at the origin                       //
//                                                               //
// Date:            Feb 14th, 2000                               //
//                                                               //
// ////////////////////////////////////////////////////////////////

/**
 * 
 * @author Phara0h
 */
public class GLPotatoChip extends GLParametricSurface
{
    private static final double TWO_PI=2*Math.PI;
    
    /**
     * 
     * @param udiv
     * @param vdiv
     */
    public GLPotatoChip(int udiv,int vdiv)
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
        //return u/(float)this.udiv-0.5f ;
        return u*(float)Math.cos(TWO_PI*v/(float)vdiv)/(float)udiv;
    }
    
    /**
     * 
     * @param u
     * @param v
     * @return
     */
    protected float y(int u, int v)
    {
        float s,t;

        s=x(u,v);
        t=z(u,v);
        return s*s*s+t*t*t;
        
    }
    
    /**
     * 
     * @param u
     * @param v
     * @return
     */
    protected float z(int u,int v)
    {
        //return v/(float)this.vdiv-0.5f;
        return u*(float)Math.sin(TWO_PI*v/(float)vdiv)/(float)udiv;
    }
}