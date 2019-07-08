package org.ridwan.fleet.parsers.location.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Calendar;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Location{
    private Double longitude;
    private Double latitude;
    private LocalDateTime utcTime;
    private ZonedDateTime localTime;
}
