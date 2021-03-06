package Phi.Geometry;

// ////////////////////////////////////////////////////////////////
// Class:           GLParametricSurface                          //
// Author:          Michael Branton                              //
// Purpose:         Represents a surface that can be defined by 3//
//                  parametric equations, x(u,v), y(u,v), z(u,v) //
//                  where (u,v) comes from a point in the unit   //
//                  square.                                      //
//                                                               //
// Date:            Sept 30, 2000                                //
//                                                               //
///////////////////////////////////////////////////////////////////

/**
 * 
 * @author phara0h
 */
public abstract class GLParametricSurface extends GLTriangulatedSurface
{    
    /**
     * 
     */
    public GLParametricSurface()
	{
		
	}
	
    /**
     * 
     * @param udiv
     * @param vdiv
     */
    public GLParametricSurface(int udiv, int vdiv)
    {
        super(udiv,vdiv);
    }
    
    /**
     * 
     */
    protected void setVertices()
    {   
        for(int v=0;v<this.vdiv;v++)
        {
            for(int u=0;u<this.udiv;u++)
            {
                // add lower triangle
                addTriangleWithNormal(
                			x(u,v),y(u,v),z(u,v),
                            x(u+1,v),y(u+1,v),z(u+1,v),
                            x(u+1,v+1),y(u+1,v+1),z(u+1,v+1));
                
                // add upper triangle
                addTriangleWithNormal(
                			x(u,v),y(u,v),z(u,v),
                            x(u+1,v+1),y(u+1,v+1),z(u+1,v+1),
                            x(u,v+1),y(u,v+1),z(u,v+1));
            }
        }
    }    
    /**
     * 
     * @param u
     * @param v
     * @return
     */
    protected  abstract float x(int u, int v);
	
    /**
     * 
     * @param u
     * @param v
     * @return
     */
    protected  abstract float y(int u, int v);
	
    /**
     * 
     * @param u
     * @param v
     * @return
     */
    protected  abstract float z(int u, int v);
}