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
package Phi.Objects;

import Phi.Application;
import Phi.Collison.PhiBounds;
import Phi.Collison.PhiCollidable;
import Phi.Geometry.GLGeometry;
import Phi.Geometry.PhiObjGeometry;
import Phi.Math.MatrixUtils;
import Phi.PhiShader;
import com.jogamp.opengl.util.GLBuffers;
import javax.media.opengl.GL;
import javax.media.opengl.GL3;

/**
 *
 * @author Jt Whissel
 */
public class PhiObject extends PhiObjGeometry implements PhiObjectTemplate, PhiCollidable
{
    // Position

    public double x = 0;
    public double y = 0;
    public double z = 0;
    public float rotX = 0;
    public float rotY = 0;
    public float rotZ = 0;
    // Velocity
    public double vx = 0;
    public double vy = 0;
    public double vz = 0;
    public double speed = 1;
    // Physics
    public double mass = 1.0;
    public double gravity = -9.8;
    public double friction = 0.1;
    // Object
    public GLGeometry geometry;
    public PhiBounds bounds;

    public PhiObject(PhiBounds objBounds)
    {
        init(objBounds);
    }

    @Override
    public void init(PhiBounds objBounds)
    {
        bounds = objBounds;
        BuildBounds();
    }

    private void BuildBounds()
    {
        if (bounds != null)
        {
            bounds.BuildBounds(vertices);
        }
    }

    @Override
    public void Update(GL3 gl, Application app)
    {
    }

    @Override
    public void display(GL3 gl, PhiShader shader)
    {
        float[] t = MatrixUtils.translation((float) x, (float) y, (float) z);
        t = MatrixUtils.multiply(t, MatrixUtils.rotationY(rotY));
        t = MatrixUtils.multiply(t, MatrixUtils.rotationX(rotX));

        //t = MatrixUtils.add(MatrixUtils.rotationZ(rotZ), t);


        gl.glUniformMatrix4fv(shader.uniform.get("Model").intValue(), 1, true, t, 0);

        super.display(gl, shader);

    }
}
