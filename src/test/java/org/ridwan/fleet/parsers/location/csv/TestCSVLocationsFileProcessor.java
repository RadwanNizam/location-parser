package org.ridwan.fleet.parsers.location.csv;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.ridwan.fleet.parsers.location.exception.InvalidLocationFormatExcetion;

import java.io.IOException;

public class TestCSVLocationsFileProcessor {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testParse_failed_invalid_file_location() throws IOException {
        exception.expect(IOException.class);
        new CSVLocationsFileProcessor().parse("invalid/file/location");
    }

    @Test
    public void testParse_success_empty_file() throws IOException {
        new CSVLocationsFileProcessor().parse(this.getClass().getClassLoader().getResource("empty_file.csv").getFile());
    }

    @Test
    public void testParse_success_some_lines_invalid() throws IOException {
        new CSVLocationsFileProcessor().parse(this.getClass().getClassLoader().getResource("locations.csv").getFile());
    }

}
