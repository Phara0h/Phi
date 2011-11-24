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
package Phi;

import java.awt.event.*;
import javax.media.opengl.GL3;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

/**
 *
 * @author Jt Whissel
 */
public interface ProjectTemplate extends GLEventListener
{
    public abstract void init(GLAutoDrawable glad);
    public abstract void dispose(GLAutoDrawable glad);
    public abstract void display(GLAutoDrawable glad);
    public abstract void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged);
    public abstract void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3);
    public abstract void windowWillClose();
    
    // Phi Meothods
    public abstract void Init(GL3 gl);// init your variables
    public abstract void InitObjects(GL3 gl); // init your Phi Objects
    public abstract void InitShaders(GL3 gl);// init your Phi Shaders
    public abstract void Draw(GL3 gl); // Called before the display in Open GL (Do logic code here)
    public abstract void KeyPressed(KeyEvent e);
    public abstract void KeyReleased(KeyEvent e);
    public abstract void KeyTyped(KeyEvent e);
    public abstract void MousePressed(MouseEvent e);
    public abstract void MouseReleased(MouseEvent e);
    public abstract void Defaults(); // Change any of the Application default variables
}
