var configuration = {
  'ws_servers': 'ws://192.168.1.171:8088/asterisk/ws',
  'uri': 'sip:100@192.168.1.171',
  'password': 'abc12345'
};

var ua = new JsSIP.UA(configuration);

ua.start();

//var userAgent = new SIP.UA({
//	  uri: '100@192.168.1.171',
//	  wsServers: ['ws://192.168.1.171'],
//	  password: 'abc12345'
//	});
//
//var options = {
//        media: {
//            constraints: {
//                audio: true,
//                video: true
//            },
//            render: {
//                remote: document.getElementById('remoteVideo'),
//                local: document.getElementById('localVideo')
//            }
//        }
//    };

//var session;

//session = userAgent.invite('sip:200@192.168.1.171', options);






//// Make an audio/video call:
//var session = null;
//
//// HTML5 <video> elements in which local and remote video will be shown
////var selfView =   document.getElementById('my-video');
////var remoteView =  document.getElementById('peer-video');
//
//// Register callbacks to desired call events
//var eventHandlers = {
//  'progress': function(e){
//    console.log('call is in progress');
//  },
//  'failed': function(e){
//    console.log('call failed with cause: '+ e.data.cause);
//  },
//  'ended': function(e){
//    console.log('call ended with cause: '+ e.data.cause);
//  },
//  'confirmed': function(e){
//    var local_stream = session.connection.getLocalStreams()[0];
//
//    console.log('call confirmed');
//
//    // Attach local stream to selfView
////    selfView = JsSIP.rtcninja.attachMediaStream(selfView, local_stream);
//  },
//  'addstream': function(e){
//    var stream = e.stream;
//
//    console.log('remote stream added');
//
//    // Attach remote stream to remoteView
////    remoteView = JsSIP.rtcninja.attachMediaStream(remoteView, stream);
//  }
//};
//
//var options = {
//  'eventHandlers': eventHandlers,
//  'mediaConstraints': {'audio': true, 'video': false}
//};
//
//
//session = ua.call('sip:200@192.168.1.171', options);