CREATE DATABASE IF NOT EXISTS db_dictionary;

-- table user
DROP TABLE IF EXISTS db_dictionary.user;
CREATE TABLE db_dictionary.user (
  id       INT AUTO_INCREMENT PRIMARY KEY,
  email    VARCHAR(255) NOT NULL
  COMMENT '',
  password VARCHAR(255) NOT NULL
  COMMENT ''
)COMMENT 'user table';

-- table dictionary
DROP TABLE IF EXISTS db_dictionary.dictionary;
CREATE TABLE db_dictionary.dictionary (
  id      INT AUTO_INCREMENT PRIMARY KEY,
  english VARCHAR(255) COMMENT '',
  chinese VARCHAR(255) COMMENT '',
  phonetic VARCHAR(255) COMMENT '音标',
  part_of_speech VARCHAR(255) COMMENT '词性'
) COMMENT '';

-- select
SELECT *
FROM db_dictionary.user;

SELECT *
FROM db_dictionary.dictionary;