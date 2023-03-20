
const SignUpButton = document.getElementById('sign-up-button');
let a = 1;
function Ip(){
    return location.host.split(":")[0];
    
    }
function buttonMoveRigth() {
    SignUpButton.style.transform = 'translateX(100%)';
}

function buttonMoveLeft() {
    SignUpButton.style.transform = 'translateX(0%)';
}

function signUp() {

    const user=document.getElementById('userSignUp').value;
    const password=document.getElementById('passSignUp').value;
    const email = document.getElementById('emailSignUp').value;

    var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(this.readyState == 4 && this.status == 200) {
			
				window.location.href = 'http://localhost:8081/Meeting/CreateMeeting.html';
			
		}
	}	
	
	xhr.open("POST", "http://"+Ip()+":8081/Meeting/SignUp");
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send("username="+user+"&password="+password+"&email="+email);

} 

function mouseOver() {
 
  
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const user=document.getElementById('userSignUp').value;
    const password=document.getElementById('passSignUp').value;
    const email = document.getElementById('emailSignUp').value;
   
    if(user != ""||password!= ""||emailPattern.test(email)){
       
        if(password==document.getElementById('Repeat_pass').value){
            
            return true;
        }
        
    }

if(a==0){
    buttonMoveLeft()
    a=1;
}if(a==1){
    buttonMoveRigth()
    a=2
}
else{
    buttonMoveLeft()
    a=1;
}

}

function SignIn(){

const user=document.getElementById('user').value;
const password=document.getElementById('pass').value;


    var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		console.log(this.responseText);
		if(this.readyState == 4 && this.status == 200 && this.responseText == "Login Successfull") {
			
				window.location.href = "http://"+Ip()+":8081/Meeting/CreateMeeting.html";
			
		}
        if(this.readyState == 4 && this.status == 200 && this.responseText == "Login not Successfull") {
			
           document.getElementById('response').style.display = "block";
        
    }
    
	}	
	
	xhr.open("POST", "http://localhost:8081/Meeting/SignIn");
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send("username="+user+"&password="+password);
    
}

function removeResponse(){
console.log("a")
document.getElementById('response').style.display = "none";
    
}