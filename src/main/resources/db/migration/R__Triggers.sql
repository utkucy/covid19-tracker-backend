CREATE OR REPLACE FUNCTION calculate_country_sum() RETURNS trigger AS $calculate_country_sum$
DECLARE
country_id integer := 0;
test_count integer := 0;
case_count integer := 0;
intubated_count integer := 0;
icu_count integer := 0;
recovered_count integer := 0;
deceased_count integer := 0;
BEGIN

    IF (TG_OP = 'INSERT' OR TG_OP = 'UPDATE') THEN
        country_id = NEW.countryID;
        SELECT SUM(numTest) into test_count from cvdata where countryID = country_id;
        SELECT SUM(numCase) into case_count from cvdata where countryID = country_id;
        SELECT SUM(numIntubated) into intubated_count from cvdata where countryID = country_id;
        SELECT SUM(numICU) into icu_count from cvdata where countryID = country_id;
        SELECT SUM(numRecovered) into recovered_count from cvdata where countryID = country_id;
        SELECT SUM(numDeceased) into deceased_count from cvdata where countryID = country_id;
    ELSIF (TG_OP = 'DELETE') THEN
        country_id = OLD.countryID;
        SELECT SUM(numTest) into test_count from cvdata where countryID = country_id;
        SELECT SUM(numCase) into case_count from cvdata where countryID = country_id;
        SELECT SUM(numIntubated) into intubated_count from cvdata where countryID = country_id;
        SELECT SUM(numICU) into icu_count from cvdata where countryID = country_id;
        SELECT SUM(numRecovered) into recovered_count from cvdata where countryID = country_id;
        SELECT SUM(numDeceased) into deceased_count from cvdata where countryID = country_id;
    END IF;

    UPDATE stats
    SET
    totalTest = test_count,
    totalCase = case_count,
    totalIntubated = intubated_count,
    totalICU = icu_count,
    totalRecovered = recovered_count,
    totalDeceased = deceased_count
    WHERE countryID = country_id;

    RETURN NULL;
END;
$calculate_country_sum$ LANGUAGE plpgsql;

CREATE TRIGGER calculate_country_sum
AFTER INSERT OR UPDATE OR DELETE ON cvdata
FOR EACH ROW EXECUTE PROCEDURE calculate_country_sum();