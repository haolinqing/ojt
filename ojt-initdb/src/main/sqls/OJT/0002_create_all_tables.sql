--ユーザ
CREATE TABLE t_user (
	user_id 	VARCHAR(4)		NOT NULL,
	username 	VARCHAR(255)	NOT NULL,
	birthday	DATE			NOT NULL,
	address		VARCHAR(255)	NOT NULL,
	telephone	VARCHAR(11)		NOT NULL,
	password	VARCHAR(255)	NOT NULL,
	status		VARCHAR(4) 		NOT NULL DEFAULT 'INIT'  CHECK(status in ('ACTV','INIT','RMVD')),
	last_update_time 	TIMESTAMP without time zone	NOT NULL,
	CONSTRAINT t_user_pk PRIMARY KEY (user_id)
);

--ルル
CREATE TABLE t_role (
	user_id		VARCHAR(4)		NOT NULL,
	role_name	VARCHAR(5)		NOT NULL,
	CONSTRAINT t_role_pk PRIMARY KEY (user_id,role_name),
	CONSTRAINT t_role_fk1 FOREIGN KEY (user_id) REFERENCES t_user (user_id)
);