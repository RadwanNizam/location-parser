- The library uses the online tool timezonedb (https://timezonedb.com/) to find the timezone of the vehicle based on
the longitude and latitude.

- To build and package the project run the command : mvn clean compile assembly:single

- To run the project run the command : java -jar target/parsers-jar-with-dependencies.jar locations.csv output.csv
The parser will read the locations from the file specified by the first parameter (locations.csv in this example)
The parser will write the result to the file specified by the second parameter (output.csv in this example)
A log file will be generated to report any problems when parsing the input



