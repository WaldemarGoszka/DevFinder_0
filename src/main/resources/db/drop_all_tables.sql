DROP TABLE IF EXISTS offer_skill CASCADE;
DROP TABLE IF EXISTS candidate_skill CASCADE;
DROP TABLE IF EXISTS candidate CASCADE;
DROP TABLE IF EXISTS offer CASCADE;
DROP TABLE IF EXISTS employer CASCADE;
DROP TABLE IF EXISTS skill CASCADE;
DROP TABLE IF EXISTS city CASCADE;

DROP TABLE IF EXISTS devfinder_user CASCADE;
DROP TABLE IF EXISTS devfinder_role CASCADE;
DROP TABLE IF EXISTS reset_password_token CASCADE;
DROP TABLE IF EXISTS email_verification_token CASCADE;
DROP TABLE IF EXISTS flyway_schema_history CASCADE;

DROP FUNCTION IF EXISTS increase_amount_of_available_offers_when_add_new();
DROP FUNCTION IF EXISTS decrease_amount_of_available_offers_when_delete();
DROP FUNCTION IF EXISTS recalculate_amount_of_available_offers_after_inserts();
DROP FUNCTION IF EXISTS decrease_amount_of_available_offers_when_change_active_to_expired();
DROP FUNCTION IF EXISTS increase_amount_of_available_offers_when_change_expired_to_active();

DROP TRIGGER IF EXISTS trigger_increase_amount_of_available_offers_when_add_new ON offer;
DROP TRIGGER IF EXISTS trigger_decrease_amount_of_available_offers_when_delete ON offer;
DROP TRIGGER IF EXISTS trigger_decrease_amount_of_available_offers_when_change_active_to_expired ON offer;
DROP TRIGGER IF EXISTS trigger_increase_amount_of_available_offers_when_change_expired_to_active ON offer;
