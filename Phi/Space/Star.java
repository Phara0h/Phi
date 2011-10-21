package Phi.Space;

import Phi.Space.SpaceObjectAttrib;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Phara0h
 */
public class  Star extends SpaceObject
{
    private ArrayList<Planet> planets_m = new ArrayList<Planet>();
    
    int timeScale = 1; // multipyed times real time.
    
    /**
     * 
     * @param udiv
     * @param vdiv
     * @param att
     */
    public Star(int udiv, int vdiv, SpaceObjectAttrib att)
    {
        super(udiv, vdiv, att);
        
        if(att != null)
        {
            attrib = att;
        }
    }
    
    /**
     * 
     * @param planet
     * @return
     */
    public boolean AddPlanet(Planet planet)
    {
        if(planet != null)
        {
            planets_m.add(planet);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * 
     * @param att
     */
    public void AddNewPlanet(SpaceObjectAttrib att)
    {
        if(att != null)
        {
            Planet temp = new Planet(udiv, vdiv, this, att);
            planets_m.add(temp);
        }
    }
    
    /**
     * 
     * @param index
     * @return
     */
    public Planet GetPlanet(int index)
    {
        if(planets_m.size() - 1 >= index)
        {
            return planets_m.get(index);
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
    public Planet[] GetAllPlanetsArray()
    {
        if(planets_m.size() > 0)
        {
            Planet[] temp = new Planet[planets_m.size()];
            for (int i = 0; i < planets_m.size(); i++)
            {
                temp[i] = planets_m.get(i);
            }
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
    public ArrayList<Planet> GetAllPlanetsList()
    {
       return planets_m;
    }
    
    /**
     * 
     * @param index
     */
    public void RemovePlanet(int index)
    {
        if(index <= planets_m.size()-1)
        {
            planets_m.remove(index);
        }
    }
    
    /**
     * 
     * @return
     */
    public int GetNumPlanets()
    {
        return planets_m.size();
    }
    
}
