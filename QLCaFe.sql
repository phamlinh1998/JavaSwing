create database QLCaFe
go
use QLCaFe
go
create table ProductType
(
	IDType varchar(10) primary key,
	TypeName nvarchar(50),
	Size nvarchar(10)
)
go
create table Product
(
	IDProduct varchar(20) primary key,
	ProductName nvarchar(100),
	IDType varchar(10) FOREIGN KEY REFERENCES ProductType(IDType),
	Price int
)
go
create table Administrator(
	Username varchar(50) Primary key,
	Password varchar(20)
)
go
create table Employee(
	UsernameEmp varchar(50) Primary key,
	Password varchar(20),
	NameEmp nvarchar(50),
	Gender nvarchar(10),
	Birthday varchar(20),
	Phone varchar(20),
	Email varchar(50),
	Address nvarchar(Max),
	Hinh varchar(50)
)
go
Create Table Revenue(
	IDRevenue int Identity(1,1) Primary key,
	Date varchar(20),
	Money varchar(20)
)
go
create table Customer(
	IDCus int Identity(100000,1) Primary key,
	IdentityCard varchar(20) not null UNIQUE,
	CusName nvarchar(50),
	DateAdd varchar(20),
	Phone varchar(20),
	Email varchar(50),
	Quantity int,
	Discount int
)
go
create table Promotions(
	IDPromo int Identity(1,1) Primary key,
	NamePromo nvarchar(50) not null UNIQUE,
	DiscountPromo int,
	StartPromo varchar(20),
	EndPromo varchar(20),
	Description nvarchar(Max)
)
go
create table Orders(
	IDOrder varchar(20) Primary key,
	DateOrder varchar(20),
	TimeOrder varchar(20),
	UsernameEmp varchar(50) foreign key REFERENCES Employee(UsernameEmp)
)
go
Create Table OrderDetails(
	IDOrder varchar(20) foreign key REFERENCES Orders(IDOrder),
	IDProduct varchar(20) foreign key REFERENCES Product(IDProduct),
	CusName nvarchar(50),
	Quantity int,
	NamePromo nvarchar(50),
	Constraint PK_OrderDetails Primary key (IDOrder,IDProduct)
)
go
--Insert 1 Administrators
Insert into Administrator values('admin','admin')

