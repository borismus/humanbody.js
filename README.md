# humanbody.js: depth camera joint tracking in your browser.

Kinect software and frameworks such as OpenNI, NITE, etc, track the
human body and, using pretty advanced computer vision techniques, map
the depth data onto a 3D skeleton. SimpleOpenNI, a processing wrapper
around these APIs, gives a bunch of control points that define a
human skeleton in 3D space.

To do interesting prototyping work that leverages the ease of UI
development on the web, I wanted to bring this data to the browser. This
project does just that: broadcasts joint data over a websocket.

## Installation

1. Get kinect sensor
2. Install SimpleOpenNI and a bunch of libraries. Follow [these
   instructions][soni] to get it working.
3. Clone this repository
4. Run the eclipse project
5. Run the test page and you'll see a bunch of joint data flowing
   through, serialized in JSON format.

## Demo

The demo is an rd.io controller that recognizes 3 gestures:

- Both hands on head to play/pause
- Left hand on head, right hand stretched horizontally to skip next
- Right hand on head, left hand stretched horizontally to skip previous

Here's a [video of it][video] in action.

You can run the demo yourself too. Follow installation steps above,
install the extension in Chrome (located in the [extension/][crx]
directory), and go to <http://rd.io>.

[video]: http://www.youtube.com/watch?v=dZcjfgILtzY
[crx]: https://github.com/borismus/humanbody.js/tree/master/extension
