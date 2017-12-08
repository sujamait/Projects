CREATE TABLE users (
  id int(10) not null AUTO_INCREMENT,
  email_id VARCHAR(254) NOT NULL,
  created_dt DATETIME,
  updated_dt DATETIME,
  PRIMARY KEY (id)
);



CREATE TABLE users_relationship (
  relating_user_id int(10) not null,
  related_user_id int(10) not null,
  type varchar(10) not null,
  created_dt DATETIME,
  updated_dt DATETIME,
  PRIMARY KEY (relating_user_id, related_user_id,type)
);

CREATE UNIQUE INDEX email_id_idx
ON users (email_id);