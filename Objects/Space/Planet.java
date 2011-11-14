package Phi.Objects.Space;

import Phi.Objects.Space.*;
import java.util.ArrayList;

/**
 *
 * @author Phara0h
 */
public class Planet extends SpaceObject
{
    private Star star_m = null;
    private ArrayList<Moon> moons_m = new ArrayList<Moon>();
    
    /**
     * 
     * @param udiv
     * @param vdiv
     * @param star
     * @param att
     */
    public Planet(int udiv, int vdiv, Star star, SpaceObjectAttrib att)
    {
        super(udiv, vdiv, att);
        
        if(star != null && att != null)
        {
            star_m = star;
        }
    }
    
    /**
     * 
     * @param moon
     */
    public void AddMoon(Moon moon)
    {
        if(moon != null)
        {
            moons_m.add(moon);
        }
    }
    
    /**
     * 
     * @param att
     */
    public void AddNewMoon(SpaceObjectAttrib att)
    {
        if(att != null)
        {
            Moon temp = new Moon(udiv, vdiv, this, att);
            moons_m.add(temp);
        }
    }
    
    /**
     * 
     * @param index
     * @return
     */
    public Moon GetMoon(int index)
    {
        if(moons_m.size() - 1 >= index)
        {
            return moons_m.get(index);
        }
        else
        {
            return null;
        }
    }
    
    /**
     * 
     * @return
     */
    public Moon[] GetAllMoonsArray()
    {
        if(moons_m.size() > 0)
        {
            Moon[] temp;
            temp = (Moon[]) moons_m.toArray();
            return temp;
        }
        else
        {
            return null;
        }
    }
    
    /**
     * 
     * @return
     */
    public ArrayList<Moon> GetAllMoonsList()
    {
        return moons_m;
    }
    
    /**
     * 
     * @param index
     */
    public void RemoveMoon(int index)
    {
        if(index <= moons_m.size()-1)
        {
            moons_m.remove(index);
        }
    }
    
    /**
     * 
     * @return
     */
    public int GetNumMoons()
    {
        return moons_m.size();
    }

    
    
    //////////////////////////
    // Getters and Setters //
    ////////////////////////
    
    
}