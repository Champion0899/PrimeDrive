START TRANSACTION;

USE PrimeDrive;

SET FOREIGN_KEY_CHECKS=0;

INSERT INTO PrimeDrive.vehicle_holdings (id, name, founding, logo)
VALUES  
("b6459c70-357a-11f0-9e9f-6e56b6962fb5", "None", 1970, "https://media.istockphoto.com/id/1209793467/de/foto/crash-tesh-dummy-im-auto.jpg?s=2048x2048&w=is&k=20&c=uJufs-uB4eOUCt6dVIjUiReTVE2ziQ5_4HdwY8YJ9qA="),
("b645a51f-357a-11f0-9e9f-6e56b6962fb5", "Toyota Motor Corporation", 1937, "https://yt3.googleusercontent.com/ytc/AIdro_mYu04AGoxqP7sr23-DoKXxGRsD3VDq2-hqFTkUdyIXpg=s900-c-k-c0x00ffffff-no-rj"),
("b645addd-357a-11f0-9e9f-6e56b6962fb5", "Volkswagen AG", 1937, "https://uploads.vw-mms.de/system/production/images/cws/078/880/images/2120c5a57137cf72be0f26130c2ca1eb3c125614/B2023CW02240_web_1600.jpg?1686922903"),
("b645b5fe-357a-11f0-9e9f-6e56b6962fb5", "Zhejiang Geely Holding Group", 1968, "https://www.carlogos.org/logo/Geely-logo-2014-2560x1440.png"),
("b645bd81-357a-11f0-9e9f-6e56b6962fb5", "Mercedes-Benz Group", 1998, "https://car-symposium.com/wp-content/uploads/MB_Logo.png"),
("b645c554-357a-11f0-9e9f-6e56b6962fb5", "Ford Motor Company", 1903, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS9Au4DG7p1vAUKar2C6Z5Qe_nC6MsSb45nfg&s"),
("b645cdf0-357a-11f0-9e9f-6e56b6962fb5", "Tata Motors", 1945, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQGL66HpYuYUHLywfLdnHUY6MmJ7tbsvWkRmQ&s"),
("b645d669-357a-11f0-9e9f-6e56b6962fb5", "Hyundai Motor Group", 2000, "https://media.licdn.com/dms/image/v2/D560BAQEtGJEPrJk4eQ/company-logo_200_200/company-logo_200_200/0/1728266891944/hyundai_motor_group_logo?e=2147483647&v=beta&t=jeE6l5BZNm5kVhqfXff0nCbyQ7vjtctRIGsg3R2ABIY"),
("b645de79-357a-11f0-9e9f-6e56b6962fb5", "BMW Group", 1916, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRdq7AN5Q8ary7HxMHEx3SxLiAwSuEFLG___A&s"),
("b645e5a9-357a-11f0-9e9f-6e56b6962fb5", "Honda Motor", 1949, "https://upload.wikimedia.org/wikipedia/commons/7/7b/Honda_Logo.svg"),
("b645ed47-357a-11f0-9e9f-6e56b6962fb5", "General Motors", 1908, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQESVjtHgcwhQL9vCBDw7YgR61SSBTvWoiQeQ&s"),
("b645f4d1-357a-11f0-9e9f-6e56b6962fb5", "Stellantis", 2021, "https://scene7.toyota.eu/is/image/toyotaeurope/Toyota_Stellantis_Logo:Medium-Landscape?ts=0&resMode=sharp2&op_usm=1.75,0.3,2,0"),
("b645fc2e-357a-11f0-9e9f-6e56b6962fb5", "Renault-Nissan-Mitsubishi", 2002, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRdv7PJBYnRnUo2IK5BLXlFiQUpHWWVycHJTA&s"),
("b6460313-357a-11f0-9e9f-6e56b6962fb5", "Mercedes-Benz Group & Zhejiang Geely Holding Group", 1970, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmrIPlkV9JRCs4HDfBTQWmH0Lw4OKaq_iRgA&s"),
("b646088e-357a-11f0-9e9f-6e56b6962fb5", "Groupe Ligier", 1969, "https://www.edify-investmentpartner.com/wp-content/uploads/2019/08/LIGIER@2x-300x185.png"),
("b6460dd8-357a-11f0-9e9f-6e56b6962fb5", "Brilliance China Automotive Holdings", 1992, "https://upload.wikimedia.org/wikipedia/en/4/45/Brilliance_Auto_logo.png"),
("b646136b-357a-11f0-9e9f-6e56b6962fb5", "SAICA Motor", 1978, "https://upload.wikimedia.org/wikipedia/commons/5/58/SAICMotor_logo.png"),
("b6461898-357a-11f0-9e9f-6e56b6962fb5", "Great Wall Motor", 1984, "https://logos-download.com/wp-content/uploads/2018/09/Great_Wall_Motors_Company_Logo_full.png");

SET FOREIGN_KEY_CHECKS=1;

COMMIT;