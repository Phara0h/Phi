package Phi.Space;

import java.awt.Color;
import javax.media.opengl.GL3;
import Phi.Geometry.GLParametricSurface;
import Phi.Light.GlobeLight;
import Phi.Math.MatrixUtils;
import Phi.*;

/**
 *
 * @author Phara0h
 */
public abstract class SpaceObject extends GLParametricSurface
{
    private static final double TWO_PI = 2 * Math.PI;
    
    /**
     * 
     */
    public SpaceObjectAttrib attrib;
    private float vx, vy, vz;
    private float posX, posY, posZ;
    /**
     * 
     */
    public float calculatedRot;
    GlobeLight light;
    
    float global_amb_light_color[] =
    {
        0.2f, 0.2f, 0.2f, 1.0f
    };

    /**
     * 
     * @param udiv
     * @param vdiv
     * @param att
     */
    public SpaceObject(int udiv, int vdiv, SpaceObjectAttrib att)
    {
        super(udiv, vdiv);
        vx = 0;
        vy = 0;
        vz = 0;
        posX = 0;
        posY = 0;
        posZ = 0;
        
        attrib = att;

    }
    
    /**
     * 
     * @param gl
     */
    public void InitLight(GL3 gl)
    {
        
        this.light = new GlobeLight(gl, 200f);
        this.light.setAmbient(Color.black);
        this.light.setDiffuse(Color.white);
        this.light.setSpecular(Color.white);
        this.light.setEmissive(Color.white);
        this.light.setPosition(GetPos());
    }

    /**
     * 
     * @param u
     * @param v
     * @return
     */
    @Override
    protected float x(int u, int v)
    {
        return (float) Math.sin(u * Math.PI / this.udiv) * (float) Math.cos(v * TWO_PI / this.vdiv);
    }

    /**
     * 
     * @param u
     * @param v
     * @return
     */
    @Override
    protected float y(int u, int v)
    {
        return (float) Math.sin(u * Math.PI / this.udiv) * (float) Math.sin(v * TWO_PI / this.vdiv);
    }

    /**
     * 
     * @param u
     * @param v
     * @return
     */
    @Override
    protected float z(int u, int v)
    {
        return (float) Math.cos(u * Math.PI / this.udiv);
    }

    /**
     * 
     * @param attrib
     * @param time
     * @return
     */
    public float[] CalculateEllipticalOrbit(SpaceObjectAttrib attrib, float time)
    {
        float semiLatusRectum = attrib.perihelion * (1.0f + attrib.eccentricity);
        float semiMajorAxis = attrib.perihelion / (1.0f - attrib.eccentricity);
        float centerOffset = semiMajorAxis - attrib.perihelion;
        float eccentricityFactor = (float) Math.sqrt((1 + attrib.eccentricity) / (1 - attrib.eccentricity));
        float meanAnomaly = (float) (2.0f * Math.PI * time);
        float eccentricAnomaly = meanAnomaly;

        for (int i = 0; i < 10; i++)
        {
            eccentricAnomaly = (float) (meanAnomaly + attrib.eccentricity * Math.sin(eccentricAnomaly));
        }

        float theta = (float) (2.0f * Math.atan(eccentricityFactor * Math.tan(eccentricAnomaly / 2.0f)));
        float r = (float) (semiLatusRectum / (1.0f + attrib.eccentricity * Math.cos(theta)));
        posX = (float) (-centerOffset - r * Math.cos(theta));
        posY = (float) (-r * Math.sin(theta));
        posZ = 0;

        float[] temp = new float[3];
        temp[0] = posX;
        temp[1] = posY;
        temp[2] = posZ;
        

        return temp;
    }

    /**
     * 
     * @param gl
     * @param ids
     * @param shader
     * @param attrib
     * @param timeScale
     */
    public void display(GL3 gl, int[] ids, ShaderControl shader, SpaceObjectAttrib attrib, int timeScale)
    {
        
        if(light != null)
        {
            
         // set light position
        gl.glUniform4fv(shader.uniform.get("LightPosition").intValue(), 1, GetPos(), 0);
        
        // update shininess
        gl.glUniform1f(shader.uniform.get("Shininess").intValue(), getShininess());

        // set surface material characteristics
        gl.glUniform4fv(shader.uniform.get("AmbientProduct").intValue(),
                1,
                MatrixUtils.prod(getAmbient(), MatrixUtils.add(light.getAmbient(), global_amb_light_color)),
                0);
        gl.glUniform4fv(shader.uniform.get("DiffuseProduct").intValue(),
                1,
                MatrixUtils.prod(getDiffuse(), light.getDiffuse()),
                0);
        gl.glUniform4fv(shader.uniform.get("SpecularProduct").intValue(),
                1,
                MatrixUtils.prod(getSpecular(), light.getSpecular()),
                0);
        }

        gl.glUniformMatrix4fv(shader.uniform.get("Model").intValue(),
                1,
                true,
                MatrixUtils.multiply(MatrixUtils.translation(posX*10, posY*10, posZ*10),
                MatrixUtils.multiply(MatrixUtils.scale(attrib.meanRadius/50, attrib.meanRadius/50,attrib.meanRadius/50), MatrixUtils.multiply(MatrixUtils.rotationY(attrib.axialTilt),MatrixUtils.rotationZ(calculatedRot)))),
                0);
        


        this.display(gl, shader);
    }
    
    /**
     * 
     * @return
     */
    public float[] GetPos()
    {
        float [] temp = new float[4];
        temp[0] = posX;
        temp[1] = posY;
        temp[2] = posZ;
        temp[3] = 1;
        
        return temp;
    }
    
    //////////////////////////
    // Getters and Setters //
    ////////////////////////
    /**
     * @return the vx
     */
    public float getVx()
    {
        return vx;
    }

    /**
     * @param vx the vx to set
     */
    public void setVx(float vx)
    {
        this.vx = vx;
    }

    /**
     * @return the vy
     */
    public float getVy()
    {
        return vy;
    }

    /**
     * @param vy the vy to set
     */
    public void setVy(float vy)
    {
        this.vy = vy;
    }

    /**
     * @return the vz
     */
    public float getVz()
    {
        return vz;
    }

    /**
     * @param vz the vz to set
     */
    public void setVz(float vz)
    {
        this.vz = vz;
    }

    /**
     * @return the posX
     */
    public float getPosX()
    {
        return posX;
    }

    /**
     * @param posX the posX to set
     */
    public void setPosX(float posX)
    {
        this.posX = posX;
    }

    /**
     * @return the posY
     */
    public float getPosY()
    {
        return posY;
    }

    /**
     * @param posY the posY to set
     */
    public void setPosY(float posY)
    {
        this.posY = posY;
    }

    /**
     * @return the posZ
     */
    public float getPosZ()
    {
        return posZ;
    }

    /**
     * @param posZ the posZ to set
     */
    public void setPosZ(float posZ)
    {
        this.posZ = posZ;
    }
}
