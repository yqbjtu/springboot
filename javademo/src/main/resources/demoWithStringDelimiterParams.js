function myFunc(msgStr, deviceIdStr) {
   var strLength = msgStr.length;
   var deviceMsg ={};
   deviceMsg.msg={};
   var data = {}

   if (strLength == 10) {
       var eid = msgStr.slice(0, 5);
       deviceMsg.eid= eid;
       var T1031 = msgStr.slice(5, 9);
       data.T1031 = T1031;
   } else if (strLength == 15) {
      var eid = msgStr.slice(0, 5);
      deviceMsg.eid= eid;

      var T1031 = msgStr.slice(5, 14);
      data.T1031 = T1031;
   } else {
       deviceMsg.eid= '00001';
       data.T1031 = 12;
   }

   	deviceMsg.msg.data = data;
   	deviceMsg.ts= (new Date()).valueOf();

    return JSON.stringify(deviceMsg);
}