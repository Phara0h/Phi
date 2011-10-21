package Phi.ImportOBJ;


import com.jogamp.opengl.util.GLBuffers;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import javax.media.opengl.GL;

/**
 * A Texture object to load and set a texture.
 * @author Jt Whissel
 */
public class GLTexture
{

    private String filePath;
    public int index;
    
    public GLTexture(String fpath)
    {
        filePath = fpath;
    }

    public void SetTexture(GL gl, int[] textures)
    {
        index = textures.length-1;
        gl.glGenTextures(1,textures,index);
        
        File file = new File(filePath);
        BufferedImage buff = null;
        ByteBuffer aTexture;	// texture
        int bpp = 3;			// byte per pixel

        try
        {
            buff = ImageIO.read(file);
        }
        catch (IOException e)
        {
            System.out.println("texture not loaded");
            e.printStackTrace();
        }

        int[] packedPixels = new int[buff.getWidth() * buff.getHeight()];

        PixelGrabber pixelgrabber = new PixelGrabber(buff, 0, 0, buff.getWidth(), buff.getHeight(),
                packedPixels, 0, buff.getWidth());
        try
        {
            pixelgrabber.grabPixels();
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException();
        }

        aTexture = GLBuffers.newDirectByteBuffer(packedPixels.length * bpp);

        for (int row = buff.getHeight() - 1; row >= 0; row--)
        {
            for (int col = 0; col < buff.getWidth(); col++)
            {
                int packedPixel = packedPixels[row * buff.getWidth() + col];
                aTexture.put((byte) ((packedPixel >> 16) & 0xFF));
                aTexture.put((byte) ((packedPixel >> 8) & 0xFF));
                aTexture.put((byte) ((packedPixel >> 0) & 0xFF));
            }
        }

        aTexture.flip();

        // now bind the texture
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[index]);
        gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGB, buff.getWidth(), buff.getHeight(),
                0, GL.GL_RGB, GL.GL_UNSIGNED_BYTE, aTexture);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_REPEAT);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_REPEAT);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
        
        // say which texture unit we're going to use
        gl.glActiveTexture(GL.GL_TEXTURE0);
        
        // use the texture
        gl.glBindTexture(GL.GL_TEXTURE_2D,textures[index]);
    }
}
