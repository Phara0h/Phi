package Phi.Geometry;




import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import javax.media.opengl.GL;
import javax.media.opengl.GL3;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author phara0h
 */
public final class PhiObjGeometry extends GLTriangulatedSurface
{
    public String GroupName;
    public ArrayList<Face> faces = new ArrayList<Face>();
    public ArrayList<Vert> verts = new ArrayList<Vert>();
    public ArrayList<Vert> normal = new ArrayList<Vert>();
    public ArrayList<Vert> texcords = new ArrayList<Vert>();
    /**
     * 
     */
    private float size_m;
    public PhiObjGeometry()
    {
        super();
        this.setMode(GL.GL_TRIANGLE_STRIP);
		
	// default color
	this.my_color=new float[4];
	this.setColor(Color.white);
        this.size=6*this.udiv*this.vdiv;
        this.vertices = new float[4*this.size];
        this.normals = new float[3*this.size];
        this.colors = new float[4*this.size];
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
        verts.add(v);
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
        texcords.add(v);
    }
    

    public void Construct()
    {
        this.vertices = new float[(faces.size()*4)*4];
        this.normals = new float[(faces.size()*4)*3];
        this.texcoordinates = new float [(faces.size()*4)*2];
        int numV, numVN, numVT = 0;
        
        BuildFaces();
        SetVerts();
        SetNorms();
        SetTexCords();
    }

    private void BuildFaces()
    {
        for (Iterator<Face> it = faces.iterator(); it.hasNext();)
        {
            Face f = it.next();
            int n1 = 0, n2 = 0, n3 = 0;
            for (int i = 0; i < f.faceIndex.length; i++)
            {
                
                // Verts
                Vert v = new Vert();
                v = verts.get(f.faceIndex[i++]-1);
                f.v[n1++] = v.x;
                f.v[n1++] = v.y;
                f.v[n1++] = v.z;
                f.v[n1++] = v.w;
                
                // TexCords 
                v = texcords.get(f.faceIndex[i++]-1);
                f.vt[n2++] = v.x; // u
                f.vt[n2++] = v.y; // v
                
                // Vertex Normals
                v = normal.get(f.faceIndex[i]-1);
                f.vn[n3++] = v.x;
                f.vn[n3++] = v.y;
                f.vn[n3++] = v.z;

                

            }
        }
    }

    private void SetVerts()
    {
        int num = 0;
        for (Iterator<Face> it = faces.iterator(); it.hasNext();)
        {
            Face face = it.next();
            for (int i = 0; i < face.v.length; i++)
            {
                this.vertices[num++] = face.v[i];
            }
        }
    }

    private void SetNorms()
    {
        int num = 0;
        for (Iterator<Face> it = faces.iterator(); it.hasNext();)
        {
            Face face = it.next();
            for (int i = 0; i < face.vn.length; i++)
            {
                this.normals[num++] = face.vn[i];
            }
        }
    }

    private void SetTexCords()
    {
        int num = 0;
        for (Iterator<Face> it = faces.iterator(); it.hasNext();)
        {
            Face face = it.next();
            for (int i = 0; i < face.vt.length; i++)
            {
                this.texcoordinates[num++] = face.vt[i];
            }
        }
    }


}
