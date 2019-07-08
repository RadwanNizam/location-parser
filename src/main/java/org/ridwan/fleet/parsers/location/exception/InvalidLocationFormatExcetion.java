package org.ridwan.fleet.parsers.location.exception;

public class InvalidLocationFormatExcetion extends Exception{
    public InvalidLocationFormatExcetion(String s) {
        super(s);
    }

    public InvalidLocationFormatExcetion(String s, Throwable throwable) {
        super(s, throwable);
    }
}
