import weathergetter.api.openweatherrequest.GetWeatherReport;
import weathergetter.api.openweatherrequest.ServerJSONRequest;
import weathergetter.controller.CityOutputWriter;
import weathergetter.file.InputReader;
import weathergetter.file.OutputWriter;
import weathergetter.controller.Controller;

/**
 * Created by admin on 20.12.2017.
 */
public class main {

    public static void main(String[] args) {
        Controller controller = new Controller(new InputReader(),
                new CityOutputWriter(
                        new OutputWriter(), new GetWeatherReport(new ServerJSONRequest())
                ));
        controller.printInputCitiesInfoToOutput();

        //weathergetter.controller.weatherRequestLoop();
    }
}
