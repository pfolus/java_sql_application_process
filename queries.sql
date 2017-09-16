SELECT first_name, last_name
FROM mentors;


SELECT nick_name
FROM mentors
WHERE City = "Miskolc";


SELECT first_name || ' ' || last_name AS full_name, phone_number
FROM applicants
WHERE first_name = "Carol";


SELECT first_name || ' ' || last_name AS full_name, phone_number
FROM applicants
WHERE email LIKE "%@adipiscingenimmi.edu";


SELECT * FROM applicants
WHERE application_code = 54823;


UPDATE applicants
SET phone_number = '003670/223-7459' 
WHERE first_name = 'Jemima' AND last_name = 'Foreman';


SELECT first_name, last_name, phone_number
FROM applicants
WHERE first_name = 'Jemima' AND last_name = 'Foreman';


DELETE FROM applicants
WHERE email LIKE '%@mauriseu.net';
