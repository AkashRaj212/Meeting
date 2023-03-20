/**
 * 
 */
function Ip(){
    return location.host.split(":")[0];
    
    }
    
 function a() {
    
window.location.href = "http://"+Ip()+":3005/";


 }