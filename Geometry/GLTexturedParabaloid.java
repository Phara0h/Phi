package Phi.Geometry;

// ////////////////////////////////////////////////////////////////
// Class:           GLTexturedParabaloid                         //
// Author:          Michael Branton                              //
// Purpose:         Represents a parabolc surface                //
//                  centered at the origin                       //
//                                                               //
// Date:            Sept 30, 2000                                //
// Modified:        12 October, 2000                             //
//                  calculated in polar coordinates so it looks  //
//                  more bowl-like                               //
//                                                               //
// ////////////////////////////////////////////////////////////////

/**
 * 
 * @author phara0h
 */
public class GLTexturedParabaloid extends GLTexturedParametricSurface
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
    public GLTexturedParabaloid(int udiv,int vdiv)
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
        //return u/(float)this.udiv-0.5f ;
        return v*(float)Math.cos(TWO_PI*u/(float)this.udiv)/(float)this.vdiv;
    }
    
    /**
     * 
     * @param u
     * @param v
     * @return
     */
    public float y(int u, int v)
    {
        float s,t;

        s=x(u,v);
        t=z(u,v);
        return s*s+t*t;
        
    }
    
    /**
     * 
     * @param u
     * @param v
     * @return
     */
    public float z(int u,int v)
    {
        //return v/(float)this.vdiv-0.5f;
        return -v*(float)Math.sin(TWO_PI*u/(float)this.udiv)/(float)this.vdiv;
    }
}