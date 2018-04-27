# MBank-Phase1-2-
Phases one and two of Mini Bank Project


DB used in the project is MSSQL.
Configurations are as follows:
--use ProjectJB
create table Clients
(
		client_id	Bigint Identity Primary Key,
		client_name nvarchar(100) Not Null,
		password	nvarchar(100) Not Null,
		type		varchar(20) Not Null,
		address		nvarchar(100),
		email		varchar(50),
		phone		varchar(20),
		comment		nvarchar(100),
)
create table Accounts
(
	account_id		Bigint Identity Primary Key ,
	client_id		Bigint Not Null ,
	balance			FLOAT Not Null ,
	credit_limit	FLOAT Not Null ,
	comment			nvarchar(100) ,
	FOREIGN KEY(client_id) REFERENCES Clients(client_id) ,
)


create table Deposits
(
		deposit_id			Bigint Identity Primary Key,
		client_id			Bigint Not Null,
		balance				FLOAT Not Null,
		type				varchar(20) Not Null,
		estimated_balance	Bigint Not Null,
		opening_date		datetime Not Null,
		closing_date		datetime Not Null,
		FOREIGN KEY(client_id) REFERENCES Clients(client_id) ,
)


create table Activity
(
		id				Bigint Identity Primary Key ,
		client_id		Bigint Not Null,
		amount			FLOAT Not Null,
		activity_date	datetime Not Null,
		commision		FLOAT Not Null,
		description		nvarchar(100),
		FOREIGN KEY(client_id) REFERENCES Clients(client_id)
)


create table Properties
(
		prop_key	varchar(50) Primary Key,
		prop_value	varchar(50) Not Null,
)

create table Logs
(	
	Id			Bigint Identity Primary Key,
	Log_date	datetime ,
	Client_id	Bigint , 
	Description nvarchar(100),

)
In this Repository the only administrative application is presented.
The administrative application and web application of the project are both rely on the same core which is under directory MBankBL (MBank Business Logic ). 
This core interacts with each of the above apps view layer by RMI.
This was done in order to avoid code duplication of the core actions by both apps.
The view and controller layers of the administrative application that are under the directory MBankGui , are written in SWING.
Testing of the core was done using JUNIT – 
and is under the directory and package MBankBL/test (classes: AdminActionTest , ClientActionTest – 
Testing of the lower level classes was done manually and by testing the highest level by JUNIT ensured correctness of all levels).

