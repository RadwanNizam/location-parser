package org.ridwan.fleet.parsers.location.timezone;

import org.ridwan.fleet.parsers.location.TimeZoneRetrieval;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.ZoneId;
import java.util.TimeZone;

public class TimeZoneDBZonRetrieval implements TimeZoneRetrieval{

    public ZoneId getZoneId(Double latitude, Double longitude) {
        RestTemplate restTemplate = new RestTemplate();

        StringBuilder request = new StringBuilder("http://vip.timezonedb.com/v2.1/get-time-zone?key=");
        request.append("4I9X2TT4PVCU").append("&format=json&fields=zoneName&by=position&lat=").
                append(latitude).append("&lng=").append(longitude);
        TimeZoneDBResponse response = restTemplate.getForObject(request.toString()
                    , TimeZoneDBResponse.class);

        return ZoneId.of(response.getZoneName());
    }

}
