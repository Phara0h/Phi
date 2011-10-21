package Phi.ImportOBJ;


import java.io.*;
import java.util.ArrayList;
import Phi.Geometry.*;
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
    public boolean groups = false;

    public XObjParse()
    {
    }

    public void TestReadFile()
    {
        String data;
        try
        {
            FileInputStream fstream = new FileInputStream("/home/phara0h/Documents/CS310/test.obj");
            DataInputStream dstream = new DataInputStream(fstream);
            BufferedReader breader = new BufferedReader(new InputStreamReader(dstream));

            while ((data = breader.readLine()) != null)
            {
                if (data.charAt(0) != '#')
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

    private void ObjSwitcher(int i, String[] s)
    {
        if (s[i] == GroupName)
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
        else if (s[i] == GeometicVertices)
        {
            AddGeoVerts(new int[]{Integer.parseInt(s[i+1]), Integer.parseInt(s[i+2], Integer.parseInt(s[i+3]))});
        }
    }

    private void NewGeoObj(String s)
    {
        GLObjGeometry temp = new GLObjGeometry();
        temp.GroupName = s;
        geomtryObjects_m.add(temp);
    }
    
    private void AddGeoVerts(int[] v)
    {
        GLObjGeometry temp = geomtryObjects_m.get(geomtryObjects_m.size()-1);
        temp.AddVert(v[0], v[1], v[2], 1);
    }
    
    private void AddFaces(int[] f)
    {
         GLObjGeometry temp = geomtryObjects_m.get(geomtryObjects_m.size()-1);
         temp.AddFace(f[0], f[1], f[2], f[3]);
    }
}
