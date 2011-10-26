package Phi.ImportOBJ;


import java.io.*;
import java.util.ArrayList;
import Phi.Geometry.*;
import Phi.Geometry.GLObjGeometry;
import com.sun.java.swing.plaf.windows.resources.windows;
/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author phara0h
 */
public class XObjParse
{

    // Vertex Data
    private static final String GeometicVertices = "v";
    private static final String TextureVertices = "vt";
    private static final String VertexNormals = "vn";
    private static final String ParameterSpaceVertices = "vp";
    // Free-form Curve/Surface Attributes
    private static final String Degree = "deg";
    private static final String BasisMatrix = "bmat";
    private static final String StepSize = "step";
    private static final String CurveOrSurface = "cstype";
    // Elements
    private static final String Point = "p";
    private static final String Line = "l";
    private static final String Face = "f";
    private static final String Curve = "curv";
    private static final String Curve2D = "curv2";
    private static final String Surface = "surf";
    // Grouping
    private static final String GroupName = "g";
    private static final String SmoothingGroup = "s";
    private static final String MergingGroup = "mg";
    private static final String ObjectName = "o";
    // Render Attributes
    private static final String BevelInterpolating = "bevel";
    private static final String ColorInterpolation = "c_interp";
    private static final String DissolveInterpolation = "d_interp";
    private static final String LevelOfDetail = "lod";
    private static final String MaterialName = "usemtl";
    private static final String MaterialLibray = "mtllib";
    private static final String ShadowCasting = "shadow_obj";
    private static final String RayTracing = "trace_obj";
    private static final String CurveApproximation = "ctech";
    private static final String SurfaceApproximation = "stech";
    // Free-Form curve/surface body statements
    private static final String ParameterValues = "parm";
    private static final String OuterTrimmingLoop = "trim";
    private static final String InnerTrimmingLoop = "hole";
    private static final String SpecialCurve = "scrv";
    private static final String SpecialPoint = "sp";
    private static final String EndStatement = "end";
    // Vars
    private ArrayList<GLObjGeometry> geomtryObjects_m = new ArrayList<GLObjGeometry>();
    // Options
    /**
     * 
     */
    public boolean groups = false;

    /**
     * 
     */
    public XObjParse()
    {
        
    }

    /**
     * 
     */
    public void TestReadFile(String fpath)
    {
        String data;
        NewGeoObj(fpath);
        try
        {
            FileInputStream fstream = new FileInputStream(fpath);
            DataInputStream dstream = new DataInputStream(fstream);
            BufferedReader breader = new BufferedReader(new InputStreamReader(dstream));

            while ((data = breader.readLine()) != null)
            {
                if (!data.isEmpty() && data.charAt(0) != '#')
                {
                    String[] splitData = data.split("\\s+");

                    for (int i = 0; i < splitData.length; i++)
                    {
                        System.out.print(splitData[i] + "\n");
                        ObjSwitcher(i, splitData);
                    }
                }

            }

            dstream.close();
        }
        catch (Exception e)
        {
        }
    }
    
    public void OpenFile(String filename)
    {
        
    }

    private void ObjSwitcher(int i, String[] s)
    {
        if (s[i] == null ? GroupName == null : s[i].equals(GroupName))
        {
            if (groups = true && geomtryObjects_m.size() > 0)
            {
                NewGeoObj(s[i]);
            }
            else if (geomtryObjects_m.size() <= 0)
            {
                NewGeoObj(s[i]);
            }
        }
        else if (s[i] == null ? GeometicVertices == null : s[i].equals(GeometicVertices))
        {
            AddGeoVert(new float[]{Float.parseFloat(s[i+1]), Float.parseFloat(s[i+2]), Float.parseFloat(s[i+3])});
        }
        else if (s[i] == null ? Face == null : s[i].equals(Face))
        {
            if(s[i+1].lastIndexOf("/") != -1)
            {
                Vert[] f = new Vert[3];
                
                for (int j = 1; j < 4; j++) 
                {
                    String[] face = s[i+j].split("/");
                    Vert v = new Vert();
                    
                    if(!face[0].isEmpty())
                        v.x = Integer.parseInt(face[0]);
                    
                    if(!face[1].isEmpty())
                        v.y = Integer.parseInt(face[1]);
                    
                    if(!face[2].isEmpty())
                        v.z = Integer.parseInt(face[2]);
                    
                    f[j-1] = v;
                        
                }                
                
                AddFaces(f);
            }
            
        }
    }

    private void NewGeoObj(String s)
    {
        GLObjGeometry temp = new GLObjGeometry();
        temp.GroupName = s;
        geomtryObjects_m.add(temp);
    }
    
    private void AddGeoVert(float[] v)
    {
        GLObjGeometry temp = geomtryObjects_m.get(geomtryObjects_m.size()-1);
        temp.AddGeometryVert(v[0], v[1], v[2], 1);
    }
    
    private void AddFaces(Vert[] f)
    {
         GLObjGeometry temp = geomtryObjects_m.get(geomtryObjects_m.size()-1);
         temp.AddFace(f[0],f[1],f[2]);
    }
}
