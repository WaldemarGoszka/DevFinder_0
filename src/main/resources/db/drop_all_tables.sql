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

DROP FUNCTION IF EXISTS update_amount_of_available_offers();
DROP FUNCTION IF EXISTS decrease_amount_of_available_offers();
DROP FUNCTION IF EXISTS recalculate_amount_of_available_offers();
DROP FUNCTION IF EXISTS decrease_amount_of_available_offers_expired();
DROP FUNCTION IF EXISTS increase_amount_of_available_offers_active();
DROP TRIGGER IF EXISTS update_employer_amount_of_available_offers ON offer;
DROP TRIGGER IF EXISTS decrease_employer_amount_of_available_offers ON offer;
DROP TRIGGER IF EXISTS decrease_amount_of_available_offers_expired_trigger ON offer;
DROP TRIGGER IF EXISTS increase_amount_of_available_offers_active_trigger ON offer;
