package Phi.Light;

import Phi.Geometry.*;
import Phi.*;
import Phi.Math.*;
import javax.media.opengl.*;
import java.awt.Color;

/**
 * @author mbranton
 *
 *
 * Class:          "GlobeLight"                                       
 * Author:          Michael Branton                              
 * Purpose:         Extends the Light class so as to display a
 * 					spherical globe surrounding the light source
 * 					An emissive color must be  specified for the
 * 					globe. The diffuse color of the light is
 * 					used as the diffuse color of the globe
 * 					surface                                                               
 *                                                               
 * Created:         October    2, 1999                           
 * Revised:         Jul  09, 2004     to use JOGL                      
 *                                                              
 */

public class GlobeLight extends Light
{
    /////////////////////////////////////////////
    //                  properties             //
    /////////////////////////////////////////////
    
    /**
     * 
     */
    protected static final int      DIVISIONS = 30;     // subdivisions of globe surface
    
    /**
     * 
     */
    protected static final float[]  def_emissive={0.0f,0.0f,0.0f,1.0f}; 
                                                        // used to reset emissive value
                                                        
    /**
     * 
     */
    protected float                 radius;             // radius of enclosing light globe
    /**
     * 
     */
    protected float[]               emissive;           // emissive color for globe
    
    /**
     * 
     */
    protected GLSphere myGlobe;
    
    ////////////////////////////////////////////
    //              methods                   //
    ////////////////////////////////////////////
    
    /**
     * 
     * @param gl
     * @param radius
     */
    public GlobeLight(GL3 gl, float radius)
    {
        super();
        myGlobe=new GLSphere(DIVISIONS,DIVISIONS);
        myGlobe.setMode(GL.GL_TRIANGLES);
        
        myGlobe.setVBO(gl);
        
        this.setRadius(radius);
        this.emissive=new float[4];
    }
    
    /**
     * 
     * @param radius
     */
    public void setRadius(float radius)
    {
            this.radius=radius;
    }
    
    /**
     * 
     * @return
     */
    public float getRadius()
    {
            return this.radius;
    }
    
    /**
     * 
     * @param r
     * @param g
     * @param b
     * @param a
     */
    public void setEmissive(float r,float g,float b,float a)
    {
        this.emissive[0]=r;
        this.emissive[1]=g;
        this.emissive[2]=b;
        this.emissive[3]=a;
        myGlobe.setColor(r,g,b,a);
    }
    
    /**
     * 
     * @param c
     */
    public void setEmissive(Color c)
    {
        this.emissive[0]=c.getRed()/MAXRED;
        this.emissive[1]=c.getGreen()/MAXGREEN;
        this.emissive[2]=c.getBlue()/MAXBLUE;
        this.emissive[3]=1.0f;
        myGlobe.setColor(c);
    }
    
    /**
     * 
     * @return
     */
    public float[] getEmissive()
    {
        return this.emissive;
    }
    
    /**
     * 
     * @return
     */
    public float[] getDefEmissive()
    {
            return GlobeLight.def_emissive;
    }
        
    
    // draw globe
    /**
     * 
     * @param gl
     * @param shader
     */
    public void display(GL3 gl,PhiShader shader)
    {
    	gl.glUniform4fv(shader.uniform.get("AmbientProduct").intValue(),
    			1,
    			this.emissive,
    			0);
    	
    	gl.glUniformMatrix4fv(shader.uniform.get("Model").intValue(),
				1,
				true,
				MatrixUtils.multiply(MatrixUtils.translation(this.position),MatrixUtils.scale(this.radius)),
				0);
    	
    	myGlobe.display(gl);
        
    }
}
            
            
            