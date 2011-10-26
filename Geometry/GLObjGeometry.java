package Phi.Geometry;


import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author phara0h
 */
public class GLObjGeometry extends GLGeometry
{


    
    

    public String GroupName;
    public ArrayList<Face> faces = new ArrayList<Face>();
    public ArrayList<Vert> geo = new ArrayList<Vert>();
    public ArrayList<Vert> normal = new ArrayList<Vert>();
    public ArrayList<Vert> texture = new ArrayList<Vert>();
    /**
     * 
     */
    public GLObjGeometry()
    {
        this.vertices = new float[(faces.size()*4)*4];
        
    }
    
    /**
     * 
     */
    @Override
    public void setVertices()
    {
        
    }
    
    /**
     * 
     * @param x
     * @param y
     * @param z
     * @param w
     */
    public void AddGeometryVert(float x, float y, float z, float w)
    {
        Vert v = new Vert();
        v.x = x;
        v.y = y;
        v.z = z;
        v.w = w;
        geo.add(v);
    }
    
    public void AddNormalVert(float x, float y, float z, float w)
    {
        Vert v = new Vert();
        v.x = x;
        v.y = y;
        v.z = z;
        v.w = w;
        normal.add(v);
    }
    
    public void AddTextureVert(float x, float y, float z, float w)
    {
        Vert v = new Vert();
        v.x = x;
        v.y = y;
        v.z = z;
        v.w = w;
        normal.add(v);
    }
    
    /**
     * 
     * @param v1
     * @param v2
     * @param v3
     * @param v4
     */
    public void AddFace(Vert geo, Vert text, Vert normal)
    {
        Face f = new Face();
        f.geo = geo;
        f.normal = normal;
        f.texture = text;
        faces.add(f);
    }
}
