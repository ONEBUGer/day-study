package cn.day.test;
/*
 * Utility functions to transform between wgs84 and google projection coordinates
 * Derived from openmap http://openmap.bbn.com/
 */

public class MercatorTransform{
    public final static double NORTH_POLE = 90.0;
    public final static double SOUTH_POLE = -NORTH_POLE;
    public final static double DATELINE = 180.0;
    public final static double LON_RANGE = 360.0;

    final public static transient double wgs84_earthEquatorialRadiusMeters_D = 6378137.0;
    private static double latfac = wgs84_earthEquatorialRadiusMeters_D;
    private static double lonfac = wgs84_earthEquatorialRadiusMeters_D;

    final public static transient double HALF_PI_D = Math.PI / 2.0d;

    /**
     * Returns google projection coordinates from wgs84 lat,long coordinates
     */
    public static double[] forward(double lat, double lon) {

        lat = normalizeLatitude(lat);
        lon = wrapLongitude(lon);

        double latrad = Math.toRadians(lat);
        double lonrad = Math.toRadians(lon);

        double lat_m = latfac * Math.log(Math.tan(((latrad + HALF_PI_D) / 2d)));
        double lon_m = lonfac * lonrad;

        double[] x = { lon_m, lat_m };
        return x;
    }

    /**
     * Returns wgs84 lat,long coordinates from google projection coordinates
     */
    public static float[] inverse(float lon_m, float lat_m) {
        double latrad = (2d * Math.atan(Math.exp(lat_m / latfac))) - HALF_PI_D;
        double lonrad = lon_m / lonfac;

        double lat = Math.toDegrees(latrad);
        double lon = Math.toDegrees(lonrad);

        lat = normalizeLatitude(lat);
        lon = wrapLongitude(lon);
        float[] x = { (float) lat, (float) lon };

        return x;
    }

    private static double wrapLongitude(double lon) {
        if ((lon < -DATELINE) || (lon > DATELINE)) {
            lon += DATELINE;
            lon = lon % LON_RANGE;
            lon = (lon < 0) ? DATELINE + lon : -DATELINE + lon;
        }
        return lon;
    }

    private static double normalizeLatitude(double lat) {
        if (lat > NORTH_POLE) {
            lat = NORTH_POLE;
        }
        if (lat < SOUTH_POLE) {
            lat = SOUTH_POLE;
        }
        return lat;
    }

}