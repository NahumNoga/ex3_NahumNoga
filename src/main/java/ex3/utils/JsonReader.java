package ex3.utils;

import org.hibernate.boot.spi.InFlightMetadataCollector;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * class JsonReader
 * utility class that read json data from urlPath
 */
public class JsonReader {

    /**
     * read data line by line from stream
     * @param rd- the stream that we read tha data from him
     * @return string of the data
     * @throws IOException if cannot read from the stream
     */
    private static String readAll(BufferedReader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    /**
     * read json data from url
     * add flag if succeed to read
     * @param url- string of urlPath
     * @return jsonObject of the data
     * @throws IOException if cannot read from the stream
     * @throws JSONException if error with the building of the json
     */
    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        try (InputStream is = new URL(url).openStream()){

            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            // add flag that succeed
            json.put("success", "1");
            return json;

        } catch (Exception e){
            //there is a problem so flag not success and msg
            return new JSONObject("{\"success\": " + "0," +
                                      "\"errorMsg\":\"user not found\"}");
        }

    }
}
