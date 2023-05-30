package teamproject.smokinglocation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class DataScheduler {

    @Scheduled(cron = "* * * 9,13 * *")
    public void dataFetch() {

    }
}
