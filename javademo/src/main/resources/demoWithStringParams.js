function myFunc(msgStr, deviceIdStr) {
   	//var scriptInput = [15,01,00,00,00,0x0B,01,03,08,01,83,0x63,0x88,02,03,0xEA,0x1B];

var eid = msgStr.slice(3, 19);

	var TIME = msgStr.slice(25, 39);
	var SIG = msgStr.slice(44, 46);
	SIG = parseInt(SIG, 10);
	var VBAT = msgStr.slice(52, 55);
	VBAT = parseInt(VBAT, 10);
	var NUM = msgStr.slice(60, 68);
	NUM = parseInt(NUM, 10);
	var LENGTH = msgStr.slice(73, 79);
	LENGTH = parseInt(LENGTH, 10);
	var JPG = msgStr.slice(80, 80+LENGTH);

	var deviceMsg ={};
   	deviceMsg.msg={};
   	var data = {}
   	data.TIME = TIME;
	data.SIG = SIG;
	data.VBAT = VBAT;
	data.NUM = NUM;
	data.LENGTH = LENGTH;
	data.JPG = JPG;
   	deviceMsg.msg.data = data;
   	deviceMsg.eid= eid;
   	deviceMsg.ts= (new Date()).valueOf();


    return JSON.stringify(deviceMsg);
}