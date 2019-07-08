Location Parser

This project can be used to parse a CSV file of locations, the locations in the input file should be of this format:
UTC Time,longitude, latitude

After parsing the file, the library will search for the Timezone of the location (using the lat and lng). I use the online library TimezoneDB (https://timezonedb.com/). Before start using this project, make sure to use your own API key for TimezoneDB (currently it is hardcoded in the class TimeZoneDBZonRetrieval.java)

To build and package the project run the command : mvn clean compile assembly:single

To run the project run the command : java -jar target/parsers-jar-with-dependencies.jar locations.csv output.csv
The parser will read the locations from the file specified by the first parameter (locations.csv in this example)
The parser will write the result to the file specified by the second parameter (output.csv in this example)
A log file will be generated to report any problems when parsing the input



