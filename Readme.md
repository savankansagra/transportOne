## Login
#### Register user.
**Features**
- [x]  It will save the useremail and phone number to postgresql database.
- [x]  generate random otp and save to storage for limited time.
- [x]  send otp to email using smtp.google.com server.
- [x]  send otp to mobile using twillo apis.
- [ ] do more research into mobile send otp for optimization of cost.
-  

**Request type**

	request type: POST
	path: api/register
	request payload: {
		"useremail": "kansagra@gmail.com",
		"telephoneNumber": "+919316370069"
	}
	response : {
		"message": "otp was send to email and phone. please enter otp."
	}
	
**Logic :**
1. enter email and phone number into user table of database. here we used postgres as database.
    -   if table already contain the entry then it will return message "fail to save user."
    -   
    

## ROLES
#### Set the Roles and security
**Features**
- [ ] Authentication of OTP.
- [ ] create two Roles as "USER" and "OWNER" and assign them access.
- [ ] create authentication of the user roles and roles based access of APIs.
-  

**Request type**

	request type: POST
	path: api/authentication/useremail
	request payload: {
		"useremail": "kansagra@gmail.com"
		"otp": "636578" // 6 digit otp message
	}
	success response : {
		"message": "Authentication successfull"
	}
	failed response : { //if email is wrong
		"message" : "enter valid email address."
	}
	failed response : { //if OTP is wrong or expired.
		"message" : "OTP is expired or it is wrong."
	}
	
	-----------------
	request type: POST
	path: api/authentication/useremail
	request payload: {
		"telephoneNumber": "+919316370069",
		"otp": "636578" // 6 digit otp message
	}
	success response : {
		"message": "Authentication successfull"
	}
	failed response : { //if mobile number is wrong
		"message" : "enter valid mobile address."
	}
	failed response : { //if OTP is wrong or expired.
		"message" : "OTP is expired or it is wrong."
	}
	
	------------------
	request type: POST
	path: api/role/setRole
	request payload: {
		"telephoneNumber": "+919316370069",
		"Role": "USER" Or "OWNER"
	}
	success response : {
		"message": "USER OR OWNER role is successfully."
	}
	failed response : { //if mobile number is wrong
		"message" : "Setting role is unsuccessfull."
	}
	
	
**Logic :**
1. enter otp code with the email address or phone number.
2. If Authentication is successfull then add the role "USER" or "OWNER" into table 
    -  we need to create the "userrole" table with mapping of user mobile number and role column.
    -  for admin role we need to make manual entry for "ADMIN" role in "userrole" table. we will assign all api access to "ADMIN" role. 
	
