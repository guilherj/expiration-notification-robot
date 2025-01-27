package br.com.expirationNotificationRobot.services;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ScheduleService {

	@Value("${app.schedule.cron.every.day.9am}")
	private String cronExpressionEveryDay;
	
	private final ExecutorService taskExecutor = Executors.newSingleThreadExecutor();

    private ThreadPoolTaskScheduler taskScheduler;

    // Inicia o agendador após a criação do bean
    @PostConstruct
    public void init() {
        taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.initialize();

        // Agenda a execução do método `executetask` usando a expressão cron carregada
        taskScheduler.schedule(this::executetask, new CronTrigger(cronExpressionEveryDay));
    }

	public void executetask() {

		log.info("Running EVERY DAY schedule tasks");
		taskExecutor.submit(this::TaskNotifyExpiredClients);
		taskExecutor.submit(this::TaskNotifyClientsDueToday);
		taskExecutor.submit(this::TaskNotifyClientsBeforeDueDate);

	}

	private void TaskNotifyExpiredClients() {
		log.info("Notifying Expired Clients.");

		try {

			Thread.sleep(3000); // Simula tempo de execução em milisegundos

			log.info("Expired clients successfully notified");

		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			log.error("Error TaskNotifyExpiredClients");
		}

	}

	private void TaskNotifyClientsDueToday() {
		log.info("Notification to clients due today.");

		try {

			Thread.sleep(3000); 

			log.info("Clients expiring today successfully notified");

		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			log.error("Error TaskNotifyClientsDueToday");
		}

	}

	private void TaskNotifyClientsBeforeDueDate() {
		log.info("Notification of clients who are yet to win.");

		try {

			Thread.sleep(3000); 

			log.info("Clients who are yet to win have been successfully notified");

		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			log.error("Error TaskNotifyClientsBeforeDueDate");
		}

	}

}
