function WSClient(host, port) {
  this.isConnected = false;
  this.host = host;
  this.port = port;

  this.callbacks = {
    message: null
  };
}

WSClient.prototype.reconnect = function() {
  if (!this.isConnected) {
    // Attempt to connect.
    this.connect();
    // Then ensure we're connected.
    setTimeout(this.reconnect.bind(this), 2000);
  }
};

WSClient.prototype.connect = function() {
  var url = 'ws://' + this.host + ':' + this.port + '/';
  //console.log(url);
  connection = new WebSocket(url);
  connection.onopen = this.onOpen_.bind(this);
  connection.onmessage = this.onMessage_.bind(this);
  connection.onclose = this.onClose_.bind(this);
  this.connection = connection;
};

WSClient.prototype.addMessageListener = function(callback) {
  this.callbacks.message = callback;
};

WSClient.prototype.onOpen_ = function() {
  console.log('open');
  this.isConnected = true;
};

WSClient.prototype.onMessage_ = function(e) {
  if (this.callbacks.message) {
    this.callbacks.message(e.data);
  }
};

WSClient.prototype.onClose_ = function() {
  console.log('close');
  this.isConnected = false;
  this.reconnect();
};
