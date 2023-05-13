import 'package:app/constants.dart';
import 'package:app/controller/chatting_controller.dart';
import 'package:app/controller/deviceInfo_controller.dart';
import 'package:app/models/chatting/chatting_teacher_list_model.dart';
import 'package:app/models/chatting/chatting_user_list_model.dart';
import 'package:app/screens/chatting/chatting_detail.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

class ChattingUserList extends StatelessWidget {
  const ChattingUserList({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    bool isTeacher = DeviceInfoController.to.isTeacher;

    return ListView.builder(
        shrinkWrap: true,
        itemCount: isTeacher ? ChattingController.to.userList.length : ChattingController.to.teacherList.length,
        itemBuilder: (BuildContext context, int index) {
          SingleUser singleUser = SingleUser(kidName: "", kidPhoto: "");
          SingleTeacher singleTeacher = SingleTeacher(teacherSeq: -1, teacherName: "");
          if (isTeacher) {
            singleUser = ChattingController.to.userList[index];
          }
          else {
            singleTeacher = ChattingController.to.teacherList[index];
          }
          return Container(
            decoration: BoxDecoration(
              border: Border(
                bottom: BorderSide(color: Colors.grey, width: 0.5)
              )
            ),
            height: 80,
            child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  // 내용
                  SizedBox(
                    height: 50,
                    child: Row(
                      children: [
                        // 사진
                        Padding(
                          padding: const EdgeInsets.fromLTRB(20, 5, 20, 5),
                          child: isTeacher ? CircleAvatar(
                              backgroundImage: NetworkImage(singleUser.kidPhoto)
                          ) : CircleAvatar(
                            backgroundImage: AssetImage('assets/images/common/flower_icon.png'),
                          ),
                        ),
                        isTeacher ? Row(
                          children: [
                            Text('${singleUser.kidName} 부모님 ', style: TextStyle(fontSize: buttonTextSize)),
                            (singleUser.parentSeq != null) ? Text('(${singleUser.parentName}님)', style: TextStyle(color: Colors.grey, fontSize: subContentTextSize)) : Text(' ')
                          ],
                        ) :
                            Row(
                              children: [
                                Text('${DeviceInfoController.to.className} ${singleTeacher.teacherName} 선생님', style: TextStyle(fontSize: buttonTextSize))
                              ],
                            )
                      ],
                    ),
                  ),
                  // 채팅 아이콘
                  Padding(
                    padding: const EdgeInsets.fromLTRB(0, 0, 20, 0),
                    child: InkWell(
                        onTap: () {
                          // 선생님: 부모님 seq == null이면 disable하게 처리.
                          Navigator.push(context, MaterialPageRoute(builder: (context) => ChattingDetail()));
                        },
                        child: Opacity(
                          opacity: 0.5,
                            child: Image.asset('assets/images/common/chatting_icon.png', height: 35)
                        )
                    ),
                  )
                ],
              )
            );
        }
    );
  }
}
