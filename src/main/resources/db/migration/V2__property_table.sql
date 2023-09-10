CREATE TABLE IF NOT EXISTS properties_table(
id BIGINT AUTO_INCREMENT,
property_name VARCHAR(50) NOT,
property_description VARCHAR (500),
property_type VARCHAR(200),
owner_id BIGINT,
FOREIGN KEY (owner_id) REFERENCES app_users(id),
PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS property_location(
id BIGINT AUTO_INCREMENT,
house_number VARCHAR(30),
street_name VARCHAR(50),
landmark VARCHAR(50),
district VARCHAR(50),
city VARCHAR(50) NOT NULL,
state VARCHAR(50) NOT NULL,
country VARCHAR(40) NOT NULL,
property_id BIGINT,
FOREIGN KEY (property_id) REFERENCES properties_table(id),
PRIMARY KEY (id));

