function Rdio() {
}

Rdio.prototype.playPause = function() {
  this.simulateClick_('.left_controls .play_pause');
};

Rdio.prototype.next = function() {
  this.simulateClick_('.left_controls .next');
};

Rdio.prototype.previous = function() {
  this.simulateClick_('.left_controls .prev');
};

Rdio.prototype.simulateClick_ = function(selector) {
  var evt = document.createEvent('MouseEvents');
  evt.initMouseEvent('click', true, false,  document, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
  document.querySelector(selector).dispatchEvent(evt);
}
