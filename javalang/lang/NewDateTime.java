package javalang.lang;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Set;

/*
https://www.baeldung.com/java-8-date-time-intro
 */
public class NewDateTime {
    public static void main(String[] args) {
        LocalDate l1 = LocalDate.parse("2015-02-20");
        ;
        LocalDate l2 = LocalDate.of(2023, 2, 2);
        l1.plusMonths(10);
        System.out.println(l1 + " l2 " + l2);
        l1.minus(1, ChronoUnit.DAYS);

        LocalTime lt1 = LocalTime.now();
        System.out.println(lt1);
        LocalTime lt2 = LocalTime.parse("06:30").plus(1, ChronoUnit.HOURS);
        System.out.println(lt2);

        LocalDateTime ldt1 = LocalDateTime.now();
        System.out.println(ldt1);

        ZoneId zoneId = ZoneId.of("Europe/Paris");
        Set<String> allZoneIds = ZoneId.getAvailableZoneIds();
        System.out.println(allZoneIds);

        LocalDateTime localDateTime = LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30);
        System.out.println(localDateTime);

        LocalDate initialDate = LocalDate.parse("2007-05-10");
        LocalDate finalDate = initialDate.plus(Period.ofDays(5));
        int days = Period.between(initialDate, finalDate).getDays();

        LocalTime initialTime = LocalTime.of(6, 30, 0);
        LocalTime finalTime = initialTime.plus(Duration.ofSeconds(30));
        long lapse = Duration.between(initialTime, finalTime).getSeconds();
        System.out.println(lapse);


    }
}
