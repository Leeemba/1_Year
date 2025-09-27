CREATE OR REPLACE FUNCTION check_event_date()
RETURNS TRIGGER AS $$
BEGIN
	IF NEW.start_date<(SELECT start_date FROM task WHERE id= NEW.task_id) THEN
		RAISE EXCEPTION 'ERROR: event start_date should not be earlier than start_date in task';
	END IF;
	IF NEW.end_date>(SELECT result_date FROM result WHERE id= NEW.result_id) THEN
		RAISE EXCEPTION 'ERROR: event end_date should not be later than result_date in result';
	END IF;
	RETURN NEW;
END
$$ LANGUAGE plpgsql;


CREATE OR REPLACE TRIGGER event_date_trigger
BEFORE INSERT ON event
FOR EACH ROW
EXECUTE PROCEDURE check_event_date();