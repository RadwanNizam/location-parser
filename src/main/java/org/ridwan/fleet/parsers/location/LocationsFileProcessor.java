package org.ridwan.fleet.parsers.location;

import org.ridwan.fleet.parsers.location.model.Location;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface LocationsFileProcessor {
    List<Location> parse(String fileLocation) throws IOException;

    void writeLocations(List<Location> locations, String outputFile) throws IOException;
}
