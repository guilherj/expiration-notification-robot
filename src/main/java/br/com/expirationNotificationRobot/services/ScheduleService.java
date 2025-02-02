package br.com.expirationNotificationRobot.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.expirationNotificationRobot.constants.NotificationRobotConstants;
import br.com.expirationNotificationRobot.domains.Client;
import br.com.expirationNotificationRobot.domains.Notification;
import br.com.expirationNotificationRobot.domains.NotificationHistory;
import br.com.expirationNotificationRobot.domains.enums.WhenNotify;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ScheduleService {

	//@Value("${app.schedule.cron.every.day}")
	@Value("${app.schedule.cron.every.two.minutes}")
	private String cronExpressionEveryDay;

	@Autowired
	private ClientService clientService;

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private NotificationHistoryService historyService;

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

		log.info("=============== Starting to run DAILY scheduling tasks ===============");
		taskExecutor.submit(this::taskNotifyExpiredClients);
		taskExecutor.submit(this::taskNotifyClientsDueToday);
		taskExecutor.submit(this::taskNotifyClientsBeforeDueDate);

		taskExecutor.submit(this::logEndSchedulingTasks);

	}

	/**
	 * Agendamento de tarefa de notificação dos clientes expirados
	 */
	private void taskNotifyExpiredClients() {
		log.info("************Notifying Expired Clients.");

		try {

			// Buscando clientes expirados
			List<Client> expiredClients = clientService.findByDueDateBefore();

			// Buscando notificação para clientes expirados.
			Notification notification = notificationService.findByWhenNotify(WhenNotify.DEPOIS_DO_VENCIMENTO);

			// percorrendo lista de clientes expirados
			expiredClients.forEach(i -> {

				// Buscando histórico de envios da notificação de expirados para cada cliente da lista.
				List<NotificationHistory> listHistory = historyService.findByClientIdAndNotificationId(i.getId(),
						notification.getId());

				// Se nenhuma notificação tiver sido enviada chama método SendNotification para enviar notificação
				if (listHistory.isEmpty()) {
					SendNotification(notification, i, NotificationRobotConstants.NEW);

				// Se já tiver sido enviado alguma notificação faz novas validações	
				} else {
					
					// Se a notificação estiver configurada para ser reenviada e não excedeu q quantidade de envios, segue a validação
					if (notification.getResend().equals(Boolean.TRUE) && listHistory.size() <= notification.getQuantityShipping()) {

						// Pega o registro com vencimento mais recente
						Optional<NotificationHistory> mostRecentNotification = listHistory.stream()
								.max((nh1, nh2) -> nh1.getDateSend().compareTo(nh2.getDateSend()));

						// Calcula intervalo de dias do último envio
						Integer daysDifference = (int) ChronoUnit.DAYS
								.between(mostRecentNotification.get().getDateSend(), NotificationRobotConstants.CURRENTDATE);
						 
						// Se o intervalo estiver dentro do configurado na notificação chama método SendNotification para enviar notificação 
						if (daysDifference == notification.getIntervalDays()) {
							SendNotification(notification, i, NotificationRobotConstants.UPDATE);
						}

					}
				}

			});

			log.info("Expired clients successfully notified");

		} catch (Exception e) {
			Thread.currentThread().interrupt();
			log.error("Error taskNotifyExpiredClients: ", e.getStackTrace());
		}

	}

	/**
	 * Agendamento de tarefa de notificação dos clientes vencendo na data atual
	 */
	private void taskNotifyClientsDueToday() {
		log.info("Notification to clients due today.");

		try {

			Thread.sleep(3000);

			log.info("Clients expiring today successfully notified");

		} catch (Exception e) {
			Thread.currentThread().interrupt();
			log.error("Error TaskNotifyClientsDueToday");
		}

	}

	/**
	 * Agendamento de tarefa de notificação dos clientes que ainda vão vencer
	 */
	private void taskNotifyClientsBeforeDueDate() {
		log.info("Notification of clients who are yet to win.");

		try {

			Thread.sleep(3000);

			log.info("Clients who are yet to win have been successfully notified");

		} catch (Exception e) {
			Thread.currentThread().interrupt();
			log.error("Error TaskNotifyClientsBeforeDueDate");
		}

	}

	/**
	 * Envia a notificação e salva/atualiza registro no banco de dados
	 * @param notification
	 * @param i
	 */
	private void SendNotification(Notification notification, Client i, String action) {
		log.info("Sending notification to client: " + i.getName());
		
		String message = StringUtils.replace(notification.getMessage(), "§d", String.valueOf(i.getDueDate().getDayOfMonth()));
		message = StringUtils.replace(notification.getMessage(), "§m", String.valueOf(i.getDueDate().getMonthValue()));

		System.out.println(message);
		
		if(action.equals(NotificationRobotConstants.NEW)) {
			historyService.saveNotificationHistory(i.getId(), notification.getId());
			
		} else historyService.updateNotificationHistory(new NotificationHistory(i.getId(), notification.getId()));
					
	}

	private void logEndSchedulingTasks() {
		log.info("=============== End of DAILY scheduling task execution ===============");

	}

}
