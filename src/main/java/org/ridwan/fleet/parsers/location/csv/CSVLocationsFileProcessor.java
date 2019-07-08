package org.ridwan.fleet.parsers.location.csv;

import com.opencsv.CSVReader;
import org.ridwan.fleet.parsers.location.LocationParser;
import org.ridwan.fleet.parsers.location.LocationsFileProcessor;
import org.ridwan.fleet.parsers.location.model.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CSVLocationsFileProcessor implements LocationsFileProcessor {

    private static final Logger logger
            = LoggerFactory.getLogger(CSVLocationsFileProcessor.class);
    private DateTimeFormatter utcTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private DateTimeFormatter localTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private LocationParser locationParser = new CSVLocationParser();

    public List<Location> parse(String fileLocation) throws IOException {
        List<Location> locations = new ArrayList<Location>();
        CSVReader csvReader = new CSVReader(new FileReader(fileLocation));

        if (csvReader == null) {
            throw new RuntimeException("can't access file " + fileLocation);
        }

        String[] locationInfo = null;
        while ((locationInfo = csvReader.readNext()) != null) {
            try {
                Location location = locationParser.parseLocation(locationInfo);
                locations.add(location);
            } catch (Exception ex) {
                logger.error("error in parsing location {}", locationInfo);
                logger.error(ex.getMessage(), ex);
            }
        }

        return locations;
    }

    @Override
    public void writeLocations(List<Location> locations, String outputFile) throws IOException {
        FileWriter writer = new FileWriter("output.csv");

        for (Location location : locations) {
            StringBuilder outputLine = new StringBuilder();
            outputLine.append(location.getUtcTime().format(utcTimeFormatter)).append(",").
                    append(String.valueOf(location.getLatitude())).append(",").
                    append(String.valueOf(location.getLongitude())).append(",").
                    append(location.getLocalTime().getZone().getId()).append(",").
                    append(location.getLocalTime().format(localTimeFormatter)).append(System.lineSeparator());

            writer.write(outputLine.toString());
        }

        writer.close();
    }
}
