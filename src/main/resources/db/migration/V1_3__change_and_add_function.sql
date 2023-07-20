ALTER TABLE employer ADD COLUMN amount_of_available_offers INTEGER DEFAULT 0;

CREATE OR REPLACE FUNCTION update_amount_of_available_offers()
    RETURNS TRIGGER AS
$$
BEGIN
    IF NEW.status = 'ACTIVE' THEN
        UPDATE employer
        SET amount_of_available_offers = amount_of_available_offers + 1
        WHERE employer_id = NEW.employer_id;
    END IF;

    RETURN NEW;
END;
$$
    LANGUAGE plpgsql;

CREATE TRIGGER update_employer_amount_of_available_offers
    AFTER INSERT ON offer
    FOR EACH ROW
EXECUTE FUNCTION update_amount_of_available_offers();

CREATE OR REPLACE FUNCTION decrease_amount_of_available_offers()
    RETURNS TRIGGER AS
$$
BEGIN
    IF OLD.status = 'EXPIRED' THEN
        UPDATE employer
        SET amount_of_available_offers = amount_of_available_offers - 1
        WHERE employer_id = OLD.employer_id;
    END IF;
    RETURN OLD;
END;
$$
    LANGUAGE plpgsql;

CREATE TRIGGER decrease_employer_amount_of_available_offers
    AFTER DELETE ON offer
    FOR EACH ROW
EXECUTE FUNCTION decrease_amount_of_available_offers();

CREATE OR REPLACE FUNCTION recalculate_amount_of_available_offers()
    RETURNS VOID AS
$$
BEGIN
    UPDATE employer AS e
    SET amount_of_available_offers = COALESCE(sub.count_offers, 0)
    FROM (
             SELECT employer_id, COUNT(*) AS count_offers
             FROM offer
             WHERE status = 'ACTIVE'
             GROUP BY employer_id
         ) AS sub
    WHERE e.employer_id = sub.employer_id;
END;
$$
    LANGUAGE plpgsql;

SELECT recalculate_amount_of_available_offers();

CREATE OR REPLACE FUNCTION decrease_amount_of_available_offers_expired()
    RETURNS TRIGGER AS
$$
BEGIN
    IF NEW.status = 'EXPIRED' AND OLD.status = 'ACTIVE' THEN
        UPDATE employer
        SET amount_of_available_offers = amount_of_available_offers - 1
        WHERE employer_id = NEW.employer_id;
    END IF;

    RETURN NEW;
END;
$$
    LANGUAGE plpgsql;

CREATE TRIGGER decrease_amount_of_available_offers_expired_trigger
    AFTER UPDATE ON offer
    FOR EACH ROW
EXECUTE FUNCTION decrease_amount_of_available_offers_expired();

CREATE OR REPLACE FUNCTION increase_amount_of_available_offers_active()
    RETURNS TRIGGER AS
$$
BEGIN
    IF NEW.status = 'ACTIVE' AND OLD.status = 'EXPIRED' THEN
        UPDATE employer
        SET amount_of_available_offers = amount_of_available_offers + 1
        WHERE employer_id = NEW.employer_id;
    END IF;

    RETURN NEW;
END;
$$
    LANGUAGE plpgsql;

CREATE TRIGGER increase_amount_of_available_offers_active_trigger
    AFTER UPDATE ON offer
    FOR EACH ROW
EXECUTE FUNCTION increase_amount_of_available_offers_active();