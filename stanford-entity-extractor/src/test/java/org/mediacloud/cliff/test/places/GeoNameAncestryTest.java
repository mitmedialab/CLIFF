package org.mediacloud.cliff.test.places;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mediacloud.cliff.ParseManager;
import org.mediacloud.cliff.places.UnknownGeoNameIdException;

import com.bericotech.clavin.gazetteer.GeoName;

public class GeoNameAncestryTest {

    public static int GEONAME_MIT = 4943351;
    public static int GEONAME_CAMBRIDGE_CITY = 4932004;
    public static int GEONAME_MIDDLESEX_COUNTY = 4943909;
    public static int GEONAME_MASSACHUSETTS_STATE = 6254926;
    public static int GEONAME_USA = 6252001;

    @Test
    public void test() throws UnknownGeoNameIdException {
        GeoName mit = ParseManager.getGeoName(GEONAME_MIT);
        //assertTrue(mit.isAncestryResolved());
        GeoName mitParent1 = mit.getParent();
        assertEquals(GEONAME_CAMBRIDGE_CITY,mitParent1.getGeonameID());
        GeoName mitParent2 = mitParent1.getParent();
        assertEquals(GEONAME_MIDDLESEX_COUNTY,mitParent2.getGeonameID());
        GeoName mitParent3 = mitParent2.getParent();
        assertEquals(GEONAME_MASSACHUSETTS_STATE,mitParent3.getGeonameID());
        GeoName mitParent4 = mitParent3.getParent();
        assertEquals(GEONAME_USA,mitParent4.getGeonameID());
        GeoName mitParent5 = mitParent4.getParent();
        assertEquals(null,mitParent5);
    }

}
