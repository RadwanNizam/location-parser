package org.ridwan.fleet.parsers.location;

import java.time.ZoneId;
import java.util.TimeZone;

public interface TimeZoneRetrieval {
    ZoneId getZoneId(Double latitude, Double longitude);
}
