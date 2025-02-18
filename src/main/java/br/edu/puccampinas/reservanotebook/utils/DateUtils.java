package br.edu.puccampinas.reservanotebook.utils;

import java.time.*;
import java.util.Date;

public class DateUtils {

    public static LocalDateTime dateToLocalDateTime(Date date) {
        try {
            OffsetDateTime utc = Instant.ofEpochMilli(date.getTime()).atOffset(ZoneOffset.of("+00:00"));
            return utc.toLocalDateTime();
        }
        catch (DateTimeException e) {
            throw e;
        }

    }
}
