package Phi.ImportOBJ;


import java.util.ArrayList;
import javax.media.opengl.GL;

/**
 * Holds the textures
 * 
 * @author Jt Whissel
 */
public class GlTextureController
{
    public ArrayList<GLTexture> textures;
    private int [] textureArray;
        
    public GlTextureController()
    {
        textures = new ArrayList<GLTexture>();
        textureArray = new int[100];
    }
    
    public void AddTexture(GLTexture e)
    {
        textures.add(e);
    }
    
    public void AddNewTexture(GL gl, String filepath)
    {
      GLTexture texture = new GLTexture(filepath);
      texture.SetTexture(gl, textureArray);
      textures.add(texture);
    }
    
    public GLTexture GetTextByName(String filename)
    {
       //code
        return null;
    }
    
    public GLTexture GetTextByIndex(int i)
    {
        //code
        return null;
    }
    
}
