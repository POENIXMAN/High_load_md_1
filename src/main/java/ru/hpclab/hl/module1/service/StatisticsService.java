package ru.hpclab.hl.module1.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import ru.hpclab.hl.module1.model.Student;

public class StatisticsService {


    @Value("${statisticsservice.infostring:lines}")
    private String infoString;

    final int delay;

    private final StudentService studentService;

    public StatisticsService(int delay, StudentService studentService) {
        this.delay = delay;
        this.studentService = studentService;
    }

    @Async(value = "applicationTaskExecutor")
    @Scheduled(fixedRateString = "${fixedRate.in.milliseconds}")
    public void scheduleFixedRateTaskAsync() throws InterruptedException {
        System.out.println(
                Thread.currentThread().getName() + " - Fixed rate task async - "+ delay + " - " + infoString + " - "
                        + studentService.getAllStudents().size());
        Thread.sleep(delay);
    }
}
