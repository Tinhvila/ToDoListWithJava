const container = document.getElementById('container');
const registerBtn = document.getElementById('register');
const loginBtn = document.getElementById('login');

registerBtn.addEventListener('click', () => {
    container.classList.add("active");
});

loginBtn.addEventListener('click', () => {
    container.classList.remove("active");
});





//pw Của sign up 
let eye = document.getElementsByClassName("eye")[0];
let eye_slash = document.getElementsByClassName("eye-slash")[0];
const passwordSignup = document.getElementById("Password");

// pw của sign up 2
let eyeSignup1 = document.getElementsByClassName("eyeSignup1")[0];
let eye_slashSignup1 = document.getElementsByClassName("eye-slashSignup1")[0];
const passwordSignup1 = document.getElementById("retypePassword");

// pw của logn in
let eyeSignin = document.getElementsByClassName("eyeSignin")[0];
let eye_slashSignin = document.getElementsByClassName("eye-slashSignin")[0];
const passwordLogin = document.getElementById("loginPassword");

eye_slash.addEventListener("click", () => {
    eye_slash.style.display = "none";
    eye.style.display = "block";
    passwordSignup.setAttribute("type", "text")
})

eye.addEventListener("click", () => {
    eye.style.display = "none";
    eye_slash.style.display = "block";
    passwordSignup.setAttribute("type", "password")
})



eye_slashSignup1.addEventListener("click", () => {
    eye_slashSignup1.style.display = "none";
    eyeSignup1.style.display = "block";
    passwordSignup1.setAttribute("type", "text")
})

eyeSignup1.addEventListener("click", () => {
    eyeSignup1.style.display = "none";
    eye_slashSignup1.style.display = "block";
    passwordSignup1.setAttribute("type", "password")
})



eye_slashSignin.addEventListener("click", () => {
    eye_slashSignin.style.display = "none";
    eyeSignin.style.display = "block";
    passwordLogin.setAttribute("type", "text")
})

eyeSignin.addEventListener("click", () => {
    eyeSignin.style.display = "none";
    eye_slashSignin.style.display = "block";
    passwordLogin.setAttribute("type", "password")
})


function validateFormSignUp() {
    
    let pw = document.signUpForm.passWord.value;
    let reTypePw = document.signUpForm.reTypePassWord.value;

    if(pw !== reTypePw){
        passwordSignup.classList.add("error"); 
        passwordSignup1.classList.add("error"); 
        document.getElementById("signUpErrorMessage").innerText = "*Password doesn't match";
        return false;
    }
}

function onInput(){
    passwordSignup1.classList.remove("error"); 
    passwordSignup.classList.remove("error"); 
    document.getElementById("signUpErrorMessage").innerText = "";
}



