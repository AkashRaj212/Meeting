const express = require('express')
const app = express()
const server = require('http').Server(app)
const io = require('socket.io')(server)
const cors = require('cors')
app.use(express.json())
app.use(cors())



const { v4: uuidV4 } = require('uuid')
const { Session } = require('inspector')

app.set('view engine', 'ejs')
app.use(express.static('public'))

app.get('/', (req, res) => {
  console.log("done")
  res.redirect(`/${uuidV4()}`)
})

app.get('/:room', (req, res) => {
  res.render('room', { roomId: req.params.room })
})

io.on('connection', socket => {
  socket.on('join-room', (roomId, userId) => {

  
    socket.join(roomId)
    socket.to(roomId).broadcast.emit('user-connected', userId)
  

    socket.on('disconnect', () => {
      socket.to(roomId).broadcast.emit('user-disconnected', userId)

    })
  })
socket.on('sharescreen', (roomId, videostream) => {
  
socket.to(roomId).broadcast.emit('loadscreenshare', videostream)
})
  

socket.on('message', (roomId,recieverId) => {
  

  socket.to(roomId).broadcast.emit('renderMessage',recieverId)

})


})


// api key - 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



server.listen(3005)

