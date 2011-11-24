package Phi.ImportOBJ;


import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import Phi.*;
import Phi.Collison.PhiRectangleBounds;
import Phi.Geometry.Face;
import Phi.Geometry.PhiObjGeometry;
import Phi.Geometry.Vert;
import Phi.Objects.PhiObject;
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
    private ArrayList<PhiObject>geomtryObjects_m = new ArrayList<PhiObject>();
    private boolean hasV, hasVN, hasVT, facesEnd;
    
    // Options
    public boolean multiObject = true; // set to false if you want all objects merged.
    
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

    
    public void OpenFile(String fpath)
    {
        String data;
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
                        //System.out.print(splitData[i] + "\n");
                        ObjSwitcher(i, splitData);
                    }
                }
                else if(!data.isEmpty() && data.charAt(0) == '#')
                {
                     String[] splitData = data.split("\\s+");

                    if(splitData.length > 1 && "object".equals(splitData[1]))
                    {
                        if(multiObject || geomtryObjects_m.size() < 1)
                        NewGeoObj(splitData[2]);
                        System.out.print("Loading: "+splitData[2] + "\n");
                    }
                       
                   
                }

            }
            



            dstream.close();
            fstream.close();
            breader.close();
        }
        catch (Exception e)
        {
            
        }
            for (Iterator<PhiObject> it = geomtryObjects_m.iterator(); it.hasNext();) 
            {
                PhiObjGeometry gLObjGeometry = it.next();
                gLObjGeometry.Construct();
                
            }
        
    }
    
    
    
    public PhiObject[] GetObjects()
    {
        PhiObject[] temp = new PhiObject[geomtryObjects_m.size()];
        for (int i = 0; i < temp.length; i++)
        {
            temp[i] = geomtryObjects_m.get(i);
        }
        return temp;
    }
    

    private void ObjSwitcher(int i, String[] s)
    {
        if (s[i] == null ? GroupName == null : s[i].equals(GroupName))
        {
             
        }
        else if (s[i] == null ? GeometicVertices == null : s[i].equals(GeometicVertices))
        {
            hasV = true;
            AddGeoVert(new float[]{Float.parseFloat(s[i+1]), Float.parseFloat(s[i+2]), Float.parseFloat(s[i+3])});
        }
        else if (s[i] == null ? VertexNormals == null : s[i].equals(VertexNormals))
        {
            hasVN = true;
            AddNorVert(new float[]{Float.parseFloat(s[i+1]), Float.parseFloat(s[i+2]), Float.parseFloat(s[i+3])});
        }
        else if (s[i] == null ? TextureVertices == null : s[i].equals(TextureVertices))
        {
            hasVT = true;
            AddTexVert(new float[]{Float.parseFloat(s[i+1]), Float.parseFloat(s[i+2]), Float.parseFloat(s[i+3])});
        }
        else if (s[i] == null ? Face == null : s[i].equals(Face))
        {
            if(s[i+1].lastIndexOf("/") != -1)
            {
                Face f = new Face();
                f.init(9);
                for (int j = 1; j < 4; j++) 
                {
                    String[] face = s[i+j].split("/");
                    Vert v = new Vert();
                    
                    if(!face[0].isEmpty())
                        f.addFaceIndex(Integer.parseInt(face[0]));
                    
                    if(!face[1].isEmpty())
                        f.addFaceIndex(Integer.parseInt(face[1]));
                    
                    if(!face[2].isEmpty())
                        f.addFaceIndex(Integer.parseInt(face[2]));   
                }                
                
                AddFace(f);
            }
            
        }
    }

    private void NewGeoObj(String s)
    {
        PhiRectangleBounds bounds = new PhiRectangleBounds();
        PhiObject temp = new PhiObject(bounds);
        temp.GroupName = s;
        geomtryObjects_m.add(temp);
    }
    
    private void AddGeoVert(float[] v)
    {
        PhiObjGeometry temp = geomtryObjects_m.get(geomtryObjects_m.size()-1);
        temp.AddGeometryVert(v[0], v[1], v[2], 1);
    }

    private void AddNorVert(float[] f)
    {
        PhiObjGeometry temp = geomtryObjects_m.get(geomtryObjects_m.size()-1);
        temp.AddNormalVert(f[0], f[1], f[2], 0);
    }

    private void AddTexVert(float[] f)
    {
        PhiObjGeometry temp = geomtryObjects_m.get(geomtryObjects_m.size()-1);
        temp.AddTextureVert(f[0], f[1], f[2], 0);
    }
    
    private void AddFace(Face f)
    {
         PhiObjGeometry temp = geomtryObjects_m.get(geomtryObjects_m.size()-1);
         temp.faces.add(f);
    }
}
