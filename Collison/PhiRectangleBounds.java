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
package Phi.Collison;

import Phi.Geometry.Vert;

/**
 *
 * @author Jt Whissel
 */
public class PhiRectangleBounds extends PhiBounds
{
    private float highX, highY, highZ;
    private float lowX, lowY, lowZ;
    
    public PhiRectangleBounds()
    {
    }
    
    @Override
    public void BuildBounds(float[] verts)
    {
        float tempHX = 0;
        float tempHY = 0;
        float tempHZ = 0;
        
        float tempLX = 0;
        float tempLY = 0;
        float tempLZ = 0;
        
        for (int i = 0; i < verts.length; i++)
        {
            if(verts[i] > tempHX)
                tempHX = verts[i];
            else if(verts[i] < tempLX)
                tempLX = verts[i];
            
            i++;
            
            if(verts[i] > tempHY)
                tempHY = verts[i];
            else if(verts[i] < tempLY)
                tempLY = verts[i];
            
            i++;
            
            if(verts[i] > tempHZ)
                tempHZ = verts[i];
            else if(verts[i] < tempLZ)
                tempLZ = verts[i];
            
            i++;
        }
        
        highX = tempHX;
        highY = tempHY;
        highZ = tempHZ;
        lowX = tempLX;
        lowY = tempLY;
        lowZ = tempLZ;
        
        BuildGates(highX-lowX, highZ - lowZ, highY - lowY);
        
        centerPoint = new Vert();
        centerPoint.x = (highX - lowX)/2;
        centerPoint.y = (highY - lowY)/2;
        centerPoint.z = (highZ - lowZ)/2;
    }

    @Override
    public double GetXArea()
    {
        return highX;
    }

    @Override
    public double GetYArea()
    {
        return highY;
    }

    @Override
    public double GetZArea()
    {
        return highZ;
    }
    
    private void BuildGates(double length, double width, double height)
    {
        Vert v1 = new Vert();
        Vert v2 = new Vert();
        Vert v3 = new Vert();
        Vert v4 = new Vert();
        
        // Front
        v1.x = (float) -length;
        v1.y = (float) height;
        v1.z = (float) -width;
        
        v2.x = (float) length;
        v2.y = (float) height;
        v2.z = (float) -width;
        
        v3.x = (float) -length;
        v3.y = (float) -height;
        v3.z = (float) -width;
        
        v4.x = (float) length;
        v4.y = (float) -height;
        v4.z = (float) -width;
        
        PhiGate gate = new PhiGate(v1, v2, v3, v4);
        gates.add(gate);
        
        // Back
        v1.x = (float) -length;
        v1.y = (float) height;
        v1.z = (float) width;
        
        v2.x = (float) length;
        v2.y = (float) height;
        v2.z = (float) width;
        
        v3.x = (float) -length;
        v3.y = (float) -height;
        v3.z = (float) width;
        
        v4.x = (float) length;
        v4.y = (float) -height;
        v4.z = (float) width;
        
        gate = new PhiGate(v1, v2, v3, v4);
        gates.add(gate);
        
        // Top
        v1.x = (float) -length;
        v1.y = (float) height;
        v1.z = (float) width;
        
        v2.x = (float) length;
        v2.y = (float) height;
        v2.z = (float) width;
        
        v3.x = (float) -length;
        v3.y = (float)  height;
        v3.z = (float) -width;
        
        v4.x = (float) length;
        v4.y = (float) height;
        v4.z = (float) -width;
        
        gate = new PhiGate(v1, v2, v3, v4);
        gates.add(gate);
        
        // bottom
        v1.x = (float) -length;
        v1.y = (float) -height;
        v1.z = (float) width;
        
        v2.x = (float) length;
        v2.y = (float) -height;
        v2.z = (float) width;
        
        v3.x = (float) -length;
        v3.y = (float)  -height;
        v3.z = (float) -width;
        
        v4.x = (float) length;
        v4.y = (float) -height;
        v4.z = (float) -width;
        
        gate = new PhiGate(v1, v2, v3, v4);
        gates.add(gate);
        
        // right
        v1.x = (float) length;
        v1.y = (float) height;
        v1.z = (float) -width;
        
        v2.x = (float) length;
        v2.y = (float) -height;
        v2.z = (float) -width;
        
        v3.x = (float) length;
        v3.y = (float) height;
        v3.z = (float) width;
        
        v4.x = (float) length;
        v4.y = (float) -height;
        v4.z = (float) width;
        
        gate = new PhiGate(v1, v2, v3, v4);
        gates.add(gate);
    
        // left
        v1.x = (float) -length;
        v1.y = (float) height;
        v1.z = (float) -width;
        
        v2.x = (float) -length;
        v2.y = (float) -height;
        v2.z = (float) -width;
        
        v3.x = (float) -length;
        v3.y = (float) height;
        v3.z = (float) width;
        
        v4.x = (float) -length;
        v4.y = (float) -height;
        v4.z = (float) width;
        
        gate = new PhiGate(v1, v2, v3, v4);
        gates.add(gate);
    }
    
}
