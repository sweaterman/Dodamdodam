import 'package:app/controller/deviceInfo_controller.dart';
import 'package:app/models/chatting/notify_model.dart';
import 'package:app/notification.dart';
import 'package:get/get.dart';
import 'package:flutter_client_sse/flutter_client_sse.dart';
import 'package:http/http.dart' as http;

class NotifyController extends GetxController {
  static NotifyController get to => Get.find();

  List<NotifyReceive> notifyList = [];

  @override
  void onInit() {
    DeviceInfoController dc = Get.put(DeviceInfoController());
    // String Url = 'http://localhost:9998/notify/${dc.token}';
    String Url = 'http://114.199.131.131:9998/notify/${dc.token}';
    SSEClient.subscribeToSSE(
        url: Url,
        header: {
          "Accept": "text/event-stream",
        }).listen((event) {
      // event 받으면 실행되는 부분
      print('알림왓다--------------------------------');
      var receive = notifyReceiveFromJson(event.data as String);
      showNotification(receive.type, receive.content);
      notifyList.add(receive);
      update();
    });
    super.onInit();
  }

  void readAlarm(String noSeq, int uSeq, int index) async {
    // String Url = "http://localhost:9998/notify";
    try {
      await http.put(
          Uri.parse(Url),
          body: {
            "notifySeq" : noSeq,
            "userSeq" : uSeq.toString()
          }
      );
    } catch (e) {
      print('알림 읽음 오류 $e');
    }
    notifyList.removeAt(index);
    update();
  }
}
