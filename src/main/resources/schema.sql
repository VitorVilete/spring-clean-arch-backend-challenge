CREATE TABLE IF NOT EXISTS INSURANCE_PRODUCT (
    ID varchar(36) AUTO_INCREMENT,
    NAME varchar(255) not null,
    CATEGORY varchar(255) not null,
    BASE_PRICE decimal(11,2) not null,
    TAXED_PRICE decimal(11,2) not null
);

//ALTER TABLE INSURANCE_PRODUCT
//ADD CONSTRAINT PK_InsuranceProduct
//PRIMARY KEY (ID, NAME, CATEGORY);

CREATE TABLE IF NOT EXISTS INSURANCE_TYPE (
    NAME varchar(255) primary key,
    IOF_TAX_VALUE decimal(11, 5),
    PIS_TAX_VALUE decimal(11, 5),
    COFINS_TAX_VALUE decimal(11, 5)
);