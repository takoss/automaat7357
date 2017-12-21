package weathergetter.file;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import weathergetter.api.APIRequest;
import weathergetter.controller.CityOutputWriter;
import weathergetter.controller.Controller;
import weathergetter.weatherreport.WeatherReport;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

/**
 * Created by admin on 20.12.2017.
 */
public class InputOutput {

    @Test
    public void testReadIsSameAsWriteOneLine() {
        InputReader inp = new InputReader();
        OutputWriter out = new OutputWriter();
        out.write("eeeeee234", "outTest.txt");
        List<String> inpList = null;
        try {
            inpList = inp.readFromURL("outTest.txt").collect(Collectors
                    .toList());
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
        Assert.assertTrue(inpList.size() == 1);
        Assert.assertEquals("eeeeee234", inpList.get(0));
    }

    @Test
    public void testReadIsSameAsWriteFiveLines() {
        InputReader inp = new InputReader();
        OutputWriter out = new OutputWriter();
        String str = "a\nb\nc\nd\ne";
        out.write(str, "outTest.txt");
        List<String> inpList = null;
        try {
            inpList = inp.readFromURL("outTest.txt").collect(Collectors
                    .toList());
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
        Assert.assertEquals("a", inpList.get(0));
        Assert.assertEquals("b", inpList.get(1));
        Assert.assertEquals("c", inpList.get(2));
        Assert.assertEquals("d", inpList.get(3));
        Assert.assertEquals("e", inpList.get(4));
        Assert.assertEquals(5, inpList.size());
    }

    @Test
    public void testIfReadsThreeCitiesThenAlsoWritesTheThreeCities() throws
            IOException {
        InputReader inputReader = mock(InputReader.class);
        CityOutputWriter cityOutputWriter = mock(CityOutputWriter.class);
        Controller controller = new Controller(inputReader, cityOutputWriter);
        List<String> listToGive = Arrays.asList("Tartu", "Tallinn", "New "
                                                                    + "York");
        when(inputReader.readFromURL(anyString())).thenReturn(listToGive.stream
                ());

        controller.printInputCitiesInfoToOutput();

        verify(cityOutputWriter).writeCityInfoAsFile(ArgumentMatchers.eq
                ("Tartu"));
        verify(cityOutputWriter).writeCityInfoAsFile(ArgumentMatchers.eq
                ("Tallinn"));
        verify(cityOutputWriter).writeCityInfoAsFile(ArgumentMatchers.eq
                ("New York"));
        verify(cityOutputWriter, times(3)).writeCityInfoAsFile(any());
    }

    @Test
    public void testIfWriteCityInfoAsFileThenOnlyTheCityStringIsWritten()
            throws IOException {
        APIRequest apiRequest = mock(APIRequest.class);
        OutputWriter outputWriter = mock(OutputWriter.class);
        CityOutputWriter cityOutputWriter = new CityOutputWriter(
                outputWriter, apiRequest);

        WeatherReport report = mock(WeatherReport.class);
        when(report.getCityName()).thenReturn("Viljandi");
        when(report.toString()).thenReturn("ViljandiToString");
        when(apiRequest.getFullWeatherReport("Viljandi")).thenReturn(report);

        cityOutputWriter.writeCityInfoAsFile("Viljandi");

        verify(outputWriter).write(ArgumentMatchers.eq("ViljandiToString"),
                ArgumentMatchers.eq("cities\\Viljandi.txt"));
        verify(outputWriter, times(1)).write(any(), any());
    }
}
