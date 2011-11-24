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
 * PhiBounds are made of a collection of PhiGates
 * PhiGates are a set of 4 points in 3d space
 * 
 *  v1 ------- v2
 *  |          |
 *  |          |
 *  |          |
 *  v3 ------- v4
 * @author Jt Whissel
 */
public class PhiGate
{
    public Vert v1,v2,v3,v4;
    public Vert centerPoint;
    
    public PhiGate(Vert vertTL, Vert vertTR, Vert vertBL, Vert vertBR)
    {
        v1 = vertTL;
        v2 = vertTR;
        v3 = vertBL;
        v4 = vertBR;
        BuildCenterpoint();
    }
    
    private void BuildCenterpoint()
    {
        centerPoint = new Vert();
        centerPoint.x =(float) Math.sqrt((v1.x*2)+(v2.x*2)+(v3.x*2)+(v4.x*2));
        centerPoint.y =(float) Math.sqrt((v1.y*2)+(v2.y*2)+(v3.y*2)+(v4.y*2));
        centerPoint.z =(float) Math.sqrt((v1.z*2)+(v2.z*2)+(v3.z*2)+(v4.z*2));
    }
    
}
