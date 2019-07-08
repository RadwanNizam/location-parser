package org.ridwan.fleet.parsers.location.timezone;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TimeZoneDBResponse implements Serializable{
    private String status;
    private String message;
    private String zoneName;
}
