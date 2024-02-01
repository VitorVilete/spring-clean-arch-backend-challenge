CREATE TABLE IF NOT EXISTS INSURANCE_PRODUCT (
    ID UUID not null,
    NAME varchar(255) not null,
    CATEGORY varchar(255) not null,
    BASE_PRICE decimal(11,2) not null,
    TAXED_PRICE decimal(11,2) not null
);