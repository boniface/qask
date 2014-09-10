import org.joda.time.{DateTimeConstants, DateTime}

DateTime.now.withTimeAtStartOfDay().withDayOfWeek(DateTimeConstants.SUNDAY).minusDays(7).toDate