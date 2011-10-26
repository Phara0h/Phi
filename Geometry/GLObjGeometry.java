package Phi.Geometry;


import java.util.ArrayList;
import java.util.Iterator;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author phara0h
 */
public class GLObjGeometry extends GLTriangulatedSurface
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
        this.normals = new float[(faces.size()*4)*4];
        this.texcoordinates = new float[(faces.size()*4)*4];
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
        texture.add(v);
    }
    

    public void Construct()
    {
        setGeoVerts();
        setNormals();
        setTextureCords();
    }

    private void setGeoVerts()
    {
        this.vertices = new float[(faces.size()*16)*3];
        int num = 0;
        for (Iterator<Face> it = faces.iterator(); it.hasNext();)
        {
            Face f = it.next();
            for (int i = 0; i < f.face.length; i++)
            {
                Vert v = new Vert();
                v = normal.get(f.face[i]-1);
                this.vertices[num++] = v.x;
                this.vertices[num++] = v.y;
                this.vertices[num++] = v.z;
                this.vertices[num++] = v.w;
            }
            
            
        }
    }
    private void setNormals()
    {
        
    }

    private void setTextureCords()
    {
        
    }
}
