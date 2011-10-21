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

    public class Face
    {
        public int v1 = 0;
        public int v2 = 0;
        public int v3 = 0;
        public int v4 = 0;
    }
    
    public class Vert
    {
        public int x = 0;
        public int y = 0;
        public int z = 0;
        public int w = 0;
    }
    
    
    public String GroupName;
    public ArrayList<Face> faces = new ArrayList<Face>();
    public ArrayList<Vert> verts = new ArrayList<Vert>();

    public GLObjGeometry()
    {
        this.vertices = new float[(faces.size()*4)*4];
        
    }
    
    @Override
    public void setVertices()
    {
        
    }
    
    public void AddVert(int x, int y, int z, int w)
    {
        Vert v = new Vert();
        v.x = x;
        v.y = y;
        v.z = z;
        v.w = w;
        verts.add(v);
    }
    
    public void AddFace(int v1, int v2, int v3, int v4)
    {
        Face f = new Face();
        f.v1 = v1;
        f.v2 = v2;
        f.v3 = v3;
        f.v4 = v4;
        faces.add(f);
    }
}
