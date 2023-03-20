// function getCookie(name) {
//     const cookies = document.cookie.split(';');
//     for (let i = 0; i < cookies.length; i++) {
//       const cookie = cookies[i].trim();
//       if (cookie.startsWith(name + '=')) {
//         return cookie.substring(name.length + 1);
//       }
//     }
//     return null;
//   }
//   setInterval(loadDataFromServer,1000)
  

//   function Ip(){
//     return location.host.split(":")[0];
    
//     }

// function responsiveChat(element) {
//   $(element).html('<form class="chat"><span></span><div class="messages"></div>');

//   function showLatestMessage(element) {
//     $('.responsive-html5-chat').find('.messages').scrollTop($('.responsive-html5-chat .messages')[0].scrollHeight);
//   }
//   showLatestMessage(element);

//   $(element + ' input[type="text"]').keypress(function (event) {
//       if (event.which == 13) {
//           event.preventDefault();
//           $(element + ' input[type="submit"]').click();
//       }
//   });
//   $(element + ' input[type="submit"]').click(function (event) {
//       event.preventDefault();
//       var message = $(element + ' input[type="text"]').val();
//       if ($(element + ' input[type="text"]').val()) {
//           var d = new Date();
//           var clock = d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds();
//           var month = d.getMonth() + 1;
//           var day = d.getDate();
//           var currentDate =
//               (("" + day).length < 2 ? "0" : "") +
//               day +
//               "." +
//               (("" + month).length < 2 ? "0" : "") +
//               month +
//               "." +
//               d.getFullYear() +
//               "&nbsp;&nbsp;" +
//               clock;
//           $(element + ' div.messages').append(
//               '<div class="message"><div class="myMessage"><p>' +
//               message +
//               "</p><date>" +
//               currentDate +
//               "</date></div></div>"
//           );
//           setTimeout(function () {
//               $(element + ' > span').addClass("spinner");
//           }, 100);
//           setTimeout(function () {
//               $(element + ' > span').removeClass("spinner");
//           }, 2000);
//       }
//       $(element + ' input[type="text"]').val("");
//       showLatestMessage(element);
//   });
// }

// function responsiveChatPush(element, sender, origin, date, message) {

//   var originClass;
//   if (origin == 'me') {
//       originClass = 'myMessage';
//   } else {
//       originClass = 'fromThem';
//   }
//   $(element + ' .messages').append('<div class="message"><div class="' + originClass + '"><p>' + message + '</p><date><b>' + sender + '</b> ' + date + '</date></div></div>');
// }

// responsiveChat('.responsive-html5-chat');


// if (parent == top) {
// $("a.article").show();
// }


// function loadDataFromServer(){

//   // renderMessage()

// }
// socket1.on('message',recieverId =>{
//   console.log(recieverId+"aa")

//   if(recieverId==getCookie('sessionId')||recieverId=="everyone"){
//     renderMessage();
//   }

// })


// let last_msgLength =0;
// function renderMessage(){
  
  
 

//     var xhr = new XMLHttpRequest();

//     xhr.onreadystatechange = function() {
  
//       if(this.readyState == 4 && this.status == 200){
      
        
//         let message = JSON.parse(this.responseText)
       
//         if(message.length>last_msgLength){
          
//           // document.querySelector('.messages').innerHTML="";
//           for (let i = last_msgLength; i < message.length; i++) {
            
//             nameOfSender = "akash";
//             if(message[i].senderSessionId == getCookie('sessionId')){
//                     user = "me"
//                     nameOfSender = "me"
//             }else{
//               user = "you"
//               nameOfSender = message[i].username;
             
//             }
             
//             if(message[i].roomId == getCookie('roomId')){
//               responsiveChatPush('.chat', nameOfSender, user, message[i].time, message[i].message);
//             }
  
            
  
//           }
//           last_msgLength = message.length;
//         }
     
        
        
//       } 
  
//     }
  
//     xhr.open("POST", "http://"+Ip()+":8081/ZOOM/renderMessages", true);
  
//       xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  
//       xhr.send("userSessionId="+getCookie('sessionId')+"&roomId="+getCookie('roomId'));

// }

// function chatAccess(){
// if(document.getElementById('chat_box').style.display=="none"){
//   document.getElementById('chat_box').style.display="block";
// }else{
//   document.getElementById('chat_box').style.display="none";
// }
// }
// $('#notify-form').submit(function (event) {
//   event.preventDefault();
//      $('.notify-form').removeClass('active')
//      $('.message-end').addClass('active')

//      setTimeout( function (event) {
//        $('.message-end').removeClass('active')
//        $('.btn-send').removeClass('active')
//        $('.field input').val("")
//      }, 2000)
//  })

//    $('.message:not(.message-end)').click(function (event) {
//      $('.notify-form').addClass('active')
//      $('.field input').focus()
//    })

//    $('.field input').keyup(function(event) {

//      if (/^[a-z0-9._-]+@[a-z0-9._-]+\.[a-z]{2,6}$/.test(event.target.value)) {
//        $('.btn-send').addClass('active')				
//      }
//      else {
//        $('.btn-send').removeClass('active')
//      }
//    })