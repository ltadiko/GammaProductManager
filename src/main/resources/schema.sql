/** Master data **/
/** Master data **/
CREATE TABLE IF NOT EXISTS Store
(
    storeId int(12) ,
    city            varchar(60) NOT NULL,
    postalCode      varchar(7) NOT NULL,
    street    varchar(60) NOT NULL,
    address_number varchar(60) NOT NULL,
    todayOpen  VARCHAR(8) ,
    todayClose VARCHAR(8) NOT NULL,
    collectionPoint BOOLEAN,
    PRIMARY KEY (storeId)
);
DELETE FROM STORE;

INSERT INTO store(storeId,city,postalCode,street,address_number,todayOpen,todayClose,collectionPoint) VALUES (1,'Maarssen','3606 AR','Industrieweg','1','08:00','20:00','true');
INSERT INTO store(storeId,city,postalCode,street,address_number,todayOpen,todayClose,collectionPoint) VALUES (2,'Utrecht','3526 GD','Europaplein','1','08:00','20:00','true');


CREATE TABLE IF NOT EXISTS ARTICLE
(
    articleId int(24) ,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(250) NOT NULL,
     storeId int(24) NOT NULL,
    PRIMARY KEY (articleId),
        FOREIGN KEY (storeId) REFERENCES STORE(storeId)
);
DELETE FROM ARTICLE;

INSERT INTO ARTICLE(articleId,name,description,storeId) values (1,'stapleblok','tuin',1);
INSERT INTO ARTICLE(articleId,name,description,storeId) values (2,'tegels','tuin',1);

/** Master data **/
CREATE TABLE IF NOT EXISTS Product
(
    productId int(24) ,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(250) NOT NULL,
    articleId int(24) NOT NULL,
    storeId int(24) NOT NULL,
    PRIMARY KEY (productId),
    FOREIGN KEY (articleId) REFERENCES ARTICLE(articleId),
    FOREIGN KEY (storeId) REFERENCES STORE(storeId)

);
DELETE FROM Product;

INSERT INTO Product(productId,name,description,articleId,storeId) values (1,'zwaart','tuin',1,1);
INSERT INTO Product(productId,name,description,articleId,storeId) values (2,'blouw','tuin',1,1);

