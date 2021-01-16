CREATE TABLE roles(
    rid INTEGER PRIMARY KEY,
    rname VARCHAR(25)
);

CREATE TABLE country(
    cid SERIAL PRIMARY KEY,
    cname VARCHAR(50)
);

CREATE TABLE users(
    countryID INTEGER REFERENCES country(cid),
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(11),
    fName VARCHAR(25),
    lName VARCHAR(25),
    rid INTEGER REFERENCES roles(rid)
);

CREATE TABLE cvdata(
    countryID INTEGER REFERENCES country(cid),
    day_data DATE,
    numTest INTEGER,
    numCase INTEGER,
    numIntubated INTEGER,
    numICU INTEGER,
    numRecovered INTEGER,
    numDeceased INTEGER,
    PRIMARY KEY(countryID, day_data)
);

CREATE TABLE stats(
    countryID INTEGER REFERENCES country(cid) PRIMARY KEY,
    totalTest INTEGER,
    totalCase INTEGER,
    totalIntubated INTEGER,
    totalICU INTEGER,
    totalRecovered INTEGER,
    totalDeceased INTEGER
);


INSERT INTO roles (rid, rname) VALUES(0 , 'Normal');
INSERT INTO roles (rid, rname) VALUES(1 , 'Admin');
