CREATE TABLE IF NOT EXISTS INSURANCE_PRODUCT (
    ID bigint AUTO_INCREMENT primary key,
    NAME varchar(255) not null,
    CATEGORY varchar(255) not null,
    BASE_PRICE decimal(11,2) not null,
    TAXED_PRICE decimal(11,2) not null
);