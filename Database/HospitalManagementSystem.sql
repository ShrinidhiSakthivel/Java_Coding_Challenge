create database hospital_management_system;
use hospital_management_system;

create table patients (
    patient_id int primary key auto_increment,
    first_name varchar(50),
    last_name varchar(50),
    date_of_birth date,
    gender varchar(10),
    contact_number varchar(15),
    address varchar(100)
);

create table doctors (
    doctor_id int primary key auto_increment,
    first_name varchar(50),
    last_name varchar(50),
    specialization varchar(50),
    contact_number varchar(15)
);

create table appointments (
    appointment_id int primary key auto_increment,
    patient_id int,
    doctor_id int,
    appointment_date date,
    description varchar(100),
    foreign key (patient_id) references patients(patient_id),
    foreign key (doctor_id) references doctors(doctor_id)
);

INSERT INTO patients (first_name, last_name, date_of_birth, gender, contact_number, address) VALUES
('karthik', 'raj', '1987-06-14', 'male', '9406455207', 'chennai'),
('anita', 'raj', '1995-10-30', 'male', '9516835936', 'salem'),
('dinesh', 'joseph', '2004-03-12', 'male', '9578839959', 'salem'),
('anita', 'veni', '2015-04-02', 'female', '9487738027', 'madurai'),
('meena', 'veni', '1999-01-06', 'male', '9312505998', 'trichy'),
('meena', 'ravi', '2015-06-24', 'male', '9120298683', 'tirupur'),
('anita', 'singh', '1997-06-20', 'female', '9992491126', 'chennai'),
('meena', 'gopal', '2000-03-10', 'male', '9292232156', 'madurai'),
('anita', 'ravi', '1981-11-09', 'female', '9744626136', 'coimbatore'),
('nisha', 'veni', '1982-12-18', 'male', '9543910024', 'erode');
select * from patients;

INSERT INTO doctors (first_name, last_name, specialization, contact_number) VALUES
('dr. sowmiya', 'reddy', 'orthopedics', '9218489750'),
('dr. anita', 'joseph', 'neurology', '9662111870'),
('dr. karthik', 'reddy', 'pediatrics', '9854274863'),
('dr. meena', 'reddy', 'dermatology', '9861631797'),
('dr. nisha', 'sharma', 'pediatrics', '9392711439'),
('dr. rahul', 'veni', 'pediatrics', '9841363515'),
('dr. nisha', 'joseph', 'ent', '9259656470'),
('dr. sowmiya', 'das', 'oncology', '9304928742'),
('dr. karthik', 'ravi', 'gynecology', '9214076105'),
('dr. karthik', 'gopal', 'oncology', '9917369399');

select * from doctors;

insert into appointments (patient_id, doctor_id, appointment_date, description) values
(1, 1, '2025-06-15', 'regular heart checkup'),
(2, 3, '2025-06-18', 'skin allergy consultation'),
(3, 2, '2025-06-20', 'migraine review'),
(4, 5, '2025-06-22', 'bone injury check'),
(5, 4, '2025-06-25', 'child vaccination');
select * from appointments;

