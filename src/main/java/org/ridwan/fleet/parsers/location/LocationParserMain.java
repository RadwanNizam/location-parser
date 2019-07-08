package org.ridwan.fleet.parsers.location;

import org.ridwan.fleet.parsers.location.csv.CSVLocationsFileProcessor;
import org.ridwan.fleet.parsers.location.model.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class LocationParserMain {
    private static final Logger logger
            = LoggerFactory.getLogger(LocationParserMain.class);

    public static void main(String args[]) {
        String inputFile = args[0];
        String outputFile = args[1];
        LocationsFileProcessor fileProcessor = new CSVLocationsFileProcessor();
        List<Location> locations;

        try {
           locations = fileProcessor.parse(inputFile);
        } catch (Exception ex) {
            logger.error("error in parsing the file", ex);
            return;
        }

        try {
            fileProcessor.writeLocations(locations, outputFile);
        } catch (Exception ex) {
            logger.error("error while writing the csv file :" + ex.getMessage(), ex);
        }
    }
}