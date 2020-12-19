package ru.stqa.pft.soap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.lavasoft.GetIpLocationResponse;
import net.webservicex.GeoIPService;
import net.webservicex.GetIpLocation;

/**
 * Description.
 *
 * @author lchernaya
 */
public class GeoIpServiceTests {

   @Test
   public void testMyIP() {
    String geoIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("77.50.186.38");
      Assert.assertEquals(geoIP, "RUS");
   }
}
