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
import javax.media.opengl.GL3;

/**
 *
 * @author Jt Whissel
 */
public interface PhiObjectTemplate
{   
    public abstract void init(PhiBounds bounds);
    public abstract void Update(GL3 gl, Application app);
}
