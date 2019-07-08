package org.ridwan.fleet.parsers.location;

import org.ridwan.fleet.parsers.location.exception.InvalidLocationFormatExcetion;
import org.ridwan.fleet.parsers.location.model.Location;

public interface LocationParser {
    public Location parseLocation(String[] locationInfo) throws InvalidLocationFormatExcetion;
}
