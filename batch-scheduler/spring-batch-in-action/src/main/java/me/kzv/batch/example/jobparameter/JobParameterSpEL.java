package me.kzv.batch.example.jobparameter;

public interface JobParameterSpEL {
    String LOCAL_DATE = "#{ T(java.time.LocalDate).parse(jobParameters[createDate], T(java.time.format.DateTimeFormatter).ofPattern('yyyy-MM-dd'))}";

}
