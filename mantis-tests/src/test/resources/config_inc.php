<?php
$g_hostname               = 'localhost';
$g_db_type                = 'mysqli';
$g_database_name          = 'bugtracker';
$g_db_username            = 'root';
$g_db_password            = '';
$g_default_timezone       = 'Europe/Moscow';
$g_crypto_master_salt     = 'ocH7m1gvY08IVu+tItK/nrMSitexjOfJbYP0S+Kj/Qc=';
$g_signup_use_captcha			= OFF;
$g_phpMailer_method = PHPMAILER_METHOD_SMTP;

/*$g_smtp_host = 'mx.test.masterdm.ru';*/
$g_smtp_host = 'localhost';
$g_smtp_port = 25;

/*$g_smtp_username = 'test1@test.masterdm.ru';*/

/**
 * SMTP Server Authentication password
 * Not used when $g_smtp_username = ''
 * @see $g_smtp_username
 * @global string $g_smtp_password
 */
/*$g_smtp_password = '12345678';*/
$g_log_level = LOG_ALL & ~LOG_DATABASE;
$g_log_destination = 'file:d:/temp/mantisbt.log';

$g_enable_email_notification = ON;
$g_send_reset_password = ON;
