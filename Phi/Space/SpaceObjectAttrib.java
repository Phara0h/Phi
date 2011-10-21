package Phi.Space;

/**
 *
 * @author Phara0h
 */
public class SpaceObjectAttrib
{
    float perihelion; // in units of AU
    float meanRadius; // number of earths
    float siderealRotation; // In earth days
    float axialTilt; // degrees
    float orbitalPeriod; // In earth years
    float eccentricity;
    
    /**
     * 
     * @param perihelion
     * @param radius
     * @param rotation
     * @param tilt
     * @param orbital
     * @param eccentricity
     */
    public SpaceObjectAttrib(float perihelion,float radius,float rotation,float tilt,float orbital,float eccentricity)
    {
        this.perihelion = perihelion;
        meanRadius = radius;
        siderealRotation = rotation;
        axialTilt = tilt;
        orbitalPeriod = orbital;
        this.eccentricity = eccentricity;
    }
    
}
            