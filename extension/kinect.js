var body = null;
var rdio = new Rdio();
var client = new WSClient('localhost', 1357);
client.reconnect();

var lastState = -1;
var State = {
  NONE: -1,
  PLAY_PAUSE: 0,
  NEXT: 1,
  PREV: 2
};

client.addMessageListener(function(data) {
  body = JSON.parse(data);
  recognize();
});

function makeVector(jointName) {
  var joint = body[jointName];
  var arr = [parseFloat(joint.x), parseFloat(joint.y), parseFloat(joint.z)];
  return $V(arr);
}

function recognize() {
  var head = makeVector('head');
  var rightHand = makeVector('rightHand');
  var leftHand = makeVector('leftHand');
  var rightShoulder = makeVector('rightShoulder');
  var leftShoulder = makeVector('rightShoulder');

  var isRightNearHead = head.distanceFrom(rightHand) < 200;
  var isLeftNearHead = head.distanceFrom(leftHand) < 200;
  var isRightExtended = Math.abs(
    rightShoulder.elements[1] - rightHand.elements[1]) < 100;
    var isLeftExtended = Math.abs(
      leftShoulder.elements[1] - leftHand.elements[1]) < 100;

      if (isRightNearHead && isLeftNearHead && lastState != State.PLAY_PAUSE) {
        setState(State.PLAY_PAUSE);
        console.log('Two hands touching head');
        rdio.playPause();
      } else if (isLeftExtended && isRightNearHead && lastState != State.NEXT) {
        setState(State.NEXT);
        console.log('Right hand out');
        rdio.next();
      } else if (isRightExtended && isLeftNearHead && lastState != State.PREV) {
        setState(State.PREV);
        console.log('Left hand out');
        rdio.previous();
      }
}

stateTimer = null;
function setState(newState) {
  // Stop the existing timer.
  clearTimeout(stateTimer);
  lastState = newState;
  // After a while, reset state.
  stateTimer = setTimeout(function() {
    lastState = State.NONE;
  }, 2000);
}
