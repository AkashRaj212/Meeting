function Ip(){
    return location.host.split(":")[0];
    
    }

    let mediaRecorder;
function record(e){

    if(e.innerText == "radio_button_unchecked"){
        e.innerText = "radio_button_checked"
        start_recording();
    }
    else{
        e.innerText = "radio_button_unchecked"
        stop_recording();
    }
    
}
async function start_recording(){
    let stream = await recordScreen();
    let mimeType = 'video/webm';
    mediaRecorder = createRecorder(stream, mimeType);
  let node = document.createElement("p");
    node.textContent = "Started recording";
    document.body.appendChild(node);
}

function stop_recording(){
    mediaRecorder.stop();
    let node = document.createElement("p");
    node.textContent = "Stopped recording";
    document.body.appendChild(node);
}

async function recordScreen() {
    return await navigator.mediaDevices.getDisplayMedia({
        audio: true, 
        video: { mediaSource: "screen"}
    });
}

function createRecorder (stream, mimeType) {
  // the stream data is stored in this array
  let recordedChunks = []; 
  
  const mediaRecorder = new MediaRecorder(stream);

  mediaRecorder.ondataavailable = function (e) {
    if (e.data.size > 0) {
      
      recordedChunks.push(e.data);
      sendChunksToServlet(e.data) 
     }
  };
  mediaRecorder.onstop = function () {
     saveFile(recordedChunks);
     recordedChunks = [];
  };
  mediaRecorder.start(200); // For every 200ms the stream data will be stored in a separate chunk.
  return mediaRecorder;
}

function saveFile(recordedChunks){
  


   const blob = new Blob(recordedChunks, {
      type: 'video/webm'
    });
    let filename = window.prompt('Enter file name'),
        downloadLink = document.createElement('a');
    downloadLink.href = URL.createObjectURL(blob);
    downloadLink.download = `${filename}.webm`;

    document.body.appendChild(downloadLink);
    downloadLink.click();
    URL.revokeObjectURL(blob); // clear from memory
    document.body.removeChild(downloadLink);
}


function sendChunksToServlet(recordedChunks) {
  
    fetch('http://'+Ip()+':3000/record/a',{
        method:"POST",
        headers:{
            'Content-Type' :'application/json; charset=UTF-8',
        },
        body:JSON.stringify({
            namegv:"run",
        }),

    }).then((response)=>{
        console.log(response);
        return response;
    })

}



