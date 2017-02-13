create table jelly_session (
    id VARCHAR(40) NOT NULL,
    board VARCHAR(4096) NOT NULL,
    PRIMARY KEY (id)
);

create table jelly_level (
    level INT NOT NULL,
    board VARCHAR(4096) NOT NULL,
    PRIMARY KEY (level)
);
