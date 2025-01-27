-- Insert's tb_cliente
insert into tb_cliente(celular, pais, data_vencimento, nome, status) values('21988888880', 'Brazil', '2025-01-15 23:59:59', 'Pedro', 'EXPIRADO');
insert into tb_cliente(celular, pais, data_vencimento, nome, status) values('21988888881', 'Brazil', '2025-01-27 23:59:59', 'Paulo', 'VENCENDO_HOJE');
insert into tb_cliente(celular, pais, data_vencimento, nome, status) values('21988888882', 'Brazil', '2025-01-30 23:59:59', 'João', 'A_VENCER');
-- Insert's tb_notificacao
insert into tb_notificacao(dias_antes, intervalo_dias, mensagem, quantidade_envio, reenviar, quando_notificar) values(-1, 2, 'Sua assinatura expirou dia §d/§m.', 3, true,  'DEPOIS_DO_VENCIMENTO');
insert into tb_notificacao(dias_antes, intervalo_dias, mensagem, quantidade_envio, reenviar, quando_notificar) values(10, 5, 'Sua assinatura expirá dia §d/§m.', 2, true,  'ANTES_DO_VENCIMENTO');
insert into tb_notificacao(dias_antes, intervalo_dias, mensagem, quantidade_envio, reenviar, quando_notificar) values(0, 0, 'Sua assinatura expirá HOJE', 1, false,  'NO_VENCIMENTO');
