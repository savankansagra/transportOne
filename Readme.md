## API Documentation and guide.
[Google doc edit](https://docs.google.com/document/d/1ISPrHjIcTkI9s83NhhRnbxwjunx5wLSMWdA7oMw8wnc/edit?usp=sharing)

[Google doc view](https://docs.google.com/document/d/e/2PACX-1vRZm2GZwp2PVe2epQMWnWyirxgFhjkWUdePaNghmFjyMm6NmJL9iBzupPhQoEDH3TYjXvILv-L5zdMb/pub)

[Figma desing url](https://www.figma.com/file/0I6yvtvgJ7h1476tWhZ8Rn/Untitled?node-id=0%3A1)
figma crendentails
kansagrasavan111@gmail.com
sa123van


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
- [x] Authentication of OTP.
- [x] create two Roles as "USER" and "OWNER" and assign them access.
- [ ] Not Working, need to do more research. - create authentication of the user roles and roles based access of APIs.
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
	path: api/authentication/userTelephoneNumber
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
2. If Authentication with Otp is successfull then create JWT token with all neccesary user claims and send to client. 
    -  It also assign the Role "USER" or "OWNER" with user_role table.
    

## Upload Truck Details - "OWNER" role side.
#### upload truck details with valid documents. 
**Features**
- [ ] create POST request of truck details. with OWNER role.
- [ ] creating flag of verification pending.
- [ ] verify document by admin user. and make flags true.


	

	
	
	
## Thanks to References ##
** Spring Security with Jwt token Authentication **
<link>https://github.com/murraco/spring-boot-jwt</link>
