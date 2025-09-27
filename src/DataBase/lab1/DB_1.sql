DROP TABLE IF EXISTS organisation CASCADE;
DROP TABLE IF EXISTS location CASCADE;
DROP TABLE IF EXISTS equipment CASCADE;
DROP TABLE IF EXISTS observer CASCADE;
DROP TABLE IF EXISTS target CASCADE;
DROP TABLE IF EXISTS task CASCADE;
DROP TABLE IF EXISTS observer_to_Task CASCADE;
DROP TABLE IF EXISTS event CASCADE;
DROP TABLE IF EXISTS object_of_observation CASCADE;
DROP TABLE IF EXISTS object_to_Event CASCADE;
DROP TABLE IF EXISTS result CASCADE;




CREATE TABLE organisation(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    country VARCHAR(128) NOT NULL,
    UNIQUE(name)
);
CREATE TABLE location(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    latitude DECIMAL(9,6) NOT NULL,
    longitude DECIMAL(9,6) NOT NULL,
    organisation_id INT,
    FOREIGN KEY(organisation_id) REFERENCES organisation(id),
    UNIQUE(name,latitude,longitude),
    CHECK ((longitude BETWEEN -180 AND 180) AND (latitude BETWEEN -90 AND 90))
);
CREATE TABLE observer(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    location_id INT,
    qualification TEXT NOT NULL,
    organisation_id INT,
    FOREIGN KEY(organisation_id) REFERENCES organisation(id),
    FOREIGN KEY(location_id) REFERENCES location(id)
); 
CREATE TABLE equipment(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    observer_id INT,
    location_id INT,
    FOREIGN KEY(observer_id) REFERENCES observer(id),
    FOREIGN KEY(location_id) REFERENCES location(id),
    UNIQUE(name)
);
CREATE TABLE target(
    id SERIAL PRIMARY KEY,
    description TEXT NOT NULL,
    name VARCHAR(255) NOT NULL
);
CREATE TABLE task(
    id SERIAL PRIMARY KEY,
    target_id INT,
    planning_start_date DATE NOT NULL,
    planning_end_date DATE NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    FOREIGN KEY(target_id) REFERENCES target(id),
    CHECK((end_date > start_date) AND (planning_end_date > planning_start_date))
);   

CREATE TABLE result(
    id SERIAL PRIMARY KEY,
    result_date DATE NOT NULL,
    coclusion TEXT NOT NULL,
    scientific_value BOOLEAN NOT NULL
);
CREATE TABLE event(
    id SERIAL PRIMARY KEY,
    description TEXT NOT NULL,
    start_date DATE,
    end_date DATE,
	task_id INT,
    result_id INT,
    FOREIGN KEY(result_id) REFERENCES result(id),
	FOREIGN KEY(task_id) REFERENCES task(id),
    CHECK(end_date > start_date) 
);


CREATE TABLE object_of_observation(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    size DECIMAL NOT NULL,
	task_id INT,
    right_ascension DECIMAL NOT NULL,
    declination DECIMAL NOT NULL,
    distance DECIMAL NOT NULL,
    luminosity DECIMAL NOT NULL,
	FOREIGN KEY(task_id) REFERENCES task(id),
    UNIQUE(name,right_ascension,declination)
);
CREATE TABLE object_to_event(
    object_id INT REFERENCES object_of_observation(id),
    event_id INT REFERENCES event(id),
    PRIMARY KEY(object_id,event_id)
);

CREATE TABLE observer_to_task(
    observer_id INT REFERENCES observer(id),
    task_id INT REFERENCES task(id),
    PRIMARY KEY(observer_id,task_id)
);



INSERT INTO organisation(name,type,country) VALUES
('NASA','space agency','USA'),
('SRI RAS,','research institute','Russia')
;
 

INSERT INTO location(name,latitude,longitude,organisation_id) VALUES
('Mauna Kea Observatory',19.826063,-155.471974,1),
('Zelenchuk Radio Astronomy Observatory',43.787833,41.564611,2)
;

INSERT INTO observer(name,surname,location_id,qualification,organisation_id) VALUES
('Sten','Odenwald',1,'Astronomer',1),
('Lev','Zelenyi',2,'Physicist',2);

INSERT INTO equipment(name,type,observer_id,location_id) VALUES
('NASA IRTF','Infrared Telescope',1,1),
('Quasar QUO','Radio telescope',2,2);

INSERT INTO target(description,name) VALUES
('Search for an exoplanet to find a potentially habitable planet in other systems','Search for an exoplanet'),
('Observations of pulsars to study the mechanism of death of stars','Search for new types of pulsar');

INSERT INTO task(target_id,planning_start_date,planning_end_date,start_date,end_date) VALUES
(1,'29-07-2009','20-02-2011','30-07-2009','25-02-2011'),
(2,'13-07-1950','13-07-1954','14-09-1972','13-06-1974');


INSERT INTO result(result_date,coclusion,scientific_value) VALUES
('25-02-2011','as a result of long-term observation, the exoplanet Kepler-174 d was discovered','TRUE'),
('13-06-1974','after observing the expansion of the star, death and transformation into a pulsar were recorded','TRUE');

INSERT INTO event (description,start_date,end_date,task_id,result_id) VALUES
('The passage of a planet against the background of a star','21-07-2009','25-02-2011',1,1),
('intense pulsation , expansion of size and death of a star','10-09-1708','13-06-1974',2,2);

INSERT INTO object_of_observation(name,type,size,task_id,right_ascension,declination,distance,luminosity) VALUES
('Kepler-174 d','exoplanet',5.43,1,19.0945,43.4956,321,0),
('PSR B1913+16','pulsar',1.4,1,16.0108,19.1312,7130,0.0148);

INSERT INTO object_to_event(object_id,event_id) VALUES
(1,1),
(2,2);


INSERT INTO observer_to_Task(observer_id,task_id) VALUES
(1,1),
(2,2);
 
