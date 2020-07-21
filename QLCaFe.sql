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
select * from Promotions
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
	IDCus int Identity(1,1) Primary key,
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
	IDCus int foreign key REFERENCES Customer(IDCus),
	Quantity int,
	NamePromo nvarchar(50) foreign key REFERENCES Promotions(NamePromo),
	Constraint PK_OrderDetails Primary key (IDOrder,IDProduct)
)
Insert into OrderDetails values('HD0001','CF02',1,4,N'Khách hàng VIP')
Insert into OrderDetails values('HD0002','CF03',2,5,N'Khách hàng VIP')
Insert into OrderDetails values('HD0005','CF03',3,5,N'')
go
Create proc tmkiem
@ten nvarchar(100)
as
begin
select * from Product p inner join ProductType pt on p.IDType = pt.IDType where ProductName like N'@ten %'
end
go
CREATE PROC TimKiemOrder
	@IDOrder varchar(20),
	@IDProduct varchar(20),
	@CusName nvarchar(50),
	@NamePromo nvarchar(50),
	@DateOrder varchar(20),
	@UsernameEmp varchar(50)
AS 
BEGIN 
	if(@IDOrder!='')
		BEGIN
			select Orders.IDOrder,IDProduct,CusName,Quantity,NamePromo,TimeOrder,DateOrder,UsernameEmp 
			from OrderDetails join [Orders] on OrderDetails.IDOrder=[Orders].IDOrder 
			where   [Orders].UsernameEmp LIKE @IDOrder
		END
	if(@IDProduct!='')
		BEGIN
			select Orders.IDOrder,IDProduct,CusName,Quantity,NamePromo,TimeOrder,DateOrder,UsernameEmp 
			from OrderDetails join [Orders] on OrderDetails.IDOrder=[Orders].IDOrder 
			where   OrderDetails.IDProduct LIKE @IDProduct
		END
	if(@CusName!='')
		BEGIN
			select Orders.IDOrder,IDProduct,CusName,Quantity,NamePromo,TimeOrder,DateOrder,UsernameEmp 
			from OrderDetails join [Orders] on OrderDetails.IDOrder=[Orders].IDOrder 
			where   OrderDetails.CusName LIKE @CusName
		END
	if(@NamePromo!='')
		BEGIN
			select Orders.IDOrder,IDProduct,CusName,Quantity,NamePromo,TimeOrder,DateOrder,UsernameEmp 
			from OrderDetails join [Orders] on OrderDetails.IDOrder=[Orders].IDOrder 
			where   OrderDetails.NamePromo LIKE @NamePromo
		END
	if(@DateOrder!='')
		BEGIN
			select Orders.IDOrder,IDProduct,CusName,Quantity,NamePromo,TimeOrder,DateOrder,UsernameEmp 
			from OrderDetails join [Orders] on OrderDetails.IDOrder=[Orders].IDOrder 
			where   Orders.DateOrder LIKE @DateOrder
		END
	if(@UsernameEmp!='')
		BEGIN
			select Orders.IDOrder,IDProduct,CusName,Quantity,NamePromo,TimeOrder,DateOrder,UsernameEmp 
			from OrderDetails join [Orders] on OrderDetails.IDOrder=[Orders].IDOrder 
			where   Orders.UsernameEmp LIKE @UsernameEmp
		END 
END 
       
select * from OrderDetails join Orders on OrderDetails.IDOrder=Orders.IDOrder join Product on OrderDetails.IDProduct=Product.IDProduct join Promotions on OrderDetails.NamePromo=Promotions.NamePromo
exec TimKiemOrder '','','',N'Khách hàng VIP','',''
--Insert 1 Administrators
Insert into Administrator values('admin','admin')
--Insert ProductType
Insert into ProductType values('T01',N'Cà phê',N'Nhỏ')
Insert into ProductType values('T02',N'Cà phê',N'Vừa')
Insert into ProductType values('T03',N'Cà phê',N'Lớn')
Insert into ProductType values('T04',N'Nước trái cây',N'Nhỏ')
Insert into ProductType values('T05',N'Nước trái cây',N'Vừa')
Insert into ProductType values('T06',N'Nước trái cây',N'Lớn')
Insert into ProductType values('T07',N'Trà sữa',N'Nhỏ')
Insert into ProductType values('T08',N'Trà sữa',N'Vừa')
Insert into ProductType values('T09',N'Trà sữa',N'Lớn')
---insert product
Insert into Product values('CF01', N'Cà phê đá', 'T01', 20000)
Insert into Product values('CF02', N'Cà phê đá', 'T02', 25000)
Insert into Product values('CF03', N'Cà phê đá', 'T03', 30000)

Insert into Employee values('vutung','123456',N'Vũ Văn Tùng',N'Nam','01/01/1996','0124566789','tung@gmail.com',N'Dĩ an','2.jpg')

Insert into Customer values('122261551',N'Vũ Văn Tùng','17/04/2017','01212692802','tung@gmail.com',20,10)
Insert into Customer values('122261552',N'Nguyễn Huỳnh Thanh Tùng','23/04/2017','01212692802','thanhtung@gmail.com',25,10)
Insert into Customer values('122261553',N'Nguyễn Ngân','20/04/2017','01212692802','nguyenngan@gmail.com',19,5)
Insert into Customer values('122261554',N'Nguyễn Văn Trí','11/04/2017','01212692802','tri@gmail.com',10,5)


--Insert 6 Order
Insert into Orders values('HD0001','11/04/2017','19:49:50','vutung')
Insert into Orders values('HD0002','20/12/2016','22:43:50','vutung')
Insert into Orders values('HD0005','20/12/2016','22:43:50','vutung')
--Insert 10 OrderDetails
Insert into OrderDetails values('HD0001','CF02',1,4,N'Khách hàng VIP')
Insert into OrderDetails values('HD0002','CF03',2,5,N'Khách hàng VIP')
Insert into OrderDetails values('HD0005','CF03',3,5,N'')
select * from OrderDetails
Insert into Revenue values('10/04/2019','179000')
Insert into Revenue values('13/03/2020','50000')
Insert into Revenue values('15/12/2018','206000')

 

Insert into Promotions values(N'Thẻ sinh viên',10,'16/07/2020','18/07/2020',N'Có thẻ sinh viên')
select Orders.IDOrder,IDProduct,CusName,Quantity,NamePromo,TimeOrder,DateOrder,UsernameEmp from OrderDetails join Orders on OrderDetails.IDOrder=Orders.IDOrder Order by OrderDetails.IDOrder DESC