package Phi.Geometry;

import javax.media.opengl.GL;
import javax.media.opengl.GL3;

/**
 *
 * @author Phara0h
 */
public final class GLBox extends GLGeometry
{
    private float size_m;
    private boolean wireframe_m;
    
    /**
     * 
     * @param gl
     */
    public GLBox(GL3 gl)
    {
        init();
    }
    
    private void init()
    {
        wireframe_m = false;
        size_m = 1.0f;
        this.setVertices();
        this.setMode(GL.GL_LINE_LOOP); // set deafult mode
    }
    
    /**
     * 
     */
    @Override
    public void setVertices()
    {
                this.vertices = new float[96];
		int i = 0;
                
		//              x                          y                         z                        1              //Back
                
                this.vertices[i++]=size_m; this.vertices[i++]=size_m; this.vertices[i++]=-size_m; this.vertices[i++]=1.0f;
                this.vertices[i++]=-size_m; this.vertices[i++]=size_m; this.vertices[i++]=-size_m; this.vertices[i++]=1.0f;
                this.vertices[i++]=-size_m; this.vertices[i++]=-size_m; this.vertices[i++]=-size_m; this.vertices[i++]=1.0f;
                this.vertices[i++]=size_m; this.vertices[i++]=-size_m; this.vertices[i++]=-size_m; this.vertices[i++]=1.0f;
                                                 
                this.vertices[i++]=size_m; this.vertices[i++]=-size_m; this.vertices[i++]=-size_m; this.vertices[i++]=1.0f; // Right
                this.vertices[i++]=size_m; this.vertices[i++]=size_m; this.vertices[i++]=-size_m; this.vertices[i++]=1.0f;
                this.vertices[i++]=size_m; this.vertices[i++]=size_m; this.vertices[i++]=size_m; this.vertices[i++]=1.0f;
                this.vertices[i++]=size_m; this.vertices[i++]=-size_m; this.vertices[i++]=size_m; this.vertices[i++]=1.0f;
                                                                                                                             //Bot
                this.vertices[i++]=size_m; this.vertices[i++]=-size_m; this.vertices[i++]=size_m; this.vertices[i++]=1.0f; 
                this.vertices[i++]=size_m; this.vertices[i++]=-size_m; this.vertices[i++]=-size_m; this.vertices[i++]=1.0f;
                this.vertices[i++]=-size_m; this.vertices[i++]=-size_m; this.vertices[i++]=-size_m; this.vertices[i++]=1.0f;
                this.vertices[i++]=-size_m; this.vertices[i++]=-size_m; this.vertices[i++]=size_m; this.vertices[i++]=1.0f;
                                                                                                                             // Front
                this.vertices[i++]=-size_m; this.vertices[i++]=-size_m; this.vertices[i++]=size_m; this.vertices[i++]=1.0f;
                this.vertices[i++]=size_m; this.vertices[i++]=-size_m; this.vertices[i++]=size_m; this.vertices[i++]=1.0f;
                this.vertices[i++]=size_m; this.vertices[i++]=size_m; this.vertices[i++]=size_m; this.vertices[i++]=1.0f;
                this.vertices[i++]=-size_m; this.vertices[i++]=size_m; this.vertices[i++]=size_m; this.vertices[i++]=1.0f;
                                                                                                                             // Top
                this.vertices[i++]=-size_m; this.vertices[i++]=size_m; this.vertices[i++]=size_m; this.vertices[i++]=1.0f;                                                                                                               // Top
                this.vertices[i++]=size_m; this.vertices[i++]=size_m; this.vertices[i++]=size_m; this.vertices[i++]=1.0f;
                this.vertices[i++]=size_m; this.vertices[i++]=size_m; this.vertices[i++]=-size_m; this.vertices[i++]=1.0f;
                this.vertices[i++]=-size_m; this.vertices[i++]=size_m; this.vertices[i++]=-size_m; this.vertices[i++]=1.0f;
                                                                                                                             // Left         
                this.vertices[i++]=-size_m; this.vertices[i++]=size_m; this.vertices[i++]=size_m; this.vertices[i++]=1.0f;
                this.vertices[i++]=-size_m; this.vertices[i++]=-size_m; this.vertices[i++]=size_m; this.vertices[i++]=1.0f;
                this.vertices[i++]=-size_m; this.vertices[i++]=-size_m; this.vertices[i++]=-size_m; this.vertices[i++]=1.0f;
                this.vertices[i++]=-size_m; this.vertices[i++]=size_m; this.vertices[i++]=-size_m; this.vertices[i++]=1.0f;
                      
    }
    
    /**
     * 
     * @param gl
     */
    @Override
      public void display(GL3 gl)
    {
//        if(wireframe_m)
//        {
//            gl.glPolygonMode ( GL3.GL_FRONT_AND_BACK, GL3.GL_LINE) ;
//        }
        
        gl.glLineWidth(1);  // Set The Line Width ( NEW )
        gl.glBindBuffer(GL3.GL_ARRAY_BUFFER, myVBO.vertexBufferObjectID);
        gl.glEnableVertexAttribArray(0);
        gl.glVertexAttribPointer(0, 4, GL.GL_FLOAT, false, 0, 0);
        gl.glDrawArrays(this.getMode(), 0, this.vertices.length/4 );
        gl.glDisableVertexAttribArray(0);
//        
//        if(wireframe_m)
//        {
//            gl.glPolygonMode ( GL3.GL_FRONT_AND_BACK, GL3.GL_FILL) ;
//        }

    }

    /**
     * @return the size
     */
    public float getSize_m()
    {
        return size_m;
    }

    /**
     * @param size_m 
     */
    public void setSize_m(float size_m)
    {
        this.size_m = size_m;
    }

    /**
     * @return wireframe status
     */
    public boolean isWireframe_m()
    {
        return wireframe_m;
    }

    /**
     * @param wireframe_m 
     */
    public void setWireframe_m(boolean wireframe_m)
    {
        this.wireframe_m = wireframe_m;
    }


}
