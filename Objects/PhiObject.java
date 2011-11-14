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

import Phi.Collison.PhiBounds;
import Phi.Collison.PhiCollidable;
import Phi.Geometry.GLGeometry;

/**
 *
 * @author Jt Whissel
 */
public abstract class PhiObject extends PhiCollidable implements PhiObjectTemplate
{
    public float [] vertices;
    public GLGeometry geometry;
    
    public PhiObject(float [] vertices, PhiBounds objBounds)
    {
        init(vertices,objBounds);
    }
    
    public PhiObject(GLGeometry geometry, PhiBounds objBounds)
    {
        init(geometry.getVerts(),objBounds);
    }
    
    @Override
    public void init(float [] verts,PhiBounds objBounds)
    {
        vertices = new float[verts.length];
        vertices = verts;
        bounds = objBounds;
        BuildBounds();
    }

    private void BuildBounds()
    {
        if(bounds != null)
        {
            bounds.BuildBounds(vertices);
        }
    }
    
}
