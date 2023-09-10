
CREATE TABLE IF NOT EXISTS user_location(
id INT AUTO_INCREMENT, country VARCHAR(50) NOT NULL, province VARCHAR(50) NOT NULL,
city VARCHAR(50) NOT NULL, address VARCHAR(500) NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS app_users(
id BIGINT AUTO_INCREMENT, first_name VARCHAR(50) NOT NULL, last_name VARCHAR(50) NOT NULL,
email VARCHAR(50) UNIQUE, user_password VARCHAR(500),
user_location_id INT,
FOREIGN KEY (user_location_id) REFERENCES user_location(id),
PRIMARY KEY(id)

);