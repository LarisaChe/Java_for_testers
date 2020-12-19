package ru.stqa.pft.soap;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLReporter;
import javax.xml.stream.XMLResolver;

import org.testng.Assert;
import org.testng.TestNGException;
import org.testng.annotations.Test;
import org.testng.xml.XMLParser;
import org.w3c.dom.NodeList;

import com.lavasoft.GeoIPService;
import com.lavasoft.GetIpLocationResponse;
import com.thoughtworks.xstream.XStream;
import net.webservicex.GetIpLocation;
import ru.stqa.pft.model.GeoIP;

/**
 * Description.
 *
 * @author lchernaya
 */
public class GeoIpServiceTests {

   @Test
   public void testMyIP() {

    //String geoIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("77.50.186.38");
      String geoIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("77.50.186.38");
      System.out.println("geoIP: "+geoIP);

     /*String xml = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("77.50.186.38");
     System.out.println("xml: "+xml);
      XStream xStream = new XStream();
      xStream.processAnnotations(GeoIP.class);
      List<GeoIP> geoIPs = (List<GeoIP>) xStream.fromXML(xml);
      geoIPs.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
      System.out.println("geoIP: "+geoIPs);
      Assert.assertEquals(geoIPs.get(0).getCountry(), "RU"); */
      Assert.assertTrue(geoIP.contains("<Country>RU</Country>"));
   }
}
