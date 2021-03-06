package Phi.Geometry;

///////////////////////////////////////////////////////////////////
// Class:           GLTriangulatedSurface                        //
// Author:          Michael Branton                              //
// Purpose:         Stores the vertices of a mesh as triangles   //
//                                                               //
//                                                               //
// Date:            Sept 30, 2000                                //
//																 // 
// Revised:			31 July, 2011								 //
//					init collor due to change in GLGeometry		 //
//					Aug 3, 2011  								 //
//                  added setVertices(udiv,vdiv) method to be	 //
//					overridden, and modified for use with JOGL 2 //
//                                                               //
///////////////////////////////////////////////////////////////////

import javax.media.opengl.*;
import Phi.Math.MatrixUtils;

/**
 * 
 * @author phara0h
 */
public abstract class GLTriangulatedSurface extends GLGeometry
{    
    /**
     * 
     */
    protected int size;
    /**
     * 
     */
    /**
     * 
     */
    protected int udiv,         	// # of divisions in each direction
                  vdiv;         	// of the mesh
    /**
     * 
     */
    /**
     * 
     */
    protected int vlocation,
    			  nlocation;
    
    /**
     * 
     */
    public GLTriangulatedSurface()
    {
    	this.udiv=0;
        this.vdiv=0;
        this.size=0;
        this.vlocation=0;
        this.nlocation=0;
        this.setVertices();
        this.setMode(GL3.GL_TRIANGLES);
    }
    
    /**
     * 
     * @param udiv
     * @param vdiv
     */
    public GLTriangulatedSurface(int udiv, int vdiv)
    {
    	this.udiv=udiv;
        this.vdiv=vdiv;
        this.size=6*this.udiv*this.vdiv;
        this.vertices = new float[4*this.size];
        this.normals = new float[3*this.size];
        this.colors = new float[4*this.size];
        this.setVertices(udiv,vdiv);
        this.setMode(GL.GL_TRIANGLES);
    }
    
    /**
     * 
     */
    protected void setVertices()
    {
    	this.vertices=null;
    	this.normals=null;
    }
     
    // input udiv, vdiv : number of vertical and horizontal divisions
    // in  a triangular mesh
    //
    // implement this to create mesh
    /**
     * 
     * @param udiv
     * @param vdiv
     */
    protected void setVertices(int udiv, int vdiv)
    {

    }
    
 // add the vertices of each triangle and the normal vector at each vertex
    /**
     * 
     * @param x1
     * @param y1
     * @param z1
     * @param x2
     * @param y2
     * @param z2
     * @param x3
     * @param y3
     * @param z3
     */
    protected void addTriangle(   float x1, float y1, float z1,
                                  float x2, float y2, float z2,
                                  float x3, float y3, float z3)
    {
        // vertex (x1,y1,z1)
        this.vertices[this.vlocation++]=x1;
        this.vertices[this.vlocation++]=y1;
        this.vertices[this.vlocation++]=z1;
        this.vertices[this.vlocation++]=1;
        
        // vertex (x2,y2,z2)
        this.vertices[this.vlocation++]=x2;
        this.vertices[this.vlocation++]=y2;
        this.vertices[this.vlocation++]=z2;
        this.vertices[this.vlocation++]=1;
        
        // vertex (x3,y3,z3)
        this.vertices[this.vlocation++]=x3;
        this.vertices[this.vlocation++]=y3;
        this.vertices[this.vlocation++]=z3;
        this.vertices[this.vlocation++]=1;
     }
    
    /**
     * 
     * @param x1
     * @param y1
     * @param z1
     * @param x2
     * @param y2
     * @param z2
     * @param x3
     * @param y3
     * @param z3
     */
    protected void addTriangleWithNormal(
    		float x1, float y1, float z1,
            float x2, float y2, float z2,
            float x3, float y3, float z3)
    {
    	// vertex (x1,y1,z1)
    	this.addTriangle(x1, y1, z1,x2, y2, z2,x3, y3, z3);
    	
    	float[] n=MatrixUtils.normalize(MatrixUtils.crossprod(x2-x1,y2-y1,z2-z1,x3-x1,y3-y1,z3-z1));
    	this.normals[this.nlocation++]=n[0];
    	this.normals[this.nlocation++]=n[1];
    	this.normals[this.nlocation++]=n[2];
    	
    	// vertex (x2,y2,z2)
    	this.normals[this.nlocation++]=n[0];
    	this.normals[this.nlocation++]=n[1];
    	this.normals[this.nlocation++]=n[2];


    	// vertex (x3,y3,z3)
    	this.normals[this.nlocation++]=n[0];
    	this.normals[this.nlocation++]=n[1];
    	this.normals[this.nlocation++]=n[2];
}
    
    /**
     * 
     * @return
     */
    public int getUdiv()
    {
        return this.udiv;
    }
    
    /**
     * 
     * @return
     */
    public int getVdiv()
    {
        return this.vdiv;
    }
    
    /**
     * 
     * @return
     */
    public int getSize()
    {
        return this.size;
    }
}