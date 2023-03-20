function chatGpt(){
 
    
console.log("chatGpt");
        const chatGptDiv = document.getElementById("chatGpt");
    
    
    if(chatGptDiv.style.display == "block"){
        chatGptDiv.style.display = "none";
        
    }else{
        chatGptDiv.style.display = "block";
    }
    
    
}

function sendToChatGpt(){
    const chatGptmsg = document.getElementById("InputmessageforchatGpt");
    message={
        "text": chatGptmsg.value,
        "time": new Date().getDate() + " / " + (new Date().getMonth()+1) + " / " + new Date().getFullYear(),
        "sender": "me"
    }

    createMessageElement(message)
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {
  
      if(this.readyState == 4 && this.status == 200){
        
        console.log(JSON.parse(this.responseText).choices[0].text);
        message={
            "text": JSON.parse(this.responseText).choices[0].text,
            "time": new Date().getDate() + " / " + (new Date().getMonth()+1) + " / " + new Date().getFullYear()
        }
        createMessageElement(message)
    
      } 
  
    }

    xhr.open("POST", "http://"+Ip()+":8081/Meeting/answer", true);
  
      xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
      xhr.send("query="+chatGptmsg.value);
      chatGptmsg.value="";

}

function createMessageElement(message) {
    // Create a new message element
    const messageElement = document.createElement('div');
    messageElement.classList.add('messagegpt');
  
    // Add the message body
    const messageBodyElement = document.createElement('div');
    messageBodyElement.classList.add('message-body');
    // messageBodyElement.innerText = `<p>${message.text}</p><span class="time">${message.time}</span>`;
    let p = document.createElement('p');
    messageElement.appendChild(p);
    p.innerText=message.text;
    let sp = document.createElement('span');
    messageElement.appendChild(sp);
    sp.setAttribute("class","time");
    sp.innerText=message.time;
    messageElement.appendChild(messageBodyElement);
  
    // Set the message style based on who sent it
    if (message.sender === 'me') {
      messageElement.classList.add('sent');
    } else {
      messageElement.classList.add('received');
    }
  
    document.getElementById('chatgpt').appendChild(messageElement);
  }