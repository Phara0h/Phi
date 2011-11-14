package Phi;

import Phi.Controls.ViewpointListener;
import Phi.Geometry.PhiObjGeometry;
import Phi.ImportOBJ.GlTextureController;
import Phi.Math.MatrixUtils;
import java.awt.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.*;
import java.util.ArrayList;
import java.util.Iterator;

/*
 * Copyright (C) 2011 Jt Whissel
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *  >.
 */
/**
 *
 * @author Jt Whissel
 */
public abstract class Application implements ProjectTemplate
{

    /////////////////////////////////////////////
    //               Properties                //
    /////////////////////////////////////////////
    public GLProfile phiProfile;
    public GLCapabilities phiCapabilities; 				// controls the capabilities of our 
    public GLCanvas phiCanvas;					// a GLDrawble context that supports 
    public Frame phiFrame;						// the frame for our spplication. 
    public FPSAnimator phiAnimator;				// provides threading for animation
    public PhiWindowAdapter phiAdapter;			// handles relevant window events, 
    public ArrayList<PhiShader> phiShaders = new ArrayList<PhiShader>();
    public ArrayList<PhiObjGeometry> phiObjects = new ArrayList<PhiObjGeometry>();
    public GlTextureController phiTextureController;
    public Viewpoint phiViewpoint;
    public float global_amb_light_color[] =
    {
        0.2f, 0.2f, 0.2f, 1.0f
    };
    public int[] textures;  // The textures 
    ////////////////////////////////////////////
    //              Defaults                  //
    ////////////////////////////////////////////    
    //Viewport
    public float[] EYE =
    {
        25f, 25f, 25f
    }; 
    
    // Default eye postion
    public int PHI = 90;
    public int THETA = 180;
    
    //GL
    public int SAMPLES = 2; // Number of samples you want to have for AA
    public int TEXTURES = 1; // Numbers of textures you plan to have in your Application
    public int FPS = 60;
    
    //Window
    public String APP_NAME = "A Phi Application";
    public int COLOR_BITS = 24; // default color depth
    public int DEPTH_BITS = 12;   // default depth bits
    public int WIDTH = 1280; // default window size
    public int HEIGHT = 720;  // default window size

    ////////////////////////////////////////////
    //              methods                   //
    ////////////////////////////////////////////
    public Application()
    {
        Defaults();

        phiProfile = GLProfile.getDefault();
        phiCapabilities = new GLCapabilities(phiProfile);
        phiCapabilities.setNumSamples(SAMPLES);
        phiCapabilities.setSampleBuffers(true);

        textures = new int[TEXTURES];
        phiTextureController = new GlTextureController(TEXTURES);
        
        phiViewpoint = new Viewpoint();
        phiViewpoint.setCamera(EYE, PHI, THETA);
        
        phiAdapter = new PhiWindowAdapter(this);
        
        phiCanvas = new GLCanvas(phiCapabilities);
        phiCanvas.addGLEventListener(this);
        phiCanvas.addKeyListener(new ViewpointListener(phiViewpoint));
        phiCanvas.requestFocus();
        
        phiFrame = new Frame(APP_NAME);
        phiFrame.setSize(WIDTH, HEIGHT);
        phiFrame.setLayout(new BorderLayout());
        phiFrame.addWindowListener(phiAdapter);
        phiFrame.add(phiCanvas);
        phiFrame.setVisible(true);

        // @TODO add key and mouse listeners
        
        phiAnimator = new FPSAnimator(phiCanvas,FPS);
        phiAnimator.start();
       
    }
    
    @Override
    public void init(GLAutoDrawable drawable)
    {
      GL3 gl = drawable.getGL().getGL3();
      Init(gl);
      InitObjects(gl);
      InitShaders(gl);
    }

    @Override
    public void display(GLAutoDrawable drawable)
    {
        GL3 gl = drawable.getGL().getGL3();
     
        // Clear the framebuffer
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        Draw(gl);
        
        for (int i = 0; i < phiObjects.size(); i++)
        {
            PhiObjGeometry objs = phiObjects.get(i);
            PhiShader shader = phiShaders.get(objs.shaderIndex);

            shader.useShader(gl);
            shader.Render(gl, this);

            if (phiTextureController != null)
            {
                phiTextureController.UseTexture(gl, i);
            }
            objs.display(gl, shader);
        }
    }

    //Handles resizing of the GLComponent
    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height)
    {
        GL3 gl = drawable.getGL().getGL3();

        // re-size viewport
        gl.glViewport(0, 0, width, height);

        for (Iterator<PhiShader> it = phiShaders.iterator(); it.hasNext();)
        {
            PhiShader shader = it.next();
            shader.ReRender(gl, this, width, height);
        }
    }

    @Override
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged)
    {
        
    }

    @Override
    public void windowWillClose()
    {
        phiAnimator.stop();
    }
    
    @Override
    public void dispose(GLAutoDrawable drawable)
    {
        
    }
}