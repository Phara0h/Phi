package Phi.Objects.Space;
import Phi.Objects.Space.*;
import javax.media.opengl.GL3;
import Phi.Math.MatrixUtils;
import Phi.PhiShader;

/**
 *
 * @author Phara0h
 */
public class Moon extends SpaceObject
{
    
    private Planet myPlanet_m = null;
    
    /**
     * 
     * @param udiv
     * @param vdiv
     * @param planet
     * @param att
     */
    public Moon(int udiv, int vdiv, Planet planet, SpaceObjectAttrib att)
    {
        super(udiv, vdiv, att);
        
        if(planet != null)
        {
            myPlanet_m = planet;
        }
    }
    
    /**
     * 
     * @param gl
     * @param ids
     * @param shader
     * @param attrib
     * @param timeScale
     */
    @Override
    public void display(GL3 gl, int[] ids, PhiShader shader, SpaceObjectAttrib attrib, int timeScale)
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

        float [] p = MatrixUtils.translation(myPlanet_m.getPosX()*10, myPlanet_m.getPosY()*10, myPlanet_m.getPosZ()*10);
        gl.glUniformMatrix4fv(shader.uniform.get("Model").intValue(),
                1,
                true,
                MatrixUtils.multiply(MatrixUtils.translation(getPosX()*100, getPosY()*100, getPosZ()*100),
                MatrixUtils.multiply(MatrixUtils.translation(myPlanet_m.getPosX()*10, myPlanet_m.getPosY()*10, myPlanet_m.getPosZ()*10),
                MatrixUtils.multiply(MatrixUtils.scale(attrib.meanRadius/50, attrib.meanRadius/50,attrib.meanRadius/50), MatrixUtils.rotationZ(calculatedRot)))),
                0);
        


        this.display(gl, shader);
    }
}
