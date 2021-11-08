CREATE TABLE EDU_MEMBERS (
                             idx int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
                             name varchar(10) NOT NULL,
                             content varchar(100),
                             reg_date  TIMESTAMP DEFAULT NOW()
);