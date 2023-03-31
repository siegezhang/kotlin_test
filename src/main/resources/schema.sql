CREATE TABLE IF NOT EXISTS messages (
    id       VARCHAR_IGNORECASE(60) default RANDOM_UUID() not null
    primary key,
    text     VARCHAR      NOT NULL
 );