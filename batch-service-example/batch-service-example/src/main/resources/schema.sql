CREATE TABLE IF NOT EXISTS products(
  ID int not null AUTO_INCREMENT,
  title varchar(100) not null,
  description varchar(200),
  price varchar(200),
  discount varchar(200),
  discounted_price varchar(150),
  PRIMARY KEY ( ID )
);