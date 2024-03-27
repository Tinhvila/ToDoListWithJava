<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <link rel="stylesheet" href="css/style.css">
<title>To Do List | Authentication</title>
</head>
<body>
	 <div class="container" id="container">

        <div class="form-container sign-up">
            <form action="Authentication" name="signUpForm" method="post" onsubmit = "return validateFormSignUp()">
                <h1 class="signUpHeading">Create Account</h1>
				<p id="signUpErrorMessage"></p>
         	<input type="hidden" name="action" value="SIGN-UP">
                <div class="form-group">
                    <label for="" class="title-input">
                        <span id="required">*</span>
                        Your Name</label>
                    <input name="userName" type="text" placeholder="Name" required>
                </div>
                <div class="form-group">
                    <label for="" class="title-input">
                        <span id="required">*</span>
                        Your Email</label>
                    <input name="email" type="email" placeholder="Email" required>
                </div>
                <div class="form-group">
                    <label for="" class="title-input">
                        <span id="required">*</span>
                        Your Password</label>
                    <input name="passWord" type="password" oninput="onInput()" placeholder="Password" id="Password" required>
                    <i class="fa-solid fa-eye eye"></i>
                    <i class="fa-solid fa-eye-slash eye-slash"></i>
                </div>
                <div class="form-group">
                    <label for="" class="title-input">
                        <span id="required">*</span>
                        Your Password</label>
                    <input name="reTypePassWord" type="password" oninput="onInput()" placeholder="Retype password" id="retypePassword" required>
                    <i class="fa-solid fa-eye  eyeSignup1"></i>
                    <i class="fa-solid fa-eye-slash  eye-slashSignup1"></i>
                </div>
                <button type="submit">Sign Up</button>
            </form>
        </div>

        <div class="form-container sign-in">
            <form action="Authentication" id="signInForm" method="post">
                <h1 class="signInHeading">Sign In</h1>
            <p id="signInErrorMassage" ></p>
               <input type="hidden" name="action" value="SIGN-IN">
                <div class="form-group">
                    <label for="" class="title-input">
                        <span id="required">*</span>
                        Your Email</label>
                    <input type="email" placeholder="Email" oninput="onInputSignIn()" name="emailLogin" id="emailLogin"required>
                </div>
                <div class="form-group">
                    <label for="" class="title-input">
                        <span id="required">*</span>
                        Your Password</label>
                    <input  type="password" placeholder="Password" oninput="onInputSignIn()" name="passwordLogin" id="loginPassword" required>
                    <i class="fa-solid fa-eye eyeSignin"></i>
                    <i class="fa-solid fa-eye-slash eye-slashSignin"></i>
                </div>

                <a href="#" id="forGetPw">Forget Your Password?</a>
                <button>Sign In</button>
            </form>
        </div>
        
        <div class="toggle-container">
            <div class="toggle">
                <div class="toggle-panel toggle-left">
                    <h1>Welcome Back!</h1>
                    <p>Enter your personal details to use all of site features</p>
                    <button class="hidden" id="login">Sign In</button>
                </div>
                <div class="toggle-panel toggle-right">
                    <h1>Hello, Friend!</h1>
                    <p>Register with your personal details to use all of site features</p>
                    <button class="hidden" id="register">Sign Up</button>
                </div>
            </div>
        </div>
    </div>

    <script src="js/script.js"></script>
    <script>
 
    	 document.getElementById("signInForm").addEventListener("submit", function() {
    		 let errorMessage = "<%=request.getAttribute("loginMessage")%>";
    		    if(errorMessage !== null && errorMessage !== "null"){
    		   	document.getElementById("emailLogin").classList.add("error");
    		    document.getElementById("loginPassword").classList.add("error");    	
    		    document.getElementById("signInErrorMassage").innerText = errorMessage;
    		    }
    	 });
    
    function onInputSignIn(){
        document.getElementById("emailLogin").classList.remove("error");
        document.getElementById("loginPassword").classList.remove("error");
        document.getElementById("signInErrorMassage").innerText = "";
    }
    
    
    
    </script>
</body>
</html>