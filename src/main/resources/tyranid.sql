CREATE DATABASE IF NOT EXISTS tyranids;
USE tyranids;

CREATE TABLE IF NOT EXISTS tyranid (id INTEGER AUTO_INCREMENT, name VARCHAR(255), hive_fleet VARCHAR(255), points INTEGER, PRIMARY KEY (id));

SELECT * FROM tyranid; 
