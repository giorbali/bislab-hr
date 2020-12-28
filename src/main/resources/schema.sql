/*DROP SCHEMA IF EXISTS hr;*/
CREATE SCHEMA IF NOT EXISTS hr;
use hr;

CREATE TABLE regions (
	region_id INT (11) UNSIGNED NOT NULL,
	region_name VARCHAR(25),
	PRIMARY KEY (region_id)
	);


