function myFunc(deviceIdStr, count, msgStr) {
   	//var scriptInput = [15,01,00,00,00,0x0B,01,03,08,01,83,0x63,0x88,02,03,0xEA,0x1B];

    //scriptInput = 	Array.prototype.slice.call(scriptInput);
    //return JSON.stringify(scriptInput);

//    return Object.prototype.toString.call(JSON.parse(scriptInput));
//    if (Object.prototype.toString.call(scriptInput) === '[object Array]') {
//         scriptInput = JSON.parse(scriptInput);
//    } else if (typeof scriptInput !== string ) {
//        return "";
//    }
    var scriptInput = JSON.parse(msgStr);
   	var temperature = scriptInput.slice(9, 13);
   	var fullTempValArray = temperature.slice(2, 4);
   	var strArrayTemp = [];
   	for(var i=0; i<2; i++) {
   		strArrayTemp.push(fullTempValArray[i].toString(16));
   	}

   	//console.log("fullTempValArray:", fullTempValArray, ", strArrayTemp:", strArrayTemp.join(''));
   	var tempVal = parseInt(strArrayTemp.join(''), 16);
   	var decimalsNumArray = temperature.slice(1, 2);
   	var decimalsNum = parseInt(decimalsNumArray.join('')) -128;

   	var actualTempValue = Number((tempVal / (Math.pow(10, decimalsNum))).toFixed(decimalsNum));

   	//console.log(actualTempValue)

   	var humidity = scriptInput.slice(13, 17);
   	//console.log("-humidity--");
   	//console.log(humidity);
   	var fullHumiValArray = humidity.slice(2, 4);

   	var humiVal = parseInt(fullHumiValArray.join(''), 16);
   	var strArrayHumi = [];
   	for(var i=0; i<2; i++) {
   		strArrayHumi.push(fullHumiValArray[i].toString(16));
   	}
    humiVal = parseInt(strArrayHumi.join(''), 16);


   	//console.log("fullHumiValArray", fullHumiValArray, fullHumiValArray.join(''), " new:", humiVal)
   	//console.log("humiVal", humiVal)
   	decimalsNumArray = humidity.slice(1, 2);
   	decimalsNum = parseInt(decimalsNumArray.join('')) % 10;
   	var actualHumiValue = Number((humiVal / (Math.pow(10, decimalsNum))).toFixed(decimalsNum));
   	//console.log(actualHumiValue);

   	var deviceMsg={};
   	deviceMsg.msg={};
   	var data = {}
   	data.temperature = actualTempValue;
   	data.humidity = actualHumiValue;
   	deviceMsg.msg.data = data;
   	deviceMsg.msg.delta = 1;
   	deviceMsg.eid= deviceIdStr;
   	deviceMsg.ts= (new Date()).valueOf();


    return JSON.stringify(deviceMsg);
}