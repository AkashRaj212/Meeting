

function Ip(){
    return location.host.split(":")[0];
    
    }

function participantsListAccess(){
    if(document.getElementById("participantsList").style.display == "none"){
       document.getElementById("participantsList").style.display = "flex";
       load_Participants();
    }else{
        document.getElementById("participantsList").style.display = "none";
    }
    }

function load_Participants(){

    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
  
      if(this.readyState == 4 && this.status == 200){

        let participants = this.responseText.replace("[","").replace("]","").split(",");
        console.log(participants)


      
      const participantList = document.getElementById("participant-list");
      participantList.innerHTML = "";
      
      participants.forEach((participant) => {
        const participantDiv = document.createElement("div");
        participantDiv.classList.add("participant");
      
        const logoDiv = document.createElement("div");
        logoDiv.classList.add("logo");
        const logoImg = document.createElement("img");
        logoImg.src = "https://w7.pngwing.com/pngs/81/570/png-transparent-profile-logo-computer-icons-user-user-blue-heroes-logo-thumbnail.png";
        logoDiv.appendChild(logoImg);
        participantDiv.appendChild(logoDiv);
      
        const nameSpan = document.createElement("span");
        nameSpan.textContent = participant;
        nameSpan.classList.add("name");
        participantDiv.appendChild(nameSpan);
      
       
        participantList.appendChild(participantDiv);
      });
      } 
      
  
    }
    
    xhr.open("POST", "http://"+Ip()+":8081/Meeting/loadParticipantsList", true);  
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("roomId="+getCookie("roomId"));

  

 }


function leave(){



  var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
  
      if(this.readyState == 4 && this.status == 200){

        document.cookie = "sessionId=";

        window.location.href ="http://"+Ip()+":8081/Meeting/thankyou.html";
      }
    }



  xhr.open("POST", "http://"+Ip()+":8081/Meeting/Leave", true);  
  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  xhr.send("sessionId="+getCookie("sessionId"));

  
}





function videoaudioOff(){
  var users = document.querySelectorAll('video');
  for(var i = 0; i < users.length; i++){
    users[i].muted = true;
    users[i].style.display = "none";
  }


}



function audioAccess(e){
  
  if(e.classList == "fa-solid fa-microphone"){
    e.classList="fa-solid fa-microphone-slash"
    
    document.getElementById(getCookie('videoId')).style.border="0px solid black";
    
  }
  else{
    e.classList="fa-solid fa-microphone"
    document.getElementById(getCookie('videoId')).style.border="1px solid gold";
   }
}


setInterval(adjustVideoDiv,10000)

function adjustVideoDiv(){
  
  let videoDiv = document.querySelectorAll(".video-item")
  for(var i = 0; i < videoDiv.length; i++){
    if(videoDiv[i].innerHTML == ""){
      videoDiv[i].remove()
    }
    if(videoDiv[i].childNodes.length == 1){
      videoDiv[i].remove()
    }
  }

}