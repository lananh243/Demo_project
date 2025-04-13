create database JDBC_PRACTICE;

use JDBC_PRACTICE;

create table Account (
    acc_id int primary key auto_increment,
    username varchar(225),
    password varchar(225),
    status boolean
);


insert into Account(username, password, status)
    values ('admin', '123456', true);

delimiter //
    create procedure log_in(
        username_in varchar(255),
        password_in varchar(255)
    )
    begin
        select * from Account
        where username = username_in and password = password_in and status = true;
    end ;

delimiter //

create table Department (
    dep_id int primary key auto_increment,
    dep_name varchar(100),
    description varchar(255),
    dep_status boolean
);

insert into Department (dep_name, description, dep_status)
values
    ('Phòng Nhân Sự', 'Phòng ban phụ trách tuyển dụng, đào tạo và quản lý nhân sự.', true),
    ('Phòng Kinh Doanh', 'Phòng ban chuyên trách bán hàng và phát triển thị trường.', true),
    ('Phòng Marketing', 'Phòng ban phụ trách các chiến lược marketing và quảng cáo sản phẩm.', true),
    ('Phòng Công Nghệ Thông Tin', 'Phòng ban hỗ trợ và phát triển hệ thống công nghệ thông tin của công ty.', true),
    ('Phòng Tài Chính', 'Phòng ban chuyên quản lý tài chính và các báo cáo tài chính.', true),
    ('Phòng Hỗ Trợ Khách Hàng', 'Phòng ban chịu trách nhiệm chăm sóc và hỗ trợ khách hàng.', true),
    ('Phòng Sản Xuất', 'Phòng ban quản lý và điều phối các hoạt động sản xuất.', false),
    ('Phòng Pháp Chế', 'Phòng ban chuyên xử lý các vấn đề pháp lý của công ty.', true),
    ('Phòng Quản Lý Dự Án', 'Phòng ban điều phối và quản lý các dự án của công ty.', true),
    ('Phòng R&D (Nghiên Cứu & Phát Triển)', 'Phòng ban nghiên cứu và phát triển các sản phẩm mới của công ty.', true);


delimiter //
create procedure add_new_department(
    name_in varchar(100),
    description_in varchar(255),
    status_in boolean
)
begin
    insert into Department(dep_name, description, dep_status)
        values (name_in, description_in, status_in);
end ;

create procedure update_department(
    id_in int,
    name_in varchar(100),
    description_in varchar(255),
    status_in boolean
)
begin
    update Department
        set dep_name = name_in,
            description = description_in,
            dep_status = status_in
    where dep_id = id_in;
end ;

create procedure delete_department(
    id_in int
)
begin
    DECLARE emp_count INT;
    select COUNT(*) INTO emp_count
    from Employee
    where dep_id = id_in;

    if emp_count = 0 then
        delete from Department where dep_id = id_in;
    end if ;
end ;

create procedure search_department_by_name(
    name_in varchar(100)
)
begin
    select * from Department
        where dep_name like concat('%', name_in, '%');
end ;

create procedure get_all_department()
begin
    select * from Department;
end ;

create procedure get_department_by_id(
    id_in int
)
begin
    select * from Department where dep_id = id_in;
end ;

create procedure get_department_by_page(
    page_in int,
    page_size_in int
)
begin
    declare offset_value int;
    set offset_value = (page_in - 1) * page_size_in;

    select * from Department
        limit offset_value, page_size_in;
end ;
delimiter //

create table Employee (
    emp_id char(5) primary key not null ,
    emp_name varchar(150) not null check (char_length(emp_name) between 15 and 150),
    email varchar(200),
    phoneNumber varchar(200),
    gender enum('MALE', 'FEMALE', 'OTHER'),
    level_salary int not null check (level_salary > 0),
    salary decimal(10,2) not null check (salary > 0),
    dateOfBirth date,
    address varchar(200),
    emp_status enum('ACTIVE', 'INACTIVE', 'ONLEAVE', 'POLICYLEAVE'),
    dep_id int,
    foreign key (dep_id) references Department(dep_id)
);

insert into Employee (emp_id, emp_name, email, phoneNumber, gender, level_salary, salary, dateOfBirth, address, emp_status, dep_id)
values
    ('E0001', 'Nguyễn Văn An Khoa', 'a@gmail.com', '0900000001', 'MALE', 1, 8000, '1990-01-15', 'Hà Nội', 'ACTIVE', 18),
    ('E0002', 'Trần Thị Bảo Hân', 'b@gmail.com', '0900000002', 'FEMALE', 2, 9000, '1992-02-20', 'TP. HCM', 'ACTIVE', 19),
    ('E0003', 'Lê Văn Công Thành', 'c@gmail.com', '0900000003', 'MALE', 1, 8500, '1989-03-10', 'Đà Nẵng', 'ONLEAVE', 20),
    ('E0004', 'Phạm Thị Duyên Mai', 'd@gmail.com', '0900000004', 'FEMALE', 3, 1200, '1995-04-18', 'Cần Thơ', 'ACTIVE', 21),
    ('E0005', 'Vũ Văn Đông Hưng', 'e@gmail.com', '0900000005', 'MALE', 2, 9500, '1988-05-25', 'Hải Phòng', 'ACTIVE', 22),
    ('E0006', 'Đặng Thị Hồng Phúc', 'f@gmail.com', '0900000006', 'FEMALE', 1, 7800, '1993-06-30', 'Hà Nam', 'POLICYLEAVE', 23),
    ('E0007', 'Ngô Văn Gia Huy', 'g@gmail.com', '0900000007', 'MALE', 2, 9900, '1991-07-22', 'Bình Dương', 'ACTIVE', 24),
    ('E0008', 'Bùi Thị Hoàng Yến', 'h@gmail.com', '0900000008', 'FEMALE', 3, 1100, '1996-08-11', 'Nam Định', 'ACTIVE', 25),
    ('E0009', 'Hoàng Văn Hữu Nhân', 'i@gmail.com', '0900000009', 'MALE', 2, 9300, '1994-09-13', 'Nghệ An', 'ACTIVE', 26),
    ('E0010', 'Lý Thị Kim Ngân', 'j@gmail.com', '0900000010', 'FEMALE', 1, 8000, '1997-10-19', 'Quảng Ninh', 'INACTIVE', 27),
    ('E0011', 'Tạ Văn Minh Triết', 'k@gmail.com', '0900000011', 'MALE', 2, 9200, '1990-11-05', 'Thái Bình', 'ACTIVE', 18),
    ('E0012', 'Cao Thị Linh Chi', 'l@gmail.com', '0900000012', 'FEMALE', 3, 1250, '1987-12-23', 'Phú Thọ', 'ACTIVE', 19),
    ('E0013', 'Hà Văn Nhật Hào', 'm@gmail.com', '0900000013', 'MALE', 2, 9500, '1992-08-08', 'Bắc Ninh', 'ACTIVE', 20),
    ('E0014', 'Đỗ Thị Ngọc Thảo', 'n@gmail.com', '0900000014', 'FEMALE', 1, 7900, '1995-09-09', 'Lào Cai', 'ACTIVE', 21),
    ('E0015', 'Trịnh Văn Quốc Bảo', 'o@gmail.com', '0900000015', 'MALE', 2, 9100, '1998-10-10', 'Tuyên Quang', 'ACTIVE', 22);


delimiter //
create procedure get_employee_by_page(
    page_in int,
    page_size_in int
)
begin
    declare offset_value int;
    set offset_value = (page_in - 1) * page_size_in;

    select * from Employee
    limit offset_value, page_size_in;
end //

create procedure add_employee(
    emp_name_in varchar(150),
    email_in varchar(200),
    phoneNumber_in varchar(200),
    gender_in enum('MALE', 'FEMALE', 'OTHER'),
    level_salary_in int,
    salary_in decimal(10,2),
    dateOfBirth_in date,
    address_in varchar(200),
    emp_status_in enum('ACTIVE', 'INACTIVE', 'ONLEAVE', 'POLICYLEAVE'),
    dep_id_in int
)
begin
    declare dep_status_value boolean;

    select dep_status into dep_status_value from Department
    where dep_id = dep_id_in;

    insert into Employee(emp_name, email, phoneNumber, gender, level_salary, salary, dateOfBirth, address, emp_status, dep_id)
        values (emp_name_in, email_in, phoneNumber_in, gender_in, level_salary_in,salary_in, dateOfBirth_in, address_in, emp_status_in, dep_id_in);
end ;

create procedure update_employee(
    emp_id_in varchar(150),
    emp_name_in varchar(150),
    email_in varchar(200),
    phoneNumber_in varchar(200),
    gender_in enum('MALE', 'FEMALE', 'OTHER'),
    level_salary_in int,
    salary_in decimal(10,2),
    dateOfBirth_in date,
    address_in varchar(200),
    emp_status_in enum('ACTIVE', 'INACTIVE', 'ONLEAVE', 'POLICYLEAVE'),
    dep_id_in int
)
begin
    update Employee
        set emp_name = emp_name_in,
            email = email_in,
            phoneNumber = phoneNumber_in,
            gender = gender_in,
            level_salary = level_salary_in,
            salary = salary_in,
            dateOfBirth = dateOfBirth_in,
            address = address_in,
            emp_status = emp_status_in,
            dep_id = dep_id_in
    where emp_id = emp_id_in;
end //

create procedure get_all_employee()
begin
    select * from Department;
end ;

create procedure get_employee_by_id(
    emp_id_in varchar(150)
)
begin
    select * from Employee where emp_id = emp_id_in;
end ;

create procedure delete_employee(
    emp_id_in varchar(150)
)
begin
    declare emp_exist int;
    select count(*) into emp_exist from Employee
    where emp_id = emp_id_in;

    if emp_exist > 0 then
        update Employee
            set emp_status = 'INACTIVE'
        where emp_id = emp_id_in;
    end if;
end ;

create procedure search_employee_by_name(
    emp_name_in varchar(150)
)
begin
    select * from Employee
    where emp_name like concat('%', emp_name_in, '%');
end //

create procedure search_employee_by_age_range(
    min_age int,
    max_age int
)
begin
    select
        emp_id,
        emp_name,
        email,
        phoneNumber,
        gender,
        level_salary,
        salary,
        dateOfBirth,
        address,
        emp_status,
        dep_id,
        timestampdiff(year , dateOfBirth, curdate()) as age
    from Employee
    where timestampdiff(year , dateOfBirth, curdate()) between min_age and max_age;
end //


create procedure sort_employee_by_salary_desc()
begin
    select * from Employee
    order by salary desc ;
end //

create procedure sort_employee_by_name_asc()
begin
    select * from Employee
        order by emp_name asc ;
end //
delimiter //

# Thống kê
delimiter //
create procedure count_employee_by_department()
begin
    select dep_id, count(*) as total
    from Employee
    group by dep_id;
end ;

create procedure total_all_employee()
begin
    select count(*) as total from Employee;
end ;

create procedure the_most_employees_in_department()
begin
    select dep_id, count(*) as total
    from Employee
    group by dep_id
    having count(*) = (
        select max(sub.count_emp)
        from (
                 select count(*) as count_emp
                 from Employee
                 group by dep_id
             ) as sub
    );
end ;

create procedure the_most_salary_in_department()
begin
    select dep_id, sum(salary) as total
        from Employee
            group by dep_id
    having sum(salary) = (
        select max(sub.total)
        from (
            select sum(salary) as total
            from Employee
            group by dep_id
             ) as sub
        );
end ;
delimiter //

call the_most_salary_in_department();
DROP PROCEDURE IF EXISTS delete_department;