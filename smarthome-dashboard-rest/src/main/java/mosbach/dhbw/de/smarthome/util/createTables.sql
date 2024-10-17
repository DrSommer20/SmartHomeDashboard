
CREATE TABLE group18_action (
    id Serial  NOT NULL,
    Name VARCHAR(20)  NOT NULL,
    group18_device_id VARCHAR(50)  NOT NULL,
    group18_routine_id Serial  NOT NULL,
    CONSTRAINT group18_action_pk PRIMARY KEY (id)
);

CREATE TABLE group18_device (
    id varchar(50)  NOT NULL,
    name varchar(50)  NOT NULL,
    status boolean  NOT NULL,
    group18_deviceType_id Serial  NOT NULL,
    group18_room_id int  NOT NULL,
    group18_user_id Serial  NOT NULL,
    CONSTRAINT group18_device_pk PRIMARY KEY (id)
);

CREATE TABLE group18_deviceType (
    id Serial  NOT NULL,
    name varchar(20)  NOT NULL,
    icon char(20)  NOT NULL,
    CONSTRAINT group18_deviceType_pk PRIMARY KEY (id)
);

CREATE TABLE group18_room (
    id Serial  NOT NULL,
    name varchar(50)  NOT NULL,
    group18_user_id Serial  NOT NULL,
    CONSTRAINT group18_room_pk PRIMARY KEY (id)
);

CREATE TABLE group18_routine (
    id Serial  NOT NULL,
    name varchar(50)  NOT NULL,
    trigger_time varchar(10)  NOT NULL,
    state boolean  NOT NULL,
    group18_user_id Serial  NOT NULL,
    CONSTRAINT group18_routine_pk PRIMARY KEY (id)
);

CREATE TABLE group18_user (
    id Serial  NOT NULL,
    firstName varchar(50)  NOT NULL,
    lastName varchar(50)  NOT NULL,
    email varchar(50)  NOT NULL,
    password varchar(100)  NOT NULL,
    pat varchar(100)  NULL,
    isVerified boolean  NOT NULL,
    CONSTRAINT group18_user_pk PRIMARY KEY (id)
);

