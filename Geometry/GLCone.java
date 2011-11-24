package Phi.Geometry;
/**
 * 
 */

/**
 * @author mbranton
 *
 */
public class GLCone extends GLParametricSurface 
{
	private static final double TWO_PI=2*Math.PI;
	
        /**
         * 
         * @param udiv
         * @param vdiv
         */
        public GLCone(int udiv,int vdiv)
	{
		super(udiv,vdiv);
	}
	
	/* (non-Javadoc)
	 * @see GLParametricSurface#x(int, int)
	 */
        /**
         * 
         * @param u
         * @param v
         * @return
         */
        @Override
	protected float x(int u, int v) 
	{
		return (float)(u*Math.cos(TWO_PI*v/((float)this.vdiv))/((float)this.udiv));
	}

	/* (non-Javadoc)
	 * @see GLParametricSurface#y(int, int)
	 */
        /**
         * 
         * @param u
         * @param v
         * @return
         */
        @Override
	protected float y(int u, int v) 
	{
		return (float)(u/(float)(this.udiv));
	}

	/* (non-Javadoc)
	 * @see GLParametricSurface#z(int, int)
	 */
        /**
         * 
         * @param u
         * @param v
         * @return
         */
        @Override
	protected float z(int u, int v) 
	{
		return (float)(u*Math.sin(TWO_PI*v/((float)this.vdiv))/((float)this.udiv));
	}
}
