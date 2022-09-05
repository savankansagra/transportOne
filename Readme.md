## Login
#### Register user.
**Features**
- [x]  It will save the useremail and phone number to postgresql database.
- [ ]  generate random otp and save to storage for limited time.
- [ ]  send otp to email using smtp.google.com server.
- [ ]  send otp to mobile using twillo apis.
- [ ] do more research into mobile send otp for optimization of cost.
-   
**Request type**
    request type: POST
	path: api/user/register
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
	
