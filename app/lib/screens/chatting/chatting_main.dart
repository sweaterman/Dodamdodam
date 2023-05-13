import 'package:app/components/chatting/chatting_teacher_list.dart';
import 'package:app/components/chatting/chatting_user_list.dart';
import 'package:app/components/common/title_appBar.dart';
import 'package:app/constants.dart';
import 'package:app/controller/chatting_controller.dart';
import 'package:app/controller/deviceInfo_controller.dart';
import 'package:app/screens/chatting/chatting_detail.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

class ChattingMain extends StatelessWidget {
  const ChattingMain({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    ChattingController cc = Get.put(ChattingController());

    return GetBuilder<ChattingController>(
        builder: (_) =>
      Scaffold(
        appBar: TitleAppBar(title: "채팅방"),
        body: Column(
          children: [
            // 사용자, 채팅방 중 선택
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                // 사용자
                Expanded(
                    child: InkWell(
                      onTap: () {
                        cc.setIsChattingList(false);
                      },
                      child: Container(
                        margin: EdgeInsets.all(10), height: 55,
                        decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(30),
                          color: cc.isChattingList ?  lightPink : cardBtnPink,
                        ),
                        child: Center(
                            child: Text("사용자", style: TextStyle(
                                color: cc.isChattingList ? Colors.grey : Colors.black, fontSize: buttonTextSize
                            )),
                        ),
                      ),
                    )
                ),
                // 채팅방
                Expanded(
                    child: InkWell(
                      onTap: () {
                        cc.setIsChattingList(true);
                      },
                      child: Container(
                        margin: EdgeInsets.all(10), height: 55,
                        decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(30),
                          color: cc.isChattingList ? cardBtnPink : lightPink,
                        ),
                        child: Center(
                          child: Text("채팅방", style: TextStyle(
                              color: cc.isChattingList ? Colors.black : Colors.grey, fontSize: buttonTextSize
                          )),
                        ),
                      ),
                    )
                ),
              ],
            ),
            cc.isChattingList ?
                ChattingUserList() : // 채팅 '방' 리스트로 바꾸기
                  ChattingUserList()
          ],
        )
      )
    );
  }
}
