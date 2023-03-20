const socket = io('/')
let MyID;
const videoGridId=[]
let a =0
const videoPausedList = []

function getCookie(name) {
    const cookies = document.cookie.split(';');
    for (let i = 0; i < cookies.length; i++) {
      const cookie = cookies[i].trim();
      if (cookie.startsWith(name + '=')) {
        return cookie.substring(name.length + 1);
      }
    }
    return null;
  }

const videoGrid = document.getElementById('video-container')
const myPeer = new Peer(undefined, {
  host: '/',
  port: '3010'
})

const myVideo = document.createElement('video')
myVideo.muted = true
const peers = {}
navigator.mediaDevices.getUserMedia({
  video: true,
  audio: true
}).then(stream => {

  var videoTrack = stream.getVideoTracks()[0];
  
    videoTrack.enabled = !videoTrack.enabled;

  document.getElementById("videoAccess").addEventListener('click', function() {
  
    var videoTrack = stream.getVideoTracks()[0];
  
    videoTrack.enabled = !videoTrack.enabled;
    
    document.getElementById("videoPtag").innerText = videoTrack.enabled ? 'Stop Video' : 'Start Video';
  });


  var audioTrack = stream.getAudioTracks()[0];
    
  audioTrack.enabled = !audioTrack.enabled;
  document.getElementById("audioAccess").addEventListener('click', function() {
 
    var audioTrack = stream.getAudioTracks()[0];
    
    audioTrack.enabled = !audioTrack.enabled;
   
    document.getElementById("microphonePtag").innerText = audioTrack.enabled ? 'Mute' : 'Unmute';
  });

  document.cookie = "videoId=" + stream.id
  addVideoStream(myVideo, stream)

  myPeer.on('call', call => {
   
 
    call.answer(stream)
    const video = document.createElement('video')
    call.on('stream', userVideoStream => {
     
      addVideoStream(video, userVideoStream)
    })
  })

  socket.on('user-connected', userId => {


    
    connectToNewUser(userId, stream)
  })
})




socket.on('user-disconnected', userId => {
  if (peers[userId]) {
    peers[userId].close()
  }
})



myPeer.on('open', id => {
  
  document.cookie = "peerId="+id; 
  socket.emit('join-room', ROOM_ID, id)
  document.cookie = "roomId="+ROOM_ID;
})


function connectToNewUser(userId, stream) {
 
  const call = myPeer.call(userId, stream)
  const video = document.createElement('video')
  call.on('stream', userVideoStream => {
   
    addVideoStream(video, userVideoStream)
  })
  call.on('close', () => {
    video.remove()
  })

  peers[userId] = call 
}

function addVideoStream(video, stream) {
  video.srcObject = stream
  
  
  video.addEventListener('loadedmetadata', () => {

    video.play();
   
  })

  
   if(video.srcObject.active==true) {


    const video_item = document.createElement('div');
  videoGrid.appendChild(video_item);
    videoGridId.push(video.srcObject.id)
    video.id=video.srcObject.id;
    
    video_item.classList.add('video-item')

    video_item.append(video)
    
  
  setTimeout(usernamelist, 5000);
}
}



function videoAccess(e){
  if(e.classList == "fa-solid fa-video"){
    e.classList="fa-solid fa-video-slash";
    

  }
  else{
    e.classList="fa-solid fa-video";
    
  }
}

function createUser(){
  document.cookie = "username = "  +document.getElementById('username').value;
  document.getElementById("whole_container").style.display = "block";
  
}



window.onload = function(event) {
  adjustVideoDiv
 if(getCookie('sessionId')== null){
  document.getElementById("whole_container").style.display = "none";
  document.getElementById("name_div").style.display = "block";
  document.querySelector(".login").style.display = "block";

 }else{
  document.getElementById("name_div").style.display = "none";
 document.querySelector(".login").style.display = "none";
  setTimeout( userIn, 1000);
 }
 
}




function currentUsers(){

var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {
  
      if(this.readyState == 4 && this.status == 200){
        
        console.log(this.responseText)
      
        document.cookie = "sessionId="+this.responseText;
      } 
  
    }

    xhr.open("POST", "http://"+Ip()+":8081/Meeting/userIn", true);
  
      xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
      xhr.send("username="+getCookie('username')+ "&roomId="+getCookie('roomId')+ "&videoId="+getCookie('videoId')+"&userId"+getCookie('userId')+"&sessionId"+getCookie('sessionId'));


}

function userIn(){
  
  document.cookie = "username = "  +document.getElementById('username').value;
  var xhr = new XMLHttpRequest();

  xhr.onreadystatechange = function() {

    if(this.readyState == 4 && this.status == 200){
      
      document.querySelector(".login").style.display = "none";
      document.getElementById("whole_container").style.display = "block";
      document.cookie = "sessionId="+this.responseText;
    } 

  }

  xhr.open("POST", "http://"+Ip()+":8081/Meeting/userIn", true);

    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    var userDetails = {"sessionId":getCookie('sessionId'),
                       "peerId":getCookie('peerId'),
                       "username":getCookie('username'),
                       "roomId":getCookie('roomId'),
                       "videoId":getCookie('videoId')
                        }
    xhr.send("userDetails="+JSON.stringify(userDetails));



}

function send_message(){

  socket.emit("message", getCookie('roomId'));


  var xhr = new XMLHttpRequest();
  console.log("as")
  xhr.onreadystatechange = function() {

    if(this.readyState == 4 && this.status == 200){
       
      
      document.getElementById('Inputmessage').value="";
      console.log(this.responseText)

      
renderMessage();

    } 

  }
  
  socket.emit("message", getCookie('roomId'),"everyone");
  xhr.open("POST", "http://"+Ip()+":8081/Meeting/chatMessage", true);

    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    var msgDetails = {"senderSessionId":getCookie('sessionId'),
                       "message":document.getElementById('Inputmessage').value,
                       "recieverSessionId":"everyone"
                        }
    xhr.send("msgDetails="+JSON.stringify(msgDetails));
}

function Ip(){
  return location.host.split(":")[0];
  }

function screenshare(){
  
navigator.mediaDevices.getDisplayMedia({ video: true })
.then(function(stream) {
  console.log(stream)
  socket.emit('sharescreen',getCookie('roomId'), stream)

  var video = document.createElement('video');
  video.srcObject = stream;
  video.autoplay = true;
  // document.getElementById("main-content")="";
  document.getElementById("main-content").appendChild(video);

  
  var screenStream = stream;

  
  var stopButton = document.createElement('button');
  stopButton.innerText = 'Stop Sharing';
  stopButton.addEventListener('click', function() {
   
    screenStream.getTracks().forEach(function(track) {
      track.stop();
    });

    
    video.remove();
    stopButton.remove();
  });
  document.getElementById("main-content").appendChild(stopButton);
})
.catch(function(error) {
  console.log('Error accessing screen capture API: ' + error);
});
}

function usernamelist(){

  

  var xhr = new XMLHttpRequest();

  xhr.onreadystatechange = function() {
    if(this.readyState == 4 && this.status == 200){
let namelist = JSON.parse(this.responseText)
console.log(namelist)
Object.keys(namelist).forEach((key) => {
  let name =  document.createElement("div");
  name.setAttribute("class", "username");
  name.innerText=namelist[key]
  document.getElementById(key).parentNode.appendChild(name);
})
  
  }
  }
  xhr.open("POST", "http://"+Ip()+":8081/Meeting/Usernames", true);
  
  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

  xhr.send("roomId="+getCookie('roomId'));

}

socket.on('loadscreenshare',stream => {
  console.log(stream);
  var video = document.createElement('video');
  video.srcObject = stream;
  video.autoplay = true;
  document.getElementById("main-content").appendChild(video);
})


function responsiveChat(element) {
  $(element).html('<form class="chat"><span></span><div class="messages"></div>');

  function showLatestMessage(element) {
    $('.responsive-html5-chat').find('.messages').scrollTop($('.responsive-html5-chat .messages')[0].scrollHeight);
  }
  showLatestMessage(element);

  $(element + ' input[type="text"]').keypress(function (event) {
      if (event.which == 13) {
          event.preventDefault();
          $(element + ' input[type="submit"]').click();
      }
  });
  $(element + ' input[type="submit"]').click(function (event) {
      event.preventDefault();
      var message = $(element + ' input[type="text"]').val();
      if ($(element + ' input[type="text"]').val()) {
          var d = new Date();
          var clock = d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds();
          var month = d.getMonth() + 1;
          var day = d.getDate();
          var currentDate =
              (("" + day).length < 2 ? "0" : "") +
              day +
              "." +
              (("" + month).length < 2 ? "0" : "") +
              month +
              "." +
              d.getFullYear() +
              "&nbsp;&nbsp;" +
              clock;
          $(element + ' div.messages').append(
              '<div class="message"><div class="myMessage"><p>' +
              message +
              "</p><date>" +
              currentDate +
              "</date></div></div>"
          );
          setTimeout(function () {
              $(element + ' > span').addClass("spinner");
          }, 100);
          setTimeout(function () {
              $(element + ' > span').removeClass("spinner");
          }, 2000);
      }
      $(element + ' input[type="text"]').val("");
      showLatestMessage(element);
  });
}

function responsiveChatPush(element, sender, origin, date, message) {

  var originClass;
  if (origin == 'me') {
      originClass = 'myMessage';
  } else {
      originClass = 'fromThem';
  }
  $(element + ' .messages').append('<div class="message"><div class="' + originClass + '"><p>' + message + '</p><date><b>' + sender + '</b> ' + date + '</date></div></div>');
}

responsiveChat('.responsive-html5-chat');


if (parent == top) {
$("a.article").show();
}


function loadDataFromServer(){

  // renderMessage()

}
socket.on('renderMessage',recieverId =>{
  console.log(recieverId+"aa")

  if(recieverId==getCookie('sessionId')||recieverId=="everyone"){
    renderMessage();
  }

})


let last_msgLength =0;
function renderMessage(){
  
  
 

    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {
  
      if(this.readyState == 4 && this.status == 200){
      
        
        let message = JSON.parse(this.responseText)
       
        if(message.length>last_msgLength){
          
          // document.querySelector('.messages').innerHTML="";
          for (let i = last_msgLength; i < message.length; i++) {
            
            nameOfSender = "akash";
            if(message[i].senderSessionId == getCookie('sessionId')){
                    user = "me"
                    nameOfSender = "me"
            }else{
              user = "you"
              nameOfSender = message[i].username;
             
            }
             
            if(message[i].roomId == getCookie('roomId')){
              responsiveChatPush('.chat', nameOfSender, user, message[i].time, message[i].message);
            }
  
            
  
          }
          last_msgLength = message.length;
        }
     
        
        
      } 
  
    }
  
    xhr.open("POST", "http://"+Ip()+":8081/Meeting/renderMessages", true);
  
      xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  
      xhr.send("userSessionId="+getCookie('sessionId')+"&roomId="+getCookie('roomId'));

}

function chatAccess(){
if(document.getElementById('chat_box').style.display=="none"){
  document.getElementById('chat_box').style.display="block";
  renderMessage()
}else{
  document.getElementById('chat_box').style.display="none";
}
}