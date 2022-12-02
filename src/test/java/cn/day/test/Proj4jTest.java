package cn.day.test;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.osgeo.proj4j.*;
import org.osgeo.proj4j.parser.Proj4Parser;

import java.awt.geom.Point2D;

/**
 * @author ZhengChangBing
 * @Date 2022/12/1 18:02
 * @Description 坐标转换练习
 */
@Slf4j
public class Proj4jTest {

    static final String WGS84_PARAM = "+title=long/lat:WGS84 +proj=longlat +datum=WGS84 +units=degrees";
    CoordinateReferenceSystem WGS84 = crsFactory.createFromParameters("WGS84",
            WGS84_PARAM);

    private static final CoordinateTransformFactory ctFactory = new CoordinateTransformFactory();
    private static final CRSFactory crsFactory = new CRSFactory();

    private static CoordinateReferenceSystem createCRS(String crsSpec) {
        CoordinateReferenceSystem crs = null;
        // test if name is a PROJ4 spec
        if (crsSpec.indexOf("+") >= 0 || crsSpec.indexOf("=") >= 0) {
            crs = crsFactory.createFromParameters("Anon", crsSpec);
        } else {
            crs = crsFactory.createFromName(crsSpec);
        }
        // crs = crsFactory.createFromParameters("Anon", crsSpec);

        return crs;
    }

    public static void main(String[] args) {
        // new CoordinateTransformTester(true).checkTransform("EPSG:4269",
        // 117.19625, 31.83879,
        // "+proj=tmerc +lat_0=0 +lon_0=117 +y_0=0 +x_0=500000 +k=0.9996 +pm=0
        // +zone=50 +to_meter=1 +a=6378137 +rf=298.257223563 +nodefs",
        // 1640416.667, 916074.825, 0.1);


//		lonlat2m();
//        m2lonlat();
//        String oldStr = "[-76926,-22834]";
//        String newStr = "[104.055679,30.587002]";
//        coordinateTest(oldStr, newStr);
        changeEPSG();
    }

    /**
     * 坐标转换方式  本地坐标-中间的坐标-经纬度坐标
     */
    private static void changeEPSG(){
        double x = 117.19625d;
        double y = 31.83879d;
        double x1 = 518568.9d;
        double y1 = 3523083.9d;
        String sourceCRS = "EPSG:4326";
        String targetCRS = "EPSG:32648";
        CoordinateTransform transform = ctFactory.createTransform(createCRS(sourceCRS), createCRS(targetCRS));
        ProjCoordinate targetCoordinate = new ProjCoordinate();
        ProjCoordinate sourceCoordinate = new ProjCoordinate(x, y);
        transform.transform(sourceCoordinate, targetCoordinate);
        double x2 = targetCoordinate.x;
        System.out.println("x2参数：" + x2);
        double y2 = targetCoordinate.y;
        System.out.println("y2参数：" + y2);

        CoordinateTransform transform1 = ctFactory.createTransform(createCRS(targetCRS), createCRS(sourceCRS));
        ProjCoordinate projCoordinate = new ProjCoordinate();
        transform1.transform(targetCoordinate, projCoordinate);
        System.out.println(projCoordinate.x);
        System.out.println(projCoordinate.y);

        double xChange = x1 - x2;
        double yChange = y1 - y2;



    }

    private static void lonlat2m() {
        System.out.println("....");

        // 117.19625 31.83879 518568.9 3522583.9
        double x1 = 117.19625d;
        double y1 = 31.83879d;
        // double x2 = 518568.9d;
        // double y2 = 3522583.9d;

        // String srcCRS = "EPSG:4269";
        String WGS84_PARAM = "+title=long/lat:WGS84 +proj=longlat +datum=WGS84 +units=degrees";
        String tgtCRS = "+proj=tmerc +lat_0=0 +lon_0=117 +y_0=0 +x_0=500000 +k=0.9996 +zone=50 +to_meter=1 +a=6378137 +ellps=WGS84 +units=m +no_defs";

        CoordinateTransform trans = ctFactory
                .createTransform(createCRS(WGS84_PARAM), createCRS(tgtCRS));
        ProjCoordinate pout = new ProjCoordinate();

        ProjCoordinate p = new ProjCoordinate(x1, y1);

        trans.transform(p, pout);

        System.out.println(p.x);
        System.out.println(p.y);
        System.out.println(pout.x);
        System.out.println(pout.y);

        System.out.println("-------		// 117.19625 31.83879 518568.9 3522583.9 ");
        p = new ProjCoordinate(y1, x1);
        trans.transform(p, pout);

//		System.out.println(p.x);
//		System.out.println(p.y);
//		System.out.println(pout.x);
//		System.out.println(pout.y);
    }


    private static void m2lonlat() {
        System.out.println("....");

        // 117.19625 31.83879 518568.9 3522583.9
//		double x2 = 117.19625d;
//		double y2 = 31.83879d;
//        double x1 = 518568.9d;
//        double y1 = 3522583.9d;
        double x1 = 518568.9d;
        double y1 = 3523083.9d;

        // String srcCRS = "EPSG:4269";
        String WGS84_PARAM = "+title=long/lat:WGS84 +proj=longlat +datum=WGS84 +units=degrees";
        String tgtCRS = "+proj=tmerc +lat_0=0 +lon_0=117 +y_0=0 +x_0=500000 +k=0.9996 +zone=50 +to_meter=1 +a=6378137 +ellps=WGS84 +units=m +no_defs";


        CoordinateTransform trans = ctFactory
                .createTransform(createCRS(tgtCRS), createCRS(WGS84_PARAM));
        ProjCoordinate pout = new ProjCoordinate();

        ProjCoordinate p = new ProjCoordinate(x1, y1);

        trans.transform(p, pout);

        System.out.println(p.x);
        System.out.println(p.y);
        System.out.println(pout.x);
        System.out.println(pout.y);

        System.out.println("-------		// 117.19625 31.83879 518568.9 3522583.9 ");
        p = new ProjCoordinate(y1, x1);
        trans.transform(p, pout);

//		System.out.println(p.x);
//		System.out.println(p.y);
//		System.out.println(pout.x);
//		System.out.println(pout.y);
    }

    private static void coordinateTest(String coordinates, String tgtCoordinates){
        Double[] doubles = JSONObject.parseObject(coordinates, Double[].class);
        Double[] tgtDoubles = JSONObject.parseObject(tgtCoordinates, Double[].class);
        Double newX = tgtDoubles[0];
        Double newY = tgtDoubles[1];
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("+proj=tmerc +ellps=WGS84 +no_defs").append(" +lat_0=").append(newX).append(" +lon_0=").append(newY);
        String tgtCRS = stringBuilder.toString();
        System.out.println(tgtCRS);
        ProjCoordinate projCoordinate = new ProjCoordinate();
        Double oldX = doubles[0];
        Double oldY = doubles[1];
        ProjCoordinate coordinate = new ProjCoordinate(oldX, oldY);
        CoordinateTransform transform = ctFactory.createTransform(createCRS(tgtCRS), createCRS(WGS84_PARAM));

        String oldStr = "[-80766,-27886]";
        Double[] parseObject = JSONObject.parseObject(oldStr, Double[].class);
        Double aDouble = parseObject[0];
        Double bDouble = parseObject[1];
        ProjCoordinate coordinateNew = new ProjCoordinate(aDouble, bDouble);
        transform.transform(coordinateNew, projCoordinate);
        log.info("转换后坐标信息x:{},y:{}", projCoordinate.x, projCoordinate.y);

        Point2D.Double point2D = new Point2D.Double(-80766, -27886);

    }
}
