package br.edu.puccampinas.reservanotebook.utils;

import java.time.*;
import java.util.Date;

public class DateUtils {

    public static LocalDateTime dateToLocalDateTime(Date date) {

        if(date==null) throw new IllegalArgumentException("Parâmetro 'date não pode ser nulo");

        OffsetDateTime utc = Instant.ofEpochMilli(date.getTime()).atOffset(ZoneOffset.of("+00:00"));
        return utc.toLocalDateTime();


    }
}
