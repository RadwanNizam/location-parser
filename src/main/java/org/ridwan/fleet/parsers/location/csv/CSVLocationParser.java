package org.ridwan.fleet.parsers.location.csv;

import org.ridwan.fleet.parsers.location.LocationParser;
import org.ridwan.fleet.parsers.location.TimeZoneRetrieval;
import org.ridwan.fleet.parsers.location.exception.InvalidLocationFormatExcetion;
import org.ridwan.fleet.parsers.location.model.Location;
import org.ridwan.fleet.parsers.location.timezone.TimeZoneDBZonRetrieval;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Date;

public class CSVLocationParser implements LocationParser{

    private static final int LOCATION_INFO_SIZE = 3;

    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";

    private static final int LATITUDE_MIN = -90;
    private static final int LATITUDE_MAX = +90;
    private static final int LONGITUDE_MIN = -180;
    private static final int LONGITUDE_MAX = +180;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final String INVALID_LOCATION = "%s is not a valid location";
    private static final String INVALID_TIME = "%s is not a valid time";
    private static final String INVALID_COORDINATE = "%s is not a valid %s";

    private TimeZoneRetrieval timeZoneRetrieval = new TimeZoneDBZonRetrieval();

    public Location parseLocation(String[] locationInfo) throws InvalidLocationFormatExcetion {
        if (locationInfo == null || locationInfo.length == 0) {
            throw new InvalidLocationFormatExcetion(String.format(INVALID_LOCATION, Arrays.toString(locationInfo)));
        }

        if(locationInfo.length<LOCATION_INFO_SIZE){
            throw new InvalidLocationFormatExcetion(String.format(INVALID_LOCATION, Arrays.toString(locationInfo)));
        }

        Location location = new Location();
        location.setLatitude(parseCoordinate(locationInfo[1], LATITUDE_MIN, LATITUDE_MAX, LATITUDE));
        location.setLongitude(parseCoordinate(locationInfo[2], LONGITUDE_MIN, LONGITUDE_MAX, LONGITUDE));

        addDates(locationInfo[0], location);
        return location;
    }

    private void addDates(String utcTimeStr, Location location) throws InvalidLocationFormatExcetion {
        try {
            ZoneId localTimeZone = timeZoneRetrieval.getZoneId(location.getLatitude(), location.getLongitude());
            ZoneId utcZoneID = ZoneId.of("Etc/UTC");

            //converting in datetime of java8
            LocalDateTime utcDateAndTime = LocalDateTime.parse(utcTimeStr.trim(), formatter);
            ZonedDateTime localZonedDateTime = ZonedDateTime.of(utcDateAndTime, utcZoneID).withZoneSameInstant(localTimeZone);

            // With Formating DateTime
            String utcDateTime = utcDateAndTime.format(formatter);
            String ausDateTime = localZonedDateTime.format(formatter);

            location.setUtcTime(utcDateAndTime);
            location.setLocalTime(localZonedDateTime);
        }catch(DateTimeParseException ex){
            throw new InvalidLocationFormatExcetion(String.format(INVALID_TIME, utcTimeStr));
        }
    }


    private Double parseCoordinate(String value, int min, int max, String coordinateName) throws InvalidLocationFormatExcetion {
        double coordinate;

        try {
            coordinate = Double.parseDouble(value.trim());
        }catch(NumberFormatException ex){
            throw new InvalidLocationFormatExcetion(String.format(INVALID_COORDINATE, value == "" ? "''" : value, coordinateName));
        }

        if (coordinate < min || coordinate > max){
            throw new InvalidLocationFormatExcetion(String.format(INVALID_COORDINATE, value, coordinateName));
        }

        return coordinate;
    }

}
