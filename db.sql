CREATE TABLE `mentors` (
	`id` INTEGER NOT NULL UNIQUE,
	`first_name`	TEXT,
	`last_name`	TEXT,
	`nick_name`	TEXT,
	`phone_number`	INTEGER,
	`email`	TEXT,
	`city`	TEXT,
	`favourite_number`	INTEGER,
	PRIMARY KEY(`id`)
	)

CREATE TABLE `applicants` (
	`id` INTEGER NOT NULL UNIQUE,
	`first_name`	TEXT,
	`last_name`	TEXT,
	`phone_number`	INTEGER,
	`email`	TEXT,
	`application_code`	INTEGER,
	PRIMARY KEY(`id`)
	)
