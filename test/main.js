var body = null;
var client = new WSClient('localhost', 1357);
client.reconnect();

client.addMessageListener(function(data) {
  body = JSON.parse(data);
  console.log(body);
});
