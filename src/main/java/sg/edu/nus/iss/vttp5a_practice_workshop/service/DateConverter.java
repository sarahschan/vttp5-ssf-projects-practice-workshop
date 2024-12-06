package sg.edu.nus.iss.vttp5a_practice_workshop.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.stereotype.Service;

@Service
public class DateConverter {
    
    private static final ZoneId DEFAULT_ZONE = ZoneId.systemDefault();

    // String -> LocalDate
    public LocalDate stringToLocalDate(String date){
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH);
        String dateSkipDayOfWeek = date.substring(5);
        LocalDate parsedDate = LocalDate.parse(dateSkipDayOfWeek, formatter);

        return parsedDate;

        // Locale.English is only needed for the day of the week. 
        //  Since the file is broken, we removed the EEE so it's not really needed but I left it for reference
        //  The pattern will now ignore things like MON TUE WED and focus only on the MM/dd/yyyy
        // You need to manually skip the letters using substring
    }


    // LocalDate -> epoch milliseconds
    public long localDateToEpoch(LocalDate date){
        return date.atStartOfDay(DEFAULT_ZONE).toInstant().toEpochMilli();
            // LocalDate does not contain time information
            //      .asStartOfDay() sets the time to midnight, according to your system's ZoneID
            // Converting to Instant ensures we have a time that is independent of any local time zone issues
            // Then, convert to epoch milliseconds - a consistent way to represent time without time zones, leap years, or daylight savings
    }


    // epoch milliseconds -> LocalDate
    public LocalDate epochToLocalDate(long epochMillis){
        return LocalDate.ofInstant(Instant.ofEpochMilli(epochMillis), DEFAULT_ZONE);
            // LocalDate.ofInstant(....)
            //      converts an Instant into LocalDate
            //      this requires an Instant!
            // Instant.ofEpochMilli(epochMillis)
            //      creates the instant to be converted
            //      ZoneID helps ensure that the correct date is returned based on your system's time zone
    }


    // string -> epoch miliseconds
    public long stringToEpoch(String date){
        LocalDate localDate = stringToLocalDate(date);
        long epochMillis = localDateToEpoch(localDate);

        return epochMillis;
    }
}
