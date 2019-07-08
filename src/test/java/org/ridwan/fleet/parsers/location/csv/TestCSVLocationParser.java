package org.ridwan.fleet.parsers.location.csv;

import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;
import org.ridwan.fleet.parsers.location.exception.InvalidLocationFormatExcetion;

@FixMethodOrder(MethodSorters.DEFAULT)
public class TestCSVLocationParser {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private CSVLocationParser locationParser = new CSVLocationParser();

    @Test
    public void testParseLocation_failed_empty_csv() throws InvalidLocationFormatExcetion {
        exception.expect(InvalidLocationFormatExcetion.class);
        exception.expectMessage(Matchers.containsString("is not a valid location"));

        String[] csv = new String[]{};

        locationParser.parseLocation(csv);
    }

    @Test
    public void testParseLocation_failed_null_csv() throws InvalidLocationFormatExcetion {
        exception.expect(InvalidLocationFormatExcetion.class);
        exception.expectMessage(Matchers.containsString("is not a valid location"));

        String[] csv = null;
        locationParser.parseLocation(csv);
    }

    @Test
    public void testParseLocation_failed_incomplete_csv_info() throws InvalidLocationFormatExcetion {
        exception.expect(InvalidLocationFormatExcetion.class);
        exception.expectMessage(Matchers.containsString("is not a valid location"));

        String[] csv = new String[]{"2013-07-10 02:52:49","-44.490947"};
        locationParser.parseLocation(csv);
    }

    @Test
    public void testParseLocation_failed_invalid_utc_time_sc1() throws InvalidLocationFormatExcetion {
        exception.expect(InvalidLocationFormatExcetion.class);
        exception.expectMessage(Matchers.containsString("2013-07-10 is not a valid time"));

        String[] csv = new String[]{"2013-07-10","-44.490947", "171.220966"};
        locationParser.parseLocation(csv);
    }

    @Test
    public void testParseLocation_failed_invalid_utc_time_sc2() throws InvalidLocationFormatExcetion {
        exception.expect(InvalidLocationFormatExcetion.class);
        exception.expectMessage(Matchers.containsString("2013-07 02:52:49 is not a valid time"));

        String[] csv = new String[]{"2013-07 02:52:49","-44.490947", "171.220966"};
        locationParser.parseLocation(csv);
    }

    @Test
    public void testParseLocation_failed_invalid_utc_time_sc3() throws InvalidLocationFormatExcetion {
        exception.expect(InvalidLocationFormatExcetion.class);
        exception.expectMessage(Matchers.containsString(" is not a valid time"));

        String[] csv = new String[]{"","-44.490947", "171.220966"};
        locationParser.parseLocation(csv);
    }

    @Test
    public void testParseLocation_failed_invalid_latitude_empty() throws InvalidLocationFormatExcetion {
        exception.expect(InvalidLocationFormatExcetion.class);
        exception.expectMessage(Matchers.containsString(" is not a valid latitude"));

        String[] csv = new String[]{"2013-07-10 02:52:49","", "171.220966"};
        locationParser.parseLocation(csv);
    }

    @Test
    public void testParseLocation_failed_invalid_latitude_exceed_range_sc1() throws InvalidLocationFormatExcetion {
        exception.expect(InvalidLocationFormatExcetion.class);
        exception.expectMessage(Matchers.containsString("-100 is not a valid latitude"));

        String[] csv = new String[]{"2013-07-10 02:52:49","-100", "171.220966"};
        locationParser.parseLocation(csv);
    }

    @Test
    public void testParseLocation_failed_invalid_latitude_exceed_range_sc2() throws InvalidLocationFormatExcetion {
        exception.expect(InvalidLocationFormatExcetion.class);
        exception.expectMessage(Matchers.containsString("100 is not a valid latitude"));

        String[] csv = new String[]{"2013-07-10 02:52:49","+100", "171.220966"};
        locationParser.parseLocation(csv);
    }

    @Test
    public void testParseLocation_failed_invalid_latitude_format() throws InvalidLocationFormatExcetion {
        exception.expect(InvalidLocationFormatExcetion.class);
        exception.expectMessage(Matchers.containsString("invalid-value is not a valid latitude"));

        String[] csv = new String[]{"2013-07-10 02:52:49","invalid-value", "171.220966"};
        locationParser.parseLocation(csv);
    }

    @Test
    public void testParseLocation_failed_invalid_longitude_empty() throws InvalidLocationFormatExcetion {
        exception.expect(InvalidLocationFormatExcetion.class);
        exception.expectMessage(Matchers.containsString(" is not a valid longitude"));

        String[] csv = new String[]{"2013-07-10 02:52:49","-44.490947", ""};
        locationParser.parseLocation(csv);
    }

    @Test
    public void testParseLocation_failed_invalid_longitude_exceed_range_sc1() throws InvalidLocationFormatExcetion {
        exception.expect(InvalidLocationFormatExcetion.class);
        exception.expectMessage(Matchers.containsString("-190 is not a valid longitude"));

        String[] csv = new String[]{"2013-07-10 02:52:49","-44.490947", "-190"};
        locationParser.parseLocation(csv);
    }

    @Test
    public void testParseLocation_failed_invalid_longitude_exceed_range_sc2() throws InvalidLocationFormatExcetion {
        exception.expect(InvalidLocationFormatExcetion.class);
        exception.expectMessage(Matchers.containsString("190 is not a valid longitude"));

        String[] csv = new String[]{"2013-07-10 02:52:49","-44.490947", "190"};
        locationParser.parseLocation(csv);
    }

    @Test
    public void testParseLocation_failed_invalid_longitude_invalid_format() throws InvalidLocationFormatExcetion {
        exception.expect(InvalidLocationFormatExcetion.class);
        exception.expectMessage(Matchers.containsString("invalid-value is not a valid longitude"));

        String[] csv = new String[]{"2013-07-10 02:52:49","-44.490947", "invalid-value"};
        locationParser.parseLocation(csv);
    }



    @Test
    public void testParseLocation_success() throws InvalidLocationFormatExcetion {
        String[] csv = new String[]{"2013-07-10 02:52:49","-44.490947","171.220966"};
        locationParser.parseLocation(csv);
    }
}
