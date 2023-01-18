/**
 * 
 */
 //날짜 함수
 	//yy-DD-mm HH:ss:mm
            function elapsedText(date) {
                //console.log(date);
                date = new Date(date);
                // 초 (밀리초)
                const seconds = 1;
                // 분
                const minute = seconds * 60;
                // 시
                const hour = minute * 60;
                // 일
                const day = hour * 24;
                
                var today = new Date();
                var elapsedTime = Math.trunc((today.getTime() - date.getTime()) / 1000);
                
                var elapsedText = "";
                if (elapsedTime < seconds) {
                    elapsedText = "방금 전";
                } else if (elapsedTime < minute) {
                    elapsedText = elapsedTime + "초 전";
                } else if (elapsedTime < hour) {
                    elapsedText = Math.trunc(elapsedTime / minute) + "분 전";
                } else if (elapsedTime < day) {
                    elapsedText = Math.trunc(elapsedTime / hour) + "시간 전";
                } else if (elapsedTime < (day * 3)) {
                    elapsedText = Math.trunc(elapsedTime / day) + "일 전";
                } else {
                    elapsedText = SimpleDateTimeFormat(date, "yyyy-MM-dd");
                }
                
                return elapsedText;
            }
            //날짜고치기
            function SimpleDateTimeFormat(date) {
                    let month = date.getMonth() + 1;
                    let day = date.getDate();
                    let hour = date.getHours();
                    let minute = date.getMinutes();
                    let second = date.getSeconds();

                    month = month >= 10 ? month : '0' + month;
                    day = day >= 10 ? day : '0' + day;
                    hour = hour >= 10 ? hour : '0' + hour;
                    minute = minute >= 10 ? minute : '0' + minute;
                    second = second >= 10 ? second : '0' + second;

                    return date.getFullYear() + '-' + month + '-' + day;
            }
